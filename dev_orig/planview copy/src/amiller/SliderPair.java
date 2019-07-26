/*
 *  Copyright (c) 2002 Andrew R. Miller. All rights reserved.
 *
 *  $Header$
 */
package amiller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *  A JPanel with two sliders that "move" together.
 *
 *  @author   Andrew R. Miller
 *  @author   Code reviewed by NOBODY on NEVER
 *  @version  $Revision$  $Modtime$
 */
public class SliderPair
    extends JPanel
{
    //
    // Class data.
    //

    /** The source revision. */
    public static final String REVISION = "$Revision$";


    //
    // Class methods.
    //


    //
    // Instance data.
    //

    final JSlider low;
    final JSlider high;


    //
    // Constructors.
    //

    public SliderPair() {
        this(new JSlider(0, 100), new JSlider(0, 100));
    }

    public SliderPair(final JSlider low, final JSlider high) {
        this(low, high, SwingConstants.HORIZONTAL);
    }


    public SliderPair(final JSlider low, final JSlider high, int orientation) {
        this.low = low;
        this.high = high;

        this.low.setOrientation(orientation);
        this.high.setOrientation(orientation);

        this.setLayout(new BorderLayout());

        switch (orientation) {
        case SwingConstants.VERTICAL:
            this.add(low, BorderLayout.WEST);
            this.add(high, BorderLayout.EAST);
            break;
        default:
            this.add(low, BorderLayout.NORTH);
            this.add(high, BorderLayout.SOUTH);
        }


        low.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (high.getValue() < low.getValue()) {
                        high.setValue(low.getValue());
                    }
                };
            });

        high.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (high.getValue() < low.getValue()) {
                        low.setValue(high.getValue());
                    }
                };
            });
    }


    //
    // Instance methods.
    //

    public int getHighValue() {
        return high.getValue();
    }

    public void setHighValue(int highValue) {
        high.setValue(highValue);
    }


    public int getLowValue() {
        return low.getValue();
    }

    public void setLowValue(int lowValue) {
        low.setValue(lowValue);
    }
}
