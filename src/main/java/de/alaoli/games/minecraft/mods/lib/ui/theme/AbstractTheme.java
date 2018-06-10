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
package de.alaoli.games.minecraft.mods.lib.ui.theme;

import de.alaoli.games.minecraft.mods.lib.ui.builder.Builder;
import de.alaoli.games.minecraft.mods.lib.ui.component.Component;
import de.alaoli.games.minecraft.mods.lib.ui.component.Label;
import de.alaoli.games.minecraft.mods.lib.ui.theme.minecraft.component.LabelTheme;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public abstract class AbstractTheme<B extends Builder<?>> implements Theme<B>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private final Map<Class<? extends Builder>, Theme<? extends Builder>> componentThemes = new HashMap<>();

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    @SuppressWarnings( "unchecked" )
    private <B extends Builder<?>> void applyOnComponent( B builder )
    {
        Class<? extends Builder> clazz = builder.getClass();

        ((Theme<B>)this.componentThemes.get( clazz )).applyOn( builder );
    }

    protected <B extends Builder<?>> void addComponentTheme( Class<? extends Builder> clazz, Theme<B> theme )
    {
        this.componentThemes.put( clazz, theme );

        theme.init();
    }

    /* **************************************************************************************************************
     * Method - Implement Theme
     ************************************************************************************************************** */

    @Override
    public void applyOn( B component )
    {
        this.applyOnComponent( component );
    }
}