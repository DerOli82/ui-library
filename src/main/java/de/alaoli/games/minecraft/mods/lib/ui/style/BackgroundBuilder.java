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
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.ColorBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.util.Image;
import de.alaoli.games.minecraft.mods.lib.ui.util.ImageBuilder;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class BackgroundBuilder<P> extends NestedBuilder<P,BackgroundBuilder<P>,Background>
        implements ColorBuilder<BackgroundBuilder<P>>, ImageBuilder<BackgroundBuilder<P>>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    Color color;
    Image image;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    BackgroundBuilder() {}

    private BackgroundBuilder( BackgroundBuilder<?> builder )
    {
        this.color = builder.color;
        this.image = builder.image;
    }

    boolean isEmpty()
    {
        return  ( this.color == null || this.color == Color.DEFAULT ) &&
                ( this.image == null || this.image.isEmpty() );
    }

    /* *************************************************************************************************************
     * Method - Implement NestedBuilder
     ************************************************************************************************************* */

    @Override
    protected BackgroundBuilder<P> self()
    {
        return this;
    }

    @Override
    public BackgroundBuilder<P> copy()
    {
        return new BackgroundBuilder<>( this );
    }

    /* *************************************************************************************************************
     * Method - Implement Builder
     ************************************************************************************************************* */

    @Override
    public Background build()
    {
        return (!this.isEmpty()) ? new Background( this ) : Background.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Implement ColorBuilder
     ************************************************************************************************************* */

    @Override
    public BackgroundBuilder<P> withColor( Color color )
    {
        this.color = color;

        return this;
    }

    /* *************************************************************************************************************
     * Method - Implement ImageBuilder
     ************************************************************************************************************* */

    @Override
    public BackgroundBuilder<P> withImage( Image image )
    {
        this.image = image;

        return this;
    }
}