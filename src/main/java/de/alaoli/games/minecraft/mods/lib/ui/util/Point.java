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
package de.alaoli.games.minecraft.mods.lib.ui.util;

import java.util.Objects;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class Point
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    public static Point EMPTY = new Point();

    private final int x, y;
    private int hashCode;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    private Point()
    {
        this.x = 0;
        this.y = 0;
    }

    private Point( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    public static Point get( int x, int y )
    {
        /*
         * @TODO caching
         */
        return new Point( x, y );
    }

    @Override
    public boolean equals( Object obj )
    {
        if( this == obj ) { return true; }
        if( obj == null || getClass() != obj.getClass() ) { return false; }

        Point point = (Point) obj;

        return this.x == point.x && this.y == point.y;
    }

    @Override
    public int hashCode()
    {
        if( this.hashCode == 0 )
        {
            this.hashCode = Objects.hash( this.x, this.y );
        }
        return this.hashCode;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }
}