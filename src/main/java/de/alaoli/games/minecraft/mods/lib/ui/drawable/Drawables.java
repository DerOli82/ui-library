/* *************************************************************************************************************
 * Copyright (c) 2018 DerOli82 <https://github.com/DerOli82>
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
package de.alaoli.games.minecraft.mods.lib.ui.drawable;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public enum Drawables
{
   NONE,
   BORDER,
   BACKGROUND,
   BACKGROUND_BUTTON,
   BACKGROUND_OPTIONS;

   public static Drawable get( Drawables drawables )
   {
      switch( drawables )
      {
         case BORDER:
            return StatelessBorder.DEFAULT;

         case BACKGROUND:
            return StatelessBackground.DEFAULT;

         case BACKGROUND_BUTTON:
            return new StatefulButtonBackground();

         case BACKGROUND_OPTIONS:
            return OptionsBackground.DEFAULT;

         case NONE:
         default:
            return NoneDrawable.DEFAULT;
      }
   }
}
