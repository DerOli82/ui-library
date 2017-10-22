package de.alaoli.games.minecraft.mods.lib.ui.event;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;

public class MouseClickedEvent extends InputEvent
{
    public final MouseEvent event;

    public <E extends Element & MouseListener> MouseClickedEvent( E element, MouseEvent event )
    {
        super( element );
        this.event = event;
    }
}
