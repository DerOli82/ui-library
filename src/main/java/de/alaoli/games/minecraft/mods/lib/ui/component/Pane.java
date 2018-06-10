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

import de.alaoli.games.minecraft.mods.lib.ui.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.style.Region;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class Pane extends AbstractComponent implements BoxComponent, Layout
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private BoxStyle boxStyle;
    private boolean needsLayout = true;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    Pane( PaneBuilder<?> builder )
    {
        super( builder );
        this.boxStyle = (builder.boxStyleBuilder!=null) ? builder.boxStyleBuilder.build() : BoxStyle.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Implement RegionListener
     ************************************************************************************************************* */

    @Override
    public void onRegionChanged( Region oldRegion, Region newRegion )
    {
        super.onRegionChanged( oldRegion, newRegion );

        this.invalidateLayout();
    }

    /* *************************************************************************************************************
     * Method - Implement BoxComponent
     ************************************************************************************************************* */

    @Override
    public BoxStyle getBoxStyle()
    {
        return this.boxStyle;
    }

    @Override
    public void setBoxStyle( BoxStyle boxStyle )
    {
        this.boxStyle = (boxStyle!=null) ? boxStyle : BoxStyle.EMPTY;
    }

    /* *************************************************************************************************************
     * Method - Implement Layout
     ************************************************************************************************************* */

    @Override
    public void layout()
    {

    }

    @Override
    public void validateLayout()
    {
        if( this.needsLayout )
        {
            this.layout();
            this.needsLayout = false;
        }
    }

    @Override
    public void invalidateLayout()
    {
        this.needsLayout = true;
    }
}