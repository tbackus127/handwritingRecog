package aihw.nnet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NNControlPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  
  private final HWNeuralNet nnet;
  private final JFrame frame;
  
  public NNControlPanel(HWNeuralNet n, JFrame frame) {
    super(new BorderLayout(), true);
    
    this.frame = frame;
    this.nnet = n;
    
    setSize(getPreferredSize());
    
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
  
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(240, 180);
  }
  
  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }
  
  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }
  
}
