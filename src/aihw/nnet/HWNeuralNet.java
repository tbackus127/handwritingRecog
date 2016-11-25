/*
Names: Jarred Durant, Tim Backus, Eric Sakshaug, Tyler Fiacco
Course: CIS421 -- Artificial Intelligence
Instructor: Dr. Laura Grabowski
Handwriting Recognition with Neural Networks

Thanks to all of the folks working on Neuroph.
Check it out at http://neuroph.sourceforge.net.
*/

package aihw.nnet;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;


public class HWNeuralNet {

  // Neuroph Javadoc: http://neuroph.sourceforge.net/javadoc/index.html
  
  private static final String NETWORK_FILENAME = "savedWeights.dat";
  
  private NeuralNetwork<BackPropagation> nnet = null;

  public HWNeuralNet() {
    
    final ArrayList<Integer> layerList = new ArrayList<Integer>();
    layerList.add(2209);
    layerList.add(127);
    layerList.add(26);
    this.nnet = new MultiLayerPerceptron(layerList);
  }

  public void train(DataSet ds, char correctChar) {
    // TODO:
    // tell the network whether it was right (for each character)
    // backpropagate
    // save weights to savedWeights
    System.out.println("Trained on " + correctChar);
    DataSet trainingData = new DataSet(26);
  }

  public char recognizeCharacter(BufferedImage img) {
    // TODO:
    // make 2D pixel array
    // process data
    // output processed characters
    return 'a';
  }

  public void saveToFile() {
    // TODO: Save weights to text file
    System.out.println("Saved network");
  }

  public void loadFromFile() {
    // TODO: load saved weights into nnet
    System.out.println("Loaded network");
    this.nnet = NeuralNetwork.createFromFile(NETWORK_FILENAME);
  }

  // public static int menu(Scanner console) {
  // System.out.println("What do you plan to do?");
  // System.out.println("1.) Train the neural network.");
  // System.out.println("2.) Give the neural network real data to process.");
  // int choice = console.nextInt();
  // while (choice < 1 || choice > 2) {
  // System.out.println("Please choose a valid option!");
  // choice = console.nextInt();
  // }
  // return choice;
  // }
}
