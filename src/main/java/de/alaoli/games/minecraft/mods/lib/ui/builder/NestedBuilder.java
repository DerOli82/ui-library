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
package de.alaoli.games.minecraft.mods.lib.ui.builder;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 *
 * @param <P> Type of the parent object, which contains the NestedBuilder
 * @param <S> Type refers to itself
 * @param <B> See {@link Builder}
 */
public abstract class NestedBuilder<P, S extends NestedBuilder<P,S,B>, B> implements Builder<B>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private P parent;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    /**
     * @return Returns a reference to itself
     */
    protected abstract S self();

    /**
     * @return Returns a copy of this Builder
     */
    public abstract S copy();

    public P done()
    {
        return this.parent;
    }

    public S withParentBuilder( P parent )
    {
        this.parent = parent;

        return this.self();
    }
}