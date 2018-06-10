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
package de.alaoli.games.minecraft.mods.lib.ui.component;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class Components
{
    private Components() {}

    public static <P> LabelBuilder<P> buildLabel()
    {
        return new LabelBuilder<>();
    }

    public static <P> PaneBuilder<P> buildPane()
    {
        return new PaneBuilder<>();
    }

    public static <P> TextFieldBuilder<P> buildTextField()
    {
        return new TextFieldBuilder<>();
    }

    public static <P> TextAreaBuilder<P> buildTextArea()
    {
        return new TextAreaBuilder<>();
    }

    public static <P> ButtonBuilder<P> buildButton()
    {
        return new ButtonBuilder<>();
    }

    public static <P> ListTextBuilder<P> buildListText()
    {
        return new ListTextBuilder<>();
    }
}