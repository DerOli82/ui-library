package de.alaoli.games.minecraft.mods.lib.ui.element.style;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;

import java.util.Optional;

public interface Box<T extends Element>
{
    T setBoxStyle( BoxStyle style );
    Optional<BoxStyle> getBoxStyle();
}
