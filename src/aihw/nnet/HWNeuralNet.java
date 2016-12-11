/*
 * Names: Jarred Durant, Tim Backus, Eric Sakshaug, Tyler Fiacco
 * Course: CIS421 -- Artificial Intelligence
 * Instructor: Dr. Laura Grabowski 
 * Handwriting Recognition with Neural Networks
 * Thanks to all of the folks working on Neuroph. Check it out at http://neuroph.sourceforge.net.
 * Neuroph Javadoc: http://neuroph.sourceforge.net/javadoc/index.html
 */

package aihw.nnet;

import java.io.File;
import java.io.FileNotFoundException;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;

import aihw.utils.DataSetFactory;

/**
 * This class acts as a wrapper for the neural network.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco EMAIL_HERE
 * @author Eric Sakshaug EMAIL_HERE
 *
 */
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
  public HWNeuralNet(final File tdataDir) {
    System.out.print("Creating network...");
    this.tdataDir = tdataDir;
    System.out.println(new File(NETWORK_FILENAME).getAbsolutePath());
    loadFromFile();
    //this.nnet = new MultiLayerPerceptron(ImageSplitter.TRAINING_IMAGE_SIZE, 127, 26);
    System.out.println(" DONE");
  }

  /**
   * Trains the neural network automatically.
   */
  public void train() {
    System.out.println("Training network...");
    try {
      this.nnet.learnInNewThread(DataSetFactory.getDataSet(this.tdataDir));
    }
    catch (FileNotFoundException fnf) {
      fnf.printStackTrace();
    }
  }

  /**
   * Recognizes a character in an image.
   * 
   * @param imgFile the file handle with the image.
   * @return a NNetResult with the character and confidence level.
   */
  public NNetResult recognizeCharacter(final File imgFile) {
    final double[] recInput = DataSetFactory.getDataRow(imgFile, 0).getInput();
    this.nnet.setInput(recInput);
    this.nnet.calculate();
    return getBestMatch(this.nnet.getOutput());
  }

  /**
   * Gets the output of the network.
   * 
   * @return a double[] of size 26, one for each letter of the alphabet.
   */
  public double[] getOutput() {
    return this.nnet.getOutput();
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
    System.out.print("Loading network...");
    this.nnet = (MultiLayerPerceptron) NeuralNetwork.createFromFile(NETWORK_FILENAME);
    System.out.println(" DONE");
  }

  /**
   * Encodes the neural network's output as a character and its certainty.
   * 
   * @param data the network's output array.
   * @return a NNetResult struct containing a char and a double.
   */
  private NNetResult getBestMatch(double[] data) {
    int bestIndex = -1;
    double bestValue = 0.0D;
    for (int i = 0; i < data.length; i++) {
      if (bestValue < data[i]) {
        bestIndex = i;
        bestValue = data[i];
      }
    }
    return new NNetResult((char) (bestIndex + 'a'), bestValue);
  }
}
