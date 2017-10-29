package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.Collection;

public abstract class ElementGroup<T extends ElementGroup<? super T>> extends Element<T>
{
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public abstract Collection<Element> getElements();

	/* **************************************************************************************************************
	 * Method - Implement Element
	 ************************************************************************************************************** */
	
	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		this.getElements().forEach( element -> element.drawElement( mouseX, mouseY, partialTicks ) );
	}	
}
