/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview;

import java.awt.geom.Point2D;

import java.text.NumberFormat;


/**
 *  Displays the model coordinates (in the complex plane)
 *  for the current mouse location in a <code>Planview</code>.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class ComplexMouseInfoPanel
    extends MouseInfoPanel
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

    public ComplexMouseInfoPanel(Planview view)
    {
        super(view);
    }

    public ComplexMouseInfoPanel(Planview view, String prefix, String suffix, int horizontalAlignment)
    {
        super(view, prefix, suffix, horizontalAlignment);
    }


    //
    // Instance methods.
    //

    public String formatPoint(Point2D modelPoint) {

        StringBuffer result = new StringBuffer();

        result.append(this.prefix);
        result.append(this.formatter.format(modelPoint.getX()));
        double modelY = modelPoint.getY();
        if (modelY >= 0.0) {
            result.append("+");
        }
        result.append(this.formatter.format(modelY));
        result.append("i");
        result.append(this.suffix);

        return result.toString();
    }
}
