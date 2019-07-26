/**
 *  Copyright (c) 2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

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
    public static final String COLORMAP_REVERSE   = "colormap.reverse";

    public static final String COLORMAP_TWO_COLOR_BLEND   = "colormap.twocolorblend";
    public static final String COLORMAP_TWO_COLOR_BLEND_1 = "colormap.twocolorblend.color1";
    public static final String COLORMAP_TWO_COLOR_BLEND_2 = "colormap.twocolorblend.color2";

    public static final String COLORMAP_PARAMETRIC         = "colormap.parametric";
    public static final String COLORMAP_PARAMETRIC_F5      = "colormap.parametric.f5";
    public static final String COLORMAP_PARAMETRIC_RAINBOW = "colormap.parametric.rainbow";
    public static final String COLORMAP_PARAMETRIC_TERRAIN = "colormap.parametric.terrain";


    public static ColorMap create(String bundleName)
        throws MissingResourceException
    {
        ResourceBundle resources = ResourceBundle.getBundle(bundleName);
        return create(resources);
    }

    public static ColorMap create(ResourceBundle resources) {

        ColorMap cmap = new TwoColorBlend(); // default to a black2white blend.

        try {
            resources.getString(COLORMAP_TWO_COLOR_BLEND);
            TwoColorBlend tcb = new TwoColorBlend();
            configure(tcb, resources);
            cmap= tcb;
        }
        catch (MissingResourceException ignore) {}

        try {
            resources.getString(COLORMAP_PARAMETRIC);

            try {
                resources.getString(COLORMAP_PARAMETRIC_F5);
                cmap = ParametricColorMap.createF5ColorMap();
            }
            catch (MissingResourceException ignore) {}

            try {
                resources.getString(COLORMAP_PARAMETRIC_RAINBOW);
                cmap = ParametricColorMap.createRainbowColorMap();
            }
            catch (MissingResourceException ignore) {}

            try {
                resources.getString(COLORMAP_PARAMETRIC_TERRAIN);
                cmap = ParametricColorMap.createTerrainColorMap();
            }
            catch (MissingResourceException ignore) {}
        }
        catch (MissingResourceException ignore) {}

        try {
            resources.getString(COLORMAP_REVERSE);
            cmap = cmap.reverse();
        }
        catch (MissingResourceException ignore) {}

        return cmap;
    }

    public static void configure(TwoColorBlend cmap, String bundleName)
        throws MissingResourceException
    {
        ResourceBundle resources = ResourceBundle.getBundle(bundleName);
        configure(cmap, resources);
    }

    public static void configure(TwoColorBlend cmap, ResourceBundle resources) {

        try {
            cmap.setColor1(XRGBColors.colorFor(resources.getString(COLORMAP_TWO_COLOR_BLEND_1)));
        }
        catch (MissingResourceException ignore) {}

        try {
            cmap.setColor2(XRGBColors.colorFor(resources.getString(COLORMAP_TWO_COLOR_BLEND_2)));
        }
        catch (MissingResourceException ignore) {}
    }
}
