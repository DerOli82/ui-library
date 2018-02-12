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

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public enum Align 
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	TOPLEFT( 7, "topleft" ),
	TOP( 8, "top" ),
	TOPRIGHT( 9, "topright" ),
	LEFT( 4, "left" ),
	CENTER( 5, "center" ),
	RIGHT( 6, "right" ),
	BOTTOMLEFT( 1, "bottomleft" ),
	BOTTOM( 2, "bottom" ),
	BOTTOMRIGHT( 3, "bottomright" );
	
	public final int id;
	public final String name;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	Align( int id, String name )
	{
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() 
	{
		return this.name;
	}

	public static Align of( int value )
	{
		for( Align align : Align.values() )
		{
			if( align.id == value ) { return align; }
		}
		throw new IllegalStateException( "'" + value + "' isn't a valid align." );
	}

	public static Align of( String value )
	{
		for( Align align : Align.values() )
		{
			if( align.name.equals( value ) ) { return align; }
		}
		throw new IllegalStateException( "'" + value + "' isn't a valid align." );
	}
}