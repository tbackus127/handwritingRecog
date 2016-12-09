package src.aihw.nnet;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

public class ControlPanelRunner {

  public static void main(String[] args) throws FileNotFoundException {
    HWNeuralNet nnet = new HWNeuralNet(new File("res/tdata"));
    NNControlPanel cp = new NNControlPanel(nnet, new JFrame());
  }

}
