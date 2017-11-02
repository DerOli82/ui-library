/* *************************************************************************************************************
 * Copyright (c) 2017 DerOli82 <https://github.com/DerOli82>
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
 ************************************************************************************************************ */
package de.alaoli.games.minecraft.mods.lib.ui.element.style;

import de.alaoli.games.minecraft.mods.lib.ui.drawable.Drawable;
import de.alaoli.games.minecraft.mods.lib.ui.element.Element;

import java.util.Optional;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 * @param <T> <T> The type of the child class, required for returning child class in all setter methods
 * @param <E> Inherits from {@link Element} and will be passed on to {@link Drawable}
 */
public interface BoxStyle<T extends BoxStyle, E extends Element>
    extends Style, AlignmentStyle<T>, Drawable<E>
{
    Optional<Drawable> getBackground();
    T setBackground( Drawable background );

    Optional<Drawable> getBorder();
    T setBorder( Drawable border );

    /**
     * @throws IllegalArgumentException, if margin value is negative
     * @param margin    Spacing between this element and parent element
     * @return          Returns this object
     */
    T setMargin( int margin ) throws IllegalArgumentException;
    T setMargin( int top, int left, int right, int bottom ) throws IllegalArgumentException;

    /**
     * @throws IllegalArgumentException, if padding value is negative
     * @param padding    Spacing between this element and child element
     * @return          Returns this object
     */
    T setPadding( int padding ) throws IllegalArgumentException;
    T setPadding( int top, int left, int right, int bottom ) throws IllegalArgumentException;
}
