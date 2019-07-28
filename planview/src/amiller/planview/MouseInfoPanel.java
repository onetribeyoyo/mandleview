/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview;

import java.awt.BorderLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.awt.geom.Point2D;

import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *  Displays the model coordinates for the current mouse
 *  location in a <code>Planview</code>.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class MouseInfoPanel
    extends JPanel
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";

    /** The number of fractional digits to display in the mouse info. */
    public static final int MAX_FRACTION_DIGITS = 4;


    //
    // Static methods.
    //


    //
    // Instance data.
    //

    /** The NumberFormat for the mouse info. */
    protected NumberFormat formatter;

    /** The view this panel will describe. */
    private Planview view = null;

    /** The label that shows the current mouse info,  displayed along the
     *  bottom of the frame.  The initial value is " ".  This ensures that
     *  the height of the component doesn't change.  */
    private JLabel label;

    /** Added to the begining of the mouse info message. */
    protected String prefix;

    /** Appended to the end of the mouse info message. */
    protected String suffix;

    //
    // Constructors.
    //

    public MouseInfoPanel(Planview view)
    {
        this(view, "", "", SwingConstants.LEADING);
    }

    public MouseInfoPanel(Planview view, String prefix, String suffix, int horizontalAlignment)
    {
        super();

        this.formatter = NumberFormat.getInstance();
        this.formatter.setMaximumFractionDigits(MAX_FRACTION_DIGITS);

        this.setPrefix(prefix);
        this.setSuffix(suffix);

        this.setLayout(new BorderLayout());
        this.label = new JLabel(" ");
        this.label.setHorizontalAlignment(horizontalAlignment);
        this.add(label, BorderLayout.CENTER);

        this.view = view;

        this.view.addMouseListener(new MouseAdapter()
            {
                public void mouseEntered(MouseEvent event) {
                    label.setText(formatPoint(getView().getModelPoint(event.getPoint())));
                }
                public void mouseExited(MouseEvent event) {
                    label.setText(" ");
                }
            } );
        this.view.addMouseMotionListener( new MouseMotionAdapter()
            {
                public void mouseDragged(MouseEvent event) {
                    label.setText(formatPoint(getView().getModelPoint(event.getPoint())));
                }
                public void mouseMoved(MouseEvent event) {
                    label.setText(formatPoint(getView().getModelPoint(event.getPoint())));
                }
            } );
    }


    //
    // Instance methods.
    //

    private Planview getView() {
        return this.view;
    }

    public String formatPoint(Point2D modelPoint) {

        StringBuffer result = new StringBuffer();

        result.append(this.prefix);
        result.append("(");
        result.append(formatter.format(modelPoint.getX()));
        result.append(", ");
        result.append(formatter.format(modelPoint.getY()));
        result.append(")");
        result.append(this.suffix);

        return result.toString();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
