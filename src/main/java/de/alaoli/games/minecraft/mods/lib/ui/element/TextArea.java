/* *************************************************************************************************************
 * Copyright (c) 2017 DerOli82 <https://github.com/DerOli82>
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
 ************************************************************************************************************ */
package de.alaoli.games.minecraft.mods.lib.ui.element;

import de.alaoli.games.minecraft.mods.lib.ui.element.state.Disableable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Focusable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Hoverable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.StateableStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardListener;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseListener;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class TextArea extends Element<TextArea>
    implements Text<TextArea>, Placeholder<TextArea>,
        Focusable<TextArea>, Hoverable<TextArea>, Disableable<TextArea>,
        MouseListener<TextArea>, KeyboardListener<TextArea>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    public static final FontRenderer FONTRENDERER = Minecraft.getMinecraft().fontRenderer;

    private boolean focused = false;
    private boolean hovered = false;
    private boolean disabled = false;

    private BoxStyle boxStyle;
    private TextStyle textStyle;

    private Consumer<? super MouseEvent> onEntered;
    private Consumer<? super MouseEvent> onExited;
    private Consumer<? super MouseEvent> onClicked;

    private Consumer<? super KeyboardEvent> onKeyPressed;

    private String placeholder;
    private String[] lines = new String[5];

    private int maxLength = 32;
    private int maxLines = 5;
    private int cursorPos = 0;
    private int cursorLine = 0;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public Optional<BoxStyle> getBoxStyle()
    {
        return Optional.ofNullable( this.boxStyle );
    }

    public TextArea setBoxStyle( BoxStyle boxStyle )
    {
        this.boxStyle = boxStyle;

        return this;
    }

    public Optional<TextStyle> getTextStyle()
    {
        return Optional.ofNullable( this.textStyle );
    }

    public TextArea setTextStyle( TextStyle textStyle )
    {
        this.textStyle = textStyle;

        return this;
    }

    /**
     * @deprecated
     * @return
     */
    public Optional<String> getText()
    {
        StringBuilder builder = new StringBuilder( " " );

        for( String line : this.lines )
        {
            if( line != null ) { builder.append( line ); }
        }
        return Optional.of( builder.toString() );
    }

    public State getState()
    {
        if( this.disabled ) {return State.DISABLED; }
        if( this.focused ) {return State.FOCUSED; }
        if( this.hovered ) {return State.HOVERED; }

        return State.NONE;
    }

    public int getMaxLength()
    {
        return this.maxLength;
    }

    public TextArea setMaxLength( int maxLength )
    {
        this.maxLength = maxLength;

        return this;
    }

    public TextArea setMaxLines( int maxLines )
    {
        this.maxLines = maxLines;
        this.lines = new String[maxLines];

        return this;
    }

    public boolean writeChar( char c )
    {
        return this.writeCharAt( this.cursorPos, this.cursorLine, c );
    }

    public boolean writeCharAt( int pos, int line, char c )
    {
        if( ( !this.disabled ) &&
            ( ChatAllowedCharacters.isAllowedCharacter( c ) ) &&
            ( this.validCursorPosAt( pos, line ) ) )
        {
            if( this.lines[line] == null ) { this.lines[line] = ""; }
            this.lines[line] = this.lines[line].substring( 0, pos ) + c + this.lines[line].substring( pos );

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeChar()
    {
        return this.removeCharAt( this.cursorPos, this.cursorLine );
    }

    public boolean removeCharAt( int pos, int line )
    {
        if( ( !this.disabled ) &&
            ( this.validCursorPosAt( pos, line ) ) &&
            ( !this.lines[line].isEmpty() ) )
        {
            this.lines[line] = this.lines[line].substring( 0, pos ) + this.lines[line].substring( pos+1 );

            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean validCursorPos( )
    {
        return this.validCursorPosAt( this.cursorPos, this.cursorLine );
    }

    protected boolean validCursorPosAt( int pos, int line )
    {

        return ((line >= 0) &&
                (line < this.maxLines) &&
                (pos >= 0) &&
                (pos <= ((this.lines[line] == null) ? 0 : Math.min(this.lines[line].length(), this.maxLength))));
    }

    /* **************************************************************************************************************
     * Method - Implement Element
     ************************************************************************************************************** */

    @Override
    public void drawElement( int mouseX, int mouseY,float partialTicks )
    {
        State state = this.getState();

        if( this.boxStyle != null ) { this.boxStyle.drawOn( this ); }
        if( this.textStyle != null )
        {
            this.textStyle.drawOn( this );

            //Cursor if focused
            if( ( this.focused ) &&
                    ( !this.disabled ) )
            {
                String text = (this.lines[this.cursorLine] != null) ? this.lines[this.cursorLine] : "";
                int x = this.box.getX() + FONTRENDERER.getStringWidth( text.substring( 0,this.cursorPos ) )+2,
                        lineheight = this.textStyle.getLineHeight(),
                        y = this.box.getY() + lineheight*this.cursorLine,
                        color = ((Optional<Color>)this.textStyle.getColor()).map( Color::getValue ).orElse( Color.BLACK );

                this.drawVerticalLine( x, y+1, y+lineheight, color );
            }
        }
    }

	/* **************************************************************************************************************
	 * Method - Implement Layout
	 ************************************************************************************************************** */

    @Override
    public void layout()
    {
        State state = this.getState();

		/*
		 * @TODO if box- or textstyle null set default style
		 */
        if( ( this.boxStyle != null ) &&
                ( this.boxStyle instanceof StateableStyle) )
        {
            ((StateableStyle)this.boxStyle).setState( state );
        }

        if( ( this.textStyle != null ) &&
                ( this.textStyle instanceof StateableStyle ) )
        {
            ((StateableStyle)this.textStyle).setState( state );
        }
    }

    @Override
    public int getPrefWidth()
    {
        return this.box.getWidth();
    }

    @Override
    public int getPrefHeight()
    {
        return this.box.getHeight();
    }

	/* **************************************************************************************************************
	 * Method - Implement Text
	 ************************************************************************************************************** */

    @Override
    public Optional<String> getTextline()
    {
        return Optional.ofNullable( this.lines[0] );
    }

    @Override
    public Collection<String> getTextlines()
    {
        Collection<String> result = new ArrayList<>();

        for( String line : this.lines )
        {
            if (line != null) {
                result.add(line);
            }
        }
        return result;
    }

    @Override
    public TextArea setTextline( String text )
    {
        this.lines[0] = text;

        return this;
    }

    @Override
    public TextArea setTextlines(Collection<String> lines)
    {
        /*
         * @TODO
         *
        if( lines != null )
        {

        }*/
        return this;
    }

    @Override
    public int countTextlines()
    {
        int count = 0;

        for (String line : this.lines)
        {
            if( (line != null) && (!line.isEmpty()) ) { count++; }
        }
        return count;
    }

    /* **************************************************************************************************************
     * Method - Implement Placeholder
     ************************************************************************************************************** */

    @Override
    public Optional<String> getPlaceholder()
    {
        return Optional.ofNullable( this.placeholder );
    }

    @Override
    public TextArea setPlaceholder( String placeholder)
    {
        this.placeholder = placeholder;

        return this;
    }

    /* **************************************************************************************************************
     * Method - Implement Focusable
     ************************************************************************************************************** */

    @Override
    public TextArea setFocus( boolean focus )
    {
        this.focused = focus;

        return this;
    }

    @Override
    public boolean isFocused()
    {
        return this.focused;
    }

    /* **************************************************************************************************************
     * Method - Implement Hoverable
     ************************************************************************************************************** */

    @Override
    public TextArea setHover(boolean hover)
    {
        this.hovered = hover;

        return this;
    }

    @Override
    public boolean isHovered()
    {
        return this.hovered;
    }

    /* **************************************************************************************************************
     * Method - Implement Disableable
     ************************************************************************************************************** */

    @Override
    public TextArea setDisable(boolean disable)
    {
        this.disabled = disable;

        return this;
    }

    public boolean isDisabled()
    {
        return this.disabled;
    }

    /* **************************************************************************************************************
     * Method - Implements MouseListener
     ************************************************************************************************************** */

    @Override
    public void mouseEntered( MouseEvent event )
    {
        if( this.onEntered != null ) { this.onEntered.accept( event );}
    }

    @Override
    public void mouseExited( MouseEvent event )
    {
        if( this.onExited != null ) { this.onExited.accept( event );}
    }

    @Override
    public void mouseClicked( MouseEvent event )
    {
        if( this.onClicked != null ) { this.onClicked.accept( event );}
    }

    @Override
    public TextArea onMouseEntered( Consumer<? super MouseEvent> consumer )
    {
        this.onEntered = consumer;

        return this;
    }

    @Override
    public TextArea onMouseExited( Consumer<? super MouseEvent> consumer )
    {
        this.onExited = consumer;

        return this;
    }

    @Override
    public TextArea onMouseClicked( Consumer<? super MouseEvent> consumer )
    {
        this.onClicked = consumer;

        return this;
    }

    /* **************************************************************************************************************
     * Method - Implements KeyboardListener
     ************************************************************************************************************** */

    @Override
    public void keyPressed( KeyboardEvent event )
    {
        if( !this.focused ) { return; }

        String text;

        switch( event.eventKey ) {
            case Keyboard.KEY_LEFT:
                if (this.cursorPos > 0) { this.cursorPos--; }
                break;

            case Keyboard.KEY_RIGHT:
                if (this.cursorPos < this.lines[this.cursorLine].length()) { this.cursorPos++; }
                break;

            case Keyboard.KEY_UP:
                if( this.cursorLine > 0 )
                {
                    this.cursorLine--;
                    this.cursorPos = Math.min( this.cursorPos, (this.lines[this.cursorLine] != null) ? this.lines[this.cursorLine].length():0);
                }
                break;

            case Keyboard.KEY_DOWN:
                if( this.cursorLine < this.maxLines-1 )
                {
                    this.cursorLine++;
                    this.cursorPos = Math.min( this.cursorPos, (this.lines[this.cursorLine] != null) ? this.lines[this.cursorLine].length():0);
                }
                break;

            case Keyboard.KEY_HOME:
                this.cursorPos = 0;
                break;

            case Keyboard.KEY_END:

                this.cursorPos = this.lines[this.cursorLine].length();
                break;

            case Keyboard.KEY_RETURN:
                if( this.cursorLine < this.maxLines-1 )
                {
                    this.cursorLine++;
                    this.cursorPos = 0;
                }
                break;

            case Keyboard.KEY_BACK:
                if( this.cursorPos > 0 ) { this.removeCharAt( --this.cursorPos, this.cursorLine ); }
                break;

            case Keyboard.KEY_DELETE:
                if( this.cursorPos < this.lines[this.cursorLine].length() ) { this.removeCharAt( this.cursorPos, this.cursorLine ); }
                break;

            default:
                if( this.writeCharAt(this.cursorPos, this.cursorLine, event.eventChar) ) { this.cursorPos++; }
                break;
        }
        if( this.onKeyPressed != null ) { this.onKeyPressed.accept( event ); }
    }

    @Override
    public TextArea onKeyPressed(  Consumer<? super KeyboardEvent> consumer )
    {
        this.onKeyPressed = consumer;

        return this;
    }
}
