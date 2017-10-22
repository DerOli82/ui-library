package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementGroup<T extends ElementGroup<T>> extends Element<ElementGroup<T>>
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/

	private final List<Element> elements = new ArrayList<>();

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
/*
	@Override
	public void addComponent( Element component )
	{
		this.elements.add( component );
		
		component.setElementParent( this );
	}
	
	@Override
	public void addComponents( Collection<Element> components )
	{
		components.forEach(this::addComponent);
	}

	@Override
	public void removeComponent( Element component )
	{
		if( this.elements.remove( component ) )
		{
			component.setElementParent( null );
		}
	}

	@Override
	public void removeComponents( Collection<Element> components ) 
	{
		components.forEach(this::removeComponent);
	}
	
	@Override
	public boolean hasComponents()
	{
		return !this.elements.isEmpty();
	}
	
	@Override
	public boolean existsComponent( Element component )
	{
		return this.elements.contains( component );
	}	

	@Override
	public Collection<Element> getComponents() 
	{
		return this.elements;
	}
	
	@Override
	public void clearComponents()
	{
		this.elements.forEach( element -> {
				if( element instanceof ElementGroup ) { ((ElementGroup)element).clearComponents(); }
				element.setElementParent( null );
		});
		this.elements.clear();
	}
	*/
	/******************************************************************************************
	 * Method - Implement Element
	 ******************************************************************************************/
	
	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		this.elements.forEach( element -> element.drawElement( mouseX, mouseY, partialTicks )  );
	}	
}
