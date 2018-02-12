/**************************************************************************************************************
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
 *************************************************************************************************************/
package de.alaoli.games.minecraft.mods.lib.ui.style;

import de.alaoli.games.minecraft.mods.lib.ui.Constants;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
final class TextStyleStateless implements TextStyle
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private final Align align;
    private final Color color;
    private final boolean shadow;
    private final int lineheight;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    TextStyleStateless()
    {
        this.align =  Constants.Style.Text.ALIGN;
        this.color = Color.valueOf( Constants.Style.Text.ALPHA, Constants.Style.Text.COLOR );
        this.shadow = Constants.Style.Text.SHADOW;
        this.lineheight = Constants.Style.Text.LINE_HEIGHT;
    }

    TextStyleStateless( TextStyleBuilder.Stateless<?> builder )
    {
        this( builder.align, builder.color, builder.shadow, builder.lineheight );
    }

    private TextStyleStateless( Align align, Color color, boolean shadow, int lineHeight )
    {
        this.align = (align!=null) ? align : Constants.Style.Text.ALIGN;
        this.color = (color!=null) ? color : Color.valueOf( Constants.Style.Text.ALPHA, Constants.Style.Text.COLOR );
        this.shadow = shadow;
        this.lineheight = (lineHeight>0) ? lineHeight : 0;
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
        return this.lineheight;
    }
}