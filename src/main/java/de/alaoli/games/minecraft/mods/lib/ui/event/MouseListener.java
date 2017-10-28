package de.alaoli.games.minecraft.mods.lib.ui.event;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;

import java.util.function.Consumer;

public interface MouseListener<T extends Element> extends InputListener
{
	void mouseEntered( MouseEvent event );
	void mouseExited( MouseEvent event );
	void mouseClicked( MouseEvent event );

	T onMouseEntered( Consumer<? super MouseEvent> consumer );
	T onMouseExited( Consumer<? super MouseEvent> consumer );
	T onMouseClicked( Consumer<? super MouseEvent> consumer );
}
