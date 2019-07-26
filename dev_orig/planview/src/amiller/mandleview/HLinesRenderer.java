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
public class HLinesRenderer
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

	public HLinesRenderer(MandlebrotSet mset) {
		super(mset);
	}


	//
	// Instance methods.
	//

	/**
	 *  RenderingAlgorithm instance that paints the mandlebrot
	 *  set one horizontal line at a time.
	 */
    public final void render(Surface surf, Planview view, Graphics g, Point p, Dimension d)
	{
		//long startTime = System.currentTimeMillis();

		int maxViewY = (int)p.getY() + (int)d.getHeight();

		for (int viewY = (int)p.getY(); viewY <= maxViewY; viewY++) {
			renderHorizontalLine(view, g, (int)p.getX(), viewY, (int)d.getWidth());
		}

		//long endTime = System.currentTimeMillis();

		//System.out.println("HLinesRenderingAlgorithm.render(...) in " + (endTime - startTime)/1000.0 + " seconds.");
	}
}
