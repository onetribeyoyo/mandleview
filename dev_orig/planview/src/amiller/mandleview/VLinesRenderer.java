/*
 *  Copyright (c) 1998-2002 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.mandleview;

import amiller.planview.Planview;

import amiller.surface.Surface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


/**
 *  ???
 *
 *  @author		Andrew R. Miller
 *  @author		Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public class VLinesRenderer
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

	public VLinesRenderer(MandlebrotSet mset) {
		super(mset);
	}


	//
	// Instance methods.
	//

	/**
	 *  RenderingAlgorithm instance that paints the mandlebrot
	 *  set one vertical line at a time.
	 */
    public final void render(Surface surf, Planview view, Graphics g, Point p, Dimension d)
	{
		//long startTime = System.currentTimeMillis();

		int maxViewX = (int)p.getX() + (int)d.getWidth();

		for (int viewX = (int)p.getX(); viewX <= maxViewX; viewX++) {
			renderVerticalLine(view, g, viewX, (int)p.getY(), (int)d.getHeight());
		}

		//long endTime = System.currentTimeMillis();

		//System.out.println("VLinesRenderingAlgorithm.render(...) in " + (endTime - startTime)/1000.0 + " seconds.");
	}
}
