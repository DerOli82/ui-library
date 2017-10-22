package de.alaoli.games.minecraft.mods.lib.ui.util;

public interface Disableable<T extends Disableable<T>>
{
    T setDisable(boolean disable);
    boolean isDisabled();
}
