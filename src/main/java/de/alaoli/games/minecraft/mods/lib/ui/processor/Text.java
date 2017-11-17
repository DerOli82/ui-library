package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.Collection;
import java.util.Optional;

public interface Text<T extends Element<T>>
{
    Optional<String> getTextline();
    Collection<String> getTextlines();
    T setTextline( String text );
    T setTextlines( Collection<String> lines );

    int countTextlines();
}
