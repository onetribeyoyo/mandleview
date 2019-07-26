/*
 *  Copyright (c) 1997-2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.planview;

import amiller.planview.rectangleselector.RectangleSelectedEvent;
import amiller.planview.rectangleselector.RectangleSelectedListener;
import amiller.planview.rectangleselector.RectangleSelector;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.OverlayLayout;

import org.apache.log4j.Category;


/**
 *  This class will plot objects in a cartesian plane by defining a mapping
 *  between a rectangular region in the cartesian plane and a rectangular region
 *  in descrete integer pixel space.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class Planview
    extends JComponent
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";

    /** The default background color. */
    public static final Color backgroundColor = Color.white;

    /** The default selection color. */
    public static final Color selectionColor = Color.orange;

    private static final String imageDir = "./images/cursors";

    /** Rectangular selections are treated as pan operations. */
    public static final int PAN_MODE = 0;
    public static final Cursor PAN_CURSOR = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);

    /** Rectangular selections are treated as zoom in operations. */
    public static final int ZOOM_IN_MODE = 1;
    private static final String zoominImagePath = imageDir + "/zoomin32x32.gif";
    private static Cursor ZOOM_IN_CURSOR = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);

    /** Rectangular selections are treated as zoom out operations. */
    public static final int ZOOM_OUT_MODE = 2;
    private static final String zoomoutImagePath = imageDir + "/zoomout32x32.gif";
    private static Cursor ZOOM_OUT_CURSOR = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);

    /** The Planview log. */
    static Category log = Category.getInstance("PLANVIEW");


    //
    // Static methods.
    //

    static {
        try {
            File file = new File(zoominImagePath);
            Image image = Toolkit.getDefaultToolkit().getImage(file.getCanonicalPath());
            ZOOM_IN_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(6, 6), "Zoom In");
        }
        catch (IOException ex) {
            log.warn("Error accessing cursor image: " + zoominImagePath);
        }

        try {
            File file = new File(zoomoutImagePath);
            Image image = Toolkit.getDefaultToolkit().getImage(file.getCanonicalPath());
            ZOOM_OUT_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(6, 6), "Zoom Out");
        }
        catch (IOException ex) {
            log.error("Error accessing cursor image: " + zoomoutImagePath);
        }
    }


    //
    // Instance data.
    //

    /** The selection mode. */
    private int mode = PAN_MODE;

    /** The location in model space of the viewer's upper left corner. */
    private Point2D modelUpperLeft = new Point2D.Double(-2.25, 1.5);

    /** The scale of the view (= pixels per model unit). */
    private double modelScale = 100.0;

    /** The preferred size for the view. */
    private Dimension preferredDimension = new Dimension(300, 300);

    /** The current dimensions of the view. */
    private Dimension currentDimension = null;

    /** The transformation between pixel and model space. */
    private AffineTransform tx;

    private boolean selectionInProgress = false;

    /** The selector used when zooming, panning, ... */
    private RectangleSelector rectSelector = new RectangleSelector(this, selectionColor);

    /** Optional axis viewer. */
    private AxisViewer axisViewer = null;

    /** Optional grid viewer. */
    private GridViewer gridViewer = null;

    /** Optional origin viewer. */
    private OriginViewer originViewer = null;



    //
    // Constructors.
    //

    public Planview()
    {
        super();

        setBackground(backgroundColor);

        setMode(PAN_MODE);

        setPreferredSize(preferredDimension);

        createTransform();

        setLayout(new OverlayLayout(this));

        // Add a RectangleSelector to notify this Planview of selection events.
        rectSelector.addRectangleSelectedListener(new RectangleSelectedListener()
            {
                public void rectangleSelected(RectangleSelectedEvent e) {
                    processSelection(e);
                }
            });

        // Add a MouseListener to coordinate reseting the mode.
        addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e) {
                    selectionInProgress = true;
                }
                public void mouseReleased(MouseEvent e) {
                    selectionInProgress = false;
                    setMode(PAN_MODE);
                }
                public void mouseEntered(MouseEvent e) {
                    requestFocus();
                }
                public void mouseExited(MouseEvent e) {
                    selectionInProgress = false;
                    setMode(PAN_MODE);
                }
            });

        // Add a KeyListener to enable mode selection via keyboard modifiers.
        addKeyListener(new KeyAdapter()
            {
                public void keyPressed(KeyEvent e) {
                    setKeyMode(e);
                }
                public void keyReleased(KeyEvent e) {
                    setKeyMode(e);
                }
                private void setKeyMode(KeyEvent e) {
                    if (!selectionInProgress) {
                        if (e.isControlDown()) {
                            setMode(ZOOM_IN_MODE);
                        }
                        else if (e.isShiftDown()) {
                            setMode(ZOOM_OUT_MODE);
                        }
                        else {
                            setMode(PAN_MODE);
                        }
                    }
                }
            });

        addComponentListener(new ComponentAdapter()
            {
                public void componentShown(ComponentEvent e) {
                    log.debug("componentShown(...)");
                }
                public void componentResized(ComponentEvent e) {
                    log.debug("componentResized(...)");

                    String message = "    was (w,h)=(";
                    if (currentDimension == null) {
                        message += "unknown, unknown";
                    }
                    else {
                        message += getWidth() + "," + getHeight();
                    }
                    message += ")";

                    log.debug(message);
                    currentDimension = new Dimension(getWidth(), getHeight());

                    log.debug("    resized to (w,h)=("
                              + currentDimension.getWidth() + ","
                              + currentDimension.getHeight() + ")");
                }
            });
    }


    //
    // Instance methods.
    //

    protected void createTransform()
    {
        // Start the transformation with a flip across the y-axis.
        tx = new AffineTransform(1.0, 0.0, 0.0, -1.0, 0.0, 0.0);

        // Use the scale and the modelViewPort to translate the
        // viewer's origin.
        tx.translate(-modelUpperLeft.getX() * modelScale,
                     -modelUpperLeft.getY() * modelScale);

        // Scale the transformation.
        tx.scale(modelScale, modelScale);
    }

    /**
     *  Returns the model space point that is equivalent
     *  of the supplied view space point.
     */
    public Point2D getModelPoint(Point viewPoint)
    {
        try {
            return tx.inverseTransform(viewPoint, new Point2D.Double());
        }
        catch (NoninvertibleTransformException ex) {
            // This should never happen.
            log.error(ex);
            return null;
        }
    }

    /**
     *  Returns the view space point that is equivalent
     *  of the supplied model space point.
     */
    public Point2D getViewPoint(Point2D.Double modelPoint) {
        return tx.transform(modelPoint, new Point2D.Double());
    }

    public double getModelScale() {
        return this.modelScale;
    }

    public void setModelScale(double newScale) {
        synchronized (this) {
            this.modelScale = newScale;
            createTransform();
        }
    }

    public Point2D getModelUpperLeft() {
        return this.modelUpperLeft;
    }

    public void setModelUpperLeft(Point2D newUpperLeft) {
        synchronized (this) {
            this.modelUpperLeft = newUpperLeft;
            createTransform();
        }
    }

    public Point2D getModelCenter() {
        return this.getModelPoint(new Point(getWidth()/2, getHeight()/2));
    }
    
    public void setModelCenter(Point2D newCenter)
    {
        Point2D oldCenter = getModelCenter();
        double dx = oldCenter.getX() - newCenter.getX();
        double dy = oldCenter.getY() - newCenter.getY();
        setModelUpperLeft(new Point2D.Double(modelUpperLeft.getX() - dx, modelUpperLeft.getY() - dy));
        createTransform();
    }

    public void paintComponent(Graphics g) {
        g.setPaintMode();

        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }


    public int getMode()
    {
        return this.mode;
    }

    public AxisViewer addAxisViewer() {
        if (this.axisViewer != null) {
            this.removeAxisViewer();
        }

        log.info("Adding AxisViewer");
        this.axisViewer = new AxisViewer(this);
        this.add(this.axisViewer);

        return this.axisViewer;
    }

    public void removeAxisViewer() {
        if (this.axisViewer != null) {
            log.info("Removing AxisViewer");
            this.remove(this.axisViewer);
            this.axisViewer = null;
        }
    }

    public GridViewer addGridViewer() {
        if (this.gridViewer != null) {
            this.removeGridViewer();
        }

        log.info("Adding GridViewer");
        this.gridViewer = new GridViewer(this);
        this.add(this.gridViewer);

        return this.gridViewer;
    }

    public void removeGridViewer() {
        if (this.gridViewer != null) {
            log.info("Removing GridViewer");
            this.remove(this.gridViewer);
            this.gridViewer = null;
        }
    }

    public OriginViewer addOriginViewer() {
        if (this.originViewer != null) {
            this.removeOriginViewer();
        }

        log.info("Adding OriginViewer");
        this.originViewer = new OriginViewer(this);
        this.add(this.originViewer);

        return this.originViewer;
    }

    public void removeOriginViewer() {
        if (this.originViewer != null) {
            log.info("Removing OriginViewer");
            this.remove(this.originViewer);
            this.originViewer = null;
        }
    }

    public void setMode(int mode)
    {
        this.mode = mode;

        switch (mode) {
        case PAN_MODE:
            setCursor(PAN_CURSOR);
            rectSelector.setMode(RectangleSelector.LINE_MODE);
            break;
        case ZOOM_IN_MODE:
            setCursor(ZOOM_IN_CURSOR);
            rectSelector.setMode(RectangleSelector.RECTANGLE_MODE);
            break;
        case ZOOM_OUT_MODE:
            setCursor(ZOOM_OUT_CURSOR);
            rectSelector.setMode(RectangleSelector.RECTANGLE_MODE);
            break;
        }
    }

    private void processSelection(RectangleSelectedEvent e)
    {
        switch (mode) {
        case PAN_MODE:
            pan(e);
            break;
        case ZOOM_IN_MODE:
            zoom(e, false);
            break;
        case ZOOM_OUT_MODE:
            zoom(e, true);
            break;
        }
        repaint();
    }

    private void pan(RectangleSelectedEvent e)
    {
        Rectangle panRect = e.getRectangle();

        // Translate the model by the panRect's width and height.

        Point viewDelta = new Point((int) -panRect.getWidth(), (int) -panRect.getHeight());
        Point2D modelDelta = getModelPoint(viewDelta);
        // Note: "tall and thin" rects have aspect > 0, and
        //       "short and fat" rects have aspect < 0.


        modelUpperLeft.setLocation(modelDelta.getX(), modelDelta.getY());
        createTransform();
    }

    private void zoom(RectangleSelectedEvent e, boolean zoomout)
    {
        Rectangle zoomRect = e.getRectangle();
        Point2D zoomCenter = getModelPoint(new Point((int)(zoomRect.getWidth()/2 + zoomRect.getX()),
                                                     (int)(zoomRect.getHeight()/2 + zoomRect.getY())));
        double zoomAspect = zoomRect.getHeight() / zoomRect.getWidth();

        double zoom = 1.0;
        if (zoomAspect > 0.0) {
            // "tall and thin" zoomRect, rescale by height.
            zoom = Math.abs(this.getHeight() / zoomRect.getHeight());
        }
        else {
            // "short and fat" zoomRect, rescale by width.
            zoom = Math.abs(this.getWidth() / zoomRect.getWidth());
        }

        if (zoomout) {
            zoom(zoomCenter, modelScale / zoom);
        }
        else {
            zoom(zoomCenter, modelScale * zoom);
        }
    }

    private void zoom(Point2D zoomCenter, double zoom)
    {
        setModelScale(zoom);
        setModelCenter(zoomCenter);
    }
}
