/**
 *  Copyright (c) 1998-2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.EtchedBorder;


/**
 *  A canvas for displaying a ColorMap (from left to right).
 */
public class ColorMapViewer
    extends JPanel
{
    //
    // Private data.
    //

    /** The current ColorMap. */
    private ColorMap colormap = null;

    private ColorMapBar bar = null;


    //
    // Constructors.
    //

    public ColorMapViewer(ColorMap colormap) {
        this(colormap, SwingConstants.HORIZONTAL, false);
    }

    public ColorMapViewer(ColorMap colormap, int orientation, boolean label) {
        super();

        this.colormap = colormap;

        if (label) {
            this.add(new JLabel(colormap.getName()));
        }

        this.bar = new ColorMapBar(orientation);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        this.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        this.add(bar);
    }


    //
    // Methods.
    //

    /** Accessor for the colormap. */
    public ColorMap getColorMap() {
        return this.colormap;
    }

    /** Mutator for the colormap. */
    public void setColorMap(ColorMap colormap)
    {
        this.colormap = colormap;
        Graphics g = getGraphics();
        if ( g != null ) {
            this.paint(g);
            g.dispose();
        }
    }


    //
    // Inner classes.
    //

    private class ColorMapBar
        extends JComponent
    {
        /** The default minimum size of the component. */
        private Dimension minimumSize = new Dimension(100, 20);

        /** The direction to orient the colors (either SwingConstants.HORIZONTAL
         *  or SwingConstants.VERTICAL). */
        private int orientation = SwingConstants.HORIZONTAL;


        ColorMapBar(int orientation) {
            super();
            this.orientation = orientation;
            switch (this.orientation) {
            case SwingConstants.HORIZONTAL:
                this.minimumSize = new Dimension(100, 20);
                break;
            case SwingConstants.VERTICAL:
                this.minimumSize = new Dimension(20, 100);
                break;
            default:
                throw new IllegalArgumentException
                    ("Orientation is not one of SwingConstants.HORIZONTAL or SwingConstants.VERTICAL.");
            }
        }

        /**
         *  Draws the ColorMap by iterating over the colors.
         */
        public void paint(Graphics g) {
            switch (orientation) {
            case SwingConstants.HORIZONTAL:
                paintHorizontal(g);
                break;
            case SwingConstants.VERTICAL:
                paintVertical(g);
                break;
            }
        }

        private void paintHorizontal(Graphics g) {
            int width  = this.getSize().width;
            int height = this.getSize().height;

            if (colormap == null) {
                g.clearRect(0, 0, width, height);
                return;
            }

            float delta = (float) 1.0 / (float) width;

            for (int x = 0; x < width; x++) {
                g.setColor(colormap.colorAt((float)x * delta));
                g.drawLine(x, 0, x, height);
            }
        }

        private void paintVertical(Graphics g) {
            int width  = this.getSize().width;
            int height = this.getSize().height;

            if (colormap == null) {
                g.clearRect(0, 0, width, height);
                return;
            }

            float delta = (float) 1.0 / (float) height;

            for (int y = 0; y < height; y++) {
                g.setColor(colormap.colorAt((float)y * delta));
                g.drawLine(0, y, width, y);
            }
        }

        public Dimension getMinimumSize() {
            return minimumSize;
        }

        public Dimension getPreferredSize() {
            return getMinimumSize();
        }
    }
}
