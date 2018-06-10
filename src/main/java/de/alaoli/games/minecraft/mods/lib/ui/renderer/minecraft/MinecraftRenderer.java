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
package de.alaoli.games.minecraft.mods.lib.ui.renderer.minecraft;

import de.alaoli.games.minecraft.mods.lib.ui.component.*;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.AbstractScreenRenderer;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.minecraft.component.*;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class MinecraftRenderer extends AbstractScreenRenderer
{
    /* *************************************************************************************************************
     * Method - Implement Renderer
     ************************************************************************************************************* */

    @Override
    public void init()
    {
        this.addComponentRenderer( Label.class, new LabelRenderer() );
        this.addComponentRenderer( TextField.class, new TextFieldRenderer() );
        this.addComponentRenderer( TextArea.class, new TextAreaRenderer() );
        this.addComponentRenderer( Pane.class, new PaneRenderer() );
        this.addComponentRenderer( Button.class, new ButtonRenderer());
        this.addComponentRenderer( ListText.class, new ListTextRenderer() );
    }
}