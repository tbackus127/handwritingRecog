
package aihw.nnet;

import java.io.File;

import javax.swing.SwingUtilities;

/**
 * This class will load the existing neural network and train it.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug Eric.Sakshaug11@gmail.com
 *
 */
public class NNTrainer {

  /**
   * Main method.
   * 
   * @param args runtime arguments. Ignored.
   */
  public static void run(final HWNeuralNet nnet) {

    System.out.println("Training network, press \"Save and Quit\" to stop.");

    // Free up the Event Dispatch Thread
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {

        // Create the frame and set its properties

        // Load the network if it exists
        final File nnSave = new File(HWNeuralNet.NETWORK_FILENAME);
        if (nnSave.exists()) {
          nnet.loadFromFile();
        }
        nnet.train();
      }
    });

  }
}
