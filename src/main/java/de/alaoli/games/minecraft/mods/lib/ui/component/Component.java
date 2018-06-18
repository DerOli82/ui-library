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

import de.alaoli.games.minecraft.mods.lib.ui.style.Region;
import de.alaoli.games.minecraft.mods.lib.ui.style.RegionBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public interface Component
{
    Optional<Component> getParent();
    void setParent( Component parent );

    boolean isRootComponent();

    Region getRegion();
    Region getRegionAbsolute();

    default void setRegion( RegionBuilder<?> builder )
    {
        this.setRegion( (builder!=null) ? builder.build() : Styles.emptyRegion() );
    }
    void setRegion( Region region );

    default void addComponent( Component... components )
    {
        this.addComponent( Arrays.asList( components ) );
    }
    default void addComponent( List<Component> components )
    {
        components.forEach( this::addComponent );
    }
    void addComponent( Component component );

    default void removeComponent( Component... components )
    {
        this.removeComponent( Arrays.asList( components ) );
    }
    default void removeComponent( List<Component> components )
    {
        components.forEach( this::removeComponent );
    }
    void removeComponent( Component component );

    Stream<Component> streamChildComponents();
}