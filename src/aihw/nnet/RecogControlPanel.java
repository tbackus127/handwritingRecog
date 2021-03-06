
package aihw.nnet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import aihw.utils.Drawing;

/**
 * This class is a GUI for the recognizer portion of the network. It provides options to draw a character, recognize the
 * network's training data, or read an image file.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug Eric.Sakshaug11@gmail.com
 *
 */
public class RecogControlPanel extends JPanel {

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
   * @param n reference to neural net.
   * @param frame reference to parent JFrame.
   */
  public RecogControlPanel(HWNeuralNet n, JFrame frame) {
    super(new BorderLayout(), true);

    this.frame = frame;
    this.nnet = n;

    // Set the size of the JFrame
    frame.setSize(new Dimension(480, 320));
    frame.add(this);

    final JButton drawButton = new JButton("Draw characters");
    final JButton trainingButton = new JButton("Recognize characters from training data");
    final JButton fileButton = new JButton("Get characters from file");

    drawButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        Drawing d = new Drawing();
        d.run();
      }
    });

    fileButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        final NNRecognizer recog = new NNRecognizer(nnet);
        final JFrame filenameFrame = new JFrame("Enter a filename to read (from cwd)");
        final JPanel filenamePanel = new JPanel(new BorderLayout());
        filenameFrame.add(filenamePanel);
        filenameFrame.setSize(480, 240);
        File f;
        NNetResult result;
        JFileChooser fc = new JFileChooser();
        int chooserResult = fc.showOpenDialog(filenameFrame);
        if (chooserResult == JFileChooser.APPROVE_OPTION) {
          f = fc.getSelectedFile();
          System.out.println("Got file! " + f.getName());
          result = recog.recognize(f);
          System.out.println(result.toString());
          fc.setSelectedFile(null);
        }
      }
    });

    trainingButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        NNRecognizer recog = new NNRecognizer(nnet);
        recog.recognizeTrainingData();
      }
    });
    add(drawButton, BorderLayout.WEST);
    add(trainingButton, BorderLayout.CENTER);
    add(fileButton, BorderLayout.EAST);
    frame.setVisible(true);
  }
}
