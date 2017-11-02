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

import de.alaoli.games.minecraft.mods.lib.ui.drawable.Drawable;
import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;

import java.util.Optional;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 * @param <T> The type will be passed on to {@link BoxStyle}
 */
public class StateableBoxStyling<T extends Element<T>>
    extends StateableStyle<StateableBoxStyling, BoxStyling>
    implements BoxStyle<StateableBoxStyling, T>
{
    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public StateableBoxStyling( BoxStyling fallback )
    {
        super( fallback );
    }

    /* **************************************************************************************************************
     * Method - Implement BoxStyle
     ************************************************************************************************************** */

    @Override
    public Optional<Drawable> getBackground()
    {
        return this.get().map( BoxStyle::getBackground ).orElse( Optional.empty() );
    }

    @Override
    public StateableBoxStyling setBackground( Drawable background )
    {
        this.get().ifPresent( style -> style.setBackground( background ) );

        return this;
    }

    @Override
    public Optional<Drawable> getBorder()
    {
        return this.get().map( BoxStyle::getBorder ).orElse( Optional.empty() );
    }

    @Override
    public StateableBoxStyling setBorder( Drawable border )
    {
        this.get().ifPresent( style -> style.setBorder( border ) );

        return this;
    }

    @Override
    public StateableBoxStyling setMargin( int margin )
    {
        this.get().ifPresent( style -> style.setMargin( margin ) );

        return this;
    }

    @Override
    public StateableBoxStyling setMargin( int top, int left, int right, int bottom )
    {
        this.get().ifPresent( style -> style.setMargin( top, left, right, bottom ) );

        return this;
    }

    @Override
    public StateableBoxStyling setPadding( int padding )
    {
        this.get().ifPresent( style -> style.setPadding( padding ) );

        return this;
    }

    @Override
    public StateableBoxStyling setPadding(int top, int left, int right, int bottom )
    {
        this.get().ifPresent( style -> style.setPadding( top, left, right, bottom ) );

        return this;
    }

    /* **************************************************************************************************************
     * Method - Implement Align
     ************************************************************************************************************** */

    @Override
    public Optional<Align> getAlign()
    {
        return this.get().map( BoxStyle::getAlign ).orElse( Optional.of( Align.TOPLEFT ) );
    }

    @Override
    public StateableBoxStyling setAlign( Align align )
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