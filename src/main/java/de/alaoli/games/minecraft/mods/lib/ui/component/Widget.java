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

import de.alaoli.games.minecraft.mods.lib.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseListener;
import de.alaoli.games.minecraft.mods.lib.ui.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.style.Region;
import de.alaoli.games.minecraft.mods.lib.ui.style.Styles;
import de.alaoli.games.minecraft.mods.lib.ui.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.util.Text;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class Widget extends AbstractComponent implements BoxComponent, TextComponent, Layout, MouseListener
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private Label labelHeadTitle;
    private Button buttonClose;

    private Layout content;

    private BoxStyle boxStyle;
    private boolean needsLayout = true;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    Widget( WidgetBuilder<?> builder )
    {
        super( builder );

        this.labelHeadTitle = builder.labelBuilder.build();
        this.buttonClose = builder.buttonBuilder.build();
        this.content = builder.content;

        this.setBoxStyle( builder.boxStyleBuilder );

        this.addComponent( this.labelHeadTitle, this.buttonClose, this.content );
    }

    /* *************************************************************************************************************
     * Method - Implement RegionListener
     ************************************************************************************************************* */

    @Override
    public void onRegionChanged(Region oldRegion, Region newRegion )
    {
        super.onRegionChanged( oldRegion, newRegion );

        this.invalidateLayout();
    }

    /* *************************************************************************************************************
     * Method - Implement BoxComponent
     ************************************************************************************************************* */

    @Override
    public BoxStyle getBoxStyle()
    {
        return this.boxStyle;
    }

    @Override
    public void setBoxStyle( BoxStyle boxStyle )
    {
        this.boxStyle = Styles.valueOrEmpty( boxStyle );
    }

    /* *************************************************************************************************************
     * Method - Implement TextComponent
     ************************************************************************************************************* */

    @Override
    public Text getText()
    {
        return this.labelHeadTitle.getText();
    }

    @Override
    public TextStyle getTextStyle()
    {
        return this.labelHeadTitle.getTextStyle();
    }

    @Override
    public void setTextStyle( TextStyle textStyle )
    {
        this.labelHeadTitle.setTextStyle( textStyle );
    }

    /* *************************************************************************************************************
     * Method - Implement Layout
     ************************************************************************************************************* */

    @Override
    public void layout()
    {
        Region region = this.getRegion();
        Region regionButton = this.buttonClose.getRegion();
        int
            y = region.getY(),
            width = region.getWidth(),
            height = region.getHeight(),
            heightLabel = this.labelHeadTitle.getRegion().getHeight();

        this.labelHeadTitle.setRegion( region.toBuilder()
            .withHeight( heightLabel )
        .build());

        this.buttonClose.setRegion( regionButton.toBuilder()
            .withPosition( width - regionButton.getWidth(), y )
        .build() );

        if( this.content != null )
        {
            this.content.setRegion( region.toBuilder()
                .withYPosition( y + heightLabel )
                .withDimensions( width,height - heightLabel )
            .build());
        }
    }

    @Override
    public void validateLayout()
    {
        if( this.needsLayout )
        {
            this.layout();
            this.needsLayout = false;
        }
    }

    @Override
    public void invalidateLayout()
    {
        this.needsLayout = true;
    }

    /* *************************************************************************************************************
     * Method - Implement MouseListener
     ************************************************************************************************************* */

    @Override
    public void onMouseEnter( MouseEvent.Enter event )
    {
        if( ( this.buttonClose.getRegion().contains( event.getX(), event.getY() ) ) &&
            ( !this.buttonClose.isFocused() ) )
        {
            this.buttonClose.setFocus( true );
            this.buttonClose.onMouseEnter( event );
            this.streamListeners()
                .filter( listener -> listener instanceof MouseListener )
                .forEach( listener -> ((MouseListener)listener).onMouseEnter( event ) );
        }
    }

    @Override
    public void onMouseLeave( MouseEvent.Leave event )
    {
        if( ( !this.buttonClose.getRegion().contains( event.getX(), event.getY() ) ) &&
            ( this.buttonClose.isFocused() ) )
        {
            this.buttonClose.setFocus( false );
            this.buttonClose.onMouseLeave( event );
            this.streamListeners()
                .filter( listener -> listener instanceof MouseListener )
                .forEach( listener -> ((MouseListener)listener).onMouseLeave( event ) );
        }
    }

    @Override
    public void onMouseClick( MouseEvent.Click event )
    {
        if( this.buttonClose.getRegion().contains(  event.getX(), event.getY()  ) )
        {
            this.buttonClose.onMouseClick( event );
            this.streamListeners()
                .filter(listener -> listener instanceof MouseListener)
                .forEach(listener -> ((MouseListener) listener).onMouseClick(event));
        }
    }
}