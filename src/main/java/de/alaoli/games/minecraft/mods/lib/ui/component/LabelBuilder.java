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
import net.minecraft.client.resources.I18n;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class LabelBuilder<P> extends ComponentBuilder<P, LabelBuilder<P>, Label>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    String text = "";
    TextStyleBuilder<LabelBuilder<P>> textStyleBuilder;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    LabelBuilder()
    {
        super();
    }

    private LabelBuilder( LabelBuilder<P> builder )
    {
        super( builder );

        this.text = builder.text;
        this.textStyleBuilder = (builder.textStyleBuilder!=null) ? builder.textStyleBuilder.copy() : null;
    }

    public LabelBuilder<P> withText( String text )
    {
        return this.withText( text, true );
    }

    public LabelBuilder<P> withText( String text, boolean translate )
    {
        this.text = (text!=null) ? ( (translate) ? I18n.format( text ) : text ) : "";

        return this;
    }

    public TextStyleBuilder<LabelBuilder<P>> withTextStyle()
    {
        if( this.textStyleBuilder == null )
        {
            this.textStyleBuilder = Styles.<LabelBuilder<P>>newTextStyleBuilder().withParentBuilder( this );
        }
        return this.textStyleBuilder;
    }

    /* **************************************************************************************************************
     * Method - Implements NestedBuilder
     ************************************************************************************************************** */

    @Override
    protected LabelBuilder<P> self()
    {
        return this;
    }

    @Override
    public LabelBuilder<P> copy()
    {
        return new LabelBuilder<>( this );
    }

    /* **************************************************************************************************************
     * Method - Implements Builder
     ************************************************************************************************************** */

    @Override
    public Label build()
    {
        return new Label( this );
    }
}