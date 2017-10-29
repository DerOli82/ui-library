package de.alaoli.games.minecraft.mods.lib.ui.element.style;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.drawable.Drawable;
import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.element.Placeholder;
import de.alaoli.games.minecraft.mods.lib.ui.element.Text;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Focusable;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class TextStyle<T extends Element<T> & Text<T>> extends Gui implements Style, Drawable<T>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

	public static final FontRenderer FONTRENDERER = Minecraft.getMinecraft().fontRenderer;
	public static final int DEFAULT_LINEHEIGHT = FONTRENDERER.FONT_HEIGHT;

	private Color color;
	private Align align;
	private int lineHeight = DEFAULT_LINEHEIGHT;
	private boolean hasShadow = false;
	
    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

	public TextStyle extend()
	{
		return new TextStyle()
			.setColor( this.color )
			.setAlign( this.align )
			.setLineHeight( this.lineHeight )
			.setShadow( this.hasShadow );
	}

	public Optional<Color> getColor()
	{
		return Optional.ofNullable( this.color );
	}
	
	public TextStyle setColor( Color color )
	{
		this.color = color;
		
		return this;
	}
	
	public Optional<Align> getAlign()
	{
		return Optional.ofNullable( this.align );
	}
	
	public TextStyle setAlign( Align align )
	{
		this.align = align;
		
		return this;
	}
	
	public TextStyle setLineHeight( int lineHeight )
	{
		this.lineHeight = lineHeight;
				
		return this;
	}
	
	public int getLineHeight()
	{
		return this.lineHeight;
	}
	
	public TextStyle setShadow( boolean shadow )
	{
		this.hasShadow = shadow;
		
		return this;
	}
	
	public boolean hasShadow()
	{
		return this.hasShadow;
	}

	public void drawTextAt( int x, int y, int width, int height, String text )
	{
		this.drawTextAt(
			x, y, width, height, text,
			(this.color!=null) ? this.color.value : Color.BLACK,
			(this.align!=null) ? this.align : Align.LEFT
		);
	}

	public void drawTextAt( int x, int y, int width, int height, String text, int color, Align align )
	{
		int textWidth = Math.min( width, FONTRENDERER.getStringWidth( text ) );

		/*
		 * Workaround
		 * @todo implement padding
		 */
		x+=2;y+=2;width-=2;height-=2;

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
				y += height/2 - this.lineHeight/2;
				break;
			case CENTER:
				y += height/2 - this.lineHeight/2;
				x += width/2 - textWidth/2;
				break;
			case RIGHT:
				x += width - textWidth;
				y += height/2 - this.lineHeight/2;
				break;

			case BOTTOMLEFT:
				y+= height - this.lineHeight;
				break;
			case BOTTOM:
				x += width/2 - textWidth/2;
				y+= height - this.lineHeight;
				break;
			case BOTTOMRIGHT:
				x += width - textWidth;
				y+= height - this.lineHeight;
				break;
			default:
				//Nothing to do
				break;
		}

		if( this.hasShadow )
		{
			FONTRENDERER.drawStringWithShadow( text, x, y, color );
		}
		else
		{
			FONTRENDERER.drawString( text, x, y, color );
		}
	}

    /* **************************************************************************************************************
     * Method - Implement Drawable
     ************************************************************************************************************** */

	@Override
	public void drawOn( T element )
	{
		int x = element.box.getX(),
			y = element.box.getY(),
			width = element.box.getWidth(),
			height = element.box.getHeight(),
			color = (this.color!=null) ? this.color.value : Color.BLACK;
		Align align = (this.align!=null) ? this.align : Align.LEFT;

		//Text
		if( element.countTextlines() == 1 )
		{
			element.getTextline().ifPresent( text -> this.drawTextAt( x, y, width, height, text, color, align ) );
		}
		else if( element.countTextlines() > 1 )
		{
			int lineY = y;

			for( String text : element.getTextlines() )
			{
				this.drawTextAt( x, lineY, width, height, text, color, align );
				lineY += this.lineHeight;
			}
		}
		else
		{
			if( element instanceof Placeholder )
			{
				//Don't draw placeholder if element has focus
				if( ( element instanceof Focusable ) && ( ((Focusable)element).isFocused() ) ) { return; }

				Placeholder<Element> ph = (Placeholder)element;
				//int colorPH = Colors.modifyDarker( this.color, 0.8f).value;

				ph.getPlaceholder().ifPresent( text -> this.drawTextAt( x, y, width, height, text, color, align ) );
			}
		}
	}
}
