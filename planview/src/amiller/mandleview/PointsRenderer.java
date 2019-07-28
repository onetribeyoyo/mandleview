/*
 *  Copyright (c) 1998-2002 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.mandleview;

import amiller.planview.Planview;

import amiller.surface.Surface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.geom.Point2D;


/**
 *  ???
 *
 *  @author		Andrew R. Miller
 *  @author		Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public class PointsRenderer
	extends MandlebrotRenderer
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


	//
	// Constructors.
	//

	public PointsRenderer(MandlebrotSet mset) {
		super(mset);
	}


	//
	// Instance methods.
	//

    public final void render(Surface surf, Planview view, Graphics g, Point p, Dimension d)
	{
		long startTime = System.currentTimeMillis();

		int maxViewX = (int)p.getX() + (int)d.getWidth();
		int maxViewY = (int)p.getY() + (int)d.getHeight();

		for (int viewX = (int)p.getX(); viewX <= maxViewX; viewX++) {
			for (int viewY = (int)p.getY(); viewY <= maxViewY; viewY++) {
				Point2D modelPoint = view.getModelPoint(new Point(viewX, viewY));
				double fXY = surf.f(modelPoint);
				renderPoint(g, viewX, viewY, fXY);
			}
		}

		long endTime = System.currentTimeMillis();

		System.out.println("PointsRenderingAlgorithm.render(...) in " + (endTime - startTime)/1000.0 + " seconds.");
	}

	public void renderPoint(Graphics g, int viewX, int viewY, double fXY) {
		System.out.println("f(" + viewX + ", " + viewY + ") = " + fXY);

        g.setColor((Color) this.colors[(int)fXY - 1]);
		g.fillRect(viewX, viewY, 1, 1);
	}

}
