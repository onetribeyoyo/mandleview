/*
 *  Copyright (c) 1997-2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.Color;

import java.util.Enumeration;
import java.util.Vector;


/**
 *  This class represents a parametric ColorMap with curves for red,
 *  green and blue with height, slope, location, and vertical offset.
 *  RGB values are normalized to [0, 255].
 */
public final class ParametricColorMap
    extends ColorMap
{
    //
    // Static methods.
    //

    public static final ParametricColorMap createF5ColorMap() {
        ParametricColorMap pcm = new ParametricColorMap("f5");

        pcm.addRed  (new ParametricFunction( 1.0, 1.0,   3.0, 0.0 ));
        pcm.addGreen(new ParametricFunction( 1.0, 1.0, 200.0, 0.0 ));
        pcm.addBlue (new ParametricFunction( 0.8, 0.33, 10.0, 0.0 ));

        return pcm;
    }

    public static final ParametricColorMap createTerrainColorMap() {
        ParametricColorMap pcm = new ParametricColorMap("Terrain");

        pcm.addRed  (new ParametricFunction( 0.65, 0.65,   9.0, 0.0 ));
        pcm.addGreen(new ParametricFunction( 0.55, 0.35,   6.0, 0.0 ));
        pcm.addBlue (new ParametricFunction( 1.0,  0.0,  500.0, 0.0 ));

        pcm.addRed  (new ParametricFunction( 0.65, 1.0,  600.0, 0.0 ));
        pcm.addGreen(new ParametricFunction( 0.85, 1.0,  400.0, 0.0 ));
        pcm.addBlue (new ParametricFunction( 0.90, 1.0,  400.0, 0.0 ));

        return pcm;
    }

    public static final ParametricColorMap createRainbowColorMap() {
        ParametricColorMap pcm = new ParametricColorMap("Rainbow");

        pcm.addRed  (new ParametricFunction( 1.5, 0.0,  6.0, -0.1 ));
        pcm.addGreen(new ParametricFunction( 1.2, 0.5,  9.0,  0.0 ));
        pcm.addBlue (new ParametricFunction( 1.5, 1.05, 6.0,  0.0 ));

        return pcm;
    }


    //
    // Private data.
    //

    /** The red parameters. */
    public Vector redParameters = new Vector();

    /** The green parameters. */
    public Vector greenParameters = new Vector();

    /** The blue parameters. */
    public Vector blueParameters = new Vector();


    //
    // Constructors.
    //

    /**
     *  Default constructor.
     */
    public ParametricColorMap() {
        super();
    }

    public ParametricColorMap(String name) {
        super(name);
    }


    //
    // Methods.
    //

    /**
     *  Adds the specified function for red.
     */
    public void addRed(ParametricFunction function) {
        redParameters.addElement(function);
    }

    /**
     *  Adds the specified function for green.
     */
    public void addGreen(ParametricFunction function) {
        greenParameters.addElement(function);
    }

    /**
     *  Adds the specified function for blue.
     */
    public void addBlue(ParametricFunction function) {
        blueParameters.addElement(function);
    }


    /**
     *  Computes the color as a sum of the RGB parameter sets.
     */
    public Color colorAt(double index) {

        double red = 0.0;
        for (Enumeration e = redParameters.elements(); e.hasMoreElements(); )
            red += ((ParametricFunction)e.nextElement()).value(index);

        double green = 0.0;
        for (Enumeration e = greenParameters.elements(); e.hasMoreElements(); )
            green += ((ParametricFunction)e.nextElement()).value(index);

        double blue = 0.0;
        for (Enumeration e = blueParameters.elements(); e.hasMoreElements(); )
            blue += ((ParametricFunction)e.nextElement()).value(index);

        return new Color((float)narrow(red),
                         (float)narrow(green),
                         (float)narrow(blue));
    }
}
