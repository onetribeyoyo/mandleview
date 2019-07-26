/*
 *  Copyright (c) 2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.surface;

import java.awt.geom.Point2D;


/**
 *  <class description>
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class SurfaceAdapter
    extends Object
    implements Surface
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

    public SurfaceAdapter() {
        super();
    }


    //
    // Instance methods.
    //

    public double f(double x, double y) {
        return 0.0;
    }

    public double f(Point2D p) {
        return f(p.getX(), p.getY());
    }
}
