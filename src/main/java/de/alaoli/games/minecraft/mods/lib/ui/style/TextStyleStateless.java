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

import de.alaoli.games.minecraft.mods.lib.ui.Constants;
import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Colors;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class TextStyleStateless implements TextStyle
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    public static final TextStyleStateless DEFAULT = new TextStyleStateless();

    private final Color color;
    private final Align align;
    private final boolean shadow;
    private final int lineHeight;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    private TextStyleStateless()
    {
        this.align =  Constants.Style.Text.ALIGN;
        this.color = Colors.factory( Constants.Style.Text.ALPHA, Constants.Style.Text.COLOR );
        this.shadow = Constants.Style.Text.SHADOW;
        this.lineHeight = Constants.Style.Text.LINE_HEIGHT;
    }

    private TextStyleStateless(Align align, Color color, boolean shadow, int lineHeight )
    {
        this.align = align;
        this.color = color;
        this.shadow = shadow;
        this.lineHeight = lineHeight;
    }

    /* **************************************************************************************************************
     * Method - Implement TextStyle
     ************************************************************************************************************** */

    @Override
    public Align getAlign()
    {
        return this.align;
    }

    @Override
    public Color getColor()
    {
        return this.color;
    }

    @Override
    public boolean hasShadow()
    {
        return this.shadow;
    }

    @Override
    public int getLineHeight()
    {
        return this.lineHeight;
    }

    /* **************************************************************************************************************
     * RegionBuilder
     ************************************************************************************************************** */

    public static final class TextStyleStatelessBuilder<P> extends NestedBuilder<P, TextStyle>
    {
        /* **************************************************************************************************************
         * Attribute
         ************************************************************************************************************** */

        private Align align;
        private Color color;
        private boolean shadow = Constants.Style.Text.SHADOW;
        private int lineHeight = Constants.Style.Text.LINE_HEIGHT;

        /* **************************************************************************************************************
         * Method
         ************************************************************************************************************** */

        public TextStyleStatelessBuilder<P> withAlign(Align align )
        {
            this.align = align;

            return this;
        }

        public TextStyleStatelessBuilder<P> withColor(int color )
        {
            return this.withColor( 1.0f, color );
        }

        public TextStyleStatelessBuilder<P> withColor(float alpha, int color )
        {
            this.color = Colors.factory( alpha, color );

            return this;
        }

        public TextStyleStatelessBuilder<P> withShadow(boolean shadow )
        {
            this.shadow = shadow;

            return this;
        }

        public TextStyleStatelessBuilder<P> withShadow()
        {
            return this.withShadow( true );
        }

        public TextStyleStatelessBuilder<P> withoutShadow()
        {
            return this.withShadow( false );
        }

        public TextStyleStatelessBuilder<P> withLineHeight(int lineHeight )
        {
            this.lineHeight = lineHeight;

            return this;
        }


        /* **************************************************************************************************************
         * Method - Implement de.alaoli.games.minecraft.mods.lib.ui.builder.RegionBuilder<TextStyleStateless>
         ************************************************************************************************************** */

        @Override
        public TextStyle build()
        {
            return new TextStyleStateless(
                (this.align!=null) ? this.align : Constants.Style.Text.ALIGN,
                (this.color!=null) ? this.color : Colors.factory( Constants.Style.Text.ALPHA, Constants.Style.Text.COLOR ),
                this.shadow,
                this.lineHeight
            );
        }
    }
}