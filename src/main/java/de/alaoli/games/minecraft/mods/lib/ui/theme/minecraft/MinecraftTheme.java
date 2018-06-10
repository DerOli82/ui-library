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
package de.alaoli.games.minecraft.mods.lib.ui.theme.minecraft;

import de.alaoli.games.minecraft.mods.lib.ui.builder.Builder;
import de.alaoli.games.minecraft.mods.lib.ui.component.*;
import de.alaoli.games.minecraft.mods.lib.ui.theme.AbstractTheme;
import de.alaoli.games.minecraft.mods.lib.ui.theme.minecraft.component.*;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class MinecraftTheme extends AbstractTheme<Builder<?>>
{
    /* **************************************************************************************************************
     * Method - Implement Theme
     ************************************************************************************************************** */

    @Override
    public void init()
    {
        this.addComponentTheme( LabelBuilder.class, new LabelTheme() );
        this.addComponentTheme( TextFieldBuilder.class, new TextFieldTheme() );
        this.addComponentTheme( TextAreaBuilder.class, new TextAreaTheme() );
        this.addComponentTheme( PaneBuilder.class, new PaneTheme() );
        this.addComponentTheme( ButtonBuilder.class, new ButtonTheme() );
        this.addComponentTheme( ListTextBuilder.class, new ListTextTheme() );
    }
}