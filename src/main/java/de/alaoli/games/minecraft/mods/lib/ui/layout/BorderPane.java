package de.alaoli.games.minecraft.mods.lib.ui.layout;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.element.ElementGroup;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.Box;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;

public class BorderPane extends ElementGroup<BorderPane> implements Box<BorderPane>, Layout
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */
	
	public final static List<Align> ALLOWED_ALIGNS = Collections.unmodifiableList( Arrays.asList( Align.TOP, Align.LEFT, Align.CENTER, Align.RIGHT, Align.BOTTOM ) );
		
	private final Map<Align, Element> borders = new HashMap<>();
	private BoxStyle boxStyle;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Optional<Element> getBorder( Align align )
	{
		if( !BorderPane.ALLOWED_ALIGNS.contains( align ) ) { throw new IllegalArgumentException( "Alignment '" + align + "' is not allowed." ); }

		return Optional.ofNullable( this.borders.get(align) );
	}

	public BorderPane setBorder( Align align, Element element )
	{
		if( !BorderPane.ALLOWED_ALIGNS.contains( align ) ) { throw new IllegalArgumentException( "Alignment '" + align + "' is not allowed." ); }
		
		element.setParent( this );
		this.borders.put( align, element );
		
		return this;
	}

	public void removeBorder( Align align )
	{
		if( this.borders.containsKey( align ) )
		{
			this.borders.get( align ).setParent( null );
			this.borders.remove( align );
		}
	}
	
	public boolean hasBorder( Align align )
	{
		return this.borders.containsKey( align );
	}

	public void clear()
	{
		this.borders.clear();
	}

	/* **************************************************************************************************************
	 * Method - Implements ElementGroup
	 ************************************************************************************************************** */

	public Collection<Element> getElements()
	{
		return this.borders.values();
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
	 * Method - Implements Box
	 ************************************************************************************************************** */

	public BorderPane setBoxStyle( BoxStyle style )
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

		this.borders.values()
			.stream()
			.filter( element -> element instanceof Layout )
			.forEach( element -> ((Layout)element).layout() );
	}

	@Override
	public void doLayout() 
	{
		if( this.borders.isEmpty() ) { return; }
		if( this.boxStyle != null ) { this.boxStyle.applyMarginOn( this ); }

		Element border;

		int x = this.box.getX(),
			y = this.box.getY(),
			width = this.box.getWidth(),
			height = this.box.getHeight(),
			top = y,
			left = x,
			right = 0,
			bottom = 0;

		if( this.borders.containsKey( Align.TOP ) )
		{
			border = this.borders.get( Align.TOP );

			border.box.setLocation( left, top );
			border.box.setWidth( width );

			top += border.box.getHeight();
		}
		
		if( this.borders.containsKey( Align.BOTTOM ) )
		{
			border = this.borders.get( Align.BOTTOM );
			bottom = border.box.getHeight();

			border.box.setLocation( left, x + height - bottom );
			border.box.setWidth( width );
		}
		
		if( this.borders.containsKey( Align.LEFT ) )
		{
			border = this.borders.get( Align.LEFT );

			border.box.setLocation( left, top);
			border.box.setHeight( height - top - bottom );

			left += border.box.getWidth();
		}
		
		if( this.borders.containsKey( Align.RIGHT ) )
		{
			border = this.borders.get( Align.RIGHT );

			right += x + width - border.box.getWidth();

			border.box.setLocation( top, right );
			border.box.setHeight( height - top - bottom );
		}
		
		if( this.borders.containsKey( Align.CENTER ) )
		{
			border = this.borders.get( Align.CENTER );

			border.box.setLocation( left, top );
			border.box.setSize(
				x + width - left - right,
				y + height - top - bottom
			);
		}
	}
}
