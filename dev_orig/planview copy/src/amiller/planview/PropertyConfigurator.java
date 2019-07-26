/**
 *  Copyright (c) 2002 Andrew R. Miller, All rights reserved.
 */
package amiller.planview;

import amiller.util.XRGBColors;

import java.awt.Color;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 *  Configures a view from a resource bundle.
 */
public class PropertyConfigurator
    extends Object
{

    public static final String PLANVIEW_AXIS         = "planview.axis";
    public static final String PLANVIEW_AXIS_COLOR   = "planview.axis.color";
    public static final String PLANVIEW_AXIS_XOR     = "planview.axis.xor";

    public static final String PLANVIEW_GRID         = "planview.grid";
    public static final String PLANVIEW_GRID_COLOR   = "planview.grid.color";
    public static final String PLANVIEW_GRID_XOR     = "planview.grid.xor";

    public static final String PLANVIEW_ORIGIN       = "planview.origin";
    public static final String PLANVIEW_ORIGIN_COLOR = "planview.origin.color";
    public static final String PLANVIEW_ORIGIN_XOR   = "planview.origin.xor";


    public static void configure(Planview view, String bundleName)
        throws MissingResourceException
    {
        ResourceBundle resources = ResourceBundle.getBundle(bundleName);
        configure(view, resources);
    }

    public static void configure(Planview view, ResourceBundle resources) {
        configureOrigin(view, resources);
        configureAxis(view, resources);
        configureGrid(view, resources);
    }

    private static void configureGrid(Planview view, ResourceBundle resources) {
        try {
            if (Boolean.valueOf(resources.getString(PLANVIEW_GRID)).booleanValue()) {
                GridViewer gv = view.addGridViewer();
                try {
                    gv.setXORMode(Boolean.valueOf(resources.getString(PLANVIEW_GRID_XOR)).booleanValue());
                }
                catch (MissingResourceException ignore) {}
                try {
                    gv.setColor(XRGBColors.colorFor(resources.getString(PLANVIEW_GRID_COLOR)));
                }
                catch (MissingResourceException ignore) {}
            }
            else {
                view.removeGridViewer();
            }
        }
        catch (MissingResourceException ignore) {}
    }

    private static void configureAxis(Planview view, ResourceBundle resources) {
        try {
            if (Boolean.valueOf(resources.getString(PLANVIEW_AXIS)).booleanValue()) {
                AxisViewer av = view.addAxisViewer();
                try {
                    av.setXORMode(Boolean.valueOf(resources.getString(PLANVIEW_AXIS_XOR)).booleanValue());
                }
                catch (MissingResourceException ignore) {}
                try {
                    av.setColor(XRGBColors.colorFor(resources.getString(PLANVIEW_AXIS_COLOR)));
                }
                catch (MissingResourceException ignore) {}
            }
            else {
                view.removeAxisViewer();
            }
        }
        catch (MissingResourceException ignore) {}
    }

    private static void configureOrigin(Planview view, ResourceBundle resources) {
        try {
            if (Boolean.valueOf(resources.getString(PLANVIEW_ORIGIN)).booleanValue()) {
                OriginViewer ov = view.addOriginViewer();
                try {
                    ov.setXORMode(Boolean.valueOf(resources.getString(PLANVIEW_ORIGIN_XOR)).booleanValue());
                }
                catch (MissingResourceException ignore) {}
                try {
                    ov.setColor(XRGBColors.colorFor(resources.getString(PLANVIEW_ORIGIN_COLOR)));
                }
                catch (MissingResourceException ignore) {}
            }
            else {
                view.removeOriginViewer();
            }
        }
        catch (MissingResourceException ignore) {}
    }
}
