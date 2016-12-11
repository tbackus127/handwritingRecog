
package src.aihw.autocorrect;

import java.util.LinkedList;

public class Word {

  private static final int DEFAULT_THRESHOLD = 0;
  
  private final int threshold;
  private final String word;
  private LinkedList<String> options = null;

  public Word(String word) {
    this.threshold = DEFAULT_THRESHOLD;
    this.word = word;
  }

  public Word(String word, int thresh) {
    this.threshold = thresh;
    this.word = word;
  }

  public String getWord() {
    return this.word;
  }

  public void levenshtein() {
    setOptions(Levenshtein.getOptions(this));
  }

  public LinkedList<String> getOptions() {
    return options;
  }

  public void setOptions(LinkedList<String> options) {
    this.options = options;
  }

  public int getThreshold() {
    return threshold;
  }

}
