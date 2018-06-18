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

        final SpacingBuilder<Values<P>> margin;
        final SpacingBuilder<Values<P>> padding;

        final BackgroundBuilder<Values<P>> background;
        final BorderBuilder<Values<P>> border;

        /* *************************************************************************************************************
         * Method
         ************************************************************************************************************* */

        private Values()
        {
            this.margin = Styles.<Values<P>>newSpacingBuilder().withParentBuilder( this );
            this.padding = Styles.<Values<P>>newSpacingBuilder().withParentBuilder( this );

            this.background = Styles.<Values<P>>newBackgroundBuilder().withParentBuilder( this );
            this.border = Styles.<Values<P>>newBorderBuilder().withParentBuilder( this );
        }

        private Values( Values<P> values )
        {
            this.margin = values.margin.copy().withParentBuilder( this );
            this.padding = values.padding.copy().withParentBuilder( this );

            this.background = values.background.copy().withParentBuilder( this );
            this.border = values.border.copy().withParentBuilder( this );
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
            return (!this.isEmpty()) ? new BoxStyle.Values( this ) : BoxStyle.Values.EMPTY;
        }
    }
    final Map<State, Values<BoxStyleBuilder<P>>> states = new HashMap<>();

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    BoxStyleBuilder()
    {
        Values<BoxStyleBuilder<P>> value = new Values<BoxStyleBuilder<P>>().withParentBuilder( this );

        this.states.put( State.NONE, value );
    }

    private BoxStyleBuilder( BoxStyleBuilder<P> builder )
    {
        this();

        builder.states.forEach( (state,value) -> {
            value.copy().withParentBuilder( this );
            this.states.put( state, value );
        });
    }

    boolean isEmpty()
    {
        return this.states.values().stream().allMatch( Values::isEmpty );
    }

    public Values<BoxStyleBuilder<P>> withState( State state )
    {
        Values<BoxStyleBuilder<P>> value;

        if( !this.states.containsKey( state ) )
        {
            value = this.states.get( State.NONE ).copy().withParentBuilder( this );

            this.states.put( state, value );
        }
        else
        {
            value = this.states.get( state );
        }
        return value;
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
        return ( !this.isEmpty() ) ? new BoxStyle( this ) : Styles.emptyBoxStyle();
    }
}