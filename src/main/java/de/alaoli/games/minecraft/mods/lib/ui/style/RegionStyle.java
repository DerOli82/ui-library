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

import de.alaoli.games.minecraft.mods.lib.ui.Constants;
import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class RegionStyle implements Region
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    public static final RegionStyle DEFAULT = new RegionStyle();

    private final Align align;
    private final int x, y, width, height;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    private RegionStyle()
    {
        this.align = Constants.Style.Region.ALIGN;
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    private RegionStyle( Align align, int x, int y, int width, int height )
    {
        this.align = (align!=null) ? align : Constants.Style.Region.ALIGN;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /* **************************************************************************************************************
     * Method - Implement Region
     ************************************************************************************************************** */

    @Override
    public Align getAlign()
    {
        return this.align;
    }

    @Override
    public int getX()
    {
        return this.x;
    }

    @Override
    public int getY()
    {
        return this.y;
    }

    @Override
    public int getWidth()
    {
        return this.width;
    }

    @Override
    public int getHeight()
    {
        return this.height;
    }

    /* **************************************************************************************************************
     * RegionBuilder
     ************************************************************************************************************** */

    public static final class RegionBuilder<P> extends NestedBuilder<P, Region>
    {
        /* **************************************************************************************************************
         * Attribute
         ************************************************************************************************************** */

        private Align align;
        private int x, y, width, height;

        /* **************************************************************************************************************
         * Method
         ************************************************************************************************************** */

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

        public RegionBuilder<P> withLocation( int x, int y )
        {
            this.x = x;
            this.y = y;

            return this;
        }

        public RegionBuilder<P> withWidth( int width )
        {
            this.width = (width > 0) ? width : 0;

            return this;
        }

        public RegionBuilder<P> withHeight( int height )
        {
            this.height = (height > 0) ? height : 0;

            return this;
        }

        public RegionBuilder<P> withSize( int width, int height )
        {
            this.withWidth( width );
            this.withHeight( height );

            return this;
        }

        public RegionBuilder<P> withBounds( int x, int y, int width, int height )
        {
            this.x = x;
            this.y = y;
            this.withWidth( width );
            this.withHeight( height );

            return this;
        }

        /* **************************************************************************************************************
         * Method - Implement Builder<RegionStyle>
         ************************************************************************************************************** */

        @Override
        public Region build()
        {
            return new RegionStyle( this.align, this.x, this.y, this.width, this.height );
        }
    }
}