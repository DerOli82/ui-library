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
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class Region
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private int x, y, width, height;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public int getX()
    {
        return this.x;
    }

    public Region setX( int x )
    {
        this.x = x;
        this.update();

        return this;
    }

    public int getY()
    {
        return this.y;
    }

    public Region setY( int y )
    {
        this.y = y;
        this.update();

        return this;
    }

    public int getWidth()
    {
        return this.width;
    }

    public Region setWidth(int width )
    {
        this.width = width;
        this.update();

        return this;
    }

    public int getHeight()
    {
        return this.height;
    }

    public Region setHeight(int height )
    {
        this.height = height;
        this.update();

        return this;
    }

    public Region setBounds( int x, int y, int width, int height )
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.update();

        return this;
    }

    public Region setLocation( int x, int y )
    {

        this.x = x;
        this.y = y;
        this.update();

        return this;
    }

    public Region setSize( int width, int height )
    {
        this.width = width;
        this.height = height;
        this.update();

        return this;
    }

    public Region translate( int x, int y )
    {
        this.x += x;
        this.y += y;

        return this;
    }

    public Region untranslate( int x, int y )
    {
        this.x -= x;
        this.y -= y;

        return this;
    }

    private void update()
    {
        this.onUpdate();
    }

    public void onUpdate() {}

    public boolean contains( int x, int y )
    {
        return this.intersects( x, y, 1, 1 );
    }

    public boolean intersects( Region region )
    {
        return this.intersects( region.x, region.y, region.width, region.height );
    }

    public boolean intersects( int x, int y, int width, int height )
    {
        //No dimension
        if( width <= 0 || height <= 0 || this.width <= 0 || this.height <= 0 ) { return false; }

        //Overflow or intersect
        return ( ( x + width < x || x + width > this.x ) &&
                 ( y + height < y || y + height > this.y ) &&
                 ( this.x + this.width < this.x || this.x + this.width > x ) &&
                 ( this.y + this.height < this.y || this.y + this.height > y ));
    }
}