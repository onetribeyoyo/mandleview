/*
 *  Copyright (c) 1998-2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.Color;


/**
 *  The natural spectrum ColorMap - ROYGBIV.
 *
 *  <p>
 *  <table border=0>
 *    <tr align=center valign=bottom>
 *      <td><strong>Color Name</strong></td>
 *      <td><strong>RGB Value</strong></td>
 *      <td><strong>Normalized Value</strong></td>
 *    </tr>
 *    <tr align=center valign=bottom>
 *      <td><hr size=1></td><td><hr size=1></td><td><hr size=1></td>
 *    </tr>
 *    <tr align=center valign=bottom>
 *      <td>red</td>
 *      <td>(255, 0, 0)</td>
 *      <td>(1.0000, 0.0000, 0.0000)</td>
 *    </tr>
 *    <tr align=center valign=bottom>
 *      <td>orange</td>
 *      <td>(255, 165, 0)</td>
 *      <td>(1.0000, 0.6471, 0.0000)</td>
 *    </tr>
 *    <tr align=center valign=bottom>
 *      <td>yellow</td>
 *      <td>(255, 255, 0)</td>
 *      <td>(1.0000, 1.0000, 0.0000)</td>
 *    </tr>
 *    <tr align=center valign=bottom>
 *      <td>green</td>
 *      <td>(0, 255, 0)</td>
 *      <td>(0.0000, 1.0000, 0.0000)</td>
 *    </tr>
 *    <tr align=center valign=bottom>
 *      <td>blue</td>
 *      <td>(0, 0, 255)</td>
 *      <td>(0.0000, 0.0000, 1.0000)</td>
 *    </tr>
 *    <tr align=center valign=bottom>
 *      <td>indigo</td>
 *      <td>(119, 65, 119) ?</td>
 *    </tr>
 *    <tr align=center valign=bottom>
 *      <td>violet</td>
 *      <td>(238, 130, 238)</td>
 *      <td>(0.9333, 0.5098, 0.9333)</td>
 *    </tr>
 *  </table>
 */
public final class NaturalSpectrum
    extends ColorMapSequence
{
    //
    // Static data.
    //

    public static final Color RED    = new Color(255,   0,   0);
    public static final Color ORANGE = new Color(255, 165,   0);
    public static final Color YELLOW = new Color(255, 255,   0);
    public static final Color GREEN  = new Color(  0, 255,   0);
    public static final Color BLUE   = new Color(  0,   0, 255);
    public static final Color INDIGO = new Color(119,  65, 119);
    public static final Color VIOLET = new Color(238, 130, 238);

    public static final TwoColorBlend RED_ORANGE    = new TwoColorBlend("Red-Orange",    RED,    ORANGE);
    public static final TwoColorBlend ORANGE_YELLOW = new TwoColorBlend("orange-yellow", ORANGE, YELLOW);
    public static final TwoColorBlend YELLOW_GREEN  = new TwoColorBlend("yellow-green",  YELLOW, GREEN);
    public static final TwoColorBlend GREEN_BLUE    = new TwoColorBlend("green-blue",    GREEN,  BLUE);
    public static final TwoColorBlend BLUE_INDIGO   = new TwoColorBlend("blue-indigo",   BLUE,   INDIGO);
    public static final TwoColorBlend INDIGO_VIOLET = new TwoColorBlend("indigo-violet", INDIGO, VIOLET);

    private static final ColorMap[] sources
        = {
            NaturalSpectrum.RED_ORANGE,
            NaturalSpectrum.ORANGE_YELLOW,
            NaturalSpectrum.YELLOW_GREEN,
            NaturalSpectrum.GREEN_BLUE,
            NaturalSpectrum.BLUE_INDIGO,
            NaturalSpectrum.INDIGO_VIOLET
        };


    //
    // Constructors.
    //

    public NaturalSpectrum() {
        super("Natural Spectrum", sources);
    }
}
