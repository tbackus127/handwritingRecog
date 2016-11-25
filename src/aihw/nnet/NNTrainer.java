
package aihw.nnet;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class NNTrainer {
  
  public static void main(String[] args) {
    System.out.println("Training network, press \"Save and Quit\" to stop.");
    
    final HWNeuralNet nnet = new HWNeuralNet(new File("res/tdata"));
    
    // Free up the Event Dispatch Thread
    SwingUtilities.invokeLater(new Runnable() {
      
      public void run() {
        final JFrame frame = new JFrame("Training NNet");
        frame.add(new NNControlPanel(nnet, frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        
        final File nnSave = new File(HWNeuralNet.NETWORK_FILENAME);
        if(nnSave.exists()) {
          nnet.loadFromFile();
        }
        nnet.train();
      }
    });
    
  }
}
