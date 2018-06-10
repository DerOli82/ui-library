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

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class Styles
{
    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    private Styles() {}

    public static <P> BackgroundBuilder<P> newBackgroundBuilder()
    {
        return new BackgroundBuilder<>();
    }

    public static <P> BorderBuilder<P> newBorderBuilder()
    {
        return new BorderBuilder<>();
    }

    public static <P> BoxStyleBuilder<P> newBoxStyleBuilder()
    {
        return new BoxStyleBuilder<>();
    }

    public static <P> RegionBuilder<P> newRegionBuilder()
    {
        return new RegionBuilder<>();
    }

    public static <P> SpacingBuilder<P> newSpacingBuilder()
    {
        return new SpacingBuilder<>();
    }

    public static <P> TextStyleBuilder<P> newTextStyleBuilder()
    {
        return new TextStyleBuilder<>();
    }
}