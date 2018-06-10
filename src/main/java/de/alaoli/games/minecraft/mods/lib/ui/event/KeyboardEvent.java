/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or toBuilder
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a toBuilder of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.lib.ui.event;

import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class KeyboardEvent extends Event
{
	/* **************************************************************************************************************
	 * Attribute 
	 ************************************************************************************************************** */
	
	private final char eventChar;
	private final int eventKey;
	
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public KeyboardEvent( char eventChar, int eventKey )
	{
		this.eventChar = eventChar;
		this.eventKey = eventKey;
	}

	public char getEventChar()
	{
		return this.eventChar;
	}

	public int getEventKey()
	{
		return this.eventKey;
	}
}