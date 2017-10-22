package de.alaoli.games.minecraft.mods.lib.ui.element.style;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.drawable.Drawable;
import de.alaoli.games.minecraft.mods.lib.ui.element.Element;

public class BoxStyle implements Style, Drawable<Element>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */
	
	private Drawable background;
	private Drawable border;
	
	public int paddingTop = 0;
	public int paddingLeft = 0;
	public int paddingRight = 0;
	public int paddingBottom = 0;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */
	
	public Optional<Drawable> getBackground()
	{
		return Optional.ofNullable( this.background );
	}	
	
	public BoxStyle setBackground( Drawable background )
	{
		this.background = background;
		
		return this;
	}

	public Optional<Drawable> getBorder()
	{
		return Optional.ofNullable( this.border );
	}
	
	public BoxStyle setBorder( Drawable border )
	{
		this.border = border;
		
		return this;
	}

	public BoxStyle setPadding( int padding )
	{
		this.paddingTop = padding;
		this.paddingLeft = padding;
		this.paddingRight = padding;
		this.paddingBottom = padding;
		
		return this;
	}

	public BoxStyle setPadding( int top, int left, int right, int bottom )
	{
		this.paddingTop = top;
		this.paddingLeft = left;
		this.paddingRight = right;
		this.paddingBottom = bottom;
		
		return this;
	}

    /* **************************************************************************************************************
     * Method - Implement Drawable
     ************************************************************************************************************** */

	@Override
	public void drawOn( Element element )
	{
		if( this.background != null ) { this.background.drawOn(element); }
		if( this.border != null ) { this.border.drawOn(element); }
	}
}
