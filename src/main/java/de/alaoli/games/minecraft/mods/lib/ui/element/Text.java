package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.Collection;
import java.util.Optional;

public interface Text<T extends Element<T>>
{
    Optional<String> getTextline();
    Optional<String> getTextline( int line );
    Collection<String> getTextlines();
    T setTextline( String text );
    T setTextline( String text, int line );
    T setTextlines( Collection<String> lines );

    int countTextlines();
}
