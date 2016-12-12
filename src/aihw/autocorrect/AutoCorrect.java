package aihw.autocorrect;

import java.util.Arrays;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

/*
 * All methods and variabless in this class--even those based on 
 * real concepts--are entirely fictional. All celebrity voices 
 * are impersonated.....Poorly. The following program contains
 * stupid language and due to its content it should not be used 
 * by anyone.
 */

public class AutoCorrect{

    public static final int MAX_DISTANCE = 5;
    public static final String DICTIONARY_FILE_LOCATION = "res/autocorrect/dictionary.txt";
    private static boolean isDictionarySetup = false;
    private static LinkedList<String> dictionary = new LinkedList<String>();



    /*
     * Checks a full sentence, or list of words, with a custom max 
     * levenshtein distance. Splits the sentence using 
     * String.split("\\s+") and passes each element in the returned
     * array to the checkWord method.
     */
    public static String checkString(String toCheck, int maxDistance){
        String[] holder = toCheck.split("\\s+");
        String toReturn = "";
        for(int i = 0 ; i < holder.length ; i++){
            toReturn += checkWord(holder[i], maxDistance);
        }
        return toReturn;
    }

    /*
     * Checks a sentence with a standard max distance.
     * This method is the same as:
     * checkString(toCheck, AutoCorrect.MAX_DISTANCE);
     */
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
            toReturn = levenshteinEngine(toCheck, i);
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

    /*
     * Iterates through the dictionary list, and passes all entries
     * one by one into the levenshtein method.
     */
    private static String levenshteinEngine(String toCheck, int dist){
        boolean found = false;
        String toReturn = null;
        if(toCheck == null){
            throw new IllegalArgumentException("Strings must not be null!");
        }

        //Because setting up the dictionary is such a
        //lengthy process, we only want to do it once.
        if(!isDictionarySetup){
            setupDictionary();
        }
        Iterator<String> iterate = dictionary.iterator();
        while(iterate.hasNext() && !found){
            String temp = iterate.next();
            if(levenshtein(toCheck, temp, dist)){
                toReturn = temp;
                found = true;
                System.out.println("Found a match of: " +
                                   toCheck +
                                   ". Matched with: " +
                                   temp);
            }
        }
        return toReturn;
    }

    /*
     * Sets up the dictionary linkedlist. 
     */
    private static void setupDictionary(){
        try{
            Scanner dictionaryScanner = new Scanner(
                                                    new FileInputStream(
                                                                        new File(DICTIONARY_FILE_LOCATION)));
            while(dictionaryScanner.hasNextLine()){
                dictionary.add(dictionaryScanner.nextLine());
            }
            isDictionarySetup = true;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * This implementation of comparation for Levenshtein Distance was pulled 
     * from:
     * https://commons.apache.org/sandbox/commons-text/jacoco/
     * org.apache.commons.text.similarity/LevenshteinDistance.java.html
     * This method was modified from its original version, 
     * it has been formatted to fit your screen.
     */
    private static boolean levenshtein(String toCheck,
                                       String dictionary,
                                       int distance){
        int toReturn = 0;

        /*
         * If the difference in length between the two strings is
         * greater than the max distance, then they cannot be a 
         * viable option, so we set a -1.
         */
        int n = toCheck.length();
        int m = dictionary.length();
        if(Math.abs(n-m) > distance){
            return false;
        }else{

            int[] p = new int[n+1]; //Past cost array, horizontally.
            int[] d = new int[n+1]; //Cost array, horizontally
            int[] tempD; //Placeholder for swapping p and d.

            //Fill in starting table values
            final int boundary = Math.min(n, distance) + 1;
            for( int i = 0 ; i < boundary ; i++){
                p[i] = i;
            }

            //These fills will ensure that the value
            //above the rightmost
            //entry of our stripe will be ignored in
            //following iterations
            Arrays.fill(p, boundary, p.length, Integer.MAX_VALUE);
            Arrays.fill(d, Integer.MAX_VALUE);

            //Iterates through t
            for(int j = 1 ; j <= m ; j++){
                //Grabs the jth character on the comparison
                final char rightJ = toCheck.charAt(j-1);
                d[0] = j;

                //Compute stripe indices, constrain to array size
                final int min = Math.max(1,j-distance);
                final int max = j > Integer.MAX_VALUE - distance ? n
                    : Math.min(n, j + distance);

                //The stripe may get out of bounds if
                //s and t are of different sizes
                if(min > max){
                    return false;
                }
                // ignore entry left of leftmost
                if (min > 1) {
                    d[min - 1] = Integer.MAX_VALUE;
                }

                // iterates through [min, max] in s
                for (int i = min; i <= max; i++) {
                    if (dictionary.charAt(i - 1) == rightJ) {
                        // diagonally left and up
                        d[i] = p[i - 1];
                    } else {
                        // 1 + minimum of cell to the left, to the
                        // top, diagonally left and up
                        d[i] = 1 + Math.min(
                                            Math.min(d[i - 1], p[i]),
                                            p[i - 1]);
                    }
                }

                // copy current distance counts to 'previous row'
                //distance counts
                tempD = p;
                p = d;
                d = tempD;
            }

            // if p[n] is greater than the threshold,
            // there's no guarantee on it being the correct
            // distance
            if (p[n] <= distance) {
                return true;
            }
        }
        return false;
    }
}


