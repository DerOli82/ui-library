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

import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.state.Stateable;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
final class TextStyleStateful implements TextStyle, Stateable
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private State state;
    private TextStyle currentStyle;

    private final Map<State, TextStyle> states = new HashMap<>();

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    TextStyleStateful( TextStyleBuilder.Stateful<?> builder )
    {
        builder.states.forEach( (key,value) -> this.states.put( key, value.build() ) );
    }

    private TextStyle getDefault()
    {
        if( !this.states.containsKey( State.NONE ) )
        {
            this.states.put( State.NONE, Styles.getTextStyleDefault() );
        }
        return this.states.get( State.NONE );
    }
    
    /* **************************************************************************************************************
     * Method - Implement Stateable
     ************************************************************************************************************** */

    @Override
    public State getState()
    {
        return this.state;
    }

    @Override
    public void setState( State state )
    {
        this.state = (state!=null) ? state : State.NONE;
        this.currentStyle = this.states.getOrDefault( this.state, this.getDefault() );
    }

    /* **************************************************************************************************************
     * Method - Implement TextStyle
     ************************************************************************************************************** */

    @Override
    public Align getAlign()
    {
        return this.currentStyle.getAlign();
    }

    @Override
    public Color getColor()
    {
        return this.currentStyle.getColor();
    }

    @Override
    public boolean hasShadow()
    {
        return this.currentStyle.hasShadow();
    }

    @Override
    public int getLineHeight()
    {
        return this.currentStyle.getLineHeight();
    }
}