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

import de.alaoli.games.minecraft.mods.lib.ui.builder.Builder;
import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyleBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.ColorBuilder;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public abstract class BackgroundBuilder<P,S extends BackgroundBuilder<P,S>> extends NestedBuilder<P,S>
{


    public static final class Stateless<P> extends BackgroundBuilder<P,Stateless<P>> implements ColorBuilder<Stateless<P>>, Builder<BackgroundStateless>
    {
        /* **************************************************************************************************************
         * Attribute
         ************************************************************************************************************** */

        Color color;

        /* **************************************************************************************************************
         * Method
         ************************************************************************************************************** */

        Stateless() {}

        /* **************************************************************************************************************
         * Method - Implement BackgroundBuilder
         ************************************************************************************************************** */

        @Override
        protected Stateless<P> self()
        {
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
        public BackgroundStateless build()
        {
            return new BackgroundStateless( this );
        }
    }
}