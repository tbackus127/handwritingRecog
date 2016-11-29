
package aihw.nnet;

/**
 * This class handles results from the neural network.
 * 
 * @author Tim Backus tbackus127@gmail.com
 *
 */
public class NNetResult {

  /** The character result. */
  private final char character;

  /** The certainty of the choice. */
  private final double certainty;

  /**
   * Default constructor.
   * 
   * @param c the character.
   * @param d the certainty from 0 to 1, inclusive.
   */
  public NNetResult(char c, double d) {
    this.character = c;
    this.certainty = d;
  }

  /**
   * Gets the character.
   * 
   * @return a char.
   */
  public char getCharacter() {
    return character;
  }

  /**
   * Gets the certainty.
   * 
   * @return a double.
   */
  public double getCertainty() {
    return certainty;
  }
}
