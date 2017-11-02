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
package de.alaoli.games.minecraft.mods.lib.ui.drawable;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.ColorStyle;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import net.minecraft.client.gui.Gui;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class Border extends Gui implements ColorStyle<Border>, Drawable<Element>
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */
	
	private Color color;
	
	private boolean hideTop = false;
	private boolean hideLeft = false;
	private boolean hideRight = false;
	private boolean hideBottom = false;
	
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Border( Color color ) 
	{
		this.color = color;
	}

	public Border hide( boolean hide )
	{
		this.hideTop = hide;
		this.hideLeft = hide;
		this.hideRight = hide;
		this.hideBottom = hide;
		
		return this;
	}

	public Border hide( boolean top, boolean left, boolean right, boolean bottom )
	{
		this.hideTop = top;
		this.hideLeft = left;
		this.hideRight = right;
		this.hideBottom = bottom;

		return this;
	}

	/* **************************************************************************************************************
	 * Method - Implement ColorStyle
	 ************************************************************************************************************** */

	@Override
	public Optional<Color> getColor()
	{
		return Optional.ofNullable( this.color );
	}

	@Override
	public Border setColor( Color color )
	{
		this.color = color;

		return this;
	}

	/* **************************************************************************************************************
	 * Method - Implement Drawable
	 ************************************************************************************************************** */
	
	@Override
	public void drawOn( Element element )
	{
		int x = element.box.getX(),
			y = element.box.getY(),
			width = element.box.getWidth(),
			height = element.box.getHeight(),
			color = (this.color!=null) ? this.color.value : Color.BLACK;

		if( !this.hideTop ) { this.drawHorizontalLine( x, x+width-1, y, color ); }
		if( !this.hideLeft ) { this.drawVerticalLine( x, y, y+height, color ); }
		if( !this.hideRight ) { this.drawVerticalLine( x+width-1, y, y+height, color ); }
		if( !this.hideBottom ) { this.drawHorizontalLine( x, x+width-1, y+height-1, color ); }
	}
}