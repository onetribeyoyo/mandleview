
import amiller.SliderPair;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Scratch
    extends Object
{
    public static void main(String[] args)
    {
		JFrame f = new JFrame();
        f.setTitle(System.getProperty("java.version"));

        JSlider sliderA;
        JSlider sliderB;
        SliderPair pair;
        {
            sliderA = new JSlider(0, 99, 30);
            sliderB = new JSlider(0, 99, 90);
            pair = new SliderPair(sliderA, sliderB, SwingConstants.VERTICAL);
            f.getContentPane().add(pair, BorderLayout.WEST);
        }
        {
            sliderA = new JSlider(0, 99, 30);
            sliderB = new JSlider(0, 99, 42);
            pair = new SliderPair(sliderA, sliderB, SwingConstants.HORIZONTAL);
            f.getContentPane().add(pair, BorderLayout.SOUTH);
        }

		f.pack();

		f.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e) { System.exit(0); }
			});

		f.setEnabled(true);
		f.setVisible(true);
    }
}
