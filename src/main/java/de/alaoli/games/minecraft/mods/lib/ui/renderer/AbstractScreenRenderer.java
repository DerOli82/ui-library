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
package de.alaoli.games.minecraft.mods.lib.ui.renderer;

import de.alaoli.games.minecraft.mods.lib.ui.component.Component;
import de.alaoli.games.minecraft.mods.lib.ui.component.Layout;
import de.alaoli.games.minecraft.mods.lib.ui.screen.Screen;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public abstract class AbstractScreenRenderer implements Renderer<Screen>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private final Map<Class<? extends Component>, RendererComponent<? extends Component>> rendererComponents = new HashMap<>();

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    @SuppressWarnings( "unchecked" )
    private <C extends Component> void renderComponent( C component, Context context )
    {
        Class<? extends Component> compClass = component.getClass();

        if( this.rendererComponents.containsKey( compClass ) )
        {
            if( component instanceof Layout ) { ((Layout)component).validateLayout(); }

            ((RendererComponent<C>)this.rendererComponents.get( compClass )).render( component, context );
        }
        component.streamChildComponents().forEach( childComponent -> this.renderComponent( childComponent, context ) );
    }

    protected  <C extends Component> void addComponentRenderer( Class<C> componentClass, RendererComponent<C> renderer )
    {
        this.rendererComponents.put( componentClass, renderer );
    }

    /* *************************************************************************************************************
     * Method - Implement Renderer
     ************************************************************************************************************* */

    @Override
    public void render( Screen screen, Context context )
    {
        screen.streamChildComponents().forEach( component -> this.renderComponent( component, context ) );
    }
}