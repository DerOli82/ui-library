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
package de.alaoli.games.minecraft.mods.lib.ui.theme.minecraft.component;

import de.alaoli.games.minecraft.mods.lib.ui.component.LabelBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.component.ListTextBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.theme.Theme;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class ListTextTheme implements Theme<ListTextBuilder<?>>
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
    public void applyOn( ListTextBuilder<?> builder )
    {
        builder.withTextStyle()
            .withState( State.NONE )
                .withAlign( Align.LEFT )
                .withColor( Color.Codes.WHITE )
                .withoutShadow()
                .withLineHeight( 9 );
    }
}