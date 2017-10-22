package de.alaoli.games.minecraft.mods.lib.ui.event;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import net.minecraftforge.fml.common.eventhandler.Event;

public class InputEvent extends Event
{
    public final Element element;

    public <E extends Element & InputListener> InputEvent( E element )
    {
        this.element = element;
    }
}
