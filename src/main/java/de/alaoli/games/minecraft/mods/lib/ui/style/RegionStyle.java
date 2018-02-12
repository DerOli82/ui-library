/**************************************************************************************************************
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
 *************************************************************************************************************/
package de.alaoli.games.minecraft.mods.lib.ui.style;

import de.alaoli.games.minecraft.mods.lib.ui.Constants;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
final class RegionStyle implements Region, RegionTransformable
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private Align align;
    private int x, y, width, height;

    private RegionStyle transformed;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    RegionStyle()
    {
        this.align = Constants.Style.Region.ALIGN;
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    private RegionStyle( Region region )
    {
        this( region.getAlign(), region.getX(), region.getY(), region.getWidth(), region.getHeight() );
    }

    RegionStyle( RegionBuilder builder )
    {
        this( builder.align, builder.x, builder.y, builder.width, builder.height );
    }

    private RegionStyle( Align align, int x, int y, int width, int height )
    {
        this.align = (align!=null) ? align : Constants.Style.Region.ALIGN;
        this.x = x;
        this.y = y;
        this.width = (width>0) ? width : 0;
        this.height = (height>0) ? height : 0;
    }

    /* **************************************************************************************************************
     * Method - Implement Region
     ************************************************************************************************************** */

    @Override
    public Align getAlign()
    {
        return (this.transformed!=null) ? this.transformed.getAlign() : this.align;
    }

    @Override
    public int getX()
    {
        return (this.transformed!=null) ? this.transformed.getX() : this.x;
    }

    @Override
    public int getY()
    {
        return (this.transformed!=null) ? this.transformed.getY() : this.y;
    }

    @Override
    public int getWidth()
    {
        return (this.transformed!=null) ? this.transformed.getWidth() : this.width;
    }

    @Override
    public int getHeight()
    {
        return (this.transformed!=null) ? this.transformed.getHeight() : this.height;
    }

    @Override
    public RegionTransformable transform( boolean fromOrigin )
    {
        if( this.transformed == null )
        {
            this.transformed = new RegionStyle( this );
        }
        else if( fromOrigin )
        {
            this.transformed.x = this.x;
            this.transformed.y = this.y;
            this.transformed.width = this.width;
            this.transformed.height = this.height;
        }
        return this.transformed;
    }

    @Override
    public void restoreOrigin()
    {
        this.transformed = null;
    }

    /* **************************************************************************************************************
     * Method - Implement RegionTransformable
     ************************************************************************************************************** */

    @Override
    public void translate( int x, int y )
    {
        this.x += x;
        this.y += y;
    }

    @Override
    public void resize( int width, int height )
    {
        this.width = (width>0) ? width : 0;
        this.height = (height>0) ? height : 0;
    }
}