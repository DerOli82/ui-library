package de.alaoli.games.minecraft.mods.lib.ui.element.state;

public interface Hoverable<T extends Hoverable<T>>
{
    T setHover( boolean hover );
    boolean isHovered();
}
