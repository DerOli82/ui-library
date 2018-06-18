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

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class Styles
{
    private Styles() {}

    /* *************************************************************************************************************
     * Method - Background
     ************************************************************************************************************* */

    public static <P> BackgroundBuilder<P> newBackgroundBuilder()
    {
        return new BackgroundBuilder<>();
    }

    public static Background valueOrEmpty( Background background )
    {
        return (background!=null) ? background : Background.EMPTY;
    }

    public static Background emptyBackground()
    {
        return Background.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Border
     ************************************************************************************************************* */

    public static <P> BorderBuilder<P> newBorderBuilder()
    {
        return new BorderBuilder<>();
    }

    public static Border valueOrEmpty( Border border )
    {
        return (border!=null) ? border : Border.EMPTY;
    }

    public static Border emptyBorder()
    {
        return Border.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - BoxStyle
     ************************************************************************************************************* */

    public static <P> BoxStyleBuilder<P> newBoxStyleBuilder()
    {
        return new BoxStyleBuilder<>();
    }

    public static BoxStyle valueOrEmpty( BoxStyle boxStyle )
    {
        return (boxStyle!=null) ? boxStyle : BoxStyle.EMPTY;
    }

    public static BoxStyle emptyBoxStyle()
    {
        return BoxStyle.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Region
     ************************************************************************************************************* */

    public static <P> RegionBuilder<P> newRegionBuilder()
    {
        return new RegionBuilder<>();
    }

    public static Region valueOrEmpty( Region region )
    {
        return (region!=null) ? region : Region.EMPTY;
    }

    public static Region emptyRegion()
    {
        return Region.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Spacing
     ************************************************************************************************************* */

    public static <P> SpacingBuilder<P> newSpacingBuilder()
    {
        return new SpacingBuilder<>();
    }

    public static Spacing valueOrEmpty( Spacing spacing )
    {
        return (spacing!=null) ? spacing : Spacing.EMPTY;
    }

    public static Spacing emptySpacing()
    {
        return Spacing.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - TextStyle
     ************************************************************************************************************* */

    public static <P> TextStyleBuilder<P> newTextStyleBuilder()
    {
        return new TextStyleBuilder<>();
    }

    public static TextStyle valueOrEmpty( TextStyle textStyle )
    {
        return (textStyle!=null) ? textStyle : TextStyle.EMPTY;
    }

    public static TextStyle emptyTextStyle()
    {
        return TextStyle.EMPTY;
    }
}