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
package de.alaoli.games.minecraft.mods.lib.ui.style;

import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.state.Stateable;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyleStateless.TextStyleStatelessBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class TextStyleStateful implements TextStyle, Stateable
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private State state;
    private TextStyleStateless currentStyle;

    private final Map<State, TextStyleStateless> states = new HashMap<>();

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    private TextStyleStateful(Map<State, TextStyleStateless> states )
    {
        this.states.putAll( states );
    }

    private TextStyleStateless getDefault()
    {
        if( !this.states.containsKey( State.NONE ) )
        {
            this.states.put( State.NONE, TextStyleStateless.DEFAULT );
        }
        return this.states.get( State.NONE );
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
        this.currentStyle = this.states.getOrDefault( this.state, this.getDefault() );
    }

    /* **************************************************************************************************************
     * Method - Implement TextStyle
     ************************************************************************************************************** */

    @Override
    public Align getAlign()
    {
        return this.currentStyle.getAlign();
    }

    @Override
    public Color getColor()
    {
        return this.currentStyle.getColor();
    }

    @Override
    public boolean hasShadow()
    {
        return this.currentStyle.hasShadow();
    }

    @Override
    public int getLineHeight()
    {
        return this.currentStyle.getLineHeight();
    }

    /* **************************************************************************************************************
     * RegionBuilder
     ************************************************************************************************************** */


    public static final class TextStyleStatefulBuilder<P> extends NestedBuilder<P, TextStyle>
    {
        /* **************************************************************************************************************
         * Attribute
         ************************************************************************************************************** */

        private final Map<State, TextStyleStatelessBuilder<P>> states = new HashMap<>();

        /* **************************************************************************************************************
         * Method
         ************************************************************************************************************** */

        @SuppressWarnings("unchecked")
        public TextStyleStatelessBuilder<P> addState(State state )
        {
            if( !this.states.containsKey( state ) )
            {
                TextStyleStatelessBuilder<P> builder = new TextStyleStatelessBuilder<>();
                builder.withParentBuilder( (P) this );

                this.states.put( state, builder );
            }
            return this.states.get( state );
        }

        /**
         * @return True, if it has more than one state or state isn't NONE
         */
        private boolean hasMultipleStates()
        {
            return ( ( this.states.size() > 1 ) || ( this.states.size() == 1 && !this.states.containsKey( State.NONE ) ) );
        }

        /* **************************************************************************************************************
         * Method - Implement Builder<TextStyle>
         ************************************************************************************************************** */

        /**
         * @return  Returns TextStyleStateful, if there're multiple states or
         *          TextStyleStateless if there is only the NONE state.
         */
        @Override
        public TextStyle build()
        {
            TextStyle textStyle;

            if( this.hasMultipleStates() )
            {
                Map<State, TextStyleStateless> states = new HashMap<>();

                this.states.forEach( (state, style) -> states.put( state, (TextStyleStateless)style.build() ) );

                //Check if default state exists
                if ( !states.containsKey(State.NONE) )
                {
                    states.put( State.NONE, TextStyleStateless.DEFAULT );
                }
                textStyle = new TextStyleStateful( states );
            }
            else
            {
                if ( states.containsKey (State.NONE ) )
                {
                    textStyle = this.states.get( State.NONE ).build();
                }
                else
                    {
                    textStyle = TextStyleStateless.DEFAULT;
                }
            }
            return textStyle;
        }
    }
}