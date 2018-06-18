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
import de.alaoli.games.minecraft.mods.lib.ui.util.Image;
import lombok.Getter;

import javax.annotation.concurrent.Immutable;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Getter
@Immutable
public final class Background implements Rebuildable<BackgroundBuilder>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    static final Background EMPTY = new Background( Color.DEFAULT, Image.EMPTY );

    private final Color color;
    private final Image image;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    private Background(Color color, Image image )
    {
        this.color = Color.valueOrDefault( color );
        this.image = Image.valueOrEmpty( image );
    }

    Background( BackgroundBuilder<?> builder )
    {
        this( builder.color, builder.image );
    }

    public boolean isEmpty()
    {
        return this == EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Implement Rebuildable
     ************************************************************************************************************* */

    @Override
    public <P> BackgroundBuilder<P> toBuilder()
    {
        return Styles.<P>newBackgroundBuilder().withColor( this.color ).withImage( this.image );
    }
}