/*
 *  Copyright (c) 1997-2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.Color;


public class TwoColorBlend
    extends ColorMap
{
    //
    // Static methods.
    //


    //
    // Private data.
    //

    private Color c1 = Color.white;
    private Color c2 = Color.black;

    private double r1;
    private double g1;
    private double b1;

    private double r2;
    private double g2;
    private double b2;

    private double rm;
    private double gm;
    private double bm;


    //
    // Constructors.
    //

    public TwoColorBlend() {
        this("Black2WhiteBlend", Color.black, Color.white);
    }

    public TwoColorBlend(Color c1, Color c2) {
        this("TwoColorBlend: " + c1 + " to " + c2, c1, c2);
    }

    public TwoColorBlend(String name, Color c1, Color c2) {
        super(name);

        this.setColor1(c1);
        this.setColor2(c2);
    }
 

    //
    // Methods.
    //

    public Color colorAt(double index) {
        double r = narrow(rm * index + r1);
        double g = narrow(gm * index + g1);
        double b = narrow(bm * index + b1);

        return new Color((float)r, (float)g, (float)b);
    }

    public Color getColor1() {
        return this.c1;
    }

    public void setColor1(Color c1) {
        this.c1 = c1;
        this.r1 = normalize(c1.getRed());
        this.g1 = normalize(c1.getGreen());
        this.b1 = normalize(c1.getBlue());
        this.reset();
    }

    public Color getColor2() {
        return this.c2;
    }

    public void setColor2(Color c2) {
        this.c2 = c2;
        this.r2 = normalize(c2.getRed());
        this.g2 = normalize(c2.getGreen());
        this.b2 = normalize(c2.getBlue());
        this.reset();
    }

    private void reset() {
        this.rm = r2 - r1;
        this.gm = g2 - g1;
        this.bm = b2 - b1;
    }
}
