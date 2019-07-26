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
 *  Decorates a <code>Planview</code> with an origin marker.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class OriginViewer
    extends Viewer
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";

    /** The default origin color. */
    public static final Color DEFAULT_COLOR = Color.black;

    /** The radius of the origin marker. */
    public static final int markerRadius = 10;


    //
    // Static methods.
    //


    //
    // Instance data.
    //

    /** When true the origin is drawn in XOR mode. */
    private boolean xorMode = false;


    //
    // Constructors.
    //

    OriginViewer(Planview view)
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

        int originX = (int) viewOrigin.getX();
        int originY = (int) viewOrigin.getY();

        if (this.xorMode) {
            g.setXORMode(this.color);
        }
        else {
            g.setColor(this.color);
        }

        g.drawLine(originX, originY - markerRadius, originX, originY - 2);
        g.drawLine(originX, originY + 2, originX, originY + markerRadius);
        g.drawLine(originX - markerRadius, originY, originX - 2, originY);
        g.drawLine(originX + 2, originY, originX + markerRadius, originY);
        g.drawOval(originX - markerRadius/2, originY - markerRadius/2, markerRadius+1, markerRadius+1);

        g.fillOval(originX - 2, originY - 2, 5, 5);
    }

    public boolean getXORMode() {
        return this.xorMode;
    }

    public void setXORMode(boolean xorMode) {
        this.xorMode = xorMode;
    }

}
