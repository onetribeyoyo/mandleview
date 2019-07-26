/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview.rectangleselector;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 *  <class description>
 *
 *  @author     Andrew R. Miller
 *  @author     Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public class RectangleSelector
    extends Object
    implements RectangleSelectable
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";


    /** Disable all drawing. */
    public static final int NULL_MODE = 0;

    /** Draw the rectangle as a line. */
    public static final int LINE_MODE = 1;

    /** Draw the rectangle as a rectangle. */
    public static final int RECTANGLE_MODE = 2;

    /** Draw the rectangle as an elipse. */
    public static final int ELIPSE_MODE = 3;


    //
    // Static methods.
    //


    //
    // Instance data.
    //

    /** The component we are tracking. */
    private Component display;

    /** The point at which the last MouseDragged event occured. */
    private Point lastDragged = null;

    /** The point at which the last MousePressed event occured. */
    private Point lastPressed = null;

    private Collection listeners = new ArrayList();

    /** The current mouse mode. */
    private int mode = LINE_MODE;

    /** The selection color. */
    private Color selectionColor;


    //
    // Constructors.
    //

    public RectangleSelector(Component display, Color selectionColor)
    {
        super();

        this.display = display;
        this.selectionColor = selectionColor;

        display.addMouseListener(new MouseAdapter()
            {
                public void mouseEntered(MouseEvent e) {
                    reset();
                }
                public void mouseExited(MouseEvent e) {
                    if ((lastPressed != null) && (lastDragged != null))
                        paintMouseTrack();
                    reset();
                }
                public void mousePressed(MouseEvent e) {
                    // Occasionaly an event is sent when the mouse is moved outside of
                    // the component.  Buggy events like this are ignored.
                    Point tmp = e.getPoint();
                    if ((tmp.x < 0)
                        || (getDisplaySize().width < tmp.x)
                        || (tmp.y < 0)
                        || (getDisplaySize().height < tmp.y))
                        return;

                    lastPressed  = e.getPoint();
                }
                public void mouseReleased(MouseEvent e) {
                    if ((lastPressed != null) && (lastDragged != null))
                        paintMouseTrack();
                    if (lastPressed != null)
                        processRectangleSelectedEvent(e);
                    reset();
                }
            });

        display.addMouseMotionListener(new MouseMotionAdapter()
            {
                public void mouseDragged(MouseEvent e) {
                    if (lastPressed != null)
                    {
                        if (lastDragged != null)
                            paintMouseTrack();

                        lastDragged = e.getPoint();
                        paintMouseTrack();
                    }
                }
            });
    }


    //
    // Instance methods.
    //


    public void addRectangleSelectedListener(RectangleSelectedListener l)
    {
        this.listeners.add(l);
    }

    public void removeRectangleSelectedListener(RectangleSelectedListener l)
    {
        this.listeners.remove(l);
    }

    private void processRectangleSelectedEvent(MouseEvent e)
    {
        Point releasePoint = e.getPoint();
        double w = releasePoint.getX() - lastPressed.getX();
        double h = releasePoint.getY() - lastPressed.getY();

        if ((w == 0) && (h == 0))
            return;

        double x = lastPressed.getX();
        double y = lastPressed.getY();

        Rectangle r = new Rectangle((int)x, (int)y, (int)w, (int)h);
        //System.out.println("    " + r);

        RectangleSelectedEvent rsEvent = new RectangleSelectedEvent(this, r, e);
        Iterator iterator = listeners.iterator();
        while ( iterator.hasNext())
        {
            RectangleSelectedListener l = (RectangleSelectedListener) iterator.next();
            l.rectangleSelected(rsEvent);
        }
    }

    private Dimension getDisplaySize()
    {
        return display.getSize();
    }

    private void paintMouseTrack()
    {
        //System.out.println(this + ".paintMouseTrack()");

        Graphics g = display.getGraphics();
        g.setXORMode(selectionColor);

        switch (mode)
        {
        case LINE_MODE:
            g.drawLine(lastPressed.x, lastPressed.y, lastDragged.x, lastDragged.y);
            break;
        case RECTANGLE_MODE:
            g.drawRect(Math.min(lastPressed.x, lastDragged.x),
                       Math.min(lastPressed.y, lastDragged.y),
                       Math.abs(lastDragged.x - lastPressed.x),
                       Math.abs(lastDragged.y - lastPressed.y));
            break;
        case ELIPSE_MODE:
            g.drawOval(Math.min(lastPressed.x, lastDragged.x),
                       Math.min(lastPressed.y, lastDragged.y),
                       Math.abs(lastDragged.x - lastPressed.x),
                       Math.abs(lastDragged.y - lastPressed.y));
            break;
        }

        g.dispose();
    }

    private void reset()
    {
        //System.out.println(this + ".reset()");

        lastDragged = null;
        lastPressed = null;
    }


    public int getMode()
    {
        return this.mode;
    }

    public void setMode(int mode)
    {
        this.mode = mode;
    }
}
