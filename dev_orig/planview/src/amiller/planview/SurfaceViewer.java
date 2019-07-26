/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview;

import amiller.surface.SurfaceRenderer;
import amiller.surface.Surface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


/**
 *  Decorates a <code>Planview</code> with a surface.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class SurfaceViewer
    extends Viewer
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";

    /** The default line color. */
    public static final Color DEFAULT_COLOR = Color.black;


    //
    // Static methods.
    //


    //
    // Instance data.
    //

    /** The line color. */
    protected Color color = DEFAULT_COLOR;

    protected Surface surf;

    protected SurfaceRenderer renderor;


    //
    // Constructors.
    //

    public SurfaceViewer(Planview view, Surface surf, SurfaceRenderer renderor)
    {
        super(view);

        this.surf = surf;
        this.renderor = renderor;
    }


    //
    // Instance methods.
    //

    public void paintComponent(Graphics g)
    {
        g.setColor(this.color);
        renderor.render(surf, view, g, new Point(0, 0), new Dimension(view.getSize()));
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
