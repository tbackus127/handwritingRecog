
package aihw.nnet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class contains the controls for the neural network.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant EMAIL_HERE
 * @author Tyler Fiacco EMAIL_HERE
 * @author Eric Sakshaug EMAIL_HERE
 *
 */
public class NNControlPanel extends JPanel {

  /** The serial version number. */
  private static final long serialVersionUID = 1L;

  /** Reference to the neural network. */
  private final HWNeuralNet nnet;

  /** Reference to the parent JFrame. */
  private final JFrame frame;

  /**
   * Default constructor.
   * 
   * @param n reference to the neural network.
   * @param frame reference to the parent JFrame.
   */
  public NNControlPanel(final HWNeuralNet n, final JFrame frame) {
    super(new BorderLayout(), true);

    this.frame = frame;
    this.nnet = n;

    // Set the size of the JFrame
    setSize(getPreferredSize());

    // Save and Quit button
    // TODO: Make smaller
    final JButton stopButton = new JButton("Save and Quit");
    stopButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        nnet.saveToFile();
        stopButton.setEnabled(false);
        stopButton.setText("Stopped");
        frame.dispose();
      }
    });
    add(stopButton);
  }

  /**
   * Gets the preferred size of this panel.
   * 
   * @return a Dimension object with length and width.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(240, 180);
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
