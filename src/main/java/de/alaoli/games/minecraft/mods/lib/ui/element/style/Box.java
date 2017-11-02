package de.alaoli.games.minecraft.mods.lib.ui.element.style;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;

import java.util.Optional;

/**
 * @deprecated
 * @param <T>
 */
public interface Box<T extends Element>
{
    T setBoxStyle( BoxStyling style );
    Optional<BoxStyling> getBoxStyle();
}
