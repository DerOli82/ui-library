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
package de.alaoli.games.minecraft.mods.lib.ui.renderer;

import de.alaoli.games.minecraft.mods.lib.ui.renderer.minecraft.MinecraftRenderer;
import de.alaoli.games.minecraft.mods.lib.ui.screen.Screen;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class RendererManager implements Renderer<Screen>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    public static final RendererManager INSTANCE = new RendererManager();

    private Renderer<Screen> defaultRenderer = new MinecraftRenderer();

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    private RendererManager()
    {
        this.init();
    }

    public static void setDefaultRenderer( Renderer defaultRenderer )
    {
        INSTANCE.defaultRenderer = (defaultRenderer!=INSTANCE) ? defaultRenderer : new MinecraftRenderer();
        INSTANCE.init();
    }
    /* *************************************************************************************************************
     * Method - Implement Renderer
     ************************************************************************************************************* */

    @Override
    public void init()
    {
        this.defaultRenderer.init();
    }

    @Override
    public void render( Screen screen, Context context )
    {
        this.defaultRenderer.render( screen, context );
    }
}