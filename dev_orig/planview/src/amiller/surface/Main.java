/**
 *  Copyright (c) 1997-2001 Andrew R. Miller, All rights reserved.
 */
package amiller.surface;

import amiller.planview.ComplexMouseInfoPanel;
import amiller.planview.Planview;
import amiller.planview.SurfaceViewer;

import java.awt.BorderLayout;
import java.awt.Point;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingConstants;


/**
 *  This is a test harness for <code>Surface</code>.
 */
public class Main
    extends Object
{
    /** The SurfaceViewer itself, displayed in the center of the frame. */
    private static final Planview view = new Planview();

    /**
     *  The test harness.
     */
    public static void main(String[] args)
    {
        //view.add(new SurfaceViewer(view, new SinXYSurface(), new PointsRenderer()));

        view.add(new SurfaceViewer
            (view,
             //new SurfaceAdapter() { public double f(double x,double y) {return Math.sin(x);} },
             //new SurfaceAdapter() { public double f(double x,double y) {return Math.sin(y);} },
             new SurfaceAdapter() { public double f(double x,double y) {return Math.sin(x)+Math.sin(y); } },
             new PointsRenderer()));

        JFrame f = new JFrame();

        f.getContentPane().add(view,
                               BorderLayout.CENTER);
        f.getContentPane().add(new ComplexMouseInfoPanel(view, "Planview: ", "", SwingConstants.LEADING),
                               BorderLayout.SOUTH);
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
