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

import de.alaoli.games.minecraft.mods.lib.ui.component.ButtonBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.component.TextFieldBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.theme.Theme;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Image;
import net.minecraft.util.ResourceLocation;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class ButtonTheme implements Theme<ButtonBuilder<?>>
{
    /* *************************************************************************************************************
     * Method - Implement Theme
     ************************************************************************************************************* */

    @Override
    public void init() {}

    @Override
    public void applyOn( ButtonBuilder<?> builder )
    {
        String texture = "textures/gui/widgets.png";

        builder.withTextStyle()
            .withState( State.NONE )
                .withAlign( Align.CENTER )
                .withColor( Color.Codes.WHITE )
                .withShadow()
                .withLineHeight( 9 )
            .done()
                .withState( State.HOVERED )
                .withAlign( Align.CENTER )
                .withColor( Color.Codes.YELLOW )
                .withShadow()
                .withLineHeight( 9 )
            .done()
                .withState( State.FOCUSED )
                .withAlign( Align.CENTER )
                .withColor( Color.Codes.YELLOW )
                .withShadow()
                .withLineHeight( 9 )
            .done()
                .withState( State.DISABLED )
                .withAlign( Align.CENTER )
                .withColor( Color.Codes.DARKGRAY )
                .withShadow()
                .withLineHeight( 9 )
            .done();

        builder.withBoxStyle()
            .withState( State.NONE )
                .withPadding().withSpacing( 2 ).done()
                .withBackground().withImage( texture, 0, 66, 0.00390625F ).done()
            .done()
            .withState( State.HOVERED )
                .withPadding().withSpacing( 2 ).done()
                .withBackground().withImage( texture, 0, 86, 0.00390625F ).done()
            .done()
                .withState( State.FOCUSED )
                .withPadding().withSpacing( 2 ).done()
                .withBackground().withImage( texture, 0, 86, 0.00390625F ).done()
            .done()
            .withState( State.DISABLED )
                .withPadding().withSpacing( 2 ).done()
                .withBackground().withImage(  texture, 0, 46, 0.00390625F ).done()
            .done();
    }
}