package de.alaoli.games.minecraft.mods.lib.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.alaoli.games.minecraft.mods.lib.ui.event.*;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Focusable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Hoverable;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.layout.Layout;
import net.minecraft.client.gui.GuiScreen;

public abstract class Screen<T extends Screen> extends GuiScreen implements Layout
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/

	private final List<Element> listeners = new ArrayList<>();
	private final LinkedList<Element> focusable = new LinkedList<>();

	private Layout layout;
	private Element focused;

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/

	public <L extends Element & Layout> T setLayout( L layout )
	{
		this.layout = layout;

		layout.setBounds(0, 0, this.width, this.height );

		return (T)this;
	}
	
	public Optional<Layout> getLayout()
	{
		return Optional.ofNullable( this.layout );
	}
	
	public <L extends Element & InputListener> T addListener( L listener )
	{
		if( listener instanceof Focusable )
		{
			this.focusable.add( listener );
		}
		this.listeners.add( listener );
		
		return (T)this;
	}
	
	public <L extends Element & InputListener> T removeListener( L listener )
	{
		if( listener instanceof Focusable )
		{
			this.focusable.remove( listener );
		}
		this.listeners.remove( listener );
		
		return (T)this;
	}
	
	public <L extends Element & InputListener> boolean hasListener( L listener )
	{
		return this.listeners.contains( listener );
	}
	
	public void clearListener()
	{
		this.listeners.clear();
	}

	public Optional<Element> getFocused()
	{
		return Optional.ofNullable( this.focused );
	}

	public T setFocus( Element element )
	{
		if( this.focusable.contains( element ) )
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
		return (T)this;
	}

	public void focusFirst()
	{
		this.listeners
			.stream()
			.filter( listener -> listener instanceof Focusable )
			.findFirst()
			.ifPresent(this::setFocus);
	}

	public void focusPrev()
	{
		if( this.focused == null )
		{
			this.focusFirst();
		}
		else
		{
			((Focusable)this.focused).setFocus( false );
			this.focused = this.focusable.pollLast();
			this.focusable.addFirst( this.focused );
			((Focusable)this.focused).setFocus( true );
		}
	}

	public void focusNext()
	{
		if( this.focused == null )
		{
			this.focusFirst();
		}
		else
		{
			((Focusable)this.focused).setFocus( false );
			Element first = this.focusable.pollFirst();
			this.focused = this.focusable.peekFirst();
			this.focusable.addLast( first );
			((Focusable)this.focused).setFocus( true );
		}
	}

	public void close()
	{

		this.mc.displayGuiScreen(null);

		if (this.mc.currentScreen == null)
		{
			this.mc.setIngameFocus();
		}
	}

	/******************************************************************************************
	 * Method - Implement GuiScreen
	 ******************************************************************************************/
	
	@Override
	public void initGui() 
	{
		super.initGui();

		//MinecraftForge.EVENT_BUS.register( this );
		this.layout();
	}
	
	@Override
	public void onGuiClosed() 
	{
		super.onGuiClosed();

		//MinecraftForge.EVENT_BUS.unregister( this );
		this.listeners.clear();
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
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
			.filter( listener -> listener instanceof MouseListener )
			.collect(Collectors.toCollection( ArrayList::new ))
			.forEach( listener -> {
				boolean hovered = listener.box.contains( event.x, event.y );

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

		this.layout.drawElement( mouseX, mouseY, partialTicks );
	}
	
	/******************************************************************************************
	 * Method - Implement Layout
	 ******************************************************************************************/

	@Override
	public void layout()
	{
		this.doLayout();
		
		if( this.layout != null ) { this.layout.layout(); }
		
	}
	
	@Override
	public void doLayout() {}

	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		this.drawScreen( mouseX, mouseY, partialTicks );
	}
}
