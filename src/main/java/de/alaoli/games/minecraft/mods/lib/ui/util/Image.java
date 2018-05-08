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
package de.alaoli.games.minecraft.mods.lib.ui.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.minecraft.util.ResourceLocation;

import javax.annotation.concurrent.Immutable;
import java.util.Objects;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Immutable
public final class Image
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    public static final Image EMPTY = new Image();

    private final int x, y, width, height;
    private ResourceLocation resource;
    private int hashCode;

    private static final Cache<String,Image> IMAGES = CacheBuilder.newBuilder()
        .maximumSize( 64 )
        .weakValues()
        .build();

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    private Image()
    {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    private Image( ResourceLocation resource,  int x, int y, int width, int height )
    {
        this.resource = resource;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals( Object o )
    {
        if( this == o ) { return true; }
        if( o == null || getClass() != o.getClass() ) { return false; }

        Image image = (Image) o;

        return this.x == image.x &&
                this.y == image.y &&
                this.width == image.width &&
                this.height == image.height &&
                Objects.equals( this.resource, image.resource );
    }

    @Override
    public int hashCode()
    {
        if( this.hashCode == 0 )
        {
            this.hashCode = Objects.hash( this.x, this.y, this.width, this.height, this.resource );
        }
        return this.hashCode;
    }

    public static Image get( String location, int x, int y, int width, int height )
    {
        String key = location + " { x:" + x + ",y:" + y + ",width:" + width + ",height:" + height + "}";

        if( !IMAGES.asMap().containsKey( key ) )
        {
            IMAGES.put( key, new Image( new ResourceLocation( location ), x, y, width, height ) );
        }
        return IMAGES.asMap().get( key );
    }

    public boolean isEmpty()
    {
        return this.resource == null || width <= 0 || height <= 0;
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

    public ResourceLocation getResource()
    {
        return this.resource;
    }
}