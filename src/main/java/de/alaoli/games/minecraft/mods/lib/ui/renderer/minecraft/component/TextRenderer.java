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

import de.alaoli.games.minecraft.mods.lib.ui.component.Component;
import de.alaoli.games.minecraft.mods.lib.ui.component.TextComponent;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.Context;
import de.alaoli.games.minecraft.mods.lib.ui.renderer.Renderer;
import de.alaoli.games.minecraft.mods.lib.ui.style.*;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Point;
import de.alaoli.games.minecraft.mods.lib.ui.util.Text;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

import java.util.*;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public interface TextRenderer<C extends Component&TextComponent> extends Renderer<C>
{
    /**
     * @return Returns Minecraft {@link FontRenderer}
     */
    default FontRenderer getFontRenderer()
    {
        Minecraft mc = Minecraft.getMinecraft();

        if( mc == null || mc.fontRenderer == null)
        {
            throw new IllegalStateException( "Can't initialize Minecraft font renderer" );
        }
        return mc.fontRenderer;
    }

    default List<String> textToLines( String textline, int width, int height, int lineHeight )
    {
        FontRenderer renderer = this.getFontRenderer();

        //Only one line
        if( renderer.getStringWidth( textline ) < width ) { return Collections.singletonList( textline ); }

        //Multiple lines
        List<String> textlines = new ArrayList<>();
        StringJoiner joiner = new StringJoiner( " " );

        for( String word : textline.split( " " ) )
        {
            if( renderer.getStringWidth( joiner.toString() + word + 4 ) < width )
            {
                joiner.add( word );
            }
            else
            {
                textlines.add( joiner.toString() );
                joiner = new StringJoiner( " " );
                joiner.add( word );
            }
        }

        if( joiner.length() > 0 )
        {
            textlines.add( joiner.toString() );
        }
        return textlines;
    }

    default Point alignText( Align align, int x, int y, int width, int height, int textWidth, int textHeight )
    {
        switch( align )
        {
            case TOPLEFT:
                //Nothing to do
                break;
            case TOP:
                x += width/2 - textWidth/2;
                break;
            case TOPRIGHT:
                x += width - textWidth;
                break;

            case LEFT:
                y += height/2 - textHeight/2;
                break;
            case CENTER:
                y += height/2 - textHeight/2;
                x += width/2 - textWidth/2;
                break;
            case RIGHT:
                x += width - textWidth;
                y += height/2 - textHeight/2;
                break;

            case BOTTOMLEFT:
                y += height - textHeight;
                break;
            case BOTTOM:
                x += width/2 - textWidth/2;
                y += height - textHeight;
                break;
            case BOTTOMRIGHT:
                x += width - textWidth;
                y += height - textHeight;
                break;
            default:
                //Nothing to do
                break;
        }
        return Point.get( x, y );
    }

    default void drawCursor( int x, int y, int lineHeight, int color )
    {

    }

    default void drawText( Text text, Region region, TextStyle style )
    {
        this.drawText( text, region, style, null );
    }

    default void drawText( Text text, Region region, TextStyle style, BoxStyle boxStyle )
    {
        int x = region.getX(),
            y = region.getY(),
            width = region.getWidth(),
            height = region.getHeight(),
            lineHeight = style.getLineHeight(),
            color = style.getColor().getValue(),
            cursorPos = text.getCursorPos(),
            textWidth = 0,
            textHeight = 0;
        FontRenderer renderer = this.getFontRenderer();

        //Add padding
        if( boxStyle != null )
        {
            Padding padding = boxStyle.getPadding();

            x += padding.getLeft();
            y += padding.getTop();
            width -= padding.getRight();
            height -= padding.getBottom();
        }
        List<String> textlines = this.textToLines( text.toString(), width, height, lineHeight );

        //Calc max text width and height
        for( String textline : textlines )
        {
            textWidth = Math.max( textWidth, renderer.getStringWidth( textline ) );
            textHeight += lineHeight + 2;
        }
        Point xy = this.alignText( style.getAlign(), x, y, width, height, textWidth, textHeight );
        x = xy.getX();
        y = xy.getY();

        for( String textline : textlines )
        {
            this.drawText( textline, x, y, color, style.hasShadow() );

            if( text.isCursorVisible() && !text.isCursorDisabled() )
            {
                if( cursorPos >= 0 && cursorPos <= textline.length() )
                {
                    int cursorX = x + this.getFontRenderer().getStringWidth( textline.substring( 0, cursorPos ) );

                    Gui.drawRect( cursorX, y - 1, cursorX + 1, y - 1 + lineHeight, color );
                }
            }
            cursorPos -= textline.length()+1;
            y += lineHeight + 2;
        }


        /*
        if( text.hasCursor() )
        {
            int cursorX = x + this.getFontRenderer().getStringWidth( textline.substring( 0, text.getCursorPos() ) );
            y--;

            Gui.drawRect( cursorX, y, cursorX + 1, y + textHeight, color );
        }*/
    }

    default void drawText( String text, int x, int y, int color, boolean hasShadow )
    {
        if( hasShadow )
        {
            this.getFontRenderer().drawStringWithShadow( text, x, y, color );
        }
        else
        {
            this.getFontRenderer().drawString( text, x, y, color );
        }
    }

    @Override
    default void render( C component, Context context )
    {
        Region region = component.getRegionAbsolute();
        Text text = component.getText();
        TextStyle style = component.getTextStyle();

        this.drawText( text, region, style );
    }
}