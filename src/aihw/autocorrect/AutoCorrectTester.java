
package aihw.autocorrect;

import aihw.autocorrect.AutoCorrect;

/**
 * This class performs tests to the autocorrect system.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug Eric.Sakshaug11@gmail.com
 *
 */
public class AutoCorrectTester {

  /**
   * Main method.
   * 
   * @param args runtime arguments. Each argument can be a separate word to autocorrect.
   */
  public static void main(String[] args) {
    System.out.println("Number of words: " + args.length);
    if (args.length > 1) {
      String toTest = "";
      for (int i = 0; i < args.length; i++) {
        toTest += args[i] + " ";
      }
      System.out.println("Autocorrected " + toTest + " to " + AutoCorrect.checkString(toTest));
    } else if (args.length == 1) {
      System.out.println("Autocorrected " + args[0] + " to " + AutoCorrect.checkWord(args[0]));
    } else {
      System.out.println("You must have an argument for testing!");
    }
  }
}
