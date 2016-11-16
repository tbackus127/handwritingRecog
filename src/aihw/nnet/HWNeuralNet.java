/*
Names: Jarred Durant, Tim Backus, Eric Sakshaug, Tyler Fiacco
Course: CIS421 -- Artificial Intelligence
Instructor: Dr. Laura Grabowski
Handwriting Recognition with Neural Networks

Thanks to all of the folks working on Neuroph.
Check it out at http://neuroph.sourceforge.net.
*/

package aihw.nnet;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;

public class HWNeuralNet {

  public static final File savedWeights = new File(
      "../../../res/tdata/savedWeights.txt");

  // this parameter being passed into the MultiLayerPerceptron constructor is
  // the number of nodes within a layer. This can be changed, but we'd need to
  // retrain the neural network after we change it. I picked 4 as an arbitrary
  // starting point.
  public static final NeuralNetwork nnet = new MultiLayerPerceptron(4);

  public static void main(String[] args) {
    if (!savedWeights.exists()) try {
      savedWeights.createNewFile();
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    readSavedWeights();
    Scanner console = new Scanner(System.in);
    int operatingMode = menu(console);
    if (operatingMode == 1) train();
    else processRealData();
  }

  public static void train() {
    // TODO:
    // read input image file, using ImageSplitter
    // train the network
    // save weights to savedWeights
  }

  public static void processRealData() {
    // TODO:
    // read input image file using ImageSplitter
    // process data
    // output processed characters
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
