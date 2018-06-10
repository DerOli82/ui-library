/**************************************************************************************************************
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
 *************************************************************************************************************/

package de.alaoli.games.minecraft.mods.lib.ui.renderer;

import de.alaoli.games.minecraft.mods.lib.ui.component.Component;
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

    private final Map<Class<? extends Component>, Renderer<? extends Component>> componentRenderers = new HashMap<>();

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    @SuppressWarnings( "unchecked" )
    private <C extends Component> void renderComponent( C component, Context context )
    {
        Class<? extends Component> compClass = component.getClass();

        if( this.componentRenderers.containsKey( compClass ) )
        {
            ((Renderer<C>)this.componentRenderers.get( compClass )).render( component, context );
        }
        component.streamChildComponents().forEach( childComponent -> this.renderComponent( childComponent, context ) );
    }

    protected  <C extends Component> void addComponentRenderer( Class<C> componentClass, Renderer<C> renderer )
    {
        this.componentRenderers.put( componentClass, renderer );

        renderer.init();
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