package de.alaoli.games.minecraft.mods.lib.ui.element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

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
import de.alaoli.games.minecraft.mods.lib.ui.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

public class TextField extends Element<TextField>
	implements Text<TextField>, Placeholder<TextField>,
		Focusable<TextField>, Hoverable<TextField>, Disableable<TextField>,
		MouseListener<TextField>, KeyboardListener<TextField>
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/

	public static final FontRenderer FONTRENDERER = Minecraft.getMinecraft().fontRenderer;

	private boolean focused = false;
	private boolean hovered = false;
	private boolean disabled = false;

	private StateableStyle<BoxStyle> boxStyle;
	private StateableStyle<TextStyle> textStyle;

	private Consumer<? super MouseEvent> onEntered;
	private Consumer<? super MouseEvent> onExited;
	private Consumer<? super MouseEvent> onClicked;

	private Consumer<? super KeyboardEvent> onKeyPressed;

	private String placeholder;
	private String text = "";
	private int maxLength = 32;
	private int cursorPos = 0;

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/

	public Optional<StateableStyle<BoxStyle>> getBoxStyle()
	{
		return Optional.ofNullable( this.boxStyle );
	}

	public TextField setBoxStyle( StateableStyle<BoxStyle> boxStyle )
	{
		this.boxStyle = boxStyle;

		return this;
	}

	public Optional<StateableStyle<TextStyle>> getTextStyle()
	{
		return Optional.ofNullable( this.textStyle );
	}

	public TextField setTextStyle( StateableStyle<TextStyle> textStyle )
	{
		this.textStyle = textStyle;

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

	/**
	 * @deprecated
	 * @param text
	 * @return
	 */
	public TextField setText( String text )
	{
		this.text = text;
		this.cursorPos = this.text.length();

		return this;
	}

	public State getState()
	{
		if( this.disabled ) {return State.DISABLED; }
		if( this.focused ) {return State.FOCUSED; }
		if( this.hovered ) {return State.HOVERED; }

		return State.NONE;
	}

	public int getMaxLength()
	{
		return this.maxLength;
	}

	public TextField setMaxLength( int maxLength )
	{
		this.maxLength = maxLength;

		return this;
	}

	public boolean writeCharAt( int pos, char c )
	{
		if( ( !this.disabled ) &&
			( ChatAllowedCharacters.isAllowedCharacter( c ) ) &&
			( pos >= 0 ) &&
			( pos <= Math.min( this.text.length(), this.maxLength)))
		{
			this.text = this.text.substring( 0, pos ) + c + this.text.substring( pos );

			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean removeCharAt( int pos )
	{
		if( ( !this.disabled ) &&
			( !this.text.isEmpty() ) &&
			( pos >= 0 ) &&
			( pos <= this.text.length() ) )
		{
			this.text = this.text.substring( 0, pos ) + this.text.substring( pos+1 );

			return true;
		}
		else
		{
			return false;
		}
	}

	/******************************************************************************************
	 * Method - Implement Element 
	 ******************************************************************************************/

	@Override
	public void drawElement( int mouseX, int mouseY, float partialTicks )
	{
		State state = this.getState();

		if( this.boxStyle != null ) { this.boxStyle.get(state).ifPresent( style -> style.drawOn( this ) ); }
		if( this.textStyle != null )
		{
			this.textStyle.get(state).ifPresent( style -> {
				style.drawOn( this );

				//Cursor if focused
				if( ( this.focused ) &&
					( !this.disabled ) )
				{
					int x = this.box.getX() + FONTRENDERER.getStringWidth( this.text.substring( 0,this.cursorPos ) )+2,
						y = this.box.getY(),
						height = this.box.getHeight(),
						color = ((Optional<Color>)style.getColor()).map( Color::getValue ).orElse( Color.BLACK );
					this.drawVerticalLine( x, y+1, y+height-2, color );
				}
			});
		}


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
	public TextField setTextline( String text )
	{
		this.text = text;

		return this;
	}

	@Override
	public TextField setTextlines(Collection<String> lines)
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

	/******************************************************************************************
	 * Method - Implement Placeholder
	 ******************************************************************************************/

	@Override
	public Optional<String> getPlaceholder()
	{
		return Optional.ofNullable( this.placeholder );
	}

	@Override
	public TextField setPlaceholder( String placeholder)
	{
		this.placeholder = placeholder;

		return this;
	}

	/******************************************************************************************
	 * Method - Implement Focusable
	 ******************************************************************************************/

	@Override
	public TextField setFocus( boolean focus )
	{
		this.focused = focus;

		return this;
	}

	@Override
	public boolean isFocused()
	{
		return this.focused;
	}

	/******************************************************************************************
	 * Method - Implement Hoverable
	 ******************************************************************************************/

	@Override
	public TextField setHover(boolean hover)
	{
		this.hovered = hover;

		return this;
	}

	@Override
	public boolean isHovered()
	{
		return this.hovered;
	}

	/******************************************************************************************
	 * Method - Implement Disableable
	 ******************************************************************************************/

	@Override
	public TextField setDisable(boolean disable)
	{
		this.disabled = disable;

		return this;
	}

	public boolean isDisabled()
	{
		return this.disabled;
	}

	/******************************************************************************************
	 * Method - Implements MouseListener
	 ******************************************************************************************/


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
	public TextField onMouseEntered( Consumer<? super MouseEvent> consumer )
	{
		this.onEntered = consumer;

		return this;
	}

	@Override
	public TextField onMouseExited( Consumer<? super MouseEvent> consumer )
	{
		this.onExited = consumer;

		return this;
	}

	@Override
	public TextField onMouseClicked( Consumer<? super MouseEvent> consumer )
	{
		this.onClicked = consumer;

		return this;
	}

	/******************************************************************************************
	 * Method - Implements KeyboardListener
	 ******************************************************************************************/
	
	@Override
	public void keyPressed( KeyboardEvent event )
	{
		if( !this.focused ) { return; }

		switch( event.eventKey ) {
			case Keyboard.KEY_LEFT:
				if (this.cursorPos > 0) { this.cursorPos--; }
				break;

			case Keyboard.KEY_RIGHT:
				if (this.cursorPos < this.text.length()) { this.cursorPos++; }
				break;

			case Keyboard.KEY_HOME:
				this.cursorPos = 0;
				break;

			case Keyboard.KEY_END:
				this.cursorPos = this.text.length();
				break;

			case Keyboard.KEY_BACK:
				if( this.cursorPos > 0 ) { this.removeCharAt( --this.cursorPos ); }
				break;

			case Keyboard.KEY_DELETE:
				if( this.cursorPos < this.text.length() ) { this.removeCharAt( this.cursorPos ); }
				break;

			default:
				if( this.writeCharAt(this.cursorPos, event.eventChar) ) { this.cursorPos++; }
				break;
		}
		if( this.onKeyPressed != null ) { this.onKeyPressed.accept( event ); }
	}

	@Override
	public TextField onKeyPressed(  Consumer<? super KeyboardEvent> consumer )
	{
		this.onKeyPressed = consumer;

		return this;
	}
}
