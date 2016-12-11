/**
 * This is our main class -- it initializes a neural net and opens a GUI.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug EMAIL_HERE
 *
 */

package aihw.nnet;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

public class ControlPanelRunner {

  public static void main(String[] args) throws FileNotFoundException {
    HWNeuralNet nnet = new HWNeuralNet(new File("res/tdata/"));
    NNControlPanel cp = new NNControlPanel(nnet, new JFrame());
  }

}
