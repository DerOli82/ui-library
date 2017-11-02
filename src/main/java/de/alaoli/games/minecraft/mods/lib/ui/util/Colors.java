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
package de.alaoli.games.minecraft.mods.lib.ui.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class Colors
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private static class LazyHolder
    {
        public static final Colors INSTANCE = new Colors();
    }

    private final Map<Integer, Color> colors = new HashMap<>();

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    private Colors() {}

    private Color getColor( int argb )
    {
        Color color;

        if( this.colors.containsKey( argb ) )
        {
            color = this.colors.get( argb );
        }
        else
        {
            color = new Color( argb, true );
            this.colors.put( argb, color );
        }
        return color;
    }

    /* **************************************************************************************************************
     * Method - Static
     ************************************************************************************************************** */

    private static Colors getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    public static Color factory( int rgb )
    {
        return getInstance().getColor( Color.combineARGB( 255, rgb ) );
    }

    public static Color factory( float alpha, int rgb )
    {
        return factory( (int)(alpha*255), rgb );
    }

    public static Color factory( int alpha, int rgb )
    {
        return getInstance().getColor( Color.combineARGB( alpha, rgb ) );
    }

    public static Color factory( int r, int g, int b )
    {
        return factory( 255, r, g, b );
    }

    public static Color factory( float alpha, int r, int g, int b )
    {
        return factory( Math.round( alpha*255 ), r, g, b );
    }

    public static Color factory( int alpha, int r, int g, int b )
    {
        return getInstance().getColor( Color.combineARGB( alpha, r, g, b ) );
    }

    public static void clear()
    {
        getInstance().colors.clear();
    }
}