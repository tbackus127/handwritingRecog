/*
 * Names: Jarred Durant, Tim Backus, Eric Sakshaug, Tyler Fiacco
 * Course: CIS421 -- Artificial Intelligence
 * Instructor: Dr. Laura Grabowski 
 * Handwriting Recognition with Neural Networks
 * Thanks to all of the folks working on Neuroph. Check it out at http://neuroph.sourceforge.net.
 * Neuroph Javadoc: http://neuroph.sourceforge.net/javadoc/index.html
 */

package aihw.nnet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;

import aihw.utils.DataSetFactory;
import aihw.utils.ImageSplitter;

public class HWNeuralNet {
  
 
  /** The filename to load and save the network from/to. */
  public static final String NETWORK_FILENAME = "savedWeights.dat";
  
  /** The network itself. */
  private MultiLayerPerceptron nnet = null;
  
  /** The training data directory. */
  private final File tdataDir;
  
  /**
   * Default constructor.
   */
  public HWNeuralNet(File tdataDir) {
    this.tdataDir = tdataDir;
    this.nnet = new MultiLayerPerceptron(ImageSplitter.TRAINING_IMAGE_SIZE, 127, 26);
  }
  
  /**
   * Trains the neural network automatically.
   */
  public void train() {
    try {
      this.nnet.learnInNewThread(DataSetFactory.getDataSet(this.tdataDir));
    } catch (FileNotFoundException fnf) {
      fnf.printStackTrace();
    }
  }
  
  public char recognizeCharacter(BufferedImage img) {
    // TODO: Probably just get a number and add it to 'a'.
    return 'a';
  }
  
  /**
   * Saves the network to a file.
   */
  public void saveToFile() {
    this.nnet.stopLearning();
    this.nnet.save(NETWORK_FILENAME);
  }
  
  /**
   * Loads the network from a file.
   */
  public void loadFromFile() {
    System.out.println("Loaded network");
    this.nnet = (MultiLayerPerceptron) NeuralNetwork.createFromFile(NETWORK_FILENAME);
  }
}
