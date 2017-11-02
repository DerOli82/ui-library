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
public class Background extends Gui implements ColorStyle<Background>, Drawable<Element>
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	private Color color;
	
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Background( Color color ) 
	{
		this.color = color;
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
	public Background setColor( Color color )
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
			width = element.getPrefWidth(),
			height = element.getPrefHeight(),
			color = (this.color!=null) ? this.color.value : Color.BLACK;

		Gui.drawRect( x, y, x + width, y + height, color );
	}
}