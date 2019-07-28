/**
 *  Copyright (c) 1997-2002 Andrew R. Miller, All rights reserved.
 */
package amiller.mandleview;

import amiller.colormap.ColorMap;
import amiller.colormap.ColorMapViewer;
import amiller.colormap.ParametricColorMap;
import amiller.colormap.TwoColorBlend;

import amiller.planview.ComplexMouseInfoPanel;
import amiller.planview.Planview;
import amiller.planview.SurfaceViewer;
import amiller.planview.SurfaceViewer2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


/**
 *  This is a test harness for <code>Surface</code>.
 */
public class Main
    extends Object
{
    /** The SurfaceViewer itself, displayed in the center of the frame. */
    private static Planview view;


    /**
     *  The test harness.
     */
    public static void main(String[] args)
    {
        view = new Planview();
        MandlebrotSet mset = new MandlebrotSet(1);
        MandlebrotRenderer renderer = new RecursiveRectsRenderer(mset);

        ColorMap cmap = amiller.colormap.PropertyConfigurator.create("mandleview");

        PropertyConfigurator.configure(renderer, "mandleview");
        amiller.planview.PropertyConfigurator.configure(view, "mandleview");

        renderer.setColorMap(cmap);

        view.setBorder(new BevelBorder(BevelBorder.LOWERED));

        view.add(new SurfaceViewer(view, mset, renderer));
        //view.add(new SurfaceViewer2(view, mset, renderer));
        //view.add(new SurfaceViewer(view, mset, new InteruptableRenderer(mset)));


        JFrame f = new JFrame("Mandlebrot Set Viewer");

        JPanel editor = new PropertyEditor(view, renderer);
        editor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        f.getContentPane().add(editor, BorderLayout.NORTH);

        f.getContentPane().add(view, BorderLayout.CENTER);

        JPanel mouseInfo = new ComplexMouseInfoPanel(view, "Coordinates: ", "", SwingConstants.LEADING);
        mouseInfo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        f.getContentPane().add(mouseInfo, BorderLayout.SOUTH);
        f.pack();

        f.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e) { System.exit(0); }
            });

        f.setLocation(new Point(30, 30));
        f.setEnabled(true);
        f.setVisible(true);

        printUsage();
    }

    public static void printUsage() {
        System.out.println("");
        System.out.println("Using the mouse to change the view:");
        System.out.println("  Pan:      <mouse-left> and drag.");
        System.out.println("");
        System.out.println("  Zoom in:  <ctrl>-<mouse-left> will zoom to a point.");
        System.out.println("            <ctrl>-<mouse-left> and drag to zoom to a region.");
        System.out.println("");
        System.out.println("  Zoom out: <shift>-<mouse-left> will zoom to a point.");
        System.out.println("            <shift>-<mouse-left> and drag to zoom to a region.");
        System.out.println("");
    }
}
