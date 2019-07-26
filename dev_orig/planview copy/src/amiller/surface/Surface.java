/*
 *  Copyright (c) 1998-2001 Andrew R. Miller.  All rights reserved.
 */
package amiller.surface;

import java.awt.geom.Point2D;


/**
 *  <class description>
 *
 *  @author   Andrew R. Miller
 *  @author   Code reviewed by NOBODY on NEVER
 *  @version  $Revision: $  $Modtime: $
 */
public interface Surface
{
    //
    // Static data.
    //

    /** The source revision. */
    String REVISION = "$Revision: $";


    //
    // Instance methods.
    //

    double f(double x, double y);

    double f(Point2D modelPoint);

}
