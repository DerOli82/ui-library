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

/**
 * @author DerOli82 <https://github.com/DerOli82>
 *
 * @param <S> Type of the implementing class (self)
 */
public interface ColorBuilder<S extends ColorBuilder<S>>
{
    default S withColor( Color.Codes code )
    {
        return this.withColor( Color.valueOf( code ) );
    }

    default S withColor( float alpha, Color.Codes code )
    {
        return this.withColor( Color.valueOf( alpha, code ) );
    }

    default S withColor( int rgb )
    {
        return this.withColor( Color.valueOf( rgb ) );
    }

    default S withColor( float alpha, int rgb )
    {
        return this.withColor( Color.valueOf( alpha, rgb ) );
    }

    default S withColor( int red, int green, int blue )
    {
        return this.withColor( Color.valueOf( red, green, blue ) );
    }

    default S withColor( float alpha, int red, int green, int blue )
    {
        return this.withColor( Color.valueOf( alpha, red, green, blue ) );
    }

    S withColor( Color color );
}