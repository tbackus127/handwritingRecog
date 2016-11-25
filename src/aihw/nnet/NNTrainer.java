package aihw.nnet;

import java.io.File;

public class NNTrainer {
  
  public static void main(String[] args) {
    System.out.println("Training network...");
    
    final HWNeuralNet nnet = new HWNeuralNet(new File("res/tdata"));
    nnet.train();
  }
}
