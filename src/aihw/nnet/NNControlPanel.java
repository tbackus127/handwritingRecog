
package src.aihw.nnet;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug EMAIL_HERE
 *
 */
public class NNControlPanel extends JPanel {

  /** The serial version number. */
  private static final long serialVersionUID = 1L;

  /** Reference to the neural network. */
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
  public NNControlPanel(final HWNeuralNet n, final JFrame frame) {
    super(new BorderLayout(), true);

    this.frame = frame;
    this.nnet = n;
    
    // Set the size of the JFrame
    frame.setSize(new Dimension(640, 480));
    frame.add(this);
    
    // Save and Quit button
    final JButton trainButton = new JButton("Train neural network");
    final JButton recogButton = new JButton("Recognize input characters");

    trainButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        TrainerControlPanel tcp = new TrainerControlPanel(new JFrame());
      }

    });
    add(trainButton, BorderLayout.WEST);
    add(recogButton, BorderLayout.EAST);
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
