package de.alaoli.games.minecraft.mods.lib.ui.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;

public class Pane extends AbstractPane<Pane> 
{
	/****************************************************************************************************
	 * Attribute
	 ****************************************************************************************************/

	private final List<Element> elements = new ArrayList<>();

	/****************************************************************************************************
	 * Method 
	 ****************************************************************************************************/
	
	public Pane addElement( Element element )
	{
		element.setElementParent( this );
		this.elements.add(element);
		
		return this;
	}
	
	/****************************************************************************************************
	 * Method - Implements AbstractPane
	 ****************************************************************************************************/
	
	@Override
	public Optional<Collection<Element>> getElements()
	{
		return Optional.of( this.elements );
	}

	/****************************************************************************************************
	 * Method - Implements Layout
	 ****************************************************************************************************/
		

	@Override
	public void doLayout() 
	{
		super.doLayout();

		int x = this.box.getX();
		int y = this.box.getY();

		for( Element element : this.elements )
		{
			element.box.translate( x, y );
		}
	}
	
}
