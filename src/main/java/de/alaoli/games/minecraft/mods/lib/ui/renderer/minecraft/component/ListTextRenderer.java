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
package de.alaoli.games.minecraft.mods.lib.ui.renderer.minecraft.component;

import de.alaoli.games.minecraft.mods.lib.ui.component.ListText;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.Context;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.Renderer;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.minecraft.MinecraftRenderer;
import de.alaoli.games.minecraft.mods.lib.ui.style.Region;
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

import java.util.List;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class ListTextRenderer extends GuiListExtended implements Renderer<ListText>
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    @AllArgsConstructor( access = AccessLevel.PRIVATE )
    private final class Entry implements TextRenderer, IGuiListEntry
    {
        /* *************************************************************************************************************
         * Attribute
         ************************************************************************************************************* */

        private final ListTextRenderer parent;

        /* *************************************************************************************************************
         * Method - Implement IGuiListEntry
         ************************************************************************************************************* */

        @Override
        public void updatePosition( int slotIndex, int x, int y, float partialTicks ) {}

        @Override
        public boolean mousePressed( int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY )
        {
            return false;
        }

        @Override
        public void mouseReleased( int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY ) {}

        @Override
        public void drawEntry( int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks )
        {
            String textLine = this.parent.tmpList.get( slotIndex );
            int color = this.parent.tmpStyle.getColor().getValue();
            boolean hasShadow = this.parent.tmpStyle.hasShadow();

            this.drawText( textLine, x, y, color, hasShadow );
        }
    }
    private final Entry tmpEntry = new Entry( this );
    private List<String> tmpList;
    private TextStyle tmpStyle;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    public ListTextRenderer()
    {
        super( Minecraft.getMinecraft(), 0, 0, 0, 0, 11 );
    }

    /* *************************************************************************************************************
     * Method - Implement GuiListExtended
     ************************************************************************************************************* */

    @Override
    public int getListWidth()
    {
        return this.width;
    }

    @Override
    protected int getScrollBarX()
    {
        return this.width - 6;
    }

    @Override
    public IGuiListEntry getListEntry( int index )
    {
        return this.tmpEntry;
    }

    @Override
    protected int getSize()
    {
        return this.tmpList.size();
    }

    /* *************************************************************************************************************
     * Method - Implement Renderer
     ************************************************************************************************************* */

    @Override
    public void init()
    {
        this.centerListVertically = false;
    }

    @Override
    public void render( ListText component, Context context )
    {
        Region region = component.getRegionAbsolute();

        this.tmpList = component.getList();
        this.tmpStyle = component.getTextStyle();

        this.left = region.getX();
        this.top = region.getY();
        this.width = region.getWidth();
        this.height = region.getHeight();
        this.right = this.left + this.width;
        this.bottom = this.top + this.height;

        this.handleMouseInput();
        this.drawScreen( context.getMouseX(), context.getMouseY(), context.getPartialTicks() );
    }
}