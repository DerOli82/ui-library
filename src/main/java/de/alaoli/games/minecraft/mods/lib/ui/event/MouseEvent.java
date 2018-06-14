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
package de.alaoli.games.minecraft.mods.lib.ui.event;

import de.alaoli.games.minecraft.mods.lib.ui.component.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Getter
@AllArgsConstructor
public abstract class MouseEvent extends Event
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    public static final int BUTTON_LEFT = 0;
	public static final int BUTTON_RIGHT = 1;

	private final Component src;

	private final int x;
	private final int y;
	private final int button;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public boolean isLeftClick()
	{
		return this.button == BUTTON_LEFT;
	}

	public boolean isRightClick()
	{
		return this.button == BUTTON_RIGHT;
	}

	/* **************************************************************************************************************
	 * Click Event
	 ************************************************************************************************************** */

	public static final class Click extends MouseEvent
	{
		public Click( MouseListener src, int x, int y, int button ) { super( src, x, y, button ); }
	}

	/* **************************************************************************************************************
	 * Enter Event
	 ************************************************************************************************************** */

	public static final class Enter extends MouseEvent
	{
		public Enter( MouseListener src, int x, int y, int button ) { super( src, x, y, button ); }
	}

	/* **************************************************************************************************************
	 * Leave Event
	 ************************************************************************************************************** */

	public static final class Leave extends MouseEvent
	{
		public Leave( MouseListener src, int x, int y, int button ) { super( src, x, y, button ); }
	}
}