/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview.rectangleselector;

import java.util.EventListener;


/**
 *  <class description>
 *
 *  @author     Andrew R. Miller
 *  @author     Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public interface RectangleSelectedListener
    extends EventListener
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";


    //
    // Instance methods.
    //

    public void rectangleSelected(RectangleSelectedEvent e);
}
