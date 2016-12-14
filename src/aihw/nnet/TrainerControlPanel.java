
package aihw.nnet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is a GUI for the trainer portion of the network. It automatically trains the network from the given
 * training data, and provides an option to save and quit.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug Eric.Sakshaug11@gmail.com
 *
 */
public class TrainerControlPanel extends JPanel {

  /** The serial version number. */
  private static final long serialVersionUID = 1L;

  private final HWNeuralNet nnet;

  /** Reference to the parent JFrame. */
  @SuppressWarnings("unused")
  private final JFrame frame;

  /**
   * Default constructor.
   * 
   * @param n reference to the neural network.
   * @param frame reference to the parent JFrame.
   */
  public TrainerControlPanel(final HWNeuralNet n, final JFrame frame) {
    super(new BorderLayout(), true);

    this.frame = frame;
    this.nnet = n;

    // Set the size of the JFrame
    frame.setSize(new Dimension(320, 120));
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
    NNTrainer.run(nnet);
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
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }

}
