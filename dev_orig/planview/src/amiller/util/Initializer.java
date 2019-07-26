/*
 *  Copyright (c) 2001 Andrew R. Miller.  All rights reserved.
 *
 *  $Header: $
 */
package amiller.util;


/**
 *  Implement this interface to provide your own strategies for
 *  application initialization.
 */
public interface Initializer
{
    //
    // Static data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision: $";


    //
    // Instance methods.
    //

    /**
     *  This method provides the implementation of the initialization
     *  functionality.
     */
    public void doInit();

}
