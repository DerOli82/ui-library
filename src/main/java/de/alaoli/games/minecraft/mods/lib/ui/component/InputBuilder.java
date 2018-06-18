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

import de.alaoli.games.minecraft.mods.lib.ui.style.BoxStyleBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyleBuilder;
import net.minecraft.client.resources.I18n;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
abstract class InputBuilder<P, S extends InputBuilder<P,S,B>,B> extends ComponentBuilder<P,S,B>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    String text;
    String placeholder;

    TextStyleBuilder<S> textStyleBuilder;
    BoxStyleBuilder<S> boxStyleBuilder;

    int maxLength;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    InputBuilder()
    {
        super();
    }

    InputBuilder( S builder )
    {
        super( builder );

        this.placeholder = builder.placeholder;
        this.textStyleBuilder = (builder.textStyleBuilder!=null) ? builder.textStyleBuilder.copy() : null;
        this.boxStyleBuilder = (builder.boxStyleBuilder!=null) ? builder.boxStyleBuilder.copy() : null;

        this.maxLength = builder.maxLength;
    }


    public S withText( String text )
    {
        return this.withText( text, true );
    }

    public S withText( String text, boolean translate )
    {
        this.text = (translate) ? I18n.format( text ) : text;

        return this.self();
    }

    public S withPlaceholder( String placeholder )
    {
        return this.withPlaceholder( placeholder, true );
    }

    public S withPlaceholder( String placeholder, boolean translate )
    {
        this.placeholder = (translate) ? I18n.format( placeholder ) : placeholder;

        return this.self();
    }

    public TextStyleBuilder<S> withTextStyle()
    {
        if( this.textStyleBuilder == null )
        {
            this.textStyleBuilder = Styles.<S>newTextStyleBuilder().withParentBuilder( this.self() );
        }
        return this.textStyleBuilder;
    }

    public BoxStyleBuilder<S> withBoxStyle()
    {
        if( this.boxStyleBuilder == null )
        {
            this.boxStyleBuilder = Styles.<S>newBoxStyleBuilder().withParentBuilder( this.self() );
        }
        return this.boxStyleBuilder;
    }

    public S withMaxLength( int maxLength )
    {
        this.maxLength = maxLength;

        return this.self();
    }
}