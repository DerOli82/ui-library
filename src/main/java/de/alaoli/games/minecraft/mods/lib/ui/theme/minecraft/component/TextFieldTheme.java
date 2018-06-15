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

import de.alaoli.games.minecraft.mods.lib.ui.component.TextFieldBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.theme.ThemeComponent;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class TextFieldTheme implements ThemeComponent<TextFieldBuilder<?>>
{
    /* *************************************************************************************************************
     * Method - Implement Theme
     ************************************************************************************************************* */

    @Override
    public void applyOn( TextFieldBuilder<?> builder )
    {
        builder.withTextStyle()
            .withState( State.NONE )
                .withAlign( Align.LEFT )
                .withColor( Color.Codes.WHITE )
                .withoutShadow()
                .withLineHeight( 9 )
            .done()
                .withState( State.DISABLED )
                .withAlign( Align.LEFT )
                .withColor( Color.Codes.DARKGRAY )
                .withoutShadow()
                .withLineHeight( 9 )
            .done();

        builder.withBoxStyle()
            .withState( State.NONE )
                .withPadding().withSpacing( 2 ).done()
                .withBackground().withColor( Color.Codes.BLACK ).done()
                .withBorder().withBorder().withColor( Color.Codes.GRAY ).done()
            .done()
            .withState( State.HOVERED )
                .withPadding().withSpacing( 2 ).done()
                .withBackground().withColor( Color.Codes.BLACK ).done()
                .withBorder().withBorder().withColor( Color.Codes.WHITE ).done()
            .done()
                .withState( State.FOCUSED )
                .withPadding().withSpacing( 2 ).done()
                .withBackground().withColor( Color.Codes.BLACK ).done()
                .withBorder().withBorder().withColor( Color.Codes.WHITE ).done()
            .done()
            .withState( State.DISABLED )
                .withPadding().withSpacing( 2 ).done()
                .withBackground().withColor( Color.Codes.BLACK ).done()
                .withBorder().withBorder().withColor( Color.Codes.DARKGRAY ).done()
            .done();
    }
}