package de.alaoli.games.minecraft.mods.lib.ui.element.state;

public interface Focusable<T extends Focusable<T>>
{
	T setFocus( boolean focus );
	boolean isFocused();
}
