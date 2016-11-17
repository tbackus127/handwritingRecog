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

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.learning.PerceptronLearning;

public class HWNeuralNet {

  private static final String NETWORK_FILENAME = "savedWeights.dat";

  // This parameter being passed into the MultiLayerPerceptron constructor is
  // the number of nodes within a layer. This can be changed, but we'd need to
  // retrain the neural network after we change it. We will have 2209 inputs
  // and 26 outputs.
  // Javadoc: http://neuroph.sourceforge.net/javadoc/index.html
  private NeuralNetwork<PerceptronLearning> nnet = null;

  public HWNeuralNet() {}

  // public static void main(String[] args) {
  // if (!savedWeights.exists()) try {
  // savedWeights.createNewFile();
  // }
  // catch (IOException e) {
  // e.printStackTrace();
  // }
  // readSavedWeights();
  // Scanner console = new Scanner(System.in);
  // int operatingMode = menu(console);
  //
  // // TODO: get args
  // if (operatingMode == 1) train(null, 'a');
  // else processRealData();
  // }

  // private static void processRealData() {
  // TODO: Make method
  // What will this method do?

  // }

  public void train(DataSet ds, char correctChar) {
    // TODO:
    // tell the network whether it was right (for each character)
    // backpropagate
    // save weights to savedWeights

    // I'll make a wrapper program that will train the NNet while running.
    // It might be a good idea to not save after every training iteration
    // instead, perhaps every 100 iterations? If I make the wrapper program,
    // I'll take care of all of that. -- Tim
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
