package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.Optional;

import org.lwjgl.util.Rectangle;

import net.minecraft.client.gui.Gui;

public abstract class Element<T extends Element> extends Gui
{
	/* **************************************************************************************************************
	 * Attribute 
	 ************************************************************************************************************** */

	public final Rectangle box = new Rectangle();
	private Element parent;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Optional<Element> getParent()
	{
		return Optional.ofNullable( this.parent );
	}

	public T setParent(Element parent )
	{
		this.parent = parent;

		return (T)this;
	}

	public boolean hasParent()
	{
		return this.parent != null;
	}

	public T setBounds(int posX, int posY, int width, int height )
	{
		this.box.setBounds( posX, posY, width, height );

		return (T)this;
	}

	public T setLocation( int posX, int posY )
	{
		this.box.setLocation( posX, posY );

		return (T)this;
	}

	public T setPosX( int posX )
	{
		this.box.setX( posX );

		return (T)this;
	}

	public T setPosY( int posY )
	{
		this.box.setY( posY );

		return (T)this;
	}

	public T setSize( int width, int height )
	{
		this.box.setSize( width, height );

		return (T)this;
	}

	public T setWidth(int width )
	{
		this.box.setWidth( width );

		return (T)this;
	}

	public T setHeight(int height )
	{
		this.box.setHeight( height );

		return (T)this;
	}

	public abstract void drawElement( int mouseX, int mouseY, float partialTicks  );
}
