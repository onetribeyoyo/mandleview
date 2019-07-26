/*
 *  Copyright (c) 2002 Andrew R. Miller.  All rights reserved.
 */
package amiller.mandleview;

import amiller.colormap.ColorMapChooser;
import amiller.colormap.ColorMapViewer;

import amiller.planview.Planview;

import java.awt.FlowLayout;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.EtchedBorder;


/**
 *  Displays widgets for editing the properties of a Mandlebrot view.
 *
 *  @author  Andrew R. Miller
 *  @author  Code reviewed by NOBODY on NEVER
 *  @version $Revision: $  $Modtime: $
 */
public class PropertyEditor
    extends JPanel
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

    /** The view this panel will describe. */
    private Planview view = null;

    /** The mandlebrot renderer for the view. */
    private MandlebrotRenderer renderer = null;


    //
    // Constructors.
    //

    public PropertyEditor(final Planview view, final MandlebrotRenderer renderer) {
        super();

        this.view = view;
        this.renderer = renderer;

        this.setLayout(new FlowLayout());

        {
            Integer[] choices
                = {
                    new Integer(8),
                    new Integer(16),
                    new Integer(32),
                    new Integer(64),
                    new Integer(128),
                    new Integer(192),
                    new Integer(256),
                    new Integer(384),
                    new Integer(512),
                    new Integer(640),
                    new Integer(768),
                    new Integer(896),
                    new Integer(1024)
                        };
            JComboBox iterations = new JComboBox(choices);
            for (int i = 0; i < choices.length; i++) {
                if (renderer.getIterations() == choices[i].intValue()) {
                    iterations.setSelectedIndex(i);
                }
            }
            iterations.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        renderer.setIterations( ((Integer)e.getItem()).intValue() );
                        view.repaint();
                    }
                });
            this.add(new JLabel("Iterations:"));
            this.add(iterations);
        }

        {
            ColorMapViewer cmapViewer = new ColorMapViewer(renderer.getColorMap());
            this.add(new JLabel("Colormap:"));
            this.add(cmapViewer);
            JButton button = new JButton("...");
            button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JDialog dialog = new JDialog();
                        dialog.setTitle("ColorMap Chooser");
                        dialog.getContentPane().add(new ColorMapChooser());
                        dialog.pack();
                        dialog.setEnabled(true);
                        dialog.setVisible(true);
                    }
                });
            Insets margin = button.getMargin();
            button.setMargin(new Insets(margin.top, 4, margin.bottom, 4));
            this.add(button);
        }
    }


    //
    // Instance methods.
    //

}
