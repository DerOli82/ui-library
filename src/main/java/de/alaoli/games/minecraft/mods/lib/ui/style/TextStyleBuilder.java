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

import de.alaoli.games.minecraft.mods.lib.ui.builder.Builder;
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
public class TextStyleBuilder<P> extends NestedBuilder<P,TextStyleBuilder<P>> implements Builder<TextStyle>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private TextStyleBuilder<TextStyleBuilder<P>> textStyleBuilder;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    TextStyleBuilder() {}

    public Stateless<TextStyleBuilder<P>> newStateless()
    {
        this.textStyleBuilder = new Stateless<>();
        this.textStyleBuilder.withParentBuilder( this );

        return (Stateless<TextStyleBuilder<P>>) this.textStyleBuilder;
    }

    public Stateful<TextStyleBuilder<P>> newStateful()
    {
        this.textStyleBuilder = new Stateful<>();
        this.textStyleBuilder.withParentBuilder( this );

        return (Stateful<TextStyleBuilder<P>>) this.textStyleBuilder;
    }

    /* **************************************************************************************************************
     * Method - Implement NestedBuilder
     ************************************************************************************************************** */

    @Override
    protected TextStyleBuilder<P> self()
    {
        return this;
    }

    /* **************************************************************************************************************
     * Method - Implement Builder
     ************************************************************************************************************** */

    @Override
    public TextStyle build()
    {
        return this.textStyleBuilder.build();
    }

    /* **************************************************************************************************************
     * Stateless Builder
     ************************************************************************************************************** */

    public static final class Stateless<P> extends TextStyleBuilder<P> implements ColorBuilder<Stateless<P>>
    {
        /* **************************************************************************************************************
         * Attribute
         ************************************************************************************************************** */

        Align align;
        Color color;
        boolean shadow;
        int lineheight;

        /* **************************************************************************************************************
         * Method
         ************************************************************************************************************** */

        Stateless() {}

        public Stateless<P> withAlign( Align align )
        {
            this.align = align;

            return this;
        }

        public Stateless<P> withShadow()
        {
            this.shadow = true;

            return this;
        }

        public Stateless<P> withoutShadow()
        {
            this.shadow = false;

            return this;
        }

        public Stateless<P> withLineheight( int lineheight )
        {
            this.lineheight = lineheight;

            return this;
        }

        /* **************************************************************************************************************
         * Method - Implement ColorBuilder
         ************************************************************************************************************** */

        @Override
        public Stateless<P> withColor( Color color )
        {
            this.color = color;

            return this;
        }

        /* **************************************************************************************************************
         * Method - Implement Builder
         ************************************************************************************************************** */

        @Override
        public TextStyle build()
        {
            return new TextStyleStateless( this );
        }

        @Override
        public Stateless<P> self()
        {
            return this;
        }
    }

    public static final class Stateful<P> extends TextStyleBuilder<P>
    {
        /* **************************************************************************************************************
         * Attribute
         ************************************************************************************************************** */

        Map<State,Stateless<Stateful<P>>> states = new HashMap<>();

        /* **************************************************************************************************************
         * Method
         ************************************************************************************************************** */

        Stateful() {}

        public Stateless<Stateful<P>> addState( State state )
        {
            if( !this.states.containsKey( state ) )
            {
                Stateless<Stateful<P>> stateless = Styles.<Stateful<P>>createTextStyleStateless();
                stateless.withParentBuilder( this );

                this.states.put( state, stateless );
            }
            return this.states.get( state );
        }

        /* **************************************************************************************************************
         * Method - Implement NestedBuilder
         ************************************************************************************************************** */

        @Override
        public Stateful<P> self()
        {
            return this;
        }

        /* **************************************************************************************************************
         * Method - Implement Builder
         ************************************************************************************************************** */

        @Override
        public TextStyle build()
        {
            return new TextStyleStateful( this );
        }
    }
}