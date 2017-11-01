
/* *************************************************************************************************************
 * Copyright (c) 2017 DerOli82 <https://github.com/DerOli82>
 *  
 *  his program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************ */
package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.layout.Layout;
import org.lwjgl.util.Rectangle;

import net.minecraft.client.gui.Gui;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 * @param <T> The type of the child class, required for returning child class in all setter methods
 */
public abstract class Element<T extends Element> extends Gui implements Layout
{
	/* **************************************************************************************************************
	 * Attribute 
	 ************************************************************************************************************** */

	/**
	 * Contains the element bounds
	 */
	public final Rectangle box = new Rectangle();

	private boolean needsLayout = true;
	private boolean fillParent = false;

	private Element parent;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Optional<Element> getParent()
	{
		return Optional.ofNullable( this.parent );
	}
	public boolean hasParent()
	{
		return this.parent != null;
	}

	/**
	 * You shouldn't use this, it's for internal usage only.
	 *
	 * @param parent	Sets parent element of this element
	 * @return			Returns this object
	 */
	public T setParent( Element parent )
	{
		this.parent = parent;

		return (T)this;
	}

	/**
	 * Pass trough {@link #box#setBounds(int, int, int, int)}
	 *
	 * @param posX		Sets X-Position of this element
	 * @param posY		Sets Y-Position of this element
	 * @param width		Sets width of this Element
	 * @param height	Sets height of this Element
	 * @return 			Returns this object
	 */
	public T setBounds(int posX, int posY, int width, int height )
	{
		this.box.setBounds( posX, posY, width, height );

		return (T)this;
	}

	/**
	 * Pass trough {@link #box#setLocation(int, int)}
	 *
	 * @param posX 	Sets X-Position of this element
	 * @param posY 	Sets Y-Position of this element
	 * @return 		Returns this object
	 */
	public T setLocation( int posX, int posY )
	{
		this.box.setLocation( posX, posY );

		return (T)this;
	}

	/**
	 * Pass trough {@link #box#setPosX(int)}
	 *
	 * @param posX	Sets X-Position of this element
	 * @return 		Returns this object
	 */
	public T setPosX( int posX )
	{
		this.box.setX( posX );

		return (T)this;
	}

	/**
	 * Pass trough {@link #box#setPosY(int)}
	 *
	 * @param posY	Sets Y-Position of this element
	 * @return 		Returns this object
	 */
	public T setPosY( int posY )
	{
		this.box.setY( posY );

		return (T)this;
	}

	/**
	 * Pass trough {@link #box#setSize(int, int)}
	 *
	 * @param width		Sets width of this Element
	 * @param height	Sets height of this Element
	 * @return 			Returns this object
	 */
	public T setSize( int width, int height )
	{
		this.box.setSize( width, height );

		return (T)this;
	}

	/**
	 * Pass trough {@link #box#setWidth(int)}
	 *
	 * @param width	Sets width of this Element
	 * @return 		Returns this object
	 */
	public T setWidth(int width )
	{
		this.box.setWidth( width );

		return (T)this;
	}

	/**
	 * Pass trough {@link #box#setHeight(int)}
	 *
	 * @param height	Sets height of this Element
	 * @return 			Returns this object
	 */
	public T setHeight(int height )
	{
		this.box.setHeight( height );

		return (T)this;
	}

	/**
	 * Drawing this element
	 *
	 * @param mouseX		X-Position of the mouse, only needed for wrapped Minecraft GUI elements
	 * @param mouseY		Y-Position of the mouse, only needed for wrapped Minecraft GUI elements
	 * @param partialTicks	The amount of time, in fractions of a tick, that has passed since the last full tick.
	 */
	public abstract void drawElement( int mouseX, int mouseY, float partialTicks );

	/* **************************************************************************************************************
	 * Method - Implement Layout
	 ************************************************************************************************************** */

	@Override
	public void validateLayout()
	{
		if( ( this.fillParent ) &&
			( this.parent != null ) )
		{
			this.box.setSize(
				this.parent.box.getWidth(),
				this.parent.box.getHeight()
			);
		}

		if( this.needsLayout )
		{
			this.needsLayout = false;
			this.layout();
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

	@Override
	public void setFillParent(boolean fillParent)
	{
		this.fillParent = fillParent;
	}

	@Override
	public int getMinWidth()
	{
		return this.getPrefWidth();
	}

	@Override
	public int getMinHeight()
	{
		return this.getPrefHeight();
	}

	@Override
	public int getMaxWidth()
	{
		return (this.parent!=null) ? this.parent.getMaxWidth() : 0;
	}

	@Override
	public int getMaxHeight()
	{
		return (this.parent!=null) ? this.parent.getMaxHeight() : 0;
	}
}
