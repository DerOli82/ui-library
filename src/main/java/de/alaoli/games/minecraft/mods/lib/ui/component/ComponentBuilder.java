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

import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.style.RegionBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;
import de.alaoli.games.minecraft.mods.lib.ui.theme.ThemeManager;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
abstract class ComponentBuilder<P,S extends ComponentBuilder<P,S,B>,B> extends NestedBuilder<P,S,B>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    RegionBuilder<S> regionBuilder;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    ComponentBuilder()
    {
        ThemeManager.applyOn( this );
    }

    ComponentBuilder( S builder )
    {
        this.regionBuilder = (builder.regionBuilder!=null) ? builder.regionBuilder.copy() : null;
    }

    public RegionBuilder<S> withRegion()
    {
        if( this.regionBuilder == null )
        {
            this.regionBuilder = Styles.<S>newRegionBuilder().withParentBuilder( this.self() );
        }
        return this.regionBuilder;
    }
}