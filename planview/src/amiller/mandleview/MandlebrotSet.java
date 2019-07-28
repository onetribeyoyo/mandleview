/*
 *	Copyright (c) 1998-2002 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.mandleview;

import amiller.surface.SurfaceAdapter;


/**
 *  <class description>
 *
 *  @author		Andrew R. Miller
 *  @author		Code reviewed by NOBODY on NEVER
 *  @version    $Revision: $  $Modtime: $
 */
public class MandlebrotSet
    extends SurfaceAdapter
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

    /** The number of iterations used to compute values in the Mandlebrot set. */
    private int iterations;



    //
    // Constructors.
    //

    public MandlebrotSet(int iterations)
    {
        super();

        this.iterations = iterations;
    }


    //
    // Instance methods.
    //

    /**
     * Computes the value in the Mandlebrot set for point (a, b) in the complex plane.
     */
    public double f(double a, double b) {
        return f(a, b, this.iterations);
    }

    /**
     *  Computes the value in the Mandlebrot set for point (a, b)
     *  in the complex plane using the specified number of iterations.
     *  The computed value is in <code>[1, iterations]</code>.
     */
    public int f(double a, double b, int iterations)
    {
        double currentX = 0.0;
        double currentY = 0.0;
        double tmpX = 0.0;
        double tmpY = 0.0;
        int result = 0;

        for (result = 0;
             ( ((currentX*currentX + currentY*currentY) < 4)
               && (result < iterations) );
             result++)
        {
            tmpX = currentX*currentX - currentY*currentY + a;
            tmpY = 2*currentX*currentY + b;
            currentX = tmpX;
            currentY = tmpY;
        }

        return result;
    }

    public String toString() {
        return this.getClass().getName() + "[iterations=" + this.iterations + "]";
    }

    /**
     *  Gets the current value for iterations.
     */
    public int getIterations() {
        return this.iterations;
    }

    /**
     *  Sets the iterations used to compute values in the Mandlebrot set.
     */
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

}
