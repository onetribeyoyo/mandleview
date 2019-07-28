/**
 *  Copyright (c) 2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.border.EtchedBorder;


/**
 *  A panel for editing a TwoColorBlend colormap.
 */
public class TwoColorBlendEditor
    extends JPanel
{
    //
    // Instance data.
    //


    //
    // Constructors.
    //

    public TwoColorBlendEditor(TwoColorBlend cmap) {
        super();

        this.layoutComponents(cmap);
    }

    private void layoutComponents(TwoColorBlend cmap) {

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();        

        constraints.gridy = 1;
        this.layoutColorMap(cmap, constraints);

        constraints.gridy++;
        this.add(new JColorChooser(cmap.getColor1()), constraints);

        constraints.gridy++;
        this.layoutButtons(constraints);
    }

    private void layoutColorMap(final TwoColorBlend cmap, GridBagConstraints constraints) {
        JPanel panel = new ColorMapViewer(cmap);
        panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(new ColorMapViewer(cmap), constraints);
    }

    private void layoutButtons(GridBagConstraints constraints) {
        JPanel panel = new JPanel();
        panel.add(new JButton("OK"));
        panel.add(new JButton("Cancel"));

        this.add(panel, constraints);
    }


    //
    // Methods.
    //

}
