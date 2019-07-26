/**
 *  Copyright (c) 2002 Andrew R. Miller, All rights reserved.
 */
package amiller.mandleview;


/**
 *  ???
 *
 *  @author   Andrew R. Miller
 *  @author   Code reviewed by NOBODY on NEVER
 *  @version  $Revision: $  $Modtime: $
 */
public class InteruptableRenderer
    extends RecursiveRectsRenderer
{
    //
    // Static data.
    //


    //
    // Static methods.
    //


    //
    // Instance data.
    //

    /** The renderer thread. */
    private Renderer renderer;


    //
    // Constructors.
    //

    public InteruptableRenderer(MandlebrotSet mset)
    {
        super(mset);

        this.init();
    }


    //
    // Instance methods.
    //

    /**
     *  For use by constructors ONLY.
     */
    private void init() {

        // Initialize a renderer thread.
        this.renderer = new Renderer("SurfaceViewerThread.Renderer");
        this.renderer.start();
    }

    public void finalize() {
        this.renderer.stopWaiting();
    }


    //
    // Inner Classes.
    //

    class Renderer
        extends Thread
    {
        private Thread waiter = this;

        public Renderer(String name) {
            super(name);
        }

        public void stopWaiting() {
            Thread moribund = waiter;
            waiter = null;
            moribund.interrupt();
        }

        public void run() {

            while (waiter == this) {

                //try {
                //    log.info(this.getName() + "...processing completed.");
                //}
                //catch (InterruptedException e) { }
            }
        }
    }
}
