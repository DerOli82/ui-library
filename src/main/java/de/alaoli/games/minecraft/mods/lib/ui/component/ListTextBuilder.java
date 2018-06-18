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
package de.alaoli.games.minecraft.mods.lib.ui.component;

import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyleBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class ListTextBuilder<P> extends ComponentBuilder<P, ListTextBuilder<P>, ListText>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    List<String> list;
    TextStyleBuilder<ListTextBuilder<P>> textStyleBuilder;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    ListTextBuilder()
    {
        super();
    }

    private ListTextBuilder( ListTextBuilder<P> builder )
    {
        super( builder );

        if( ( builder.list != null ) && ( !builder.list.isEmpty() ) )
        {
            this.list = new ArrayList<>();
            this.list.addAll( builder.list );
        }
        this.textStyleBuilder = (builder.textStyleBuilder!=null) ? builder.textStyleBuilder.copy() : null;
    }

    public ListTextBuilder<P> withEntries( List<String> entries )
    {
        this.list = entries;

        return this;
    }

    public TextStyleBuilder<ListTextBuilder<P>> withTextStyle()
    {
        if( this.textStyleBuilder == null )
        {
            this.textStyleBuilder = Styles.<ListTextBuilder<P>>newTextStyleBuilder().withParentBuilder( this );
        }
        return this.textStyleBuilder;
    }

    /* **************************************************************************************************************
     * Method - Implements NestedBuilder
     ************************************************************************************************************** */

    @Override
    protected ListTextBuilder<P> self()
    {
        return this;
    }

    @Override
    public ListTextBuilder<P> copy()
    {
        return new ListTextBuilder<>( this );
    }

    /* **************************************************************************************************************
     * Method - Implements Builder
     ************************************************************************************************************** */

    @Override
    public ListText build()
    {
        return new ListText( this );
    }
}