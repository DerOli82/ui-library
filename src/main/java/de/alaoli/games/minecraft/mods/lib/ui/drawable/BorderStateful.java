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
package de.alaoli.games.minecraft.mods.lib.ui.drawable;

import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.state.Stateable;
import de.alaoli.games.minecraft.mods.lib.ui.drawable.BorderStateless.BorderStatelessBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class BorderStateful implements Border, Stateable
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private State state;
    private Border currentBorder;

    private final Map<State, BorderStateless> states = new HashMap<>();

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    private BorderStateful(Map<State, BorderStateless> states )
    {
        this.states.putAll( states );
    }

    /* **************************************************************************************************************
     * Method - Implement Stateable
     ************************************************************************************************************** */

    @Override
    public State getState()
    {
        return this.state;
    }

    @Override
    public void setState( State state )
    {
        this.state = (state!=null) ? state : State.NONE;
        this.currentBorder = this.states.get( this.state );
    }

    /* **************************************************************************************************************
     * Method - Implement Drawable
     ************************************************************************************************************** */

    @Override
    public void drawAt( int x, int y, int width, int height )
    {
        if( this.currentBorder != null ) { this.currentBorder.drawAt( x, y, width, height );}
    }

    /* **************************************************************************************************************
     * RegionBuilder
     ************************************************************************************************************** */

    public static final class BorderStatefulBuilder<P> extends NestedBuilder<P, Border>
    {
        /* **************************************************************************************************************
         * Attribute
         ************************************************************************************************************** */

        private Map<State, BorderStatelessBuilder<P>> states = new HashMap<>();

        /* **************************************************************************************************************
         * Method
         ************************************************************************************************************** */

        public BorderStatelessBuilder<P> addState(State state )
        {
            if( !this.states.containsKey( state ) )
            {
                BorderStatelessBuilder<P> builder = new BorderStatelessBuilder<>();
                this.states.put( state, builder );
            }
            return this.states.get( state );
        }

        /**
         * @return True, if it has more than one state or state isn't NONE
         */
        private boolean hasMultipleStates()
        {
            return ( this.states.size() > 1 ) || ( this.states.size() == 1 && !this.states.containsKey( State.NONE ) );
        }

        /* **************************************************************************************************************
         * Method - Implement Builder<Drawable>
         ************************************************************************************************************** */

        /**
         * @return Returns BorderStateful, if there're multiple states (incl NONE == null),
         *         BorderStateless, if there is only the NONE state or null, if there isn't any state
         */
        @Override
        public Border build()
        {
            Border border;

            if( this.hasMultipleStates() )
            {
                Map<State, BorderStateless> states = new HashMap<>();

                this.states.forEach( (state,value) -> states.put( state, (BorderStateless)value.build()) );
                border = new BorderStateful( states );
            }
            else
            {
                border = ( this.states.containsKey( State.NONE ) ) ? this.states.get( State.NONE ).build() : (Border) DrawableNone.DEFAULT;
            }
            return border;
        }
    }
}