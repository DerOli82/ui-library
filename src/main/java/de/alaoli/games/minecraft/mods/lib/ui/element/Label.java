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
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 *
 ************************************************************************************************************** */
package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyling;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class Label extends Element<Label> implements Text<Label>
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	private TextStyle textStyle;
	private String text;
	
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Optional<TextStyle> getTextStyle()
	{
		return Optional.ofNullable( this.textStyle );
	}

	public Label setTextStyle( TextStyle textStyle )
	{
		this.textStyle = textStyle;
		
		return this;
	}

	/* **************************************************************************************************************
	 * Method - Implement Element
	 ************************************************************************************************************** */

	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		this.validateLayout();

		if( this.textStyle != null ) { this.textStyle.drawOn( this ); }
	}

	/* **************************************************************************************************************
	 * Method - Implement Layout
	 ************************************************************************************************************** */

	@Override
	public void layout()
	{
		/*
		 * @TODO if textstyle null set default style
		 */
	}

	@Override
	public int getPrefWidth()
	{
		return Math.max( TextStyling.FONTRENDERER.getStringWidth( this.text ), this.box.getWidth() );
	}

	@Override
	public int getPrefHeight()
	{
		return Math.max(
			(this.textStyle!=null) ? this.textStyle.getLineHeight() : TextStyling.DEFAULT_LINEHEIGHT,
			this.box.getHeight()
		);
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
	public Label setTextline( String text )
	{
		this.text = text;

		return this;
	}

	@Override
	public Label setTextlines(Collection<String> lines)
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
}
