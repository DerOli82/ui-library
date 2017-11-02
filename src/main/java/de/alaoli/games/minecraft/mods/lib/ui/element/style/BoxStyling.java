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
 * @param <T> The type will be passed on to {@link Drawable}
 */
public class BoxStyling<T extends Element<T>> implements BoxStyle<BoxStyling, T>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */
	
	private Drawable background;
	private Drawable border;
	private Align align;

	public int marginTop = 0;
	public int marginLeft = 0;
	public int marginRight = 0;
	public int marginBottom = 0;

	public int paddingTop = 0;
	public int paddingLeft = 0;
	public int paddingRight = 0;
	public int paddingBottom = 0;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public BoxStyling extend()
	{
		return new BoxStyling()
			.setBackground( this.background)
			.setBorder( this.border )
			.setAlign( this.align )
			.setMargin( this.marginTop, this.marginLeft, this.marginRight, this.marginBottom )
			.setPadding( this.paddingTop, this.paddingLeft, this.paddingRight, this.paddingBottom );
	}

	public void applyAlignOn( T parent, Element child )
	{
		int parentX = parent.box.getX(),
			parentY = parent.box.getY(),
			parentWidth = parent.box.getWidth(),
			parentHeight = parent.box.getHeight(),
			childWidth = child.box.getWidth(),
			childHeight = child.box.getHeight();
		Align align = (this.align!=null) ? this.align : Align.TOPLEFT;

		switch( align )
		{
			case TOPLEFT:
				child.setLocation( parentX, parentY );
				break;
			case TOP:
				child.setLocation(
					(parentX + Math.round( 0.5f*parentWidth ) - Math.round( 0.5f*childWidth )),
					parentY
				);
				break;
			case TOPRIGHT:
				child.setLocation(
					(parentX + parentWidth - childWidth),
					parentY
				);
				break;

			case LEFT:
				child.setLocation(
					parentX,
					(parentY + Math.round( 0.5f*parentHeight ) - Math.round( 0.5f*childHeight ))
				);
				break;
			case CENTER:
				child.setLocation(
					(parentX + Math.round( 0.5f*parentWidth ) - Math.round( 0.5f*childWidth )),
					(parentY + Math.round( 0.5f*parentHeight ) - Math.round( 0.5f*childHeight ))
				);
				break;
			case RIGHT:
				child.setLocation(
					(parentX + parentWidth - childWidth),
					(parentY + Math.round( 0.5f*parentHeight ) - Math.round( 0.5f*childHeight ))
				);
				break;

			case BOTTOMLEFT:
				child.setLocation(
					parentX,
					(parentY + parentHeight - childHeight)
				);
				break;
			case BOTTOM:
				child.setLocation(
					(parentX + Math.round( 0.5f*parentWidth ) - Math.round( 0.5f*childWidth )),
					(parentY + parentHeight - childHeight)
				);
				break;
			case BOTTOMRIGHT:
				child.setLocation(
					(parentX + parentWidth - childWidth),
					(parentY + parentHeight - childHeight)
				);
				break;

			default:
				child.setLocation( parentX, parentY );
				break;
		}
	}

	public void applyMarginOn( T element )
	{
		element.box.translate( this.marginLeft, this. marginTop );
		element.box.setSize(
			element.box.getWidth() - this.marginLeft - this.marginRight,
			element.box.getHeight() - this.marginTop - this.marginBottom
		);
	}

    /* **************************************************************************************************************
     * Method - Implement BoxStyle
     ************************************************************************************************************** */

	@Override
	public Optional<Drawable> getBackground()
	{
		return Optional.ofNullable( this.background );
	}

	@Override
	public BoxStyling setBackground(Drawable background )
	{
		this.background = background;

		return this;
	}

	@Override
	public Optional<Drawable> getBorder()
	{
		return Optional.ofNullable( this.border );
	}

	@Override
	public BoxStyling setBorder(Drawable border )
	{
		this.border = border;

		return this;
	}

	@Override
	public BoxStyling setMargin(int margin )
	{
		if( margin < 0 ) { throw new IllegalArgumentException( "Negative 'margin' value not allowed" ); }

		this.marginTop = margin;
		this.marginLeft = margin;
		this.marginRight = margin;
		this.marginBottom = margin;

		return this;
	}

	@Override
	public BoxStyling setMargin(int top, int left, int right, int bottom )
	{
		if( top < 0 ) { throw new IllegalArgumentException( "Negative 'margin-top' value not allowed" ); }
		if( left < 0 ) { throw new IllegalArgumentException( "Negative 'margin-left' value not allowed" ); }
		if( right < 0 ) { throw new IllegalArgumentException( "Negative 'margin-right' value not allowed" ); }
		if( bottom < 0 ) { throw new IllegalArgumentException( "Negative 'margin-bottom' value not allowed" ); }

		this.marginTop = top;
		this.marginLeft = left;
		this.marginRight = right;
		this.marginBottom = bottom;

		return this;
	}

	@Override
	public BoxStyling setPadding(int padding )
	{
		if( padding < 0 ) { throw new IllegalArgumentException( "Negative 'padding' value not allowed" ); }

		this.paddingTop = padding;
		this.paddingLeft = padding;
		this.paddingRight = padding;
		this.paddingBottom = padding;

		return this;
	}

	@Override
	public BoxStyling setPadding(int top, int left, int right, int bottom )
	{
		if( top < 0 ) { throw new IllegalArgumentException( "Negative 'padding-top' value not allowed" ); }
		if( left < 0 ) { throw new IllegalArgumentException( "Negative 'padding-left' value not allowed" ); }
		if( right < 0 ) { throw new IllegalArgumentException( "Negative 'padding-right' value not allowed" ); }
		if( bottom < 0 ) { throw new IllegalArgumentException( "Negative 'padding-bottom' value not allowed" ); }

		this.paddingTop = top;
		this.paddingLeft = left;
		this.paddingRight = right;
		this.paddingBottom = bottom;

		return this;
	}

    /* **************************************************************************************************************
     * Method - Implement Align
     ************************************************************************************************************** */

    @Override
	public Optional<Align> getAlign()
	{
		return Optional.ofNullable( this.align );
	}

	@Override
	public BoxStyling setAlign(Align align )
	{
		this.align = align;

		return this;
	}

    /* **************************************************************************************************************
     * Method - Implement Drawable
     ************************************************************************************************************** */

	@Override
	public void drawOn( T element )
	{
		if( this.background != null ) { this.background.drawOn( element); }
		if( this.border != null ) { this.border.drawOn( element); }
	}
}
