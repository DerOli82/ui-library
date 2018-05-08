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
package de.alaoli.games.minecraft.mods.lib.ui.style;

import de.alaoli.games.minecraft.mods.lib.ui.builder.Rebuildable;

import javax.annotation.concurrent.Immutable;
import java.util.Objects;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Immutable
public final class Region implements Rebuildable<RegionBuilder>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    public static final Region EMPTY = new Region();

    private final int x, y, width, height;
    private int hashCode;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    private Region()
    {
        this( 0, 0, 0, 0 );
    }

    private Region( int x, int y, int width, int height )
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    Region( RegionBuilder<?> builder )
    {
        this( builder.x, builder.y, builder.width, builder.height );
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) { return true; }
        if( o == null || getClass() != o.getClass() ) { return false; }

        Region region = (Region) o;

        return this.x == region.x && this.y == region.y && this.width == region.width && this.height == region.height;
    }

    @Override
    public int hashCode()
    {
        if( this.hashCode == 0 ) { this.hashCode = Objects.hash( this.x, this.y, this.width, this.height ); }

        return this.hashCode;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    /**
     * @return Returns true, if width or height <= 0
     */
    public boolean isEmpty()
    {
        return this.getWidth() <= 0 || this.getHeight() <= 0;
    }

    /**
     * @return  Returns true, if the x and y coordinate ist within this {@link Region}
     */
    public boolean contains( int x, int y )
    {
        return this.intersects( x, y, 1, 1 );
    }

    public boolean intersects( Region region )
    {
        return this.intersects( region.getX(), region.getY(), region.getWidth(), region.getHeight() );
    }

    public boolean intersects( int x, int y, int width, int height )
    {
        //No dimension
        if( width <= 0 || height <= 0 || this.getWidth() <= 0 || this.getHeight() <= 0 ) { return false; }

        //Overflow or intersect
        return ( ( x + width < x || x + width > this.getX() ) &&
                ( y + height < y || y + height > this.getY() ) &&
                ( this.getX() + this.getWidth() < this.getX() || this.getX() + this.getWidth() > x ) &&
                ( this.getY() + this.getHeight() < this.getY() || this.getY() + this.getHeight() > y ));
    }

    /* *************************************************************************************************************
     * Method - Implement Rebuildable
     ************************************************************************************************************* */

    @Override
    public <P> RegionBuilder<P> toBuilder()
    {
        return new RegionBuilder<P>().withBounds( this.x, this.y, this.width, this.height );
    }
}