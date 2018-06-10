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
package de.alaoli.games.minecraft.mods.lib.ui.renderer.minecraft.component;

import de.alaoli.games.minecraft.mods.lib.ui.component.Label;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.Context;
import net.minecraft.client.gui.FontRenderer;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class LabelRenderer implements TextRenderer<Label>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private FontRenderer fontRenderer;

    /* *************************************************************************************************************
     * Method - Implement TextRenderer
     ************************************************************************************************************* */

    @Override
    public FontRenderer getFontRenderer()
    {
        return this.fontRenderer;
    }

    /* *************************************************************************************************************
     * Method - Implement Renderer
     ************************************************************************************************************* */

    @Override
    public void init()
    {
        this.fontRenderer = TextRenderer.super.getFontRenderer();
    }

    @Override
    public void render( Label component, Context context )
    {
        TextRenderer.super.render( component, context );
    }
}