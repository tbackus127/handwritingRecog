/*
Names: Jarred Durant, Tim Backus, Eric Sakshaug, Tyler Fiacco
Course: CIS421 -- Artificial Intelligence
Instructor: Dr. Laura Grabowski
Handwriting Recognition with Neural Networks

Thanks to all of the folks working on Neuroph.
Check it out at http://neuroph.sourceforge.net.
*/

package src.aihw.nnet;

import java.io.*;
import java.awt.image.*;
import java.util.*;
import org.neuroph.core.*;
import org.neuroph.core.input.*;
import org.neuroph.core.learning.*;
import org.neuroph.nnet.*;


public class HWNeuralNet {
  // This file will be read on every run, and written to when we are training.
  public static final File savedWeights =
                                new File("../../../res/tdata/savedWeights.txt");

  // This parameter being passed into the MultiLayerPerceptron constructor is
  // the number of nodes within a layer. This can be changed, but we'd need to
  // retrain the neural network after we change it. We will have 2209 inputs
  // and 26 outputs.
  public static final NeuralNetwork nnet = new Perceptron(2209, 26);

  public static void main(String[] args) {
    if (!savedWeights.exists()) savedWeights.createNewFile();
    readSavedWeights();
    Scanner console = new Scanner(System.in);
    int operatingMode = menu(console);
    if (operatingMode == 1) train();
    else processRealData();
  }

  public static void train() {
    // TODO:
    // tell the network whether it was right (for each character)
    // backpropagate
    // save weights to savedWeights
	  DataSet trainingData = new DataSet(26);
  }

  public static char read(BufferedImage img) {
    // TODO:
    // make 2D pixel array
    // process data
    // output processed characters
	return 'a';
  }

  public static void readSavedWeights() {
    // TODO: load saved weights into nnet
  }

  public static int menu(Scanner console) {
    System.out.println("What do you plan to do?");
    System.out.println("1.) Train the neural network.");
    System.out.println("2.) Give the neural network real data to process.");
    int choice = console.nextInt();
    while (choice < 1 || choice > 2) {
      System.out.println("Please choose a valid option!");
      choice = console.nextInt();
    }
    return choice;
  }
}
