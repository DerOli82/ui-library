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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.*;

import javax.annotation.concurrent.Immutable;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Getter
@ToString
@Immutable
@EqualsAndHashCode
@AllArgsConstructor( access = AccessLevel.PRIVATE )
public final class Color
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	public static final Color DEFAULT = new Color( 0 );

	/**
	 * Default Minecraft color codes
	 */
	@Getter
	@AllArgsConstructor
	public enum Codes
	{
		BLACK( 0x000000 ),
		DARKBLUE( 0x0000AA ),
		DARKGREEN( 0x00AA00 ),
		DARKAQUA( 0x00AAAA ),
		DARKRED( 0xAA0000 ),
		DARKPURPLE( 0xAA00AA ),
		GOLD( 0xFFAA00 ),
		GRAY( 0xAAAAAA ),
		DARKGRAY( 0x555555 ),
		BLUE( 0x5555FF ),
		GREEN( 0x55FF55 ),
		AQUA( 0x55FFFF ),
		RED( 0xFF5555 ),
		LIGHTPURPLE( 0xFF55FF ),
		YELLOW( 0xFFFF55 ),
		WHITE( 0xFFFFFF );

		private final int value;
	}

	/**
	 * ARGB color value
	 */
	private final int value;

	/**
	 * Cached colors to prevent creating new object for already used colors
	 */
	private static final Cache<Integer,Color> CACHE = CacheBuilder.newBuilder()
		.maximumSize( 256 )
		.weakValues()
		.build();
	static { CACHE.put( 0, DEFAULT ); }

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public boolean isEmpty()
	{
		return this.value == 0;
	}

	public int getAlpha()
	{
		return (this.value >> 24) & 0xFF;
	}

	public int getRed()
	{
		return (this.value >> 16) & 0xFF;
	}

	public int getGreen()
	{
		return (this.value >> 8) & 0xFF;
	}

	public int getBlue()
	{
		return (this.value) & 0xFF;
	}

	/* **************************************************************************************************************
	 * Method - Static
	 ************************************************************************************************************** */

	public static Color valueOrDefault( Color color )
	{
		return (color!=null) ? color : DEFAULT;
	}

	public static Color valueOf( Codes code ) throws IllegalArgumentException
	{
		return valueOf( code.value );
	}

	public static Color valueOf( int rgb ) throws IllegalArgumentException
	{
		return valueOf( 1.0f, rgb );
	}

	public static Color valueOf( float alpha, Codes code ) throws IllegalArgumentException
	{
		return valueOf( alpha, code.value );
	}

	public static Color valueOf( int red, int green, int blue ) throws IllegalArgumentException
	{
		return valueOf( 1.0f, red, green, blue );
	}

	public static Color valueOf( float alpha, int red, int green, int blue ) throws IllegalArgumentException
	{
		if( red < 0 || red > 255 ) { throw new IllegalArgumentException( "'red' value must be between 0 and 255." ); }
		if( green < 0 || green > 255 ) { throw new IllegalArgumentException( "'green' value must be between 0 and 255." ); }
		if( blue < 0 || blue > 255 ) { throw new IllegalArgumentException( "'blue' value must be between 0 and 255." ); }

		return valueOf( alpha, ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF)) );
	}

	public static Color valueOf( float alpha, int rgb ) throws IllegalArgumentException
	{
		if( alpha < 0 || alpha > 1.0f ) { throw new IllegalArgumentException( "'alpha' value must be between 0.0f and 1.0f." ); }
		if( rgb < 0 || rgb > 16777215 ) { throw new IllegalArgumentException( "'rgb' value must be between 0 and 16777215." ); }

		int value = ((((int)(alpha*255)) & 0xFF) << 24) | rgb;

		if( value == DEFAULT.value ) { return DEFAULT; }

		Color result;

		//No cache hit
		if( !CACHE.asMap().containsKey( value ) )
		{
			result = new Color( value );
			CACHE.put( value, result );
		}
		else
		{
			result = CACHE.asMap().getOrDefault( value, DEFAULT );
		}
		return result;
	}
}