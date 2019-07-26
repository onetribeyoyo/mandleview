/**
 *  Copyright (c) 2002 Andrew R. Miller, All rights reserved.
 */
package amiller.colormap;

import java.awt.Color;
import java.awt.Dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;


/**
 *  A modal dialog for choosing a color.
 */
public class ColorChooserDialog
    extends JDialog
{
    //
    // Instance data.
    //

    private Color c = Color.black;
    private JColorChooser cc = new JColorChooser();


    //
    // Constructors.
    //

    public ColorChooserDialog(Dialog owner, String title, Color c) {
        super(owner, title, true);

        this.c = c;
        this.cc.setColor(c);

        this.getContentPane().add(cc);
        this.getContentPane().add(layoutButtons());
        this.pack();
    }


    //
    // Methods.
    //

    private JComponent layoutButtons() {
        JPanel panel = new JPanel();

        JButton button = new JButton("OK");
        panel.add(button);
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // somehow return the cc's current color.
                }
            });

        button = new JButton("Revert");
        panel.add(button);
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cc.setColor(c);
                }
            });
        panel.add(new JButton("Revert"));

        button = new JButton("Cancel");
        panel.add(button);
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //this.close();
                }
            });

        return panel;
    }
}
