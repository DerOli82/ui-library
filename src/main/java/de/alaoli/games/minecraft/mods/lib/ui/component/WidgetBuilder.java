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

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class WidgetBuilder<P> extends ComponentBuilder<P, WidgetBuilder<P>,Widget>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    LabelBuilder<WidgetBuilder<P>> labelBuilder;
    ButtonBuilder<WidgetBuilder<P>> buttonBuilder;

    Layout content;

    BoxStyleBuilder<WidgetBuilder<P>> boxStyleBuilder;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    WidgetBuilder()
    {
        super();

        this.labelBuilder = Components.<WidgetBuilder<P>>buildLabel().withParentBuilder( this );
        this.buttonBuilder = Components.<WidgetBuilder<P>>buildButton().withParentBuilder( this );
    }

    public LabelBuilder<WidgetBuilder<P>> withTitle()
    {
        return this.labelBuilder;
    }

    public ButtonBuilder<WidgetBuilder<P>> withCloseButton()
    {
        return this.buttonBuilder;
    }

    public WidgetBuilder<P> withContent( Layout content )
    {
        this.content = content;

        return this;
    }

    public BoxStyleBuilder<WidgetBuilder<P>> withBoxStyle()
    {
        if( this.boxStyleBuilder == null )
        {
            this.boxStyleBuilder = Styles.newBoxStyleBuilder();
            this.boxStyleBuilder.withParentBuilder( this );
        }
        return this.boxStyleBuilder;
    }

    /* *************************************************************************************************************
     * Method - Implement NestedBuilder
     ************************************************************************************************************* */
    @Override
    protected WidgetBuilder<P> self()
    {
        return this;
    }

    @Override
    public WidgetBuilder<P> copy()
    {
        WidgetBuilder<P> builder = new WidgetBuilder<>();
        builder.boxStyleBuilder = this.boxStyleBuilder.copy();

        return builder;
    }

    /* *************************************************************************************************************
     * Method - Implement NestedBuilder
     ************************************************************************************************************* */

    @Override
    public Widget build()
    {
        return new Widget( this );
    }

}