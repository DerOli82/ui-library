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
package de.alaoli.games.minecraft.mods.lib.ui.style;

import de.alaoli.games.minecraft.mods.lib.ui.builder.Rebuildable;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.state.Stateable;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;

import javax.annotation.concurrent.Immutable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class TextStyle implements Stateable, Rebuildable<TextStyleBuilder>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    public static final TextStyle EMPTY = new TextStyle();

    @Immutable
    static final class Values implements Rebuildable<TextStyleBuilder.Values>
    {
        /* *************************************************************************************************************
         * Attribute
         ************************************************************************************************************* */

        public static final Values EMPTY = new Values();

        private final Align align;
        private final Color color;
        private final boolean hasShadow;
        private final int lineHeight;

        /* *************************************************************************************************************
         * Method
         ************************************************************************************************************* */
        
        private Values()
        {
            this.align = Align.LEFT;
            this.color = Color.DEFAULT;
            this.hasShadow = false;
            this.lineHeight = 0;
        }

        Values( TextStyleBuilder.Values builder )
        {
            this.align = builder.align;
            this.color = builder.color;
            this.hasShadow = builder.hasShadow;
            this.lineHeight = builder.lineHeight;
        }

        /* *************************************************************************************************************
         * Method - Implement Rebuildable
         ************************************************************************************************************* */

        @Override
        public <P> TextStyleBuilder.Values<P> toBuilder()
        {
            return new TextStyleBuilder.Values<P>()
                .withAlign( this.align )
                .withColor( this.color )
                .withShadow( this.hasShadow )
                .withLineHeight( this.lineHeight );
        }
    }
    final Map<State,Values> states = new HashMap<>();

    private State state = State.NONE;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    private TextStyle()
    {
        this.states.put( State.NONE, Values.EMPTY );
    }

    TextStyle( TextStyleBuilder<?> builder )
    {
        builder.states.forEach( (state,values) -> this.states.put( state, values.build() ) );

        if( !this.states.containsKey( State.NONE ) )
        {
            this.states.put( State.NONE, Values.EMPTY );
        }
    }

    public Align getAlign()
    {
        return this.states.getOrDefault( this.state, this.states.get( State.NONE ) ).align;
    }

    public Color getColor()
    {
        return this.states.getOrDefault( this.state, this.states.get( State.NONE ) ).color;
    }

    public boolean hasShadow()
    {
        return this.states.getOrDefault( this.state, this.states.get( State.NONE ) ).hasShadow;
    }

    public int getLineHeight()
    {
        return this.states.getOrDefault( this.state, this.states.get( State.NONE ) ).lineHeight;
    }

    /* *************************************************************************************************************
     * Method - Implement Stateable
     ************************************************************************************************************* */

    @Override
    public State getState()
    {
        return this.state;
    }

    @Override
    public void setState( State state )
    {
        this.state = (state!=null) ? state : State.NONE;
    }

    /* *************************************************************************************************************
     * Method - Implement Rebuildable
     ************************************************************************************************************* */

    @Override
    public <P> TextStyleBuilder<P> toBuilder()
    {
        TextStyleBuilder<P> builder = new TextStyleBuilder<>();

        this.states.forEach( (state,value) -> builder.states.put( state, value.toBuilder() ) );

        return builder;
    }
}