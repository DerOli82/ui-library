/* *************************************************************************************************************
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
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.lib.ui.style;

import de.alaoli.games.minecraft.mods.lib.ui.util.Align;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public interface Region
{
    Align getAlign();

    int getX();
    int getY();
    int getWidth();
    int getHeight();

    default boolean contains( int x, int y )
    {
        return this.intersects( x, y, 1, 1 );
    }

    default boolean intersects( Region region )
    {
        return this.intersects( region.getX(), region.getY(), region.getWidth(), region.getHeight() );
    }

    default boolean intersects( int x, int y, int width, int height )
    {
        //No dimension
        if( width <= 0 || height <= 0 || this.getWidth() <= 0 || this.getHeight() <= 0 ) { return false; }

        //Overflow or intersect
        return ( ( x + width < x || x + width > this.getX() ) &&
            ( y + height < y || y + height > this.getY() ) &&
            ( this.getX() + this.getWidth() < this.getX() || this.getX() + this.getWidth() > x ) &&
            ( this.getY() + this.getHeight() < this.getY() || this.getY() + this.getHeight() > y ));
    }
}