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
 *  Decorates a <code>Planview</code> with a grid.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class GridViewer
    extends Viewer
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";

    /** The default grid color. */
    public static final Color DEFAULT_COLOR = Color.orange;


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

    GridViewer(Planview view)
    {
        super(view);

        this.setColor(DEFAULT_COLOR);
    }


    //
    // Instance methods.
    //

    public void paintComponent(Graphics g)
    {
        if (this.xorMode) {
            g.setXORMode(this.color);
        }
        else {
            g.setColor(this.color);
        }

        Point2D.Double p1 = new Point2D.Double();

        // Draw the vertical grid.
        {
            double modelX = Math.ceil(this.view.getModelUpperLeft().getX());
            p1.setLocation(modelX, 0.0);
            int viewX = (int) this.view.getViewPoint(p1).getX();
            while (viewX <= this.getWidth()) {
                g.drawLine(viewX, 0, viewX, this.getHeight());
                modelX += 1.0;
                p1.setLocation(modelX, 0.0);
                viewX = (int) this.view.getViewPoint(p1).getX();
            }
        }

        // Draw the horizontal grid.
        {
            double modelY = Math.floor(this.view.getModelUpperLeft().getY());
            p1.setLocation(0.0, modelY);
            int viewY = (int) this.view.getViewPoint(p1).getY();
            while (viewY <= this.getHeight()) {
                g.drawLine(0, viewY, this.getWidth(), viewY);
                modelY -= 1.0;
                p1.setLocation(0.0, modelY);
                viewY = (int) this.view.getViewPoint(p1).getY();
            }
        }
    }

    public boolean getXORMode() {
        return this.xorMode;
    }

    public void setXORMode(boolean xorMode) {
        this.xorMode = xorMode;
    }

}
