
package aihw.utils;

import java.io.File;
import java.util.Arrays;

import aihw.nnet.HWNeuralNet;
import aihw.nnet.NNRecognizer;
import aihw.nnet.NNetResult;
import aihw.autocorrect.AutoCorrect;

/**
 * This class reads words from the res/data/word directory.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug Eric.Sakshaug11@gmail.com
 *
 */
public class WordReader {

  /** The resource path. */
  private static String resPath = "res/data/word/";

  /** The training data path. */
  private static String HWPath = "res/tdata/";

  /**
   * Main method.
   * 
   * @param args runtime args. 0: the word path; 1: the training data path.
   */
  public static void main(String[] args) {
    System.out.println("Starting to read a String from saved data");
    if (args.length == 1) {
      resPath = args[0];
    } else if (args.length == 2) {
      resPath = args[0];
      HWPath = args[1];
    } else {
      System.out.println("Your output is: \n" + readString());
    }
  }

  /**
   * Reads the character images as a string into the network.
   * 
   * @return the read and autocorrected String.
   */
  public static String readString() {

    NNRecognizer nNet = new NNRecognizer(new HWNeuralNet(new File(HWPath)));
    File resFolder = new File(resPath);
    File[] fileArray = resFolder.listFiles(new FileFilter(resFolder));

    // Sorts the files numericaly.
    Arrays.sort(fileArray, new NumericFileComparator());

    for (int i = 0; i < fileArray.length; i++) {
      System.out.println(fileArray[i]);
    }

    String lastName = fileArray[fileArray.length - 1].getName();
    lastName = lastName.substring(0, lastName.indexOf("."));
    int numOfChars = new Integer(lastName).intValue();
    String output = "";
    int j = 0;

    for (int i = 1; i <= numOfChars && j < fileArray.length; i++) {
      String currFileName = i + ".jpg";
      if (fileArray[j].getName().equals(currFileName)) {
        NNetResult res = nNet.recognize(fileArray[j]);
        System.out
            .println("Recognized: " + res.getCharacter() + " With: " + (res.getCertainty() * 100.0) + "% certainty");
        output += res.getCharacter();
        j++;
      } else {
        output += " ";
      }
    }
    return AutoCorrect.checkString(output);
  }
}
