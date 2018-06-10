/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or toBuilder
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a toBuilder of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.lib.ui.screen;

import de.alaoli.games.minecraft.mods.lib.ui.component.Component;
import de.alaoli.games.minecraft.mods.lib.ui.event.*;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.Context;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.RendererManager;
import de.alaoli.games.minecraft.mods.lib.ui.state.Disableable;
import de.alaoli.games.minecraft.mods.lib.ui.state.Focusable;
import de.alaoli.games.minecraft.mods.lib.ui.state.Hoverable;
import de.alaoli.games.minecraft.mods.lib.ui.style.Region;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public abstract class Screen extends GuiScreen implements Component
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private Region region = Region.EMPTY;

    private final Context cachedContext = new Context();
    private final List<Component> components = new ArrayList<>();
    private final List<Listener> listeners = new ArrayList<>();
    private final LinkedList<Listener> focusable = new LinkedList<>();

    private Listener focused;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    public abstract void create();
    public abstract void show();
    public abstract void resize( int width, int height );
    public abstract void hide();

    protected void addListener( Listener listener )
    {
        this.listeners.add( listener );

        if( listener instanceof Focusable ) { this.focusable.add( listener ); }
    }

    protected void removeListener( Listener listener )
    {
        this.listeners.remove( listener );

        if( listener instanceof Focusable ) { this.focusable.remove( listener ); }
    }

    private boolean focusAvailable()
    {/*
		return !this.focusable.isEmpty() &&
				this.focusable.stream()
					.filter(focusable -> !(focusable instanceof Disableable) || !((Disableable) focusable).isDisabled())
					.count() > 0;
*/
        return !this.focusable.isEmpty() &&
                this.focusable.stream()
                .anyMatch( focusable -> !(focusable instanceof Disableable) || !((Disableable)focusable).isDisabled() );
    }

    private void setFocus( Listener listener )
    {
        if( !this.focusAvailable() ) { return; }

        if( ( listener instanceof Focusable ) &&
            ( this.focusable.contains(listener) ) )
        {
            if( focused != null ) { ((Focusable)this.focused).setFocus( false ); }
            this.focused = listener;
            ((Focusable)this.focused).setFocus( true );

            Listener first = this.focusable.peekFirst();

            while( !first.equals(listener) )
            {
                first = this.focusable.pollFirst();
                this.focusable.addLast( first );
                first = this.focusable.peekFirst();
            }
        }
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
            Listener first = this.focusable.pollFirst();
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

    /* *************************************************************************************************************
     * Method - Implement GuiScreen
     ************************************************************************************************************* */

    @Override
    public void initGui()
    {
        super.initGui();

        this.region = this.region.toBuilder().withDimensions( this.width, this.height ).build();
    }

    @Override
    public void onResize( Minecraft mc, int width, int height )
    {
        super.onResize( mc, width, height );

        this.region = this.region.toBuilder().withDimensions( width, height ).build();
        this.resize( width, height );
    }

    @Override
    public void drawScreen( int mouseX, int mouseY, float partialTicks )
    {
        this.cachedContext.update( mouseX, mouseY, partialTicks );
        RendererManager.INSTANCE.render( this, this.cachedContext );
    }

    @Override
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();

        if( this.listeners.isEmpty() ) { return; }

        int x = Mouse.getEventX() * this.width / this.mc.displayWidth,
            y  =this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1,
            button = Mouse.getEventButton();

        this.listeners
            .stream()
            .filter( listener -> listener instanceof MouseListener )
            .collect(Collectors.toCollection( ArrayList::new ))
            .forEach( listener -> {
                boolean hovered = ((MouseListener)listener).getRegion().contains( x, y );

                //Element entered or exited event
                if( listener instanceof Hoverable)
                {
                    if( ( hovered ) &&
                        ( !((Hoverable)listener).isHovered() ) )
                    {
                        ((Hoverable)listener).setHover( true );
                        ((MouseListener)listener).onMouseEnter( new MouseEvent.Enter( (MouseListener)listener, x, y, button ) );
                    }
                    else if(( !hovered ) &&
                        ( ((Hoverable)listener).isHovered() ) )
                    {
                        ((Hoverable)listener).setHover( false );
                        ((MouseListener)listener).onMouseLeave( new MouseEvent.Leave( (MouseListener)listener, x, y, button ) );
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
                    ((MouseListener)listener).onMouseClick( new MouseEvent.Click( (MouseListener)listener, x, y, button ) );
                }

            } );
    }

    @Override
    public void handleKeyboardInput() throws IOException
    {
        int eventKey = Keyboard.getEventKey();
        char  eventChar = Keyboard.getEventCharacter();

        //Close Screen
        if( eventKey == Keyboard.KEY_ESCAPE ) { ScreenManager.hide(); return; }

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
            ( ( eventKey == Keyboard.KEY_NONE && eventChar >= 32 ) ))
        {
            KeyboardEvent event = new KeyboardEvent( eventChar, eventKey );

            this.listeners
                .stream()
                .filter( listener -> listener instanceof KeyboardListener )
                .collect(Collectors.toCollection( ArrayList::new ))
                .forEach( listener -> ((KeyboardListener)listener).onKeyPressed( event ) );
        }
        this.mc.dispatchKeypresses();
    }


    /* *************************************************************************************************************
     * Method - Implement Component
     ************************************************************************************************************* */

    @Override
    public Optional<Component> getParent()
    {
        return Optional.empty(); //Screen is always the root component
    }

    @Override
    public void setParent( Component parent ) { /*Screen is always the root component*/ }

    @Override
    public boolean isRootComponent()
    {
        return true; //Screen is always the root component
    }

    @Override
    public Region getRegion()
    {
        return this.region;
    }

    @Override
    public void setRegion( Region region )
    {
        if( region == null )
        {
            region = Region.EMPTY;
        }
        this.region = region;
    }

    @Override
    public Region getRegionAbsolute()
    {
        return this.region; //Size is always absolute, because screen is always root component
    }


    @Override
    public void addComponent( Component component )
    {
        if( component == this ) { return; }

        this.components.add( component );
    }

    @Override
    public void removeComponent( Component component )
    {
        if( component == this ) { return; }

        this.components.remove( component );
    }

    @Override
    public Stream<Component> streamChildComponents()
    {
        return this.components.stream();
    }
}