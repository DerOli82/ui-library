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
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.ColorBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class TextStyleBuilder<P> extends NestedBuilder<P,TextStyleBuilder<P>,TextStyle>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    public static final class Values<P> extends NestedBuilder<P,Values<P>,TextStyle.Values>
            implements ColorBuilder<Values<P>>
    {
        /* *************************************************************************************************************
         * Attribute
         ************************************************************************************************************* */

        Align align = Align.LEFT;
        Color color = Color.DEFAULT;
        boolean hasShadow = false;
        int lineHeight = 0;

        /* *************************************************************************************************************
         * Method
         ************************************************************************************************************* */

        Values() {}

        private Values( Values<P> values )
        {
            this.align = values.align;
            this.color = values.color;
            this.hasShadow = values.hasShadow;
            this.lineHeight = values.lineHeight;
        }

        public boolean isEmpty()
        {
            return this.align == Align.LEFT && this.color.equals( Color.DEFAULT ) && !this.hasShadow && this.lineHeight == 0;
        }

        public Values<P> withAlign( Align align )
        {
            this.align = (align!=null) ? align : Align.LEFT;

            return this;
        }

        public Values<P> withShadow( boolean shadow )
        {
            this.hasShadow = shadow;

            return this;
        }

        public Values<P> withShadow()
        {
            return this.withShadow( true );
        }

        public Values<P> withoutShadow()
        {
            return this.withShadow( false );
        }

        public Values<P> withLineHeight( int lineHeight )
        {
            this.lineHeight = Math.max( lineHeight, 0 );

            return this;
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
        public TextStyle.Values build()
        {
            return (!this.isEmpty()) ? new TextStyle.Values( this ) : TextStyle.Values.EMPTY;
        }

        /* *************************************************************************************************************
         * Method - Implement ColorBuilder
         ************************************************************************************************************* */

        @Override
        public Values<P> withColor( Color color )
        {
            this.color = (color!=null) ? color : Color.valueOf( Color.Codes.WHITE );

            return this;
        }
    }
    final Map<State,Values<TextStyleBuilder<P>>> states = new HashMap<>();

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    TextStyleBuilder()
    {
        Values<TextStyleBuilder<P>> value = new Values<TextStyleBuilder<P>>().withParentBuilder( this );

        this.states.put( State.NONE, value );
    }

    private TextStyleBuilder( TextStyleBuilder<P> builder )
    {
        builder.states.forEach( (state,value) -> this.states.put( state, value.copy() ) );
    }

    public boolean isEmpty()
    {
        return this.states.values().stream().allMatch( Values::isEmpty );
    }

    public Values<TextStyleBuilder<P>> withState( State state )
    {
        Values<TextStyleBuilder<P>> value;

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
    protected TextStyleBuilder<P> self()
    {
        return this;
    }

    @Override
    public TextStyleBuilder<P> copy()
    {
        return new TextStyleBuilder<>( this );
    }

    /* *************************************************************************************************************
     * Method - Implement Builder
     ************************************************************************************************************* */

    @Override
    public TextStyle build()
    {
        return (!this.isEmpty() ) ? new TextStyle( this ) : Styles.emptyTextStyle();
    }
}