package de.alaoli.games.minecraft.mods.lib.ui.element;

import de.alaoli.games.minecraft.mods.lib.ui.element.state.Disableable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Focusable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.Hoverable;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.StateableStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardListener;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

public class Button extends Element<Button>
	implements Text<Button>,
		Focusable<Button>, Hoverable<Button>, Disableable<Button>,
		MouseListener<Button>, KeyboardListener<Button>
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	public static final FontRenderer FONTRENDERER = Minecraft.getMinecraft().fontRenderer;

	private boolean focused = false;
	private boolean hovered = false;
	private boolean disabled = false;

	private StateableStyle<BoxStyle> boxStyle;
	private StateableStyle<TextStyle> textStyle;

	private String text;

	private Consumer<? super MouseEvent> onEntered;
	private Consumer<? super MouseEvent> onExited;
	private Consumer<? super MouseEvent> onClicked;

	private Consumer<? super KeyboardEvent> onKeyPressed;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Optional<StateableStyle<BoxStyle>> getBoxStyle()
	{
		return Optional.ofNullable( this.boxStyle );
	}

	public Button setBoxStyle( StateableStyle<BoxStyle> boxStyle )
	{
		this.boxStyle = boxStyle;

		return this;
	}

	public Optional<StateableStyle<TextStyle>> getTextStyle()
	{
		return Optional.ofNullable( this.textStyle );
	}

	public Button setTextStyle( StateableStyle<TextStyle> textStyle )
	{
		this.textStyle = textStyle;

		return this;
	}

	/**
	 * @deprecated
	 * @param text
	 * @return
	 */
	public Button setText( String text )
	{
		this.text = text;

		return this;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public Optional<String> getText()
	{
		return Optional.ofNullable( this.text );
	}

	public State getState()
	{
		if( this.disabled ) {return State.DISABLED; }
		if( this.focused ) {return State.FOCUSED; }
		if( this.hovered ) {return State.HOVERED; }

		return State.NONE;
	}

	/* **************************************************************************************************************
	 * Method - Implement Element
	 ************************************************************************************************************** */

	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		State state = this.getState();

		if( this.boxStyle != null ) { this.boxStyle.get(state).ifPresent( style -> style.drawOn( this ) ); }
		if( this.textStyle != null ) { this.textStyle.get(state).ifPresent( style -> style.drawOn( this )); }
	}

	/* **************************************************************************************************************
	 * Method - Implement Text
	 ************************************************************************************************************** */

	@Override
	public Optional<String> getTextline()
	{
		return Optional.ofNullable( this.text );
	}

	@Override
	public Collection<String> getTextlines()
	{
		Collection<String> result = new ArrayList<>();

		if( this.text != null )
		{
			result.add( this.text );
		}
		return result;
	}

	@Override
	public Button setTextline( String text )
	{
		this.text = text;

		return this;
	}

	@Override
	public Button setTextlines(Collection<String> lines)
	{
		if( lines != null )
		{
			StringBuilder builder = new StringBuilder( " " );
			lines.forEach(builder::append);
			this.text = builder.toString();
		}
		return this;
	}

	@Override
	public int countTextlines()
	{
		return ( ( this.text != null ) && ( !this.text.isEmpty() ) ) ? 1 : 0;
	}

    /* **************************************************************************************************************
     * Method - Implement Focusable
     ************************************************************************************************************** */

	@Override
	public Button setFocus( boolean focus )
	{
		this.focused = focus;

		return this;
	}

	@Override
	public boolean isFocused()
	{
		return this.focused;
	}

	/* **************************************************************************************************************
	 * Method - Implement Hoverable
	 ************************************************************************************************************** */

	@Override
	public Button setHover(boolean hover)
	{
		this.hovered = hover;

		return this;
	}

	@Override
	public boolean isHovered()
	{
		return this.hovered;
	}

	/* **************************************************************************************************************
	 * Method - Implement Disableable
	 ************************************************************************************************************** */

	@Override
	public Button setDisable(boolean disable)
	{
		this.disabled = disable;

		return this;
	}

	public boolean isDisabled()
	{
		return this.disabled;
	}

	/* **************************************************************************************************************
	 * Method - Implements MouseListener
	 ************************************************************************************************************** */

	@Override
	public void mouseEntered( MouseEvent event )
	{
		if( this.onEntered != null ) { this.onEntered.accept( event );}
	}

	@Override
	public void mouseExited( MouseEvent event )
	{
		if( this.onExited != null ) { this.onExited.accept( event );}
	}

	@Override
	public void mouseClicked( MouseEvent event )
	{
		if( this.onClicked != null ) { this.onClicked.accept( event );}
	}

	@Override
	public Button onMouseEntered( Consumer<? super MouseEvent> consumer )
	{
		this.onEntered = consumer;

		return this;
	}

	@Override
	public Button onMouseExited( Consumer<? super MouseEvent> consumer )
	{
		this.onExited = consumer;

		return this;
	}

	@Override
	public Button onMouseClicked( Consumer<? super MouseEvent> consumer )
	{
		this.onClicked = consumer;

		return this;
	}

    /* **************************************************************************************************************
     * Method - Implements KeyboardListener
     ************************************************************************************************************** */

	@Override
	public void keyPressed( KeyboardEvent event )
	{
		if( !this.focused ) { return; }

		if( this.onKeyPressed != null ) { this.onKeyPressed.accept( event ); }
	}

	@Override
	public Button onKeyPressed(  Consumer<? super KeyboardEvent> consumer )
	{
		this.onKeyPressed = consumer;

		return this;
	}
}
