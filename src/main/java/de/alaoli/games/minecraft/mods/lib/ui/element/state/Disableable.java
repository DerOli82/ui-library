package de.alaoli.games.minecraft.mods.lib.ui.element.state;

public interface Disableable<T extends Disableable<T>>
{
    T setDisable(boolean disable);
    boolean isDisabled();
}
