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

import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class RegionBuilder<P> extends NestedBuilder<P,RegionBuilder<P>,Region>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    int x, y, width, height;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    RegionBuilder() {}

    private RegionBuilder( RegionBuilder<P> builder )
    {
        this.x = builder.x;
        this.y = builder.y;
        this.width = builder.width;
        this.height = builder.height;
    }

    public boolean isEmpty()
    {
        return x == 0  && y == 0 && width == 0 && height == 0;
    }

    public RegionBuilder<P> withXPosition( int x )
    {
        this.x = x;

        return this;
    }

    public RegionBuilder<P> withYPosition( int y )
    {
        this.y = y;

        return this;
    }

    public RegionBuilder<P> withPosition( int x, int y )
    {
        return this.withXPosition( x ).withYPosition( y );
    }

    public RegionBuilder<P> withWidth( int width )
    {
        this.width = Math.max( width, 0 );

        return this;
    }

    public RegionBuilder<P> withHeight( int height )
    {
        this.height = Math.max( height, 0 );

        return this;
    }

    public RegionBuilder<P> withDimensions( int width, int height )
    {
        return this.withWidth( width ).withHeight( height );
    }

    public RegionBuilder<P> withBounds( int x, int y, int width, int height )
    {
        return this.withXPosition( x ).withYPosition( y ).withWidth( width ).withHeight( height );
    }

    public RegionBuilder<P> translate( int xy )
    {
        return this.translate( xy, xy );
    }

    public RegionBuilder<P> translate( int x, int y )
    {
        this.x += x;
        this.y += y;

        return this;
    }

    public RegionBuilder<P> grow( int size )
    {
        return this.grow( size, size );
    }

    public RegionBuilder<P> grow( int width, int height )
    {
        this.width = Math.max( this.width + width, 0 );
        this.height = Math.max( this.height + height, 0 );

        return this;
    }

    public RegionBuilder<P> shrink( int size )
    {
        return this.shrink( size, size );
    }

    public RegionBuilder<P> shrink( int width, int height )
    {
        this.width = Math.max( this.width - width, 0 );
        this.height = Math.max( this.height - height, 0 );

        return this;
    }

    /* *************************************************************************************************************
     * Method - Implement NestedBuilder
     ************************************************************************************************************* */

    @Override
    protected RegionBuilder<P> self()
    {
        return this;
    }

    @Override
    public RegionBuilder<P> copy()
    {
        return new RegionBuilder<>( this );
    }

    /* *************************************************************************************************************
     * Method - Implement Builder
     ************************************************************************************************************* */

    @Override
    public Region build()
    {
        return (!this.isEmpty()) ? new Region( this ) : Styles.emptyRegion();
    }
}