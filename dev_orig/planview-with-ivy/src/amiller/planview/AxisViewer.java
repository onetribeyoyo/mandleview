/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.geom.Point2D;


/**
 *  Decorates a <code>Planview</code> with axis.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class AxisViewer
    extends Viewer
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";

    /** The default axis color. */
    public static final Color DEFAULT_COLOR = Color.red;


    //
    // Static methods.
    //


    //
    // Instance data.
    //

    /** When true the grid is drawn in XOR mode. */
    private boolean xorMode = false;


    //
    // Constructors.
    //

    AxisViewer(Planview view)
    {
        super(view);

        this.setColor(DEFAULT_COLOR);
    }


    //
    // Instance methods.
    //

    public void paintComponent(Graphics g)
    {
        Point2D viewOrigin = this.view.getViewPoint(new Point2D.Double(0.0, 0.0));

        if (this.xorMode) {
            g.setXORMode(this.color);
        }
        else {
            g.setColor(this.color);
        }

        g.drawLine((int)viewOrigin.getX(), 0, (int)viewOrigin.getX(), this.getHeight());
        g.drawLine(0, (int)viewOrigin.getY(), this.getWidth(), (int)viewOrigin.getY());
    }

    public boolean getXORMode() {
        return this.xorMode;
    }

    public void setXORMode(boolean xorMode) {
        this.xorMode = xorMode;
    }

}
