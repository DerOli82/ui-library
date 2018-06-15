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
package de.alaoli.games.minecraft.mods.lib.ui.theme;

import de.alaoli.games.minecraft.mods.lib.ui.builder.Builder;

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

    private final Map<Class<? extends Builder>, ThemeComponent<? extends Builder>> themeComponents = new HashMap<>();

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    @SuppressWarnings( "unchecked" )
    private void applyOnComponent( B builder )
    {
        Class<? extends Builder> clazz = builder.getClass();

        ((ThemeComponent<B>)this.themeComponents.get( clazz )).applyOn( builder );
    }

    protected void addThemeComponent(Class<? extends Builder> clazz, ThemeComponent<? extends Builder> theme )
    {
        this.themeComponents.put( clazz, theme );
    }

    /* **************************************************************************************************************
     * Method - Implement Theme
     ************************************************************************************************************** */

    @Override
    public void applyOn( B builder )
    {
        this.applyOnComponent( builder );
    }
}