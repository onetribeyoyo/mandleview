/*
 *  Copyright (c) 1997-2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.Color;


/**
 *  A ColorMapSequence is a colormap representing the sequence of two
 *  or more source colormaps.  If for example there are two sources,
 *  index values in [0, 0.5] will be mapped from the first source, and
 *  values in (0.5, 1] from the second.  The sequence remains dependent
 *  on the sources, thus changes to any source will be reflected by
 *  the sequence.
 */
public class ColorMapSequence
    extends ColorMap
{
    //
    // Private data.
    //

    private ColorMap[] sources;


    //
    // Constructors.
    //

    /**
     *  Creates a ColorMapSequence with a default name.
     */
    public ColorMapSequence(ColorMap[] sources) {
        this("ColorMapSequence: " + sources, sources);
    }

    public ColorMapSequence(String name, ColorMap[] sources) {
        super(name);

        if (sources.length < 2) {
            throw new IllegalArgumentException("Cannot create a ColorMapSequence with fewer than 2 sources.");
        }

        this.sources = new ColorMap[sources.length];

        for (int i = 0; i < sources.length; i++) {
            this.sources[i] = sources[i];
        }
    }


    //
    // Methods.
    //

    public Color colorAt(double index) {

        // First find the source for the index value.
        // source = min( N-1, max( 0, ceiling(x*N, 1) - 1 ))

        int n = sources.length;

        int sourceIndex = Math.min( n - 1, Math.max( 0, (int) Math.ceil(index * n) - 1 ));
        ColorMap source = sources[sourceIndex];

        // then map the index using the correct source.
        double mappedIndex = n *(index - (double)sourceIndex / (double)n);

        //System.out.println(index + "	" + sourceIndex + "	" + mappedIndex);

        return source.colorAt(mappedIndex);
    }
}
