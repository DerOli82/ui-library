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

import de.alaoli.games.minecraft.mods.lib.ui.builder.Builder;
import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class BoxStyleBuilder<P> extends NestedBuilder<P,BoxStyleBuilder<P>,BoxStyle>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    public static final class Values<P> extends NestedBuilder<P,Values<P>,BoxStyle.Values>
    {
        /* *************************************************************************************************************
         * Attribute
         ************************************************************************************************************* */

        SpacingBuilder<Values<P>> margin;
        SpacingBuilder<Values<P>> padding;

        BackgroundBuilder<Values<P>> background;
        BorderBuilder<Values<P>> border;

        /* *************************************************************************************************************
         * Method
         ************************************************************************************************************* */

        private Values()
        {
            this.margin = new SpacingBuilder<>();
            this.margin.withParentBuilder( this );

            this.padding = new SpacingBuilder<>();
            this.padding.withParentBuilder( this );

            this.background = new BackgroundBuilder<>();
            this.background.withParentBuilder( this );

            this.border = new BorderBuilder<>();
            this.border.withParentBuilder( this );
        }

        private Values( Values<P> values )
        {
            this.margin = values.margin.copy();
            this.margin.withParentBuilder( this );

            this.padding = values.padding.copy();
            this.padding.withParentBuilder( this );

            this.background = values.background.copy();
            this.background.withParentBuilder( this );

            this.border = values.border.copy();
            this.border.withParentBuilder( this );
        }

        boolean isEmpty()
        {
            return this.margin.isEmpty() && this.padding.isEmpty() && this.background.isEmpty() && this.border.isEmpty();
        }

        public SpacingBuilder<Values<P>> withMargin()
        {
            return this.margin;
        }

        public SpacingBuilder<Values<P>> withPadding()
        {
            return this.padding;
        }

        public BackgroundBuilder<Values<P>> withBackground()
        {
            return this.background;
        }

        public BorderBuilder<Values<P>> withBorder()
        {
            return this.border;
        }


        /* *************************************************************************************************************
         * Method - Implement NestedBuilder
         ************************************************************************************************************* */

        @Override
        protected Values<P> self()
        {
            return this;
        }

        @Override
        public Values<P> copy()
        {
            return new Values<>( this );
        }

        /* *************************************************************************************************************
         * Method - Implement Builder
         ************************************************************************************************************* */

        @Override
        public BoxStyle.Values build()
        {
            return new BoxStyle.Values( this );
        }
    }
    final Map<State, Values<BoxStyleBuilder<P>>> states = new HashMap<>();

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    BoxStyleBuilder()
    {
        Values<BoxStyleBuilder<P>> value = new Values<>();
        value.withParentBuilder( this );

        this.states.put( State.NONE, value );
    }

    private BoxStyleBuilder( BoxStyleBuilder<P> builder )
    {
        this();

        builder.states.forEach( (state,value) -> {
            value.withParentBuilder( this );
            this.states.put( state, value );
        });
    }

    boolean isEmpty()
    {
        return false;
    }

    public Values<BoxStyleBuilder<P>> withState( State state )
    {
        if( !this.states.containsKey( state ) )
        {
            Values<BoxStyleBuilder<P>> value = new Values<>( this.states.get( State.NONE ) );
            value.withParentBuilder( this );

            this.states.put( state, value );

            return value;
        }
        else
        {
            return this.states.get( state );
        }
    }

    /* *************************************************************************************************************
     * Method - Implement NestedBuilder
     ************************************************************************************************************* */

    @Override
    protected BoxStyleBuilder<P> self()
    {
        return this;
    }

    @Override
    public BoxStyleBuilder<P> copy()
    {
        return new BoxStyleBuilder<>( this );
    }

    /* *************************************************************************************************************
     * Method - Implement Builder
     ************************************************************************************************************* */

    @Override
    public BoxStyle build()
    {
        return ( !this.isEmpty() ) ? new BoxStyle( this ) : BoxStyle.EMPTY;
    }
}