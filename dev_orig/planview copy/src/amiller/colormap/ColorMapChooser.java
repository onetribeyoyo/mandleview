/**
 *  Copyright (c) 2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.border.EtchedBorder;


/**
 *  A panel with a list of color maps to choose from.
 */
public class ColorMapChooser
    extends JPanel
{
    //
    // Instance data.
    //


    //
    // Constructors.
    //

    public ColorMapChooser() {
        super();

        this.layoutComponents();
    }

    private void layoutComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridy++;
        this.add(layoutColorMapChoices(), constraints);

        constraints.gridy++;
        this.add(layoutReverseToggle(), constraints);

        constraints.gridy++;
        this.add(layoutButtons(), constraints);
    }

    private JComponent layoutColorMapChoices() {

        ColorMap[] choices
            = {
                ParametricColorMap.createF5ColorMap(),
                ParametricColorMap.createRainbowColorMap(),
                ParametricColorMap.createTerrainColorMap(),
                new NaturalSpectrum(),
                NaturalSpectrum.RED_ORANGE,
                NaturalSpectrum.ORANGE_YELLOW,
                NaturalSpectrum.YELLOW_GREEN,
                NaturalSpectrum.GREEN_BLUE,
                NaturalSpectrum.BLUE_INDIGO,
                NaturalSpectrum.INDIGO_VIOLET
            };

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.ipadx = 4;

        ButtonGroup bg = new ButtonGroup();
        for (int i = 0; i < choices.length; i++) {
            final ColorMap cmap = choices[i];

            constraints.gridy = i;
            constraints.gridx = 0;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            panel.add(new ColorMapViewer(cmap), constraints);

            constraints.anchor = GridBagConstraints.WEST;
            constraints.fill = GridBagConstraints.NONE;
            constraints.gridx = GridBagConstraints.RELATIVE;
            JRadioButton rb = new JRadioButton(cmap.getName());
            bg.add(rb);
            panel.add(rb, constraints);

            if (cmap instanceof TwoColorBlend) {
                JButton button = new JButton("...");
                button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JDialog dialog = new JDialog();
                            dialog.setTitle("TwoColorBlend Editor");
                            dialog.getContentPane().add(new TwoColorBlendEditor((TwoColorBlend)cmap));
                            dialog.pack();
                            dialog.setEnabled(true);
                            dialog.setVisible(true);
                        }
                    });
                panel.add(button, constraints);
            }
        }

        return panel;
    }

    private JComponent layoutReverseToggle() {
        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        panel.add(new JCheckBox());
        panel.add(new JLabel("reverse"));
        return panel;
    }

    private JComponent layoutButtons() {
        JPanel panel = new JPanel();
        panel.add(new JButton("Apply"));
        panel.add(new JButton("Cancel"));
        return panel;
    }


    //
    // Methods.
    //

}
