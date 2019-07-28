/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview.rectangleselector;

import java.awt.AWTEvent;
import java.awt.Rectangle;

import java.awt.event.MouseEvent;


/**
 *  <class description>
 *
 *  @author     Andrew R. Miller
 *  @author     Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public class RectangleSelectedEvent
    extends AWTEvent
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

    private MouseEvent mEvent = null;
    private Rectangle rect = null;


    //
    // Constructors.
    //

    public RectangleSelectedEvent(RectangleSelectable source, Rectangle rect, MouseEvent mEvent)
    {
        super(source, -1);

        this.rect = rect;
        this.mEvent = mEvent;
    }


    //
    // Instance methods.
    //

    public MouseEvent getMouseEvent()
    {
        return this.mEvent;
    }

    public Rectangle getRectangle()
    {
        return this.rect;
    }

    public RectangleSelectable getRectangleSelectable()
    {
        return (RectangleSelectable) this.getSource();
    }

    public String toString()
    {
        return
            RectangleSelectedEvent.class.getName()
            + "["

            + "x="      + this.rect.getX()      + ","
            + "y="      + this.rect.getY()      + ","
            + "width="  + this.rect.getWidth()  + ","
            + "height=" + this.rect.getHeight() + ","

            + "clickCount=" + this.mEvent.getClickCount()

            + (this.mEvent.isAltDown()      ? ",Alt"      : "")
            + (this.mEvent.isAltGraphDown() ? ",AltGraph" : "")
            + (this.mEvent.isControlDown()  ? ",Control"  : "")
            + (this.mEvent.isMetaDown()     ? ",Shift"    : "")
            + (this.mEvent.isShiftDown()    ? ",Meta"     : "")

            + "]";
    }
}
