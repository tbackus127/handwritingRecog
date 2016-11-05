package aihw.autocorrect;

import java.util.Arrays;

public class Word{

    String word;
    LinkedList<String> options = null;
    
    public Word(String word){
	threshold = DEFAULT_THRESHOLD;
	this.word = word;
    }

    public Word(String word, int thresh){
	threshold = thresh;
	this.word = word;
    }

    public int getString(){
	return word;
    }

    public void levenshtein(){
	options = Levenshtein.getOptions(this);
    }

}
