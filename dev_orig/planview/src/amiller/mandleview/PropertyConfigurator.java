/**
 *  Copyright (c) 2002 Andrew R. Miller, All rights reserved.
 */
package amiller.mandleview;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 *  Configures a view from a resource bundle.
 */
public class PropertyConfigurator
    extends Object
{

    public static final String MANDLEVIEW_ITERATIONS = "mandleview.iterations";

    public static void configure(MandlebrotRenderer renderer, String bundleName)
        throws MissingResourceException
    {
        ResourceBundle resources = ResourceBundle.getBundle(bundleName);
        configure(renderer, resources);
    }

    public static void configure(MandlebrotRenderer renderer, ResourceBundle resources) {
        try {
            renderer.setIterations(Integer.parseInt(resources.getString(MANDLEVIEW_ITERATIONS)));
        }
        catch (MissingResourceException ignore) {}
    }
}
