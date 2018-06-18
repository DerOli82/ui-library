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
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import lombok.Getter;

import javax.annotation.concurrent.Immutable;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Immutable
public final class Border implements Rebuildable<BorderBuilder>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    static final Border EMPTY = new Border( Color.DEFAULT, false, false, false, false );

    @Getter
    private final Color color;
    private final boolean hasTop, hasLeft, hasRight, hasBottom;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    private Border( Color color, boolean hasTop, boolean hasLeft, boolean hasRight, boolean hasBottom )
    {
        this.color = Color.valueOrDefault( color );

        this.hasTop = hasTop;
        this.hasLeft = hasLeft;
        this.hasRight = hasRight;
        this.hasBottom = hasBottom;
    }

    Border( BorderBuilder<?> builder )
    {
        this( builder.color, builder.hasTop, builder.hasLeft, builder.hasRight, builder.hasBottom );
    }

    public boolean isEmpty()
    {
        return this == EMPTY;
    }

    public boolean hasTop()
    {
        return this.hasTop;
    }

    public boolean hasLeft()
    {
        return this.hasLeft;
    }

    public boolean hasRight()
    {
        return this.hasRight;
    }

    public boolean hasBottom()
    {
        return this.hasBottom;
    }

    /* *************************************************************************************************************
     * Method - Implement Rebuildable
     ************************************************************************************************************* */

    @Override
    public <P> BorderBuilder<P> toBuilder()
    {
        return Styles.<P>newBorderBuilder()
            .withColor( this.color )
            .withBorder( this.hasTop, this.hasLeft, this.hasRight, this.hasBottom );
    }
}