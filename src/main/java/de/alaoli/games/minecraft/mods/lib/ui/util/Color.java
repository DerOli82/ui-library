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

/**
 * You shouldn't use this clas.
 * For creating and handling color instances, use the static methods in {@link Colors}.
 *
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class Color 
{	
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	public static final int BLACK = 0x000000;
	public static final int DARKBLUE = 0x0000AA;
	public static final int DARKGREEN = 0x00AA00;
	public static final int DARKAQUA = 0x00AAAA;
	public static final int DARKRED = 0xAA0000;
	public static final int DARKPURPLE = 0xAA00AA;
	public static final int GOLD = 0xFFAA00;
	public static final int GRAY = 0xAAAAAA;
	public static final int DARKGRAY = 0x555555;
	public static final int BLUE = 0x5555FF;
	public static final int GREEN = 0x55FF55;
	public static final int AQUA = 0x55FFFF;
	public static final int RED = 0xFF5555;
	public static final int LIGHTPURPLE = 0xFF55FF;
	public static final int YELLOW = 0xFFFF55;
	public static final int WHITE = 0xFFFFFF;

	public final int value;
	
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Color( int argb, boolean includesAlpha )
	{
		if( includesAlpha )
		{
			this.value = argb;
		}
		else
		{
			this.value = combineARGB( 255, argb );
		}

	}

	public Color( int rgb )
	{
		this( 255, rgb );
	}

	public Color( float alpha, int rgb )
	{
		this( (int)(alpha*255), rgb );
	}

	public Color( int alpha, int rgb )
	{
		this.value = combineARGB( alpha, rgb );
	}

	public Color( int r, int g, int b )
	{
		this( 255, r, g, b );
	}
	
	public Color( float alpha, int r, int g, int b )
	{
		this( Math.round( alpha*255 ), r, g, b );
	}
	
	public Color( int alpha, int r, int g, int b )
	{
		this.value = combineARGB( alpha, r, g, b );
	}

	@Override
	public boolean equals( Object obj )
	{
		return obj instanceof Color && ((Color) obj).value == this.value;
	}

	@Override
	public int hashCode() 
	{
		return this.value;
	}
	
	public final int getValue()
	{
		return this.value;
	}

	public int getAlpha() {
		return (this.value >> 24) & 0xff;
	}

	public int getRed() {
		return (this.value >> 16) & 0xFF;
	}

	public int getGreen() {
		return (this.value >> 8) & 0xFF;
	}

	public int getBlue() {
		return (this.value) & 0xFF;
	}

	/**
	 * Combines alpha, red, green, blue value into argb value
	 *
	 * @throws IllegalArgumentException, if alpha, r, g or b value isn't between 0 and 255
	 *
	 * @param alpha	Transparency value
	 * @param r		Red color value
	 * @param g		Green color value
	 * @param b		Blue color value
	 * @return		Returns argb value
	 */
	public static int combineARGB(int alpha, int r, int g, int b ) throws IllegalArgumentException
	{
		if( alpha < 0 || alpha > 255 ) { throw new IllegalArgumentException( "'alpha' value must be between 0 and 255." ); }
		if( r < 0 || r > 255 ) { throw new IllegalArgumentException( "'r' value must be between 0 and 255." ); }
		if( g < 0 || g > 255 ) { throw new IllegalArgumentException( "'g' value must be between 0 and 255." ); }
		if( b < 0 || b > 255 ) { throw new IllegalArgumentException( "'b' value must be between 0 and 255." ); }

		return ((alpha & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF));
	}

	/**
	 * Combines alpha and rgb value into argb value
	 *
	 * @throws IllegalArgumentException, if alpha value isn't between 0 and 255
	 * @throws IllegalArgumentException, if rgb value isn't between 0 and 16777215
	 *
	 * @param alpha	Transparency value
	 * @param rgb	Combined Red, green and blue color value
	 * @return		Returns argb value
	 */
	public static int combineARGB(int alpha, int rgb ) throws IllegalArgumentException
	{
		if( alpha < 0 || alpha > 255 ) { throw new IllegalArgumentException( "'alpha' value must be between 0 and 255." ); }
		if( rgb < 0 || rgb > 16777215 ) { throw new IllegalArgumentException( "'rgb' value must be between 0 and 16777215." ); }

		return ((alpha & 0xFF) << 24) | rgb;
	}
}