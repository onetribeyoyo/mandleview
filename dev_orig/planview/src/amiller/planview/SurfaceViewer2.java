/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview;

import amiller.surface.SurfaceRenderer;
import amiller.surface.Surface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.image.BufferedImage;


/**
 *  Decorates a <code>Planview</code> with a surface.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class SurfaceViewer2
    extends SurfaceViewer
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

    private BufferedImage buffer = null;


    //
    // Constructors.
    //

    public SurfaceViewer2(Planview view, Surface surf, SurfaceRenderer renderor)
    {
        super(view, surf, renderor);
    }


    //
    // Instance methods.
    //

    public void paintComponent(Graphics g)
    {
        if (buffer == null) {
            buffer = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_INT_RGB);
        }

        Graphics bg = buffer.getGraphics();
        bg.setColor(this.color);
        renderor.render(surf, view, bg, new Point(0, 0), new Dimension(view.getSize()));

        g.drawImage(buffer, 0, 0, null);
    }
}
