/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.lib.ui.component;

import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardEvent;
import de.alaoli.games.minecraft.mods.lib.ui.util.Text;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class TextField extends InputComponent
{
    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    TextField( TextFieldBuilder<?> builder )
    {
        super( builder );
    }

    /* *************************************************************************************************************
     * Method - Implement MouseListener
     ************************************************************************************************************* */

    @Override
    public void onKeyPressed( KeyboardEvent event )
    {
        if( !this.isFocused() ) { return; }

        Text text = this.getText();

        switch( event.getEventKey() )
        {
            case Keyboard.KEY_LEFT:
                text.cursorPosPrev();
                break;

            case Keyboard.KEY_RIGHT:
                text.cursorPosNext();
                break;

            case Keyboard.KEY_HOME:
                text.cursorPosFirst();
                break;

            case Keyboard.KEY_END:
                text.cursorPosLast();
                break;

            case Keyboard.KEY_BACK:
                text.cursorPosPrev();
                text.delete();
                break;

            case Keyboard.KEY_DELETE:
                text.delete();
                break;

            default:
                char c = event.getEventChar();

                if( ChatAllowedCharacters.isAllowedCharacter( c ) ) { text.insert( c ); }
                break;
        }
        super.onKeyPressed( event );
    }
}