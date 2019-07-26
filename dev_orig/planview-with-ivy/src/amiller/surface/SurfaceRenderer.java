/*
 *  Copyright (c) 1998-2002 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.surface;

import amiller.planview.Planview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


/**
 *  This class renders a <code>Surface</code> onto a <code>Planview</code>
 *  in a point by point manner.
 *
 *  @author        Andrew R. Miller
 *  @author        Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public interface SurfaceRenderer
{
    //
    // Static data.
    //

    /** The source revision. */
    String REVISION = "$Revision: $";


    //
    // Instance methods.
    //

    /**
     *  Renders the Surface over the region defined by Point <code>p</code>
     *  and Dimension <code>d</code>.
     *
     *  @param    surf    The Surface instance.
     *  @param    view    The View  instance.
     *  @param    g        The Graphics object used to paint the mandlebrot set.
     *  @param    p        The Point defining the paint region.
     *  @param    d        the Dimension defining the paint region.
     */
    void render(Surface surf, Planview view, Graphics g, Point p, Dimension d);

}
