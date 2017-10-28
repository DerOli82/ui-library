package de.alaoli.games.minecraft.mods.lib.ui.drawable;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import net.minecraft.client.gui.Gui;

public class Border extends Gui implements Drawable<Element>
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/
	
	private Color color;
	
	public boolean hideTop = false;
	public boolean hideLeft = false;
	public boolean hideRight = false;
	public boolean hideBottom = false;
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public Border() {}
	
	public Border( Color color ) 
	{
		this.color = color;
	}
		
	public Optional<Color> getColor()
	{
		return Optional.ofNullable( this.color );
	}
	
	public Border setColor( Color color )
	{
		this.color = color;
		
		return this;
	}
	
	public Border hide( boolean hide )
	{
		this.hideTop = hide;
		this.hideLeft = hide;
		this.hideRight = hide;
		this.hideBottom = hide;
		
		return this;
	}

	public Border hide( boolean top, boolean left, boolean right, boolean bottom )
	{
		this.hideTop = top;
		this.hideLeft = left;
		this.hideRight = right;
		this.hideBottom = bottom;

		return this;
	}

	/******************************************************************************************
	 * Method - Implement Drawable
	 ******************************************************************************************/
	
	@Override
	public void drawOn( Element element )
	{
		int color = (this.color!=null) ? this.color.value : Color.DEFAULT,
			width = element.box.getWidth(),
			height = element.box.getHeight(),
			x = element.box.getX(),
			y = element.box.getY();

		if( !this.hideTop ) { this.drawHorizontalLine( x, x+width-1, y, color ); }
		if( !this.hideLeft ) { this.drawVerticalLine( x, y, y+height, color ); }
		if( !this.hideRight ) { this.drawVerticalLine( x+width-1, y, y+height, color ); }
		if( !this.hideBottom ) { this.drawHorizontalLine( x, x+width-1, y+height-1, color ); }
	}
}
