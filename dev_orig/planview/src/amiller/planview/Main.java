/**
 *  Copyright (c) 1997-2001 Andrew R. Miller, All rights reserved.
 */
package amiller.planview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingConstants;


/**
 *  This is a test harness for the <code>Planview</code>.
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
        JFrame f = new JFrame();

        PropertyConfigurator.configure(view, "planview");

        ComplexMouseInfoPanel cmip = new ComplexMouseInfoPanel(view, "Planview: ", "", SwingConstants.LEADING);

        f.getContentPane().add(view, BorderLayout.CENTER);
        f.getContentPane().add(cmip, BorderLayout.SOUTH);
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
