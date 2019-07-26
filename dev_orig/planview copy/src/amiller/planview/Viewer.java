/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview;

import java.awt.Color;

import javax.swing.JComponent;


/**
 *  Abstract base class for <code>Planview</code> viewers.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public abstract class Viewer
    extends JComponent
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";

    /** The default origin color. */
    public static final Color DEFAULT_COLOR = Color.black;


    //
    // Static methods.
    //


    //
    // Instance data.
    //

    /** The viewer's base color. */
    protected Color color = DEFAULT_COLOR;

    protected Planview view = null;


    //
    // Constructors.
    //

    Viewer(Planview view)
    {
        super();

        this.view = view;
    }


    //
    // Instance methods.
    //

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
