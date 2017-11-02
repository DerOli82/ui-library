/* *************************************************************************************************************
 * Copyright (c) 2017 DerOli82 <https://github.com/DerOli82>
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
 ************************************************************************************************************ */
package de.alaoli.games.minecraft.mods.lib.ui.element.style;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.element.Text;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Colors;

import java.util.Optional;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 * @param <T> The type will be passed on to {@link TextStyle}
 */
public class StateableTextStyling<T extends Element<T> & Text<T>>
    extends StateableStyle<StateableTextStyling, TextStyling>
    implements TextStyle<StateableTextStyling, T>
{
    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public StateableTextStyling( TextStyling fallback )
    {
        super( fallback );
    }

    /* **************************************************************************************************************
     * Method - Implement TextStyle
     ************************************************************************************************************** */

    @Override
    public int getLineHeight()
    {
        return this.get().map( TextStyling::getLineHeight ).orElse( TextStyling.DEFAULT_LINEHEIGHT );
    }

    @Override
    public StateableTextStyling setLineHeight( int lineHeight )
    {
        this.get().ifPresent( style -> style.setLineHeight( lineHeight ) );

        return this;
    }

    @Override
    public boolean hasShadow()
    {
        return this.get().map( TextStyling::hasShadow ).orElse( false );
    }

    @Override
    public StateableTextStyling setShadow( boolean shadow )
    {
        this.get().ifPresent( style -> style.setShadow( shadow ) );

        return this;
    }

    /* **************************************************************************************************************
     * Method - Implement ColorStyle
     ************************************************************************************************************** */

    @Override
    public Optional<Color> getColor()
    {
        return this.get().map( TextStyling::getColor ).orElse( Optional.of( Colors.factory( Color.BLACK ) ) );
    }

    @Override
    public StateableTextStyling setColor(Color color )
    {
        this.get().ifPresent( style -> style.setColor( color ) );

        return this;
    }

    /* **************************************************************************************************************
     * Method - Implement AlignmentStyle
     ************************************************************************************************************** */

    @Override
    public Optional<Align> getAlign()
    {
        return this.get().map( TextStyling::getAlign ).orElse( Optional.of( Align.TOPLEFT ) );
    }

    @Override
    public StateableTextStyling setAlign(Align align )
    {
        this.get().ifPresent( style -> style.setAlign( align ) );

        return this;
    }

    /* **************************************************************************************************************
     * Method - Implement Drawable
     ************************************************************************************************************** */

    @Override
    public void drawOn( T element )
    {
        this.get().ifPresent( style -> style.drawOn( element ) );
    }
}
