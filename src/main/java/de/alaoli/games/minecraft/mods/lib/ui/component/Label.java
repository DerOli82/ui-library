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
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.util.Text;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class Label extends AbstractComponent implements TextComponent
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private final Text text = new Text();
    private TextStyle textStyle;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    Label( LabelBuilder<?> builder )
    {
        super( builder );

        this.text.setMaxLength( builder.text.length() );
        this.text.setValue( builder.text );
        this.setTextStyle( builder.textStyleBuilder );
    }

    /* *************************************************************************************************************
     * Method - Implement TextComponent
     ************************************************************************************************************* */

    @Override
    public Text getText()
    {
        return this.text;
    }

    @Override
    public TextStyle getTextStyle()
    {
        return this.textStyle;
    }

    @Override
    public void setTextStyle( TextStyle textStyle )
    {
        this.textStyle = Styles.valueOrEmpty( textStyle );
    }
}