/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or toBuilder
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a toBuilder of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.lib.ui.renderer.minecraft.component;

import de.alaoli.games.minecraft.mods.lib.ui.component.BoxComponent;
import de.alaoli.games.minecraft.mods.lib.ui.component.Component;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.Context;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.Renderer;
import de.alaoli.games.minecraft.mods.lib.ui.style.Background;
import de.alaoli.games.minecraft.mods.lib.ui.style.Border;
import de.alaoli.games.minecraft.mods.lib.ui.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.style.Region;
import de.alaoli.games.minecraft.mods.lib.ui.util.Image;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public interface BoxRenderer<C extends Component & BoxComponent> extends Renderer<C>
{
    default Minecraft getMinecraft()
    {
        Minecraft mc = Minecraft.getMinecraft();

        if( mc == null )
        {
            throw new IllegalStateException( "Can't initialize Minecraft instance" );
        }
        return mc;
    }

    @Override
    default void render( C component, Context context )
    {
        Region region = component.getRegionAbsolute();
        BoxStyle boxStyle = component.getBoxStyle();
        Background background = boxStyle.getBackground();
        Image bgImage = background.getImage();
        Border border = boxStyle.getBorder();

        int x = region.getX(),
            y = region.getY(),
            width = region.getWidth(),
            height = region.getHeight(),
            right = x + width,
            bottom = y + height,
            bgColor = background.getColor().getValue();

        //Background
        Gui.drawRect( x, y, right, bottom, bgColor );

        //Background Image
        if( !bgImage.isEmpty() )
        {
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            float factor = bgImage.getFactor();
            int textureX = bgImage.getX(),
                textureY = bgImage.getY();
            this.getMinecraft().getTextureManager().bindTexture( bgImage.getResource() );

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX );
            bufferbuilder.pos( x, y + height, 0 ).tex( textureX * factor, (textureY + height) * factor ).endVertex();
            bufferbuilder.pos( x + width, y + height, 0 ).tex( (textureX + width) * factor, (textureY + height) * factor ).endVertex();
            bufferbuilder.pos( x + width, y, 0 ).tex( (textureX + width) * factor, textureY * factor ).endVertex();
            bufferbuilder.pos( x, y, 0 ).tex( textureX * factor, textureY * factor ).endVertex();
            tessellator.draw();
        }

        //Border
        if( !border.isEmpty() )
        {
            int bColor = border.getColor().getValue();

            if( border.hasTop() ) { Gui.drawRect( x, y, right + 1, y + 1, bColor ); }
            if( border.hasLeft() ) { Gui.drawRect( x, y + 1, x + 1, bottom, bColor ); }
            if( border.hasRight() ) { Gui.drawRect( right, y + 1, right + 1, bottom, bColor ); }
            if( border.hasBottom() ) { Gui.drawRect( x, bottom, right + 1, bottom + 1, bColor ); }
        }
    }
}