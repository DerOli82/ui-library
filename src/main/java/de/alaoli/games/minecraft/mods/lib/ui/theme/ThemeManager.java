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
import de.alaoli.games.minecraft.mods.lib.ui.theme.minecraft.MinecraftTheme;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class ThemeManager implements Theme<Builder<?>>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    public static final ThemeManager INSTANCE = new ThemeManager();

    private Theme<Builder<?>> defaultTheme = new MinecraftTheme();

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    private ThemeManager() {}

    public static void setDefaultTheme( Theme<Builder<?>> defaultTheme )
    {
        INSTANCE.defaultTheme = (defaultTheme!=INSTANCE) ? defaultTheme : new MinecraftTheme();
    }

    public static void applyOnComponent( Builder<?> component )
    {
        INSTANCE.applyOn( component );
    }

    /* *************************************************************************************************************
     * Method - Implement Theme
     ************************************************************************************************************* */

    @Override
    public void init()
    {
        this.defaultTheme.init();
    }

    @Override
    public void applyOn( Builder<?> component )
    {
        this.defaultTheme.applyOn( component );
    }
}