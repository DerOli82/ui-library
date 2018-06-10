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
package de.alaoli.games.minecraft.mods.lib.ui.component;

import de.alaoli.games.minecraft.mods.lib.ui.builder.Builder;
import de.alaoli.games.minecraft.mods.lib.ui.style.BoxStyleBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;
import de.alaoli.games.minecraft.mods.lib.ui.theme.ThemeManager;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class PaneBuilder<P> extends ComponentBuilder<P, PaneBuilder<P>,Pane>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    BoxStyleBuilder<PaneBuilder<P>> boxStyleBuilder;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    PaneBuilder()
    {
        ThemeManager.INSTANCE.applyOn( this );
    }

    public BoxStyleBuilder<PaneBuilder<P>> withBoxStyle()
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
    protected PaneBuilder<P> self()
    {
        return this;
    }

    @Override
    public PaneBuilder<P> copy()
    {
        PaneBuilder<P> builder = new PaneBuilder<>();
        builder.boxStyleBuilder = this.boxStyleBuilder.copy();

        return builder;
    }

    /* *************************************************************************************************************
     * Method - Implement NestedBuilder
     ************************************************************************************************************* */

    @Override
    public Pane build()
    {
        return new Pane( this );
    }

}