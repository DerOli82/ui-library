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
package de.alaoli.games.minecraft.mods.lib.ui.element;

import de.alaoli.games.minecraft.mods.lib.ui.style.Region;
import de.alaoli.games.minecraft.mods.lib.ui.style.RegionTransformable;
import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;

import java.util.Optional;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public abstract class Element implements Layout
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private Element parent;
    private Region region;

    private boolean needsLayout = true;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    Element( ElementBuilder builder )
    {
        this.region = (builder.region!=null) ? builder.region.build() : Styles.createRegionDefault();
    }

    /**
     * @return Optionally returning the parent {@link Element}
     */
    public Optional<Element> getParent()
    {
        return Optional.ofNullable( this.parent );
    }

    void setParent( Element parent )
    {
        this.parent = parent;
    }

    /**
     * @return Returns the {@link Region} of this {@link Element}
     */
    public Region getRegion()
    {
        return this.region;
    }

    public RegionTransformable transformRegion()
    {
        this.invalidateLayout();

        return this.region.transform();
    }

    public RegionTransformable transformRegion( boolean fromOrigin )
    {
        return this.region.transform( fromOrigin );
    }

    /**
     * Drawing this {@link Element} within the given {@link Region}, if the region has no dimension,
     * drawing will be skipped.
     *
     * @param mouseX		X-Region of the mouse, only needed for wrapped Minecraft GUI elementFactory
     * @param mouseY		Y-Region of the mouse, only needed for wrapped Minecraft GUI elementFactory
     * @param partialTicks	The amount of time, in fractions of a tick, that has passed since the last full tick.
     */
    public void drawElement( int mouseX, int mouseY, float partialTicks )
    {
        this.validateLayout();

        Region region = this.getRegion();

        if( region.isEmpty() ) { return; }

        this.drawElementAt( region.getX(), region.getY(), region.getWidth(), region.getHeight(), mouseX, mouseY, partialTicks );
    }

    /**
     * Drawing this element at the given location and dimension, if it has no dimension,
     * drawing will be skipped.
     *
     * @param x				Drawing x-position
     * @param y				Drawing y-position
     * @param width			Drawing width
     * @param height		Drawing height
     * @param mouseX		X-Region of the mouse, only needed for wrapped Minecraft GUI elementFactory
     * @param mouseY		Y-Region of the mouse, only needed for wrapped Minecraft GUI elementFactory
     * @param partialTicks	The amount of time, in fractions of a tick, that has passed since the last full tick.
     */
    protected abstract void drawElementAt( int x, int y, int width, int height, int mouseX, int mouseY, float partialTicks );

    /* **************************************************************************************************************
     * Method - Implement Layout
     ************************************************************************************************************** */

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