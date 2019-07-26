/*
 *  Copyright (c) 1998-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.surface;

import amiller.planview.Planview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.geom.Point2D;


/**
 *  This class renders a <code>Surface</code> onto a <code>Planview</code>
 *  in a point by point manner.
 *
 *  @author        Andrew R. Miller
 *  @author        Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public class PointsRenderer
    extends Object
    implements SurfaceRenderer
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";


    //
    // Static methods.
    //


    //
    // Instance data.
    //

    private String name = "Rendering Algorithm";


    //
    // Constructors.
    //

    public PointsRenderer() {
        this("Points Rendering Algorithm Adapter");
    }

    public PointsRenderer(String name) {
        super();

        this.name = name;
    }


    //
    // Instance methods.
    //

    public String toString() {
        return name;
    }

    /**
     *  RenderingAlgorithm instance that paints the surface point by point.
     */
    public final void render(Surface surf, Planview view, Graphics g, Point p, Dimension d)
    {
        int maxViewX = (int)p.getX() + (int)d.getWidth();
        int maxViewY = (int)p.getY() + (int)d.getHeight();

        for (int viewX = (int)p.getX(); viewX <= maxViewX; viewX++) {
            for (int viewY = (int)p.getY(); viewY <= maxViewY; viewY++) {
                Point2D modelPoint = view.getModelPoint(new Point(viewX, viewY));
                double fXY = surf.f(modelPoint);
                renderPoint(g, viewX, viewY, fXY);
            }
        }
    }

    private double sliceSeperation = 0.5;

    private double sliceThickness = 0.01;

    public void renderPoint(Graphics g, int viewX, int viewY, double fXY) {
        for (double slice = -4.0; slice <= 4.0; slice += sliceSeperation) {
            if (((slice - sliceThickness) < fXY) && (fXY < (slice + sliceThickness))) {
                g.fillRect(viewX, viewY, 1, 1);
                return;
            }
        }
    }
}
