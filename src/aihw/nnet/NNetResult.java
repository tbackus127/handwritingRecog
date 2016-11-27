package aihw.nnet;


public class NNetResult {
  private final char character;
  private final double certainty;
  
  public NNetResult(char c, double d) {
    this.character = c;
    this.certainty = d;
  }

  public char getCharacter() {
    return character;
  }

  public double getCertainty() {
    return certainty;
  }
}
