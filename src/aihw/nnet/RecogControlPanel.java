package src.aihw.nnet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.aihw.utils.Drawing;

import javax.swing.JFrame;

public class RecogControlPanel extends JPanel {

  /** The serial version number. */
  private static final long serialVersionUID = 1L;
  
  private final HWNeuralNet nnet;

  /** Reference to the parent JFrame. */
  @SuppressWarnings("unused")
  private final JFrame frame;
  
  public RecogControlPanel(HWNeuralNet n, JFrame frame) {
    super(new BorderLayout(), true);

    this.frame = frame;
    this.nnet = n;
    
    // Set the size of the JFrame
    frame.setSize(new Dimension(480, 320));
    frame.add(this);
    
    // Save and Quit button
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
        NNRecognizer recog = new NNRecognizer(nnet);
        // TODO type filename (browse?);
        // recog.recognize(filename);
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
    add(fileButton, BorderLayout.EAST);
    frame.setVisible(true);
  }
}
