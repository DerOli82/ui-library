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
package de.alaoli.games.minecraft.mods.lib.ui.component;

import de.alaoli.games.minecraft.mods.lib.ui.event.Listener;
import de.alaoli.games.minecraft.mods.lib.ui.style.Margin;
import de.alaoli.games.minecraft.mods.lib.ui.style.Region;
import de.alaoli.games.minecraft.mods.lib.ui.style.RegionListener;
import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
abstract class AbstractComponent implements Component, RegionListener
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private Component parent;

    private Region region = Styles.emptyRegion();
    private Region regionAbsolute;

    private final List<Component> childComponents = new ArrayList<>();
    private final List<Listener> listeners = new ArrayList<>();

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    AbstractComponent( ComponentBuilder<?,?,?> builder )
    {
        this.setRegion( builder.regionBuilder );
    }

    public void addListener( Listener listener )
    {
        this.listeners.add( listener );
    }

    public void removeListener( Listener listener )
    {
        this.listeners.remove( listener );
    }

    public void clearListeners()
    {
        this.listeners.clear();
    }

    private void notifyRegionChanged( Region oldRegion, Region newRegion )
    {
        for( Component parent = this.parent; parent != null; parent = parent.getParent().orElse(null) )
        {
            if( parent instanceof RegionListener )
            {
                ((RegionListener)parent).onRegionChanged( oldRegion, newRegion );
            }
        }
        this.childComponents.stream()
            .filter( component -> component instanceof RegionListener )
            .forEach( component -> ((RegionListener)component).onRegionChanged( oldRegion, newRegion ) );

        this.listeners.stream()
            .filter( component -> component instanceof RegionListener )
            .forEach( listener -> ((RegionListener)listener).onRegionChanged( oldRegion, newRegion ) );

        this.regionAbsolute = null;
    }

    Stream<Listener> streamListeners()
    {
        return this.listeners.stream();
    }

    /* *************************************************************************************************************
     * Method - Implement Component
     ************************************************************************************************************* */

    @Override
    public Optional<Component> getParent()
    {
        return Optional.ofNullable( this.parent );
    }

    @Override
    public void setParent( Component parent )
    {
        if( parent == this ) { return; }

        this.parent = parent;
    }

    @Override
    public boolean isRootComponent()
    {
        return this.parent == null;
    }

    @Override
    public Region getRegion()
    {
        return this.region;
    }

    @Override
    public void setRegion( Region region )
    {
        Region oldRegion = this.region;
        this.region = Styles.valueOrEmpty( region );

        this.notifyRegionChanged( oldRegion, this.region );
    }

    @Override
    public Region getRegionAbsolute()
    {
        int x, y, width, height;

        if (this.regionAbsolute == null )
        {
            this.regionAbsolute = this.region;

            for( Component parent = this.parent; parent != null; parent = parent.getParent().orElse(null) )
            {
                x = parent.getRegion().getX();
                y = parent.getRegion().getY();
                width = 0;
                height = 0;

                if( parent instanceof BoxComponent )
                {
                    Margin margin = ((BoxComponent)parent).getBoxStyle().getMargin();

                    x += margin.getLeft();
                    y += margin.getTop();
                    width -= margin.getRight();
                    height -= margin.getBottom();
                }
                this.regionAbsolute = this.regionAbsolute.toBuilder()
                    .translate( x, y )
                    .shrink( width, height )
                .build();
            }
        }
        return this.regionAbsolute;
    }

    @Override
    public void addComponent( Component component )
    {
        if( component == this ) { return; }

        component.setParent( this );

        this.childComponents.add( component );
    }

    @Override
    public void removeComponent( Component component )
    {
        component.setParent( null );

        this.childComponents.remove( component );
    }

    @Override
    public Stream<Component> streamChildComponents()
    {
        return this.childComponents.stream();
    }

    /* *************************************************************************************************************
     * Method - Implement RegionListener
     ************************************************************************************************************* */

    @Override
    public void onRegionChanged( Region oldRegion, Region newRegion )
    {
        this.regionAbsolute = null;
    }
}