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
public final class StatelessBackground implements Background
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	public static final StatelessBackground DEFAULT = new StatelessBackground();

	private final Color color;
	
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	private StatelessBackground()
	{
		this.color = Colors.factory( Constants.Style.Background.ALPHA, Constants.Style.Background.COLOR );
	}
	
	private StatelessBackground( Color color )
	{
		this.color = color;
	}

	/* **************************************************************************************************************
	 * Method - Implement Drawable
	 ************************************************************************************************************** */
	
	@Override
	public void drawAt( int x, int y, int width, int height )
	{
		Gui.drawRect( x, y, x+width, y+height, this.color.value );
	}

	/* **************************************************************************************************************
	 * RegionBuilder
	 ************************************************************************************************************** */

	public static final class StatelessBackgroundBuilder<P> extends NestedBuilder<P, Background>
	{
		/* **************************************************************************************************************
		 * Attribute
		 ************************************************************************************************************** */

		private Color color;

		/* **************************************************************************************************************
		 * Method
		 ************************************************************************************************************** */

		public StatelessBackgroundBuilder<P> withColor( int color )
		{
			return this.withColor( 1.0f, color );
		}

		public StatelessBackgroundBuilder<P> withColor( float alpha, int color )
		{
			this.color = Colors.factory( alpha, color );

			return this;
		}

		/* **************************************************************************************************************
		 * Method - Implement de.alaoli.games.minecraft.mods.lib.ui.builder.RegionBuilder<StatelessTextStyle>
		 ************************************************************************************************************** */

		@Override
		public Background build()
		{
			return new StatelessBackground(
				(this.color!=null) ? this.color : Colors.factory( Constants.Style.Background.ALPHA, Constants.Style.Background.COLOR )
			);
		}
	}
}