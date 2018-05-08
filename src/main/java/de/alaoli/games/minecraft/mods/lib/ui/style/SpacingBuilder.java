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
public final class SpacingBuilder<P> extends NestedBuilder<P, SpacingBuilder<P>,Spacing>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    int top, left, right, bottom;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    SpacingBuilder() {}

    private SpacingBuilder( SpacingBuilder<?> builder )
    {
        this.top = builder.top;
        this.left = builder.left;
        this.right = builder.right;
        this.bottom = builder.bottom;
    }

    public boolean isEmpty()
    {
        return this.top == 0 && this.left == 0 && this.right == 0 && this.bottom == 0;
    }

    public SpacingBuilder<P> withSpacing( int spacing )
    {
        return this.withSpacing( spacing, spacing, spacing, spacing );
    }

    public SpacingBuilder<P> withSpacing( int top, int left, int right, int bottom )
    {
        return this.withTop( top ).withLeft( left ).withRight( right ).withBottom( bottom );
    }

    public SpacingBuilder<P> withTop( int top )
    {
        this.top = Math.max( top, 0 );

        return this;
    }

    public SpacingBuilder<P> withLeft( int left )
    {
        this.left = Math.max( left, 0 );

        return this;
    }

    public SpacingBuilder<P> withRight( int right )
    {
        this.right = Math.max( right, 0 );

        return this;
    }

    public SpacingBuilder<P> withBottom( int bottom )
    {
        this.bottom = Math.max( bottom, 0 );

        return this;
    }

    /* *************************************************************************************************************
     * Method - Implement NestedBuilder
     ************************************************************************************************************* */

    @Override
    protected SpacingBuilder<P> self()
    {
        return this;
    }


    @Override
    public SpacingBuilder<P> copy()
    {
        return new SpacingBuilder<>( this );
    }

    /* *************************************************************************************************************
     * Method - Implement Builder
     ************************************************************************************************************* */

    @Override
    public Spacing build()
    {
        return (!this.isEmpty()) ? new Spacing( this ) : Spacing.EMPTY;
    }
}