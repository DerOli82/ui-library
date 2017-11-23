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

import de.alaoli.games.minecraft.mods.lib.ui.util.Region;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 * @param <T> The type of {@link Region} on which you want to {@link #drawOn(Region)}
 */
public interface Drawable<T extends Region>
{
	/**
	 * Drawing something on the given {@link Region}, if the {@link Region} is null,
	 * drawing will be skipped.
	 *
	 * @param region The element on which you want to draw something
	 */
	default void drawOn( T region )
	{
		if( region == null ) { return; }

		this.drawAt( region.getX(), region.getY(), region.getWidth(), region.getHeight() );
	}

	/**
	 * Drawing something at the given location and dimension, if it has no dimension,
	 * drawing will be skipped.
	 *
	 * @param x			Drawing x-position
	 * @param y			Drawing y-position
	 * @param width		Drawing width
	 * @param height	Drawing height
	 */
	void drawAt( int x, int y, int width, int height );
}