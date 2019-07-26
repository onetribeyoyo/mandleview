/*
 *  Copyright (c) 2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Category;
import org.apache.log4j.PropertyConfigurator;


/**
 *  Implements startup initialization for log4j.
 */
public class LogInitializer
    extends Object
    implements Initializer
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";

    /** The actual contents of the LOG4J_RESOURCE_BUNDLE. */
    public static ResourceBundle log4jResources = null;


    //
    // Static methods.
    //


    //
    // Instance data.
    //

    /**
     *  The property file that contains the log4j properties
     */
    private String bundleName = "log";


    //
    // Constructors.
    //
    
    public LogInitializer(String bundleName) {
        super();

        this.bundleName = bundleName;
    }


    //
    // Instance methods.
    //

    /**
     *  Initializes log4j as specified in the property file.
     *  See the log4j documentation for a detailed description of the contents
     *  of the property file.
     *
     *  If you would like to see initialization info in the log add the following
     *  to the property file.
     *  <blockquote><code>
     *  log4j.configDebug
     *  </code></blockquote>
     */
    public void doInit()
    {
        log4jResources = ResourceBundle.getBundle(bundleName);
        Properties properties = new Properties();

        for (Enumeration keys = log4jResources.getKeys(); keys.hasMoreElements(); ) {
            String key = (String)keys.nextElement();
            properties.setProperty(key, log4jResources.getString(key));
        }

        PropertyConfigurator.configure(properties);
    }

}
