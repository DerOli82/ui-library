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
package de.alaoli.games.minecraft.mods.lib.ui.component;

import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyleBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.util.Text;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public interface TextComponent extends Component
{
    Text getText();
    TextStyle getTextStyle();

    default void setTextStyle( TextStyleBuilder<?> builder )
    {
        this.setTextStyle( (builder!=null) ? builder.build() : Styles.emptyTextStyle() );
    }
    void setTextStyle( TextStyle textStyle );
}