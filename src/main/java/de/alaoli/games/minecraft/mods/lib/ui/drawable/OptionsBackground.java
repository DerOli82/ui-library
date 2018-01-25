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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

/**
 * Default Minecraft Options Background
 *
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class OptionsBackground extends Gui implements Drawable
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	public static final OptionsBackground DEFAULT = new OptionsBackground();

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	private OptionsBackground() {}

	/* **************************************************************************************************************
	 * Method - Implement Drawable
	 ************************************************************************************************************** */
	
	@Override
	public void drawAt( int left, int top, int width, int height )
	{
		int right = left + width,
			bottom = top + height;
		Minecraft mc = Minecraft.getMinecraft();

		if( mc.world != null )
		{
			drawGradientRect( left, top, right, bottom, 0xC0101010, 0xD0101010);
		}
		else // Draw dark dirt background
		{
			Tessellator tess = Tessellator.getInstance();
			VertexBuffer buffer = tess.getBuffer();
			float scale = 32.0F;

			GlStateManager.disableLighting();
			GlStateManager.disableFog();
			mc.renderEngine.bindTexture( OPTIONS_BACKGROUND );
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR );
			buffer.pos( left,  bottom, 0.0D).tex(left  / scale, bottom / scale ).color(0x20, 0x20, 0x20, 0xFF ).endVertex();
			buffer.pos( right, bottom, 0.0D).tex(right / scale, bottom / scale ).color(0x20, 0x20, 0x20, 0xFF ).endVertex();
			buffer.pos( right, top,    0.0D).tex(right / scale, top / scale ).color(0x20, 0x20, 0x20, 0xFF ).endVertex();
			buffer.pos( left,  top,    0.0D).tex(left  / scale, top / scale ).color(0x20, 0x20, 0x20, 0xFF ).endVertex();

			tess.draw();
		}
	}
}