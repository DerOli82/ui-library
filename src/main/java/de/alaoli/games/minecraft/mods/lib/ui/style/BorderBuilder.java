/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or toBuilder
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a toBuilder of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.lib.ui.style;

import de.alaoli.games.minecraft.mods.lib.ui.builder.Builder;
import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.ColorBuilder;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class BorderBuilder<P> extends NestedBuilder<P,BorderBuilder<P>,Border>
        implements ColorBuilder<BorderBuilder<P>>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    Color color;

    boolean hasTop, hasLeft, hasRight, hasBottom;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    BorderBuilder() {}

    private BorderBuilder( BorderBuilder<?> builder )
    {
        this.color = builder.color;

        this.hasTop = builder.hasTop;
        this.hasLeft = builder.hasLeft;
        this.hasRight = builder.hasRight;
        this.hasBottom = builder.hasBottom;
    }

    boolean isEmpty()
    {
        return ( ( this.color == null ) || ( this.color == Color.DEFAULT ) ) ||
                ( ( !this.hasTop ) && ( !this.hasLeft ) && ( !this.hasRight ) && ( !this.hasBottom ) );
    }

    public BorderBuilder<P> withBorder()
    {
        return this.withBorder( true, true, true, true );
    }

    public BorderBuilder<P> withoutBorder()
    {
        return this.withBorder( false, false, false, false );
    }

    public BorderBuilder<P> withBorder( boolean top, boolean left, boolean right, boolean bottom )
    {
        this.hasTop = top;
        this.hasLeft = left;
        this.hasRight = right;
        this.hasBottom = bottom;

        return this;
    }

    public BorderBuilder<P> withTop()
    {
        this.hasTop = true;

        return this;
    }

    public BorderBuilder<P> withoutTop()
    {
        this.hasTop = false;

        return this;
    }

    public BorderBuilder<P> withLeft()
    {
        this.hasLeft = true;

        return this;
    }

    public BorderBuilder<P> withoutLeft()
    {
        this.hasLeft = false;

        return this;
    }

    public BorderBuilder<P> withRight()
    {
        this.hasRight = true;

        return this;
    }

    public BorderBuilder<P> withoutRight()
    {
        this.hasRight = false;

        return this;
    }

    public BorderBuilder<P> withBottom()
    {
        this.hasBottom = true;

        return this;
    }

    public BorderBuilder<P> withoutBottom()
    {
        this.hasBottom = false;

        return this;
    }

    /* *************************************************************************************************************
     * Method - Implement NestedBuilder
     ************************************************************************************************************* */

    @Override
    protected BorderBuilder<P> self()
    {
        return this;
    }

    @Override
    public BorderBuilder<P> copy()
    {
        return new BorderBuilder<>( this );
    }

    /* *************************************************************************************************************
     * Method - Implement Builder
     ************************************************************************************************************* */

    @Override
    public Border build()
    {
        return (!this.isEmpty()) ? new Border( this ) : Border.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Implement ColorBuilder
     ************************************************************************************************************* */

    @Override
    public BorderBuilder<P> withColor( Color color )
    {
        this.color = color;

        return this;
    }
}