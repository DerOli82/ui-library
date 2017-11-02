/* *************************************************************************************************************
 * Copyright (c) 2017 DerOli82 <https://github.com/DerOli82>
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
 ************************************************************************************************************ */
package de.alaoli.games.minecraft.mods.lib.ui.element.style;

import de.alaoli.games.minecraft.mods.lib.ui.element.state.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 * @param <T> The type of the child class, required for returning child class in add, remove and set methods
 * @param <S> The type of the stateable styling class
 */
public abstract class StateableStyle<T extends StateableStyle, S extends Style> implements Style
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private final Map<State, S> states = new HashMap<>();

    private State currentState = State.NONE;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public StateableStyle( S fallback )
    {
        this.add( State.NONE, fallback );
    }

    public T add( State state, S style )
    {
        this.states.put( state, style );

        return (T)this;
    }

    /**
     * Note: You cannot remove {@link State#NONE} state, because it must be remain as fallback state.
     *
     * @throws IllegalArgumentException, if you try to remove the fallback {@link State#NONE} state.
     * @param state The state you want to remove
     * @return      Returns this object
     *
     */
    public T remove( State state ) throws IllegalArgumentException
    {
        if( state == State.NONE ) { throw new IllegalArgumentException( "Cannot remove 'none' state, because it must be remain as fallback state" );}

        this.states.remove( state );

        return (T)this;
    }

    public boolean exists( State state )
    {
        return this.states.containsKey( state );
    }

    public Optional<S> get()
    {
        return this.get( this.currentState );
    }

    public Optional<S> get( State state )
    {
        if( this.states.containsKey( state  ) )
        {
            return Optional.ofNullable( this.states.get( state ) );
        }
        else if ( this.states.containsKey( State.NONE ) )   //Fallback
        {
            return Optional.ofNullable( this.states.get( State.NONE ) );
        }
        else
        {
            return Optional.empty();
        }
    }

    public T setState( State state )
    {
        if( state == null )
        {
            this.currentState = State.NONE;
        }
        else
        {
            this.currentState = state;
        }
        return (T)this;
    }
}
