package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyle;

public class Label extends Element<Label> implements Text<Label>
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	private TextStyle textStyle;
	private String text;
	
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Label setText( String text )
	{
		this.text = text;
		
		return this;
	}	
	
	public Optional<String> getText() 
	{
		return Optional.ofNullable( this.text );
	}
	
	public Label setTextStyle( TextStyle textStyle )
	{
		this.textStyle = textStyle;
		
		return this;
	}
	
	public Optional<TextStyle> getTextStyle()
	{
		return Optional.ofNullable( this.textStyle );
	}
	
	/* **************************************************************************************************************
	 * Method - Implement Element
	 ************************************************************************************************************** */

	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		if( this.textStyle != null ) { this.textStyle.drawOn( this ); }
	}

	/* **************************************************************************************************************
	 * Method - Implement Text
	 ************************************************************************************************************** */

	@Override
	public Optional<String> getTextline()
	{
		return Optional.ofNullable( this.text );
	}

	@Override
	public Collection<String> getTextlines()
	{
		Collection<String> result = new ArrayList<>();

		if( this.text != null )
		{
			result.add( this.text );
		}
		return result;
	}

	@Override
	public Label setTextline( String text )
	{
		this.text = text;

		return this;
	}

	@Override
	public Label setTextlines(Collection<String> lines)
	{
		if( lines != null )
		{
			StringBuilder builder = new StringBuilder( " " );
			lines.forEach(builder::append);
			this.text = builder.toString();
		}
		return this;
	}

	@Override
	public int countTextlines()
	{
		return 1;
	}
}
