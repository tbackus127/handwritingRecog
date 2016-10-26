
package aihw.utils;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This class will take a scanned image with 26 lowercase letters hand-written
 * into its respective boxes and split it into 26 separate images to be later
 * fed into a neural network for handwriting recognition.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * 
 */
public class ImageSplitter {

  private static final String[] VALID_IMAGE_EXTENSIONS = {"jpg", "png", "gif"};
  
  /**
   * Main method.
   * 
   * @param args args[0]: the scanned image file location.
   */
  public static void main(String[] args) {

    // Check against invalid runtime params and print usage message
    if (args.length != 1) {
      System.err.println("Invalid runtime arguments. Usage:\n  "
          + "\"java ImageSplitter <scanned image file>\"");
      return;
    }

    // Create file handle and check if it can be used
    File scanFile = new File(args[0]);
    if(!isFileValid(scanFile)) {
      return;
    }

    System.out.println("Scanning \"" + scanFile.getAbsolutePath() + "\"...");
  }

  /**
   * Splits an image into its individual letter images.
   * 
   * @param img the File handle to the scanned image.
   */
  private static void splitImage(File img) {
    // TODO: Split the image
  }

  /**
   * Saves the image to the disk.
   * 
   * @param ch the character this image contains. Will be saved to
   *          'res/tdata/CHAR/COUNT.jpg'.
   * @param img the BufferedImage object containing the character's image data.
   */
  private static void saveImage(char ch, BufferedImage img) {
    // TODO: Save the image
  }
  
  private static boolean isFileValid(File scanFile) {
    // Check if the file exists
    if (!scanFile.exists()) {
      System.err.println("The system cannot find the file specified:  \n\""
          + scanFile.getAbsolutePath() + "\"");
      return false;
    }
    
    for(int i = 0; i < VALID_IMAGE_EXTENSIONS.length; i++) {
      if(!scanFile.getName().endsWith(VALID_IMAGE_EXTENSIONS[i])) {
        return false;
      }
      
    }
    
    return true;
  }
}
