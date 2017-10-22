package de.alaoli.games.minecraft.mods.lib.ui.drawable;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;

public interface Drawable<T extends Element>
{
	void drawOn( T element );
}
