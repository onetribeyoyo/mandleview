/*
 *  Copyright (c) 1998-2002 Andrew R. Miller.  All rights reserved.
 */
package amiller.mandleview;

import amiller.colormap.ColorMap;
import amiller.colormap.TwoColorBlend;

import amiller.planview.Planview;

import amiller.surface.SurfaceRenderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.geom.Point2D;

import java.util.Iterator;
import java.util.List;


/**
 *  This class renders a <code>Surface</code> onto a <code>Planview</code>
 *  in a point by point manner.
 *
 *  @author		Andrew R. Miller
 *  @author		Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public abstract class MandlebrotRenderer
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

	protected MandlebrotSet mset;

	protected ColorMap cmap;
    Object[] colors;


	//
	// Constructors.
	//

	public MandlebrotRenderer(MandlebrotSet mset) {
		super();

		this.mset = mset;

		this.setColorMap(new TwoColorBlend(Color.black, Color.white));
	}


	//
	// Instance methods.
	//

	public ColorMap getColorMap() {
		return cmap;
	}

	public void setColorMap(ColorMap cmap) {
		this.cmap = cmap;

        List colorList = cmap.colorList(this.mset.getIterations());
        colors = colorList.toArray();
        colors[colors.length - 1] = Color.black;
	}

	public int getIterations() {
		return this.mset.getIterations();
	}

	public void setIterations(int iterations) {
		this.mset.setIterations(iterations);
        this.setColorMap(this.getColorMap());
	}

	/**
	 *  Utility method that paints a single horizontal line (of width <code>viewW</code>)
	 *  of the mandlebrot set starting with point (<code>viewX</code>, <code>viewY</code>).
	 */
	public Color renderHorizontalLine(Planview view, Graphics g, int viewX, int viewY, int viewW)
	{
		int maxViewX = viewX + viewW;

		Point2D modelPoint = view.getModelPoint(new Point(viewX, viewY));
		int fXY = (int)mset.f(modelPoint);
		Color color = (Color) this.colors[fXY - 1];

		int   x0     = viewX;
		Color color0 = color;
		Color oneColor = color;

		for( viewX = x0 + 1; viewX < maxViewX; viewX++ ) {
			modelPoint = view.getModelPoint(new Point(viewX, viewY));
            fXY = (int)mset.f(modelPoint);
            color = (Color) this.colors[fXY - 1];

			if (color != color0)
			{
				oneColor = null;

				g.setColor(color0);
				g.drawLine(x0, viewY, viewX - 1, viewY);

				x0 = viewX;
				color0 = color;
			}
		}
		if ( (viewX != x0) || (color != color0) ) {
			g.setColor(color);
			g.drawLine(x0, viewY, viewX - 1, viewY);
		}

		if (color != color0)
			oneColor = null;

		return oneColor;
	}


	/**
	 *  Utility method that paints a single vertical line (of height <code>viewH</code>)
	 *  of the mandlebrot set starting with point (<code>viewX</code>, <code>viewY</code>).
	 */
	public Color renderVerticalLine(Planview view, Graphics g, int viewX, int viewY, int viewH)
	{
		int maxViewY = viewY + viewH;

		Point2D modelPoint = view.getModelPoint(new Point(viewX, viewY));
		int fXY = (int)mset.f(modelPoint);
		Color color = (Color) this.colors[fXY - 1];

		int   y0     = viewY;
		Color color0 = color;
		Color oneColor = color;

		for( viewY = y0 + 1; viewY < maxViewY; viewY++ ) {
			modelPoint = view.getModelPoint(new Point(viewX, viewY));
            fXY = (int)mset.f(modelPoint);
            color = (Color) this.colors[fXY - 1];

			if (color != color0)
			{
				oneColor = null;

				g.setColor(color0);
				g.drawLine(viewX, y0, viewX, viewY - 1);

				y0 = viewY;
				color0 = color;
			}
		}
		if ( (viewY != y0) || (color != color0) ) {
			g.setColor(color);
			g.drawLine(viewX, y0, viewX, viewY - 1);
		}

		if (color != color0)
			oneColor = null;

		return oneColor;
	}

}
