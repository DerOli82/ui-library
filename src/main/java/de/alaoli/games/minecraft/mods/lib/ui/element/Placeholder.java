package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.Optional;

public interface Placeholder<T extends Element>
{
    Optional<String> getPlaceholder();
    T setPlaceholder( String placeholder);
}
