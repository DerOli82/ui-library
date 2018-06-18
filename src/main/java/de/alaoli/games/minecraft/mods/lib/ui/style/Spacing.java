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

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Immutable
final class Spacing implements Margin, Padding, Rebuildable<SpacingBuilder>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    static final Spacing EMPTY = new Spacing( 0, 0, 0 ,0 );

    private final int top, left, right, bottom;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    private Spacing( int top, int left, int right, int bottom )
    {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    Spacing( SpacingBuilder<?> builder )
    {
        this( builder.top, builder.left, builder.right, builder.bottom );
    }

    public boolean isEmpty()
    {
        return this == EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Implement Margin / Padding
     ************************************************************************************************************* */

    @Override
    public int getTop()
    {
        return this.top;
    }

    @Override
    public int getLeft()
    {
        return this.left;
    }

    @Override
    public int getRight()
    {
        return this.right;
    }

    @Override
    public int getBottom()
    {
        return this.bottom;
    }

    /* *************************************************************************************************************
     * Method - Implement Rebuildable
     ************************************************************************************************************* */

    @Override
    public <P> SpacingBuilder<P> toBuilder()
    {
        return new SpacingBuilder<P>().withSpacing( this.top, this.left, this.right, this.bottom );
    }
}