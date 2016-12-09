package src.aihw.nnet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TrainerControlPanel extends JPanel {

    /** The serial version number. */
    private static final long serialVersionUID = 1L;


    /** Reference to the parent JFrame. */
    @SuppressWarnings("unused")
    private final JFrame frame;

    /**
     * Default constructor.
     * 
     * @param n reference to the neural network.
     * @param frame reference to the parent JFrame.
     */
    public TrainerControlPanel(final JFrame frame) {
      super(new BorderLayout(), true);

      this.frame = frame;
      
      // Set the size of the JFrame
      frame.setSize(new Dimension(640, 480));
      frame.add(this);
      
      // Save and Quit button
      final JButton stopButton = new JButton("Save and Quit");

      stopButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          nnet.saveToFile();
          stopButton.setEnabled(false);
          frame.dispose();
        }
      });
      add(stopButton, BorderLayout.SOUTH);
      frame.setVisible(true);
    }

    /**
     * Gets the preferred size of this panel.
     * 
     * @return a Dimension object with length and width.
     */
    @Override
    public Dimension getPreferredSize() {
      return new Dimension(640, 480);
    }

    /**
     * Gets the minimum size of this panel.
     * 
     * @return a Dimension object with length and width.
     */
    @Override
    public Dimension getMinimumSize() {
      return getPreferredSize();
    }

    /**
     * Gets the maximum size of this panel.
     * 
     * @return a Dimension object with length and width.
     */
    @Override

}
