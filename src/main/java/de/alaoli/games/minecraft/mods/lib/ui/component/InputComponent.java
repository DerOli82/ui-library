/**************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
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
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 *************************************************************************************************************/
package de.alaoli.games.minecraft.mods.lib.ui.component;

import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardListener;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseListener;
import de.alaoli.games.minecraft.mods.lib.ui.state.Disableable;
import de.alaoli.games.minecraft.mods.lib.ui.state.Focusable;
import de.alaoli.games.minecraft.mods.lib.ui.state.Hoverable;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.util.Text;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public abstract class InputComponent extends AbstractComponent
        implements TextComponent, BoxComponent, Hoverable, Focusable, Disableable, MouseListener, KeyboardListener
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    protected final Text text = new Text();
    protected final Text placeholder = new Text();

    protected TextStyle textStyle;
    protected BoxStyle boxStyle;

    protected boolean hovered = false;
    protected boolean focused = false;
    protected boolean disabled = false;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    protected InputComponent()
    {
        super();
    }

    InputComponent(InputBuilder<?,?,?> builder )
    {
        super( builder );

        this.text.setMaxLength( builder.maxLength );
        this.text.setValue( (builder.text!=null) ? builder.text : "" );
        this.placeholder.setMaxLength( builder.placeholder.length() );
        this.placeholder.setValue( (builder.placeholder!=null) ? builder.placeholder : "" );
        this.textStyle = (builder.textStyleBuilder!=null) ? builder.textStyleBuilder.build() : TextStyle.EMPTY;
        this.boxStyle = (builder.boxStyleBuilder!=null) ? builder.boxStyleBuilder.build() : BoxStyle.EMPTY;
    }

    public Text getPlaceholder()
    {
        return this.placeholder;
    }

    protected void updateState()
    {
        State state = State.NONE;

        if( this.hovered ) { state = State.HOVERED; }
        if( this.focused ) { state = State.FOCUSED; }
        if( this.disabled ) { state = State.DISABLED; }

        if( state == State.FOCUSED )
        {
            this.text.showCursor();
        }
        else
        {
            this.text.hideCursor();
        }
        this.textStyle.setState( state );
        this.boxStyle.setState( state );
    }

    /* *************************************************************************************************************
     * Method - Implement TextComponent
     ************************************************************************************************************* */

    @Override
    public Text getText()
    {
        return this.text;
    }

    @Override
    public TextStyle getTextStyle()
    {
        return this.textStyle;
    }

    @Override
    public void setTextStyle( TextStyle textStyle )
    {
        this.textStyle = (textStyle!=null) ? textStyle : TextStyle.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Implement BoxComponent
     ************************************************************************************************************* */

    @Override
    public BoxStyle getBoxStyle()
    {
        return this.boxStyle;
    }

    @Override
    public void setBoxStyle( BoxStyle boxStyle )
    {
        this.boxStyle = boxStyle;
    }



    /* *************************************************************************************************************
     * Method - Implement Hoverable
     ************************************************************************************************************* */

    @Override
    public void setHover( boolean hover )
    {
        this.hovered = hover;

        this.updateState();
    }

    @Override
    public boolean isHovered()
    {
        return this.hovered;
    }

    /* *************************************************************************************************************
     * Method - Implement Focusable
     ************************************************************************************************************* */

    @Override
    public void setFocus( boolean focus )
    {
        this.focused = focus;

        this.updateState();
    }

    @Override
    public boolean isFocused()
    {
        return this.focused;
    }

    /* *************************************************************************************************************
     * Method - Implement Disableable
     ************************************************************************************************************* */

    @Override
    public void setDisable( boolean disable )
    {
        this.disabled = disable;

        this.updateState();
    }

    @Override
    public boolean isDisabled()
    {
        return this.disabled;
    }

    /* *************************************************************************************************************
     * Method - Implement MouseListener
     ************************************************************************************************************* */

    @Override
    public void onMouseEnter( MouseEvent.Enter event )
    {
        this.streamListeners()
            .filter( listener -> listener instanceof MouseListener )
            .forEach( listener -> ((MouseListener)listener).onMouseEnter( event ) );
    }

    @Override
    public void onMouseLeave( MouseEvent.Leave event )
    {
        this.streamListeners()
            .filter( listener -> listener instanceof MouseListener )
            .forEach( listener -> ((MouseListener)listener).onMouseLeave( event ) );
    }

    @Override
    public void onMouseClick( MouseEvent.Click event )
    {
        this.streamListeners()
            .filter( listener -> listener instanceof MouseListener )
            .forEach( listener -> ((MouseListener)listener).onMouseClick( event ) );
    }

    /* *************************************************************************************************************
     * Method - Implement MouseListener
     ************************************************************************************************************* */

    @Override
    public void onKeyPressed( KeyboardEvent event )
    {
        this.streamListeners()
            .filter( listener -> listener instanceof KeyboardListener )
            .forEach( listener -> ((KeyboardListener)listener).onKeyPressed( event ) );
    }
}