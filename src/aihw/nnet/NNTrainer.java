
package aihw.nnet;

import java.io.File;

/**
 * This class handles automating neural network training.
 * 
 * @author Tim Backus tbackus127@gmail.com
 *
 */
public class NNTrainer {

  /** The amount of images to train the network on until it saves. */
  private static final int DEFAULT_AUTOSAVE_ITERATIONS = 100;

  /** Whether or not the network can keep being trained. */
  private static volatile boolean isRunning = false;

  /** The neural network itself. */
  private static HWNeuralNet nnet;

  /**
   * Main method.
   * 
   * @param args runtime args; ignored.
   */
  public static void main(String[] args) {
    if (trainingDataExists()) {
      trainNetwork(DEFAULT_AUTOSAVE_ITERATIONS);
    }
  }

  /**
   * Trains the neural network, autosaving after the specified number of images.
   * 
   * @param autosaveThresh the auto-save threshold.
   */
  private static void trainNetwork(int autosaveThresh) {

    // Load network
    loadNetwork();

    // Train network
    while (isRunning) {
      for (int i = 0; i < autosaveThresh && isRunning; i++) {
        nnet.train(null, 'a');
      }
      nnet.saveToFile();
    }
  }

  /**
   * Stops training the network and saves it.
   */
  public static synchronized void stopTraining() {
    isRunning = false;
  }
  
  /**
   * Loads the network from file.
   * 
   * @throws NNetworkLoadException
   */
  private static void loadNetwork() {
    nnet = new HWNeuralNet();
    nnet.loadFromFile();
  }

  /**
   * Checks if there is training data available.
   * 
   * @return true if training is able to be automated; false if not.
   */
  private static boolean trainingDataExists() {

    // Check if main dir exists
    final String tdataDir = "res/tdata/";
    final File tdataFolder = new File(tdataDir);
    System.out.println(tdataFolder.getAbsolutePath());
    if (!tdataFolder.exists() || !tdataFolder.isDirectory()) {
      System.err.println("Training data folder does not exist!");
      return false;
    }

    // Check if subdirs exist
    for (char c = 'a'; c <= 'z'; c++) {
      final File charFolder = new File(tdataDir + "/" + c);
      if (!charFolder.exists() || !charFolder.isDirectory()) {
        System.err.println("Training data folder \"" + c + "\" does not exist!");
        return false;
      }

      // Check if at least one training image exists for each letter
      final File[] charFiles = charFolder.listFiles();
      if (charFiles.length < 1) {
        System.err.println("No training files in " + c + " folder!");
      }
    }

    return true;
  }
}
