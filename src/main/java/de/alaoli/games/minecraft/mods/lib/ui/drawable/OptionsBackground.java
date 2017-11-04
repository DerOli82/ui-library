/**************************************************************************************************************
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
 *************************************************************************************************************/
package de.alaoli.games.minecraft.mods.lib.ui.drawable;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
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
public class OptionsBackground extends Gui implements Drawable<Element>
{
	/* **************************************************************************************************************
	 * Method - Implement Drawable
	 ************************************************************************************************************** */
	
	@Override
	public void drawOn( Element element )
	{
		Minecraft mc = Minecraft.getMinecraft();
		int left = element.box.getX(),
			top = element.box.getY(),
			right = left + element.getPrefWidth(),
			bottom = top + element.getPrefHeight();
					
		if( mc.world != null )
		{
			drawGradientRect( left, top, right, bottom, 0xC0101010, 0xD0101010);
		}
		else // Draw dark dirt background
		{
			Tessellator tess = Tessellator.getInstance();
			VertexBuffer worldr = tess.getBuffer();
			float scale = 32.0F;

			GlStateManager.disableLighting();
			GlStateManager.disableFog();
			mc.renderEngine.bindTexture( Gui.OPTIONS_BACKGROUND );
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			worldr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR );
			worldr.pos( left,  bottom, 0.0D).tex(left  / scale, bottom / scale ).color(0x20, 0x20, 0x20, 0xFF ).endVertex();
			worldr.pos( right, bottom, 0.0D).tex(right / scale, bottom / scale ).color(0x20, 0x20, 0x20, 0xFF ).endVertex();
			worldr.pos( right, top,    0.0D).tex(right / scale, top / scale ).color(0x20, 0x20, 0x20, 0xFF ).endVertex();
			worldr.pos( left,  top,    0.0D).tex(left  / scale, top / scale ).color(0x20, 0x20, 0x20, 0xFF ).endVertex();

			tess.draw();
		}
	}
}