/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or toBuilder
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a toBuilder of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.lib.ui.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class Text
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private final StringBuffer value = new StringBuffer();
    private Queue<String> words;

    private int maxLength;
    private int maxLines;

    private int cursorPos;
    private int cursorLine;

    private boolean hasCursor = false;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    public Text()
    {
        this.value.setLength( 0 );
    }

    @Override
    public String toString()
    {
        return this.value.toString();
    }

    public boolean isEmpty()
    {
        return this.value.length() == 0;
    }

    public int length()
    {
        return this.value.length();
    }

    public Queue<String> getWords()
    {
        if( this.words == null )
        {
            this.words = new LinkedList<>(Arrays.asList(this.value.toString().split(" ")));
        }
        return this.words;
    }

    public void setMaxLength( int maxLength )
    {
        this.maxLength = maxLength;
    }

    public void setMaxLines( int maxLines )
    {
        this.maxLines = maxLines;
    }

    public void setValue( String value )
    {
        value = (value.length() > this.maxLength) ? value.substring( 0, this.maxLength) : value;

        this.value.setLength( 0 );
        this.value.append( value );
        this.cursorPosLast();

        this.words = null;
    }

    public void insert( char c )
    {
        if( this.length() == this.maxLength) { return; }

        this.value.insert( this.cursorPos, c );
        this.cursorPosNext();

        this.words = null;
    }

    public void delete()
    {
        if( this.isEmpty() || this.cursorPos >= this.length() ) { return; }

        this.value.deleteCharAt( this.cursorPos );

        this.words = null;
    }

    public void showCursor()
    {
        this.hasCursor = true;
    }

    public void hideCursor()
    {
        this.hasCursor = false;
    }

    public boolean hasCursor()
    {
        return this.hasCursor;
    }

    public int getCursorPos()
    {
        return this.cursorPos;
    }

    public void setCursorPos( int cursorPos )
    {
        this.cursorPos = cursorPos;
    }

    public void cursorPosFirst()
    {
        this.cursorPos = 0;
    }

    public void cursorPosLast()
    {
        this.cursorPos = Math.min( this.value.length(), this.maxLength);
    }

    public void cursorPosNext()
    {
        this.cursorPos = Math.min( ++this.cursorPos, this.value.length() );
    }

    public void cursorPosPrev()
    {
        this.cursorPos = Math.max( --this.cursorPos, 0 );
    }

    public int getCursorLine()
    {
        return this.cursorLine;
    }

    public void setCursorLine( int cursorLine )
    {
        this.cursorLine = cursorLine;
    }

    public void cursorLineFirst()
    {
        this.cursorLine = 0;
    }

    public void cursorLineLast()
    {
        this.cursorLine = this.maxLines;
    }

    public void cursorLineNext()
    {
        this.cursorLine = Math.min( ++this.cursorLine, this.maxLines );
    }

    public void cursorLinePrev()
    {
        this.cursorLine = Math.max( --this.cursorLine, 0 );
    }
}