/*
 *  Copyright (c) 1997-2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;


/**
 *  This class represents a function over [0,1] that generates an ordered
 *  list of colors.
 */
public abstract class ColorMap
    extends Object
{
    //
    // Static data.
    //

    public static final int LINEAR = 0;
    public static final int LOGARITHMIC = 1;


    //
    // Instance data.
    //

    /** The user visible name of the color map. */
    private String name = "<unnamed>";

    private int defaultMapping = LINEAR;


    //
    // Constructors.
    //

    public ColorMap(String name) {
        this(name, LINEAR);
    }

    public ColorMap(String name, int defaultMapping) {
        this();

        this.name = name;
        this.defaultMapping = defaultMapping;
    }

    public ColorMap() {
        super();
    }


    //
    // Methods.
    //

    /**
     *  Maps values in [0,1] to the appropriate color.
     */
    public abstract Color colorAt(double index);

    /**
     *  Accessor for the name.
     */
    public String getName() {
        return name;
    }

    /**
     *  Mutator for the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.getName() + " ColorMap";
    }

    /**
     *  Maps an integer value in [0, 255] to a double in [0.0, 1.0]
     */
    public double normalize(int index) {
        return (double)index / 255.0;
    }

    /**
     *  Maps a double value in [0.0, 1.0] to an integer in [0, 255]
     */
    public int denormalize(double index) {
        return (int) (index * 255.0);
    }

    /**
     *  Ensures that the index is in [0,1].  Values less than 0 are
     *  mapped to 0 and those greater than 1 are mapped to 1.
     */
    public double narrow(double index) {
        return Math.max(0.0, Math.min(index, 1.0));
    }


    /**
     *  Returns a list of colors computed using this colormap and the default mapping.
     *
     *  @param  length   The length of the list.
     */
    public List colorList(int length) {
        return this.colorList(length, this.defaultMapping);
    }

    /**
     *  Returns a list of colors computed using this colormap (useful
     *  when computing a color value is expensive).
     *
     *  @param  length   The length of the list.
     *  @param  mapping  One of <code>ColorMap.LINEAR</code> or <code>ColorMap.LOGARITHMIC</code>.
     */
    public List colorList(int length, int mapping) {

        switch (mapping) {
        case LINEAR:
            {
                List colors = new ArrayList();
                double delta = 1.0 / (double)(length - 1);
                for (int i = 0; i < length; i++) {
                    colors.add(colorAt((double)i * delta));
                }
                return colors;
            }

        case LOGARITHMIC:
            {
                List colors = new ArrayList();

                return colors;
            }

        default:
            throw new IllegalArgumentException
                ("Mapping is not one of ColorMap.LINEAR or ColorMap.LOGARITHMIC.");
        }
    }

    /**
     *  Returns the reverse of this colormap.  The reverse remains dependent on
     *  this colormap, thus changes to this will have the corresponding effect on
     *  the reverse.
     */
    public ColorMap reverse() {
        return new ReversedColorMap(this);
    }


    //
    // Inner classes
    //

    class ReversedColorMap
        extends ColorMap
    {
        private ColorMap source;

        ReversedColorMap(ColorMap source) {
            this(source.getName() + " reversed", source);
        }

        ReversedColorMap(String name, ColorMap source) {
            super(name);
            this.source = source;
        }

        public Color colorAt(double index) {
            return source.colorAt(1.0 - index);
        }

        public ColorMap reverse() {
            return source;
        }
    }
}
