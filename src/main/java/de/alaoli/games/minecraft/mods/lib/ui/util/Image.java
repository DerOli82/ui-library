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
import lombok.*;
import net.minecraft.util.ResourceLocation;

import javax.annotation.concurrent.Immutable;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Getter
@Immutable
@EqualsAndHashCode
@AllArgsConstructor( access = AccessLevel.PRIVATE )
public final class Image
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    public static final Image EMPTY = new Image( null, 0, 0, 1.0F );

    private final ResourceLocation resource;
    private final int x, y;
    private final float factor;

    private static final Cache<String,Image> IMAGES = CacheBuilder.newBuilder()
        .maximumSize( 64 )
        .weakValues()
        .build();

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public static Image valueOrEmpty( Image image )
    {
        return (image!=null) ? image : EMPTY;
    }

    public static Image get( String location, int x, int y, float factor )
    {
        if( ( location == null ) || ( location.isEmpty() ) ) { return  EMPTY; }

        Image result;
        String key = location + " { x:" + x + ",y:" + y + ",factor:" + factor + "}";

        if( !IMAGES.asMap().containsKey( key ) )
        {
            result = new Image( new ResourceLocation( location ), x, y, factor );
            IMAGES.put( key, result );
        }
        else
        {
            result = IMAGES.asMap().getOrDefault( key, EMPTY );
        }
        return result;
    }

    public boolean isEmpty()
    {
        return this.resource == null;
    }
}