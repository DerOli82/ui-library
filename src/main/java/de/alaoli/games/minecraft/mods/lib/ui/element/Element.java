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
package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.layout.Layout;
import de.alaoli.games.minecraft.mods.lib.ui.util.Region;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public abstract class Element extends Region implements Layout
{
	/* **************************************************************************************************************
	 * Attribute 
	 ************************************************************************************************************** */

	private Element parent;
	private boolean needsLayout = true;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Optional<Element> getParent()
	{
		return Optional.ofNullable( this.parent );
	}

	/**
	 * You shouldn't use this, it's for internal usage only.
	 *
	 * @param parent	Sets parent of this {@link Element}
	 * @return			Returns this object
	 */
	public Element setParent( Element parent )
	{
		this.parent = parent;

		return this;
	}

	/**
	 * Drawing this {@link Element} within the given {@link Region}, if the region has no dimension,
	 * drawing will be skipped.
	 *
	 * @param mouseX		X-Position of the mouse, only needed for wrapped Minecraft GUI elements
	 * @param mouseY		Y-Position of the mouse, only needed for wrapped Minecraft GUI elements
	 * @param partialTicks	The amount of time, in fractions of a tick, that has passed since the last full tick.
	 */
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		this.validateLayout();

		if( ( this.getWidth() <= 0 ) || ( this.getHeight() <= 0 ) ) { return; }

		this.drawElementAt( this.getX(), this.getY(), this.getWidth(), this.getHeight(), mouseX, mouseY, partialTicks );
	}

	/**
	 * Drawing this element at the given location and dimension, if it has no dimension,
	 * drawing will be skipped.
	 *
	 * @param x				Drawing x-position
	 * @param y				Drawing y-position
	 * @param width			Drawing width
	 * @param height		Drawing height
	 * @param mouseX		X-Position of the mouse, only needed for wrapped Minecraft GUI elements
	 * @param mouseY		Y-Position of the mouse, only needed for wrapped Minecraft GUI elements
	 * @param partialTicks	The amount of time, in fractions of a tick, that has passed since the last full tick.
	 */
	public abstract void drawElementAt( int x, int y, int width, int height, int mouseX, int mouseY, float partialTicks );

	/* **************************************************************************************************************
	 * Method - Implement Region
	 ************************************************************************************************************** */

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.invalidateLayout();
	}

	/* **************************************************************************************************************
	 * Method - Implement Layout
	 ************************************************************************************************************** */

	@Override
	public void validateLayout()
	{
		if( this.needsLayout )
		{
			this.layout();
			this.needsLayout = false;
		}
	}

	@Override
	public void invalidateLayout()
	{
		this.needsLayout = true;
	}

	@Override
	public boolean needsLayout()
	{
		return this.needsLayout;
	}
}