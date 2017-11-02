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
package de.alaoli.games.minecraft.mods.lib.ui.element;

import de.alaoli.games.minecraft.mods.lib.ui.element.state.Disableable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Focusable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Hoverable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.StateableStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyling;
import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardListener;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class Button extends Element<Button>
	implements Text<Button>,
		Focusable<Button>, Hoverable<Button>, Disableable<Button>,
		MouseListener<Button>, KeyboardListener<Button>
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	private BoxStyle boxStyle;
	private TextStyle textStyle;

	private String text;

	private boolean focused = false;
	private boolean hovered = false;
	private boolean disabled = false;

	private Consumer<? super MouseEvent> onEntered;
	private Consumer<? super MouseEvent> onExited;
	private Consumer<? super MouseEvent> onClicked;

	private Consumer<? super KeyboardEvent> onKeyPressed;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Optional<BoxStyle> getBoxStyle()
	{
		return Optional.ofNullable( this.boxStyle );
	}

	public Button setBoxStyle( BoxStyle boxStyle )
	{
		this.boxStyle = boxStyle;

		return this;
	}

	public Optional<TextStyle> getTextStyle()
	{
		return Optional.ofNullable( this.textStyle );
	}

	public Button setTextStyle( TextStyle textStyle )
	{
		this.textStyle = textStyle;

		return this;
	}

	public State getState()
	{
		if( this.disabled ) { return State.DISABLED; }
		if( this.focused ) { return State.FOCUSED; }
		if( this.hovered ) { return State.HOVERED; }

		return State.NONE;
	}

	/* **************************************************************************************************************
	 * Method - Implement Element
	 ************************************************************************************************************** */

	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		this.validateLayout();

		if( this.boxStyle != null ) { this.boxStyle.drawOn( this ); }
		if( this.textStyle != null ) { this.textStyle.drawOn( this ); }
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
		return Math.max( TextStyling.FONTRENDERER.getStringWidth( this.text ), this.box.getWidth() );
	}

	@Override
	public int getPrefHeight()
	{
		return Math.max( (this.textStyle!=null)?this.textStyle.getLineHeight():0, TextStyling.DEFAULT_LINEHEIGHT );
	}

	/* **************************************************************************************************************
	 * Method - Implement Text
	 ************************************************************************************************************** */

	@Override
	public Optional<String> getTextline()
	{
		return Optional.ofNullable( this.text );
	}

	@Override
	public Collection<String> getTextlines()
	{
		Collection<String> result = new ArrayList<>();

		if( this.text != null )
		{
			result.add( this.text );
		}
		return result;
	}

	@Override
	public Button setTextline( String text )
	{
		this.text = text;

		return this;
	}

	@Override
	public Button setTextlines(Collection<String> lines)
	{
		if( lines != null )
		{
			StringBuilder builder = new StringBuilder( " " );
			lines.forEach(builder::append);
			this.text = builder.toString();
		}
		return this;
	}

	@Override
	public int countTextlines()
	{
		return ( ( this.text != null ) && ( !this.text.isEmpty() ) ) ? 1 : 0;
	}

    /* **************************************************************************************************************
     * Method - Implement Focusable
     ************************************************************************************************************** */

	@Override
	public Button setFocus( boolean focus )
	{
		this.focused = focus;
		this.invalidateLayout();

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
	public Button setHover(boolean hover)
	{
		this.hovered = hover;
		this.invalidateLayout();

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
	public Button setDisable(boolean disable)
	{
		this.disabled = disable;
		this.invalidateLayout();

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
	public Button onMouseEntered( Consumer<? super MouseEvent> consumer )
	{
		this.onEntered = consumer;

		return this;
	}

	@Override
	public Button onMouseExited( Consumer<? super MouseEvent> consumer )
	{
		this.onExited = consumer;

		return this;
	}

	@Override
	public Button onMouseClicked( Consumer<? super MouseEvent> consumer )
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

		if( this.onKeyPressed != null ) { this.onKeyPressed.accept( event ); }
	}

	@Override
	public Button onKeyPressed(  Consumer<? super KeyboardEvent> consumer )
	{
		this.onKeyPressed = consumer;

		return this;
	}
}
