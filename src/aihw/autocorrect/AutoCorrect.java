package aihw.autocorrect;

public class AutoCorrect{

    public static final int MAX_DISTANCE = 5;
    public static final String DICTIONARY_FILE_LOCATION = "res/autocorrect/dictionary.txt";


    public static String checkString(String toCheck, int maxDistance){
        return null;
    }

    public static String checkString(String toCheck){
        return checkString(toCheck, MAX_DISTANCE);
    }

    /*
     * Checks the string with a custom max Levenshtein distance.
     * This method will return the first string with the lowest 
     * Levenshtein distance. 
     * Example: Input string: "Dun" Possible matches at distance 1: 
     * "Bun, Dan, Den, Din, Don, Dune, Dung, Dunk, Duns, Dunt, Duny, 
     * Fun, Gun, Hun, Nun, Pun, Run, Sun"
     * Bun would be returned, as it is the first in alphabetical order. 
     */
    public static String checkWord(String toCheck, int maxDistance){
      
      return null;
    }

    /*
     * Checks the string with a standard max distance.
     * This method is the same as: 
     * AutoCorrect.checkString(toCheck, AutoCorrect.MAX_DISTANCE);
     */
    public static String checkWord(String toCheck){
	return checkWord(toCheck, MAX_DISTANCE);
    }

    

}
