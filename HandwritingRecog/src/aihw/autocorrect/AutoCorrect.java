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
    public static final String DICTIONARY_FILE_LOCATION = System.getProperty("user.dir") + 
                       "/handwritingRecog/res/autocorrect/dictionary.txt";
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
            toReturn += checkWord(holder[i], maxDistance) + " ";
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
        String toReturn = levenshteinEngine(toCheck, maxDistance);
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
                String[] results = new String[dist+1];
                for(int i = 0 ; i < results.length ; i++){
                    results[i] = "";
                }
                int least = Integer.MAX_VALUE;
                while(iterate.hasNext() && !found){
                    String temp = iterate.next();
                    int len = levenshtein(toCheck, temp, dist);
                    if(len == 0){
                        toReturn = temp;
                        found = true;
                    }else if(len > 0){
                        results[len] = temp;
                        least = Math.min(len, least);
                    }
                }                
                //System.out.println("Least: " + least);
                if(!found && least > 0){
                    toReturn = results[least];
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
        private static int levenshtein(CharSequence left, CharSequence right,
            int threshold) {
        if (left == null || right == null) {
          throw new IllegalArgumentException("Strings must not be null");
        }
        if (threshold < 0) {
          throw new IllegalArgumentException("Threshold must not be negative");
        }

        /*
         * This implementation only computes the distance if it's less than or equal
         * to the threshold value, returning -1 if it's greater. The advantage is
         * performance: unbounded distance is O(nm), but a bound of k allows us to
         * reduce it to O(km) time by only computing a diagonal stripe of width 2k +
         * 1 of the cost table. It is also possible to use this to compute the
         * unbounded Levenshtein distance by starting the threshold at 1 and
         * doubling each time until the distance is found; this is O(dm), where d is
         * the distance.
         *
         * One subtlety comes from needing to ignore entries on the border of our
         * stripe eg. p[] = |#|#|#|* d[] = *|#|#|#| We must ignore the entry to the
         * left of the leftmost member We must ignore the entry above the rightmost
         * member
         *
         * Another subtlety comes from our stripe running off the matrix if the
         * strings aren't of the same size. Since string s is always swapped to be
         * the shorter of the two, the stripe will always run off to the upper right
         * instead of the lower left of the matrix.
         *
         * As a concrete example, suppose s is of length 5, t is of length 7, and
         * our threshold is 1. In this case we're going to walk a stripe of length
         * 3. The matrix would look like so:
         *
         * <pre> 1 2 3 4 5 1 |#|#| | | | 2 |#|#|#| | | 3 | |#|#|#| | 4 | | |#|#|#| 5
         * | | | |#|#| 6 | | | | |#| 7 | | | | | | </pre>
         *
         * Note how the stripe leads off the table as there is no possible way to
         * turn a string of length 5 into one of length 7 in edit distance of 1.
         *
         * Additionally, this implementation decreases memory usage by using two
         * single-dimensional arrays and swapping them back and forth instead of
         * allocating an entire n by m matrix. This requires a few minor changes,
         * such as immediately returning when it's detected that the stripe has run
         * off the matrix and initially filling the arrays with large values so that
         * entries we don't compute are ignored.
         *
         * See Algorithms on Strings, Trees and Sequences by Dan Gusfield for some
         * discussion.
         */

        int n = left.length(); // length of left
        int m = right.length(); // length of right

        // if one string is empty, the edit distance is necessarily the length
        // of the other
        if (n == 0) {
          return m <= threshold ? m : -1;
        } else if (m == 0) {
          return n <= threshold ? n : -1;
        }

        if (n > m) {
          // swap the two strings to consume less memory
          final CharSequence tmp = left;
          left = right;
          right = tmp;
          n = m;
          m = right.length();
        }

        int[] p = new int[n + 1]; // 'previous' cost array, horizontally
        int[] d = new int[n + 1]; // cost array, horizontally
        int[] tempD; // placeholder to assist in swapping p and d

        // fill in starting table values
        final int boundary = Math.min(n, threshold) + 1;
        for (int i = 0; i < boundary; i++) {
          p[i] = i;
        }
        // these fills ensure that the value above the rightmost entry of our
        // stripe will be ignored in following loop iterations
        Arrays.fill(p, boundary, p.length, Integer.MAX_VALUE);
        Arrays.fill(d, Integer.MAX_VALUE);

        // iterates through t
        for (int j = 1; j <= m; j++) {
          final char rightJ = right.charAt(j - 1); // jth character of right
          d[0] = j;

          // compute stripe indices, constrain to array size
          final int min = Math.max(1, j - threshold);
          final int max = j > Integer.MAX_VALUE - threshold ? n
              : Math.min(n, j + threshold);

          // the stripe may lead off of the table if s and t are of different
          // sizes
          if (min > max) {
            return -1;
          }

          // ignore entry left of leftmost
          if (min > 1) {
            d[min - 1] = Integer.MAX_VALUE;
          }

          // iterates through [min, max] in s
          for (int i = min; i <= max; i++) {
            if (left.charAt(i - 1) == rightJ) {
              // diagonally left and up
              d[i] = p[i - 1];
            } else {
              // 1 + minimum of cell to the left, to the top, diagonally
              // left and up
              d[i] = 1 + Math.min(Math.min(d[i - 1], p[i]), p[i - 1]);
            }
          }

          // copy current distance counts to 'previous row' distance counts
          tempD = p;
          p = d;
          d = tempD;
        }

        // if p[n] is greater than the threshold, there's no guarantee on it
        // being the correct
        // distance
        if (p[n] <= threshold) {
          return p[n];
        }
        return -1;
      }

        }


