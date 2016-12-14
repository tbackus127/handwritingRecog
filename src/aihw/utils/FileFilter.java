
package aihw.utils;

import java.io.FilenameFilter;
import java.io.File;

/**
 * A simple file filter to accept jpgs.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug Eric.Sakshaug11@gmail.com
 *
 */
public class FileFilter implements FilenameFilter {

  /** The directory. */
  private File directory;

  /**
   * Default constructor.
   * 
   * @param dir the directory as a File object.
   */
  public FileFilter(File dir) {
    directory = dir;
  }

  /**
   * Whether or not to accept a given file.
   * 
   * @param dir the File object to test.
   * @param name the name of the file.
   */
  @Override
  public boolean accept(File dir, String name) {
    return (dir.equals(directory) && name.endsWith(".jpg"));
  }

}
