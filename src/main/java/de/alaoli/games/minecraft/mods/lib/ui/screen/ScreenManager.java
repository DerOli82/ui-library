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
package de.alaoli.games.minecraft.mods.lib.ui.screen;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class ScreenManager
{
    /* *************************************************************************************************************
     * Attribute
     ************************************************************************************************************* */

    private static final ScreenManager INSTANCE = new ScreenManager();

    private Screen currScreen;
    private Screen nextScreen;

    /* *************************************************************************************************************
     * Method
     ************************************************************************************************************* */

    private ScreenManager() {}

    public static void register()
    {
        MinecraftForge.EVENT_BUS.register( INSTANCE );
    }

    public static void unregister()
    {
        MinecraftForge.EVENT_BUS.unregister( INSTANCE );
    }

    public static <S extends Screen> S create( Class<S> screenClass ) throws IllegalAccessException, InstantiationException
    {
        S screen = screenClass.newInstance();
        screen.create();

        return screen;
    }

    public static <S extends Screen> S show( Class<S> screenClass ) throws InstantiationException, IllegalAccessException
    {
        return show( create( screenClass ) );
    }

    public static <S extends Screen> S show( S screen ) throws InstantiationException, IllegalAccessException
    {
        INSTANCE.nextScreen = screen;

        return screen;
    }

    public static void hide()
    {
        if( INSTANCE.currScreen == null ) { return; }

        Minecraft mc = Minecraft.getMinecraft();

        if( mc.currentScreen == INSTANCE.currScreen )
        {
            INSTANCE.currScreen.hide();
            mc.displayGuiScreen( null );

            if ( mc.currentScreen == null ) { mc.setIngameFocus(); }
        }
        INSTANCE.currScreen = null;
    }

    /* **************************************************************************************************************
     * Method - Event
     ************************************************************************************************************** */

    @SubscribeEvent
    public void openScreenEvent( TickEvent.ClientTickEvent event )
    {
        if( ( this.nextScreen != null ) &&
            ( event.phase == TickEvent.Phase.START ) )
        {
            Minecraft mc = Minecraft.getMinecraft();

            if( mc != null )
            {
                mc.displayGuiScreen( this.nextScreen );

                this.nextScreen.show();
                this.nextScreen.resize( this.nextScreen.width, this.nextScreen.height );

                this.currScreen = this.nextScreen;
            }
            this.nextScreen = null;
        }
    }
}