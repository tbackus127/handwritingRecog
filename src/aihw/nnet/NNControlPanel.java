
package aihw.nnet;

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
 * @author Jarred Durant jarreddurant14@gmail.com
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
   * @param n
   *          reference to the neural network.
   * @param frame
   *          reference to the parent JFrame.
   */
  public NNControlPanel(final HWNeuralNet n, final JFrame frame) {
    super(new BorderLayout(), true);

    this.frame = frame;
    this.nnet = n;

    // Set the properties of the JFrame
    frame.setSize(new Dimension(640, 480));
    frame.add(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);

    final JButton trainButton = new JButton("Train neural network");
    final JButton recogButton = new JButton("Recognize input characters");

    trainButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        final JFrame trainFrame = new JFrame("Training Neural Network");
        TrainerControlPanel tcp = new TrainerControlPanel(nnet, trainFrame);
      }

    });

    recogButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        final JFrame recogFrame = new JFrame("Testing Neural Network");
        RecogControlPanel rcp = new RecogControlPanel(nnet, recogFrame);

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
