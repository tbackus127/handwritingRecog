package aihw.autocorrect;

public class AutoCorrect{

    public static final int MAX_DISTANCE = 5;
    public static final String DICTIONARY_FILE_LOCATION = "res/autocorrect/dictionary.txt";
    private static boolean isDictionarySetup = false;
    private static LinkedList<String> dictionary = new LinkedList<String>();

    public static String checkString(String toCheck, int maxDistance){
      String[] holder = toCheck.split("\\s+");
      String toReturn = "";
      for(int i = 0 ; i < toCheck.length ; i++){
        toReturn += checkWord(holder[i], maxDistance);
      }
      return toReturn;
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
     * (But not really, because apparently dun is a word that 
     * I didn't know about)
     */
    public static String checkWord(String toCheck, int maxDistance){
        boolean found = false;
        String toReturn = "";
        for(int i = 0 ; !found && i <= maxDistance ; i++){
            toReturn = levenshtein(toCheck, i);
            found = (toReturn != null);
      }
      return toReturn;
    }

    /*
     * Checks the word with a standard max distance.
     * This method is the same as: 
     * AutoCorrect.checkString(toCheck, AutoCorrect.MAX_DISTANCE);
     */
    public static String checkWord(String toCheck){
	return checkWord(toCheck, MAX_DISTANCE);
    }

    private static String levenshteinEngine(String toCheck, int dist){
        if(toCheck == null){
            throw new IllegalArgumentException("Strings must not be null!");
        }
        if(!isDictionarySetup){
            setupDictionary();
        }
    }

    private static void setupDictionary(){
        try{
            Scanner DictionaryScanner = new Scanner(
                                        new FileInputStream(
                                        new File(DICTIONARY_FILE_LOCATION)));
            while(DictionaryScanner.hasNextLine()){
                Dictionary.add(dictionary.nextLine());
            }
            isDictionarySetup = true;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static String levenshtein(String toCheck, String dict, int dist){
        
    }

}
