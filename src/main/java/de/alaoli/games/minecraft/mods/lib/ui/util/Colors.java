package de.alaoli.games.minecraft.mods.lib.ui.util;

/*
 * @// TODO: color pool
 */
public class Colors
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    private Colors() {}

    public static Color factory()
    {
        return factory( Color.BLACK );
    }

    public static Color factory( int rgb )
    {
        return factory( 255, rgb );
    }

    public static Color factory( float alpha, int rgb )
    {
        return factory( (int)(alpha*255), rgb );
    }

    public static Color factory( int alpha, int rgb )
    {
        return new Color( alpha, rgb );
    }

    public static Color factory( int r, int g, int b )
    {
        return factory( 255, r, g, b );
    }

    public static Color factory( float alpha, int r, int g, int b )
    {
        return factory( Math.round( alpha*255 ), r, g, b );
    }

    public static Color factory( int alpha, int r, int g, int b )
    {
        return new Color( alpha, r, g, b);
    }

    public static Color modifyDarker( Color color, float factor )
    {
       // if( factor > 0.0f ) { throw new IllegalArgumentException( "'factor' value must be greater than 0." ); }

        return new Color(
            color.getAlpha(),
            Math.min( (int)(color.getRed()/factor ), 0 ),
            Math.min( (int)(color.getGreen()/factor ), 0 ),
            Math.min( (int)(color.getBlue()/factor ), 0 )
        );
    }
}
