/*
 *  Copyright (c) 1999-2002 Andrew R. Miller.  All rights reserved.
 */
package amiller.util;

import java.awt.Color;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 *  Contains constants for each of the entires in the X rgb.txt file.
 *  The color names and values are read directly from rgb.txt (which
 *  must be on the classpath.)
 */
public class XRGBColors
    extends Object
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";


    /** The file name of the colors. */
    private static String RGB_TXT = "rgb.txt";

    /** A mapping between the named colors and the actual values. */
    private static Map colors = null;


    //
    // Static methods.
    //

    /**
     *  Looks for a color corresponding to the given name
     *  (color names are not case sensitive).
     */
    public static Color colorFor(String name) {
        if (colors == null) {
            synchronized(XRGBColors.class) {
                if (colors == null)
                    loadColors(RGB_TXT);
            }
        }

        return (Color) colors.get(name.toLowerCase());
    }

    private static void loadColors(final String filename) {
        InputStream stream = (InputStream)java.security.AccessController.doPrivileged
            (
             new java.security.PrivilegedAction() {
                     public Object run() {
                         return ClassLoader.getSystemResourceAsStream(filename);
                     }
                 }
             );

        if (stream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            colors = new HashMap();
            try {
                String line = reader.readLine();
                while (line != null) {
                    if (!line.startsWith("#")) {
                        StringTokenizer tokenizer = new StringTokenizer(line);
                        if (tokenizer.countTokens() >= 4) {
                            int r = Integer.parseInt(tokenizer.nextToken());
                            int g = Integer.parseInt(tokenizer.nextToken());
                            int b = Integer.parseInt(tokenizer.nextToken());
                            String name = tokenizer.nextToken();
                            while (tokenizer.countTokens() > 0) {
                                name += " ";
                                name += tokenizer.nextToken();
                            }
                            colors.put(name.toLowerCase(), new Color(r, g, b));
                            //System.out.println(name + "	-->	" + colors.get(name));
                        }
                    }
                    line = reader.readLine();
                }
            }
            catch (IOException e) {
                // This should never happen unless the filename is invalid.
                //System.err.println(e);
            }
        }
    }


    //
    // Constructors.
    //
    
    private XRGBColors() {
        super();
    }

}
