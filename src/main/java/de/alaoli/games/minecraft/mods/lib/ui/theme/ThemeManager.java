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
import de.alaoli.games.minecraft.mods.lib.ui.theme.minecraft.MinecraftTheme;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public final class ThemeManager
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private static Theme<Builder<?>> defaultTheme;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    public static void init()
    {
        setDefaultTheme( new MinecraftTheme() );
    }

    public static void setDefaultTheme( Theme<Builder<?>> theme )
    {
        defaultTheme = (theme!=null) ? theme : new MinecraftTheme();
        defaultTheme.init();
    }

    public static void applyOn( Builder<?> builder )
    {
        defaultTheme.applyOn( builder );
    }
}