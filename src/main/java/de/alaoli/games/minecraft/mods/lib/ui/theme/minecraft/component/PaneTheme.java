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
package de.alaoli.games.minecraft.mods.lib.ui.theme.minecraft.component;

import de.alaoli.games.minecraft.mods.lib.ui.component.Pane;
import de.alaoli.games.minecraft.mods.lib.ui.component.PaneBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;
import de.alaoli.games.minecraft.mods.lib.ui.theme.Theme;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Image;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class PaneTheme implements Theme<PaneBuilder<?>>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    /* *************************************************************************************************************
     * Method - Implement Theme
     ************************************************************************************************************* */

    @Override
    public void init()
    {

    }

    @Override
    public void applyOn( PaneBuilder<?> builder )
    {
        builder.withBoxStyle()
            .withState( State.NONE)
                .withBackground().withColor( 0.5f, 0, 0, 0 );
    }
}