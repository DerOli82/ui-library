package de.alaoli.games.minecraft.mods.lib.ui.event;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;

import java.util.function.Consumer;

public interface KeyboardListener<T extends Element> extends InputListener
{
	void keyPressed(KeyboardEvent event);

	T onKeyPressed(  Consumer<? super KeyboardEvent> consumer );
}
