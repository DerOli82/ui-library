package de.alaoli.games.minecraft.mods.lib.ui.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.element.ElementGroup;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.Box;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.BoxStyle;

public class VBox extends ElementGroup<VBox> implements Box<VBox>, Layout
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	private final List<Element> elements = new ArrayList<>();
	private BoxStyle boxStyle;

	/****************************************************************************************************
	 * Method 
	 ****************************************************************************************************/
	
	public VBox addElement( Element element )
	{
		element.setParent( this );
		this.elements.add(element);
		
		return this;
	}

	public VBox remove( Element element )
	{
		if( this.elements.contains( element ) )
		{
			element.setParent( null );
			this.elements.remove( element );
		}
		return this;
	}

	/* **************************************************************************************************************
	 * Method - Implements Element
	 ************************************************************************************************************** */

	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		if( this.boxStyle != null ) { this.boxStyle.drawOn( this ); }

		super.drawElement(mouseX, mouseY, partialTicks);
	}

	/* **************************************************************************************************************
	 * Method - Implements ElementGroup
	 ************************************************************************************************************** */

	public Collection<Element> getElements()
	{
		return this.elements;
	}

	/* **************************************************************************************************************
	 * Method - Implements Box
	 ************************************************************************************************************** */

	public VBox setBoxStyle( BoxStyle style )
	{
		this.boxStyle = style;

		return this;
	}

	public Optional<BoxStyle> getBoxStyle()
	{
		return Optional.ofNullable( this.boxStyle );
	}

	/* **************************************************************************************************************
	 * Method - Implements Layout
	 ************************************************************************************************************** */

	@Override
	public void layout()
	{
		this.doLayout();

		this.elements
			.stream()
			.filter( element -> element instanceof Layout )
			.forEach( element -> ((Layout)element).layout() );
	}

	@Override
	public void doLayout() 
	{
		int x = this.box.getX(),
			y = this.box.getY(),
			width = this.box.getWidth(),
			height = this.box.getHeight();

		for( Element element : this.elements )
		{
			if( this.boxStyle !=null )
			{
				this.boxStyle.applyAlignOn( this, element );
			}
			else
			{
				element.box.setLocation( x, y );
			}
			y += element.box.getHeight();
		}
	}
}
