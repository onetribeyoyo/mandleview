/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview.rectangleselector;


/**
 *  <class description>
 *
 *  @author     Andrew R. Miller
 *  @author     Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public interface RectangleSelectable
{
    //
    // Static data.
    //

    /** The source revision. */
    String REVISION = "$Revision: $";


    //
    // Instance methods.
    //

    void addRectangleSelectedListener(RectangleSelectedListener l);

    void removeRectangleSelectedListener(RectangleSelectedListener l);
}
