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

import de.alaoli.games.minecraft.mods.lib.ui.builder.Builder;
import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class RegionBuilder<P> extends NestedBuilder<P, RegionBuilder<P>> implements Builder<Region>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    Align align;
    int x, y, width, height;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    RegionBuilder() {}

    public RegionBuilder<P> withAlign( Align align )
    {
        this.align = align;

        return this;
    }

    public RegionBuilder<P> withX( int x )
    {
        this.x = x;

        return this;
    }

    public RegionBuilder<P> withY( int y )
    {
        this.y = y;

        return this;
    }

    public RegionBuilder<P> withWidth( int width )
    {
        this.width = width;

        return this;
    }

    public RegionBuilder<P> withHeight( int height )
    {
        this.height = height;

        return this;
    }

    public RegionBuilder<P> withLocation(int x, int y )
    {
        return this.withX( x ).withY( y );
    }

    public RegionBuilder<P> withSize( int width, int height )
    {
        return this.withWidth( width ).withHeight( height );
    }

    public RegionBuilder<P> withBounds(int x, int y, int width, int height )
    {
        return this.withX( x ).withY( y ).withWidth( width ).withHeight( height );
    }

    /* **************************************************************************************************************
     * Method - Implement Builder<RegionBuilder, Region>
     ************************************************************************************************************** */

    @Override
    public Region build()
    {
        return new RegionStyle( this );
    }

    @Override
    public RegionBuilder<P> self()
    {
        return this;
    }
}