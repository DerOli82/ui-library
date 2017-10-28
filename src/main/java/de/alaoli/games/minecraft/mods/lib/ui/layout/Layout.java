package de.alaoli.games.minecraft.mods.lib.ui.layout;

public interface Layout
{
	void layout();
	void doLayout();
	void drawElement( int mouseX, int mouseY, float partialTicks  );
}
