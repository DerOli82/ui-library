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
package de.alaoli.games.minecraft.mods.lib.ui;

import de.alaoli.games.minecraft.mods.lib.ui.renderer.RendererManager;
import de.alaoli.games.minecraft.mods.lib.ui.screen.ScreenManager;
import de.alaoli.games.minecraft.mods.lib.ui.theme.ThemeManager;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class UI
{
    private UI() {}

    public static void init()
    {
        ThemeManager.init();
        RendererManager.init();
        ScreenManager.register();
    }
}