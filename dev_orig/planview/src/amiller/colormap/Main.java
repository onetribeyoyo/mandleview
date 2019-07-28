/**
 *  Copyright (c) 1997-2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingConstants;


/**
 *  This is a test harness for <code>ColorMap</code>s.
 */
public class Main
    extends Object
{
    private static ColorMap cm;

    /**
     *  The test harness.
     */
    public static void main(String[] args)
    {
        //cm = new TwoColorBlend(Color.white, Color.black);
        //cm = NaturalSpectrum.RED_ORANGE;
        //cm = NaturalSpectrum.ORANGE_YELLOW;
        //cm = NaturalSpectrum.YELLOW_GREEN;
        //cm = NaturalSpectrum.GREEN_BLUE;
        //cm = NaturalSpectrum.BLUE_INDIGO;
        //cm = NaturalSpectrum.INDIGO_VIOLET;
        //cm = new TwoColorBlend(NaturalSpectrum.RED, NaturalSpectrum.VIOLET);
        //cm = ParametricColorMap.createF5ColorMap();
        //cm = ParametricColorMap.createTerrainColorMap();
        //cm = ParametricColorMap.createRainbowColorMap();

        cm = new NaturalSpectrum();

        JFrame f = new JFrame();

        //f.getContentPane().add(new ColorMapViewer(cm), BorderLayout.NORTH);
        f.getContentPane().add(new ColorMapChooser(), BorderLayout.CENTER);
        //f.getContentPane().add(new ColorMapViewer(ParametricColorMap.createRainbowColorMap()), BorderLayout.SOUTH);

        f.pack();

        f.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e) { System.exit(0); }
            });

        f.setLocation(new Point(30, 30));
        f.setEnabled(true);
        f.setVisible(true);
    }
}
