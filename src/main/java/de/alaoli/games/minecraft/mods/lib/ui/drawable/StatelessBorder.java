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
package de.alaoli.games.minecraft.mods.lib.ui.drawable;

import de.alaoli.games.minecraft.mods.lib.ui.Constants;
import de.alaoli.games.minecraft.mods.lib.ui.builder.NestedBuilder;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Colors;
import net.minecraft.client.gui.Gui;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class StatelessBorder extends Gui implements Border
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	public static final StatelessBorder DEFAULT = new StatelessBorder();

	private final Color color;
	private boolean hideTop, hideLeft, hideRight, hideBottom;
	
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	private StatelessBorder()
	{
		this.color = Colors.factory( Constants.Style.Border.ALPHA, Constants.Style.Border.COLOR );
	}

	private StatelessBorder( Color color )
	{
		this.color = color;
	}

	private StatelessBorder( Color color, boolean top, boolean left, boolean right, boolean bottom )
	{
		this.color = color;

		this.hideTop = top;
		this.hideLeft = left;
		this.hideRight = right;
		this.hideBottom = bottom;
	}

	/* **************************************************************************************************************
	 * Method - Implement Drawable
	 ************************************************************************************************************** */
	
	@Override
	public void drawAt( int left, int top, int width, int height )
	{
		int right = left + width,
			bottom = top + height,
			color = (this.color!=null) ? this.color.value : Color.BLACK;

		if( !this.hideTop ) { this.drawHorizontalLine( left, right-1, top, color ); }
		if( !this.hideLeft ) { this.drawVerticalLine( left, top, bottom, color ); }
		if( !this.hideRight ) { this.drawVerticalLine( right-1, top, bottom, color ); }
		if( !this.hideBottom ) { this.drawHorizontalLine( left, right-1, bottom-1, color ); }
	}

	/* **************************************************************************************************************
	 * RegionBuilder
	 ************************************************************************************************************** */

	public static final class StatelessBorderBuilder<P> extends NestedBuilder<P, Border>
	{
		/* **************************************************************************************************************
		 * Attribute
		 ************************************************************************************************************** */

		private Color color;
		private boolean hideTop = Constants.Style.Border.HIDE_TOP,
						hideLeft = Constants.Style.Border.HIDE_LEFT,
						hideRight = Constants.Style.Border.HIDE_RIGHT,
						hideBottom = Constants.Style.Border.HIDE_BOTTOM;

		/* **************************************************************************************************************
		 * Method
		 ************************************************************************************************************** */

		public StatelessBorderBuilder<P> withColor(int color )
		{
			return this.withColor( 1.0f, color );
		}

		public StatelessBorderBuilder<P> withColor(float alpha, int color )
		{
			this.color = Colors.factory( alpha, color );

			return this;
		}

		public StatelessBorderBuilder<P> withHidden( boolean top, boolean left, boolean right, boolean bottom )
		{
			this.hideTop = top;
			this.hideLeft = left;
			this.hideRight = right;
			this.hideBottom = bottom;

			return this;
		}

		/* **************************************************************************************************************
		 * Method - Implement Builder<Border>
		 ************************************************************************************************************** */

		@Override
		public Border build()
		{
			return new StatelessBorder(
				(this.color!=null) ? this.color : Colors.factory( Constants.Style.Border.ALPHA, Constants.Style.Border.COLOR ),
				this.hideTop,
				this.hideLeft,
				this.hideRight,
				this.hideBottom
			);
		}
	}
}