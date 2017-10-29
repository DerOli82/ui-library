package de.alaoli.games.minecraft.mods.lib.ui.util;

public class Color 
{	
	/********************************************************************************
	 * Attribute
	 ********************************************************************************/

	public static final int BLACK = 0x000000;
	public static final int DARKBLUE = 0x0000AA;
	public static final int DARKGREEN = 0x00AA00;
	public static final int DARKAQUA = 0x00AAAA;
	public static final int DARKRED = 0xAA0000;
	public static final int DARKPURPLE = 0xAA00AA;
	public static final int GOLD = 0xFFAA00;
	public static final int GRAY = 0xAAAAAA;
	public static final int DARKGRAY = 0x555555;
	public static final int BLUE = 0x5555FF;
	public static final int GREEN = 0x55FF55;
	public static final int AQUA = 0x55FFFF;
	public static final int RED = 0xFF5555;
	public static final int LIGHTPURPLE = 0xFF55FF;
	public static final int YELLOW = 0xFFFF55;
	public static final int WHITE = 0xFFFFFF;

	public final int value;
	
	/********************************************************************************
	 * Method
	 ********************************************************************************/

	public Color()
	{
		this( BLACK );
	}

	public Color( int rgb )
	{
		this( 255, rgb );
	}

	public Color( float alpha, int rgb )
	{
		this( (int)(alpha*255), rgb );
	}

	public Color( int alpha, int rgb )
	{
		this.value = argbToIntValue( alpha, rgb );
	}

	public Color( int r, int g, int b )
	{
		this( 255, r, g, b );
	}
	
	public Color( float alpha, int r, int g, int b )
	{
		this( Math.round( alpha*255 ), r, g, b );
	}
	
	public Color( int alpha, int r, int g, int b )
	{
		this.value = argbToIntValue( alpha, r, g, b );
	}

	@Override
	public boolean equals( Object obj )
	{
		return obj instanceof Color && ((Color) obj).value == this.value;
	}

	@Override
	public int hashCode() 
	{
		return this.value;
	}
	
	public final int getValue()
	{
		return this.value;
	}

	public int getAlpha() {
		return (this.value >> 24) & 0xff;
	}

	public int getRed() {
		return (this.value >> 16) & 0xFF;
	}

	public int getGreen() {
		return (this.value >> 8) & 0xFF;
	}

	public int getBlue() {
		return (this.value) & 0xFF;
	}

	public static int argbToIntValue( int alpha, int r, int g, int b )
	{
		if( alpha < 0 || alpha > 255 ) { throw new IllegalArgumentException( "'alpha' value must be between 0 and 255." ); }
		if( r < 0 || r > 255 ) { throw new IllegalArgumentException( "'r' value must be between 0 and 255." ); }
		if( g < 0 || g > 255 ) { throw new IllegalArgumentException( "'g' value must be between 0 and 255." ); }
		if( b < 0 || b > 255 ) { throw new IllegalArgumentException( "'b' value must be between 0 and 255." ); }

		return ((alpha & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF));
	}

	public static int argbToIntValue( int alpha, int rgb )
	{
		if( alpha < 0 || alpha > 255 ) { throw new IllegalArgumentException( "'alpha' value must be between 0 and 255." ); }
		if( rgb < 0 || rgb > 16777215 ) { throw new IllegalArgumentException( "'rgb' value must be between 0 and 16777215." ); }

		return ((0xFF) << 24) | rgb;
	}
}
