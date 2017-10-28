package de.alaoli.games.minecraft.mods.lib.ui.wrapped;

import de.alaoli.games.minecraft.mods.lib.ui.element.Element;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.Box;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.layout.Layout;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScrollingText extends Element<ScrollingText> implements Layout, Box<ScrollingText>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    public static final FontRenderer FONTRENDERER = Minecraft.getMinecraft().fontRenderer;

    private final List<String> lines = new ArrayList<>();
    private ScrollingList wrapped;

    private BoxStyle boxStyle;
    private TextStyle textStyle;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public ScrollingText setTextStyle(TextStyle textStyle )
    {
        this.textStyle = textStyle;

        return this;
    }

    public Optional<TextStyle> getTextStyle()
    {
        return Optional.ofNullable( this.textStyle );
    }

    public ScrollingText setLines( List<String> lines )
    {
        this.lines.clear();
        if( lines == null ) { return this; }
        this.lines.addAll(lines);

        return this;
    }

    public List<String> getLines()
    {
        return this.lines;
    }

    public Optional<ScrollingList> getScrollingList()
    {
        return Optional.ofNullable( this.wrapped );
    }

    /* **************************************************************************************************************
     * Method - Implement Element
     ************************************************************************************************************** */

    @Override
    public void drawElement( int mouseX, int mouseY, float partialTicks)
    {
        if( this.boxStyle != null ) { this.boxStyle.drawOn( this ); }
        this.wrapped.drawScreen( mouseX, mouseY, partialTicks );
    }

    /* **************************************************************************************************************
     * Method - Implement Layout
     ************************************************************************************************************** */

    @Override
    public void layout()
    {
        this.doLayout();
    }

    @Override
    public void doLayout()
    {
        this.wrapped = new ScrollingList( this );
    }

	/* **************************************************************************************************************
	 * Method - Implements Box
	 ************************************************************************************************************** */

    public ScrollingText setBoxStyle(BoxStyle style )
    {
        this.boxStyle = style;

        return this;
    }

    public Optional<BoxStyle> getBoxStyle()
    {
        return Optional.ofNullable( this.boxStyle );
    }
    /* **************************************************************************************************************
     * Class - GuiScrollingList
     ************************************************************************************************************** */

    public static class ScrollingList extends GuiScrollingList
    {
        private ScrollingText parent;

        public ScrollingList( ScrollingText parent )
        {
            super(
                Minecraft.getMinecraft(),
                parent.box.getWidth()-2,
                parent.box.getHeight()-2,
                parent.box.getY()+1,
                parent.box.getY() + parent.box.getHeight()-1,
                parent.box.getX()+1,
                parent.getTextStyle().map( TextStyle::getLineHeight ).orElse( 0 )
            );
            this.parent = parent;
        }

        /* ****************************************************************************************************
         * Method - Implement GuiScrollingList
         **************************************************************************************************** */

        @Override
        protected int getSize()
        {
            return this.parent.lines.size();
        }

        @Override
        protected void elementClicked(int index, boolean doubleClick) {}

        @Override
        protected boolean isSelected(int index) { return false; }

        @Override
        protected void drawBackground() {}

        @Override
        protected void drawSlot(int index, int right, int y, int height, Tessellator tess)
        {
            /*
             * @TODO text padding
             */
            String line = (this.parent.lines != null) && (this.parent.lines.size() >= index) ? this.parent.lines.get( index ) : null;

            if( ( line == null )||( this.parent.textStyle == null ) ) { return; }

            this.parent.textStyle.drawTextAt( this.parent.box.getX()+3, y, this.listWidth-3, height, line );
        }
    }
}
