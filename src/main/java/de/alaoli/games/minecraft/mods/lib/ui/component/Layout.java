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
package de.alaoli.games.minecraft.mods.lib.ui.component;

import java.util.Observable;
import java.util.Observer;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public interface Layout
{
    /**
     * Computes all information needed for drawing and calls {@link #invalidateLayout()}
     * on child elementBuilders which has been changed. Don't call this method directly, it's for
     * internal usage only, instead you should use {@link #validateLayout()}.
     */
    void layout ();

    /**
     * Ensures that the element has been laid out. Calls {@link #layout()} if element is invalid.
     * Should be called by the element before drawing is performed.
     */
    void validateLayout();

    /**
     * Set this layout to invalid causing {@link #layout()} to happen the next time {@link #validateLayout()} is called.
     * Should be called, if the element requires a {@link #layout()} after state has been changed.
     */
    void invalidateLayout();
}