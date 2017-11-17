/* *************************************************************************************************************
 * Copyright (c) 2017 DerOli82 <https://github.com/DerOli82>
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
 ************************************************************************************************************ */
package de.alaoli.games.minecraft.mods.lib.ui.util;

/**
 * 3x3 Matrix
 *
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class Matrix3
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

    public static final int M00 = 0;
    public static final int M01 = 1;
    public static final int M02 = 2;
    public static final int M10 = 3;
    public static final int M11 = 4;
    public static final int M12 = 5;
    public static final int M20 = 6;
    public static final int M21 = 7;
    public static final int M22 = 8;

    public float[] val = new float[9];

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Matrix3()
    {
        this.setIdentity();
    }

    public Matrix3 setIdentity()
    {
        this.val[M00] = 1;
        this.val[M10] = 0;
        this.val[M20] = 0;

        this.val[M01] = 0;
        this.val[M11] = 1;
        this.val[M21] = 0;

        this.val[M02] = 0;
        this.val[M12] = 0;
        this.val[M22] = 1;

        return this;
    }

    public Matrix3 setTranslation( float x, float y )
    {
        this.val[M00] = 1;
        this.val[M10] = 0;
        this.val[M20] = 0;

        this.val[M01] = 0;
        this.val[M11] = 1;
        this.val[M21] = 0;

        this.val[M02] = x;
        this.val[M12] = y;
        this.val[M22] = 1;

        return this;
    }

    public Matrix3 addTranslation( float x, float y )
    {
        this.val[M02] += x;
        this.val[M12] += y;

        return this;
    }
}
