/* **************************************************************************************************************
 * Copyright 2017 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 *
 ************************************************************************************************************** */
package de.alaoli.games.minecraft.mods.lib.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.alaoli.games.minecraft.mods.lib.ui.element.state.Disableable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Focusable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Hoverable;
import de.alaoli.games.minecraft.mods.lib.ui.event.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.layout.Layout;
import net.minecraft.client.gui.GuiScreen;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public abstract class Screen extends GuiScreen implements Layout
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	private final List<Element> listeners = new ArrayList<>();
	private final LinkedList<Element> focusable = new LinkedList<>();

	private boolean initialized = false;
	private boolean needsLayout = true;

	private Element layout;
	private Element focused;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public abstract void init();

	public Optional<Layout> getLayout()
	{
		return Optional.ofNullable( this.layout );
	}

	public Screen setLayout( Element layout )
	{
		this.layout = layout;

		layout.setBounds(0, 0, this.width, this.height );

		return this;
	}

	public <L extends Element & InputListener> Screen addListener(L listener )
	{
		if( listener instanceof Focusable )
		{
			this.focusable.add( listener );
		}
		this.listeners.add( listener );
		
		return this;
	}
	
	public <L extends Element & InputListener> Screen removeListener( L listener )
	{
		if( listener instanceof Focusable )
		{
			this.focusable.remove( listener );
		}
		this.listeners.remove( listener );
		
		return this;
	}

 	private boolean focusAvailable()
	{
		return !this.focusable.isEmpty() &&
				this.focusable.stream()
					.filter(focusable -> !(focusable instanceof Disableable) || !((Disableable) focusable).isDisabled())
					.count() > 0;
	}

	private Screen setFocus( Element element )
	{
		if( !this.focusAvailable() ) { return this; }

		if( ( element instanceof Focusable ) &&
			( this.focusable.contains( element ) ) )
		{
			if( focused != null ) { ((Focusable)this.focused).setFocus( false ); }
			this.focused = element;
			((Focusable)this.focused).setFocus( true );

			Element first = this.focusable.peekFirst();

			while( !first.equals( element ) )
			{
				first = this.focusable.pollFirst();
				this.focusable.addLast( first );
				first = this.focusable.peekFirst();
			}
		}
		return this;
	}

	private void focusFirst()
	{
		if( !this.focusAvailable() ) { return; }

		this.listeners
			.stream()
			.filter( listener -> listener instanceof Focusable )
			.findFirst()
			.ifPresent( this::setFocus );
	}

	private void focusPrev()
	{
		if( !this.focusAvailable() ) { return; }

		if( ( this.focused == null )||
			( this.focusable.size() <= 1 ) )
		{
			this.focusFirst();
		}
		else
		{
			((Focusable)this.focused).setFocus( false );
			this.focused = this.focusable.pollLast();
			this.focusable.addFirst( this.focused );

			//Skip if disabled
			if( ( focused instanceof Disableable ) &&
				( ((Disableable)focused).isDisabled() ) )
			{
				this.focusPrev();
			}
			else
			{
				((Focusable) this.focused).setFocus( true );
			}
		}
	}

	private void focusNext()
	{
		if( !this.focusAvailable() ) { return; }

		if( ( this.focused == null ) ||
			( this.focusable.size() <= 1 ) )
		{
			this.focusFirst();
		}
		else
		{
			((Focusable)this.focused).setFocus( false );
			Element first = this.focusable.pollFirst();
			this.focused = this.focusable.peekFirst();
			this.focusable.addLast( first );

			//Skip if disabled
			if( ( this.focused instanceof Disableable ) &&
				( ((Disableable)this.focused).isDisabled() ) )
			{
				this.focusNext();
			}
			else
			{
				((Focusable) this.focused).setFocus( true );
			}
		}
	}

	protected void close()
	{
		this.mc.displayGuiScreen(null);

		if (this.mc.currentScreen == null)
		{
			this.mc.setIngameFocus();
		}
	}

	/* **************************************************************************************************************
	 * Method - Implement GuiScreen
	 ************************************************************************************************************** */
	
	@Override
	public void initGui() 
	{
		super.initGui();

		if( !this.initialized )
		{
			this.init();
			this.initialized = true;
		}
	}

	@Override
	public void onResize( Minecraft mcIn, int w, int h )
	{
		super.onResize( mcIn, w, h );

		if( this.layout != null )
		{
			this.layout.setSize( w, h );
		}
		this.invalidateLayout();
	}

	@Override
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		
		if( this.listeners.isEmpty() ) { return; }

		MouseEvent event = new MouseEvent(
			Mouse.getEventX() * this.width / this.mc.displayWidth,
			this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1,
			Mouse.getEventButton()
		);
		this.listeners
			.stream()
			.filter( listener -> listener instanceof MouseListener)
			.collect(Collectors.toCollection( ArrayList::new ))
			.forEach( listener -> {
				boolean hovered = listener.contains( event.x, event.y );

				//Element entered or exited event
				if( listener instanceof Hoverable)
				{
					if( ( hovered ) &&
						( !((Hoverable)listener).isHovered() ) )
					{
						((Hoverable)listener).setHover( true );
						((MouseListener)listener).mouseEntered( event );
						MinecraftForge.EVENT_BUS.post( new MouseEnteredEvent( (Element&MouseListener)listener, event ) );
					}
					else if(( !hovered ) &&
							( ((Hoverable)listener).isHovered() ) )
					{
						((Hoverable)listener).setHover( false );
						((MouseListener)listener).mouseExited( event );
						MinecraftForge.EVENT_BUS.post( new MouseExitedEvent( (Element&MouseListener)listener, event ) );
					}
				}

				//Element clicked or released event
				if( ( hovered ) &&
					( Mouse.getEventButtonState() ) )
				{
					if( ( listener instanceof Focusable ) &&
						( !((Focusable)listener).isFocused() ))
					{
						this.setFocus( listener );
					}
					((MouseListener)listener).mouseClicked( event );
					MinecraftForge.EVENT_BUS.post( new MouseClickedEvent( (Element&MouseListener)listener, event ) );
				}
			});
	}

	@Override
	public void handleKeyboardInput() throws IOException 
	{
		int eventKey = Keyboard.getEventKey();
		char  eventChar = Keyboard.getEventCharacter();

		//Close Screen
		if( eventKey == Keyboard.KEY_ESCAPE ) { this.close(); return; }

		//Focus change
		if( ( !this.focusable.isEmpty() ) &&
			( Keyboard.getEventKeyState() ) &&
			( eventKey == Keyboard.KEY_TAB ) )
		{
			if( ( Keyboard.isKeyDown( Keyboard.KEY_LSHIFT ) ) ||
				( Keyboard.isKeyDown( Keyboard.KEY_RSHIFT ) ) )
			{
				this.focusPrev();
			}
			else
			{
				this.focusNext();
			}
		}

		//Nothing to do
		if ( this.listeners.isEmpty() ) { return; }
		
		if( ( Keyboard.getEventKeyState() ) ||
			(( eventKey == Keyboard.KEY_NONE && eventChar >= 32 ) ))
		{
			KeyboardEvent event = new KeyboardEvent( eventChar, eventKey );

			this.listeners
				.stream()
				.filter( listener -> listener instanceof KeyboardListener)
				.collect(Collectors.toCollection( ArrayList::new ))
				.forEach( listener -> ((KeyboardListener)listener).keyPressed( event ) );
		}
		this.mc.dispatchKeypresses();
	}

	@Override
	public void drawScreen( int mouseX, int mouseY, float partialTicks )
	{
		if( this.layout == null ) { return; }

		this.validateLayout();
		this.layout.drawElement( mouseX, mouseY, partialTicks );
	}
	
	/* **************************************************************************************************************
	 * Method - Implement Layout
	 ************************************************************************************************************** */

	@Override
	public void validateLayout()
	{
		if( this.needsLayout )
		{
			this.needsLayout = false;
			this.layout();
		}
	}

	@Override
	public void invalidateLayout()
	{
		this.needsLayout = true;
	}

	@Override
	public boolean needsLayout()
	{
		return this.needsLayout;
	}
}