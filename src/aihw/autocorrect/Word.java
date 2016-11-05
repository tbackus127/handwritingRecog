package aihw.autocorrect;

import java.util.Arrays;

public class Word{

    String word;

    //The furthese edit distance to be considered a viable replacement.
    //5 is currently a placegholder, it should be tweaked in the future.
    private static final int DEFAULT_THRESHOLD = 5; 

    //This allows us to change the threshold for consideration
    private int threshold;
    
    public Word(String word){
	threshold = DEFAULT_THRESHOLD;
	this.word = word;
    }

    public Word(String word, int thresh){
	threshold = thresh;
	this.word = word;
    }

    public int getThreshold(){
	return threshold;
    }

    public static computeDistance(Word original, String possibility){
	
    }

}
