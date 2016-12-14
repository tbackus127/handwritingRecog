
package aihw.utils;

import java.io.File;
import java.util.Comparator;

/**
 * This class acts as a file comparator.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug Eric.Sakshaug11@gmail.com
 *
 */
public class NumericFileComparator implements Comparator<File> {

  /**
   * Compares two files.
   * 
   * @param file1 the first file.
   * @param file2 the second file.
   * @return -1, 0, or 1 as specified by the Comparator's compare() method.
   */
  public int compare(File file1, File file2) {
    int toReturn = 0;
    String name1 = file1.getName().substring(0, file1.getName().indexOf("."));
    String name2 = file2.getName().substring(0, file2.getName().indexOf("."));
    int num1 = new Integer(name1).intValue();
    int num2 = new Integer(name2).intValue();
    toReturn = num1 < num2 ? -1 : num1 == num2 ? 0 : 1;

    return toReturn;
  }

}
