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
import de.alaoli.games.minecraft.mods.lib.ui.element.Text;

/**
 * @param <T> <T> The type of the child class, required for returning child class in all setter methods
 * @param <E> Inherits from {@link Element}&{@link Text} and will be passed on to {@link Drawable}
 */
public interface TextStyle<T extends TextStyle, E extends Element<E> & Text<E>>
    extends Style, ColorStyle<T>, AlignmentStyle<T>, Drawable<E>
{
    /**
     * @return Returns the height of a textline
     */
    int getLineHeight();

    /**
     *
     * @param lineHeight    Sets the height of a textline
     * @return              Returns this object
     */
    T setLineHeight( int lineHeight );

    /**
     * @return Returns if text has a shadow
     */
    boolean hasShadow();

    /**
     * @param shadow    If true, the text has a shadow
     * @return          Returns this object
     */
    T setShadow( boolean shadow );
}
