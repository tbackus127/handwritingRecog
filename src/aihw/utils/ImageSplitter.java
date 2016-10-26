
package aihw.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

/**
 * This class will take a scanned image with 26 lowercase letters hand-written
 * into its respective boxes and split it into 26 separate images to be later
 * fed into a neural network for handwriting recognition.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * 
 */
public class ImageSplitter {

  private static final String[] VALID_IMAGE_EXTENSIONS = { "jpg", "png", "gif" };

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
    if (!isFileValid(scanFile)) {
      return;
    }

    doImageSplit(scanFile);

    System.out.println("Scanning \"" + scanFile.getAbsolutePath() + "\"...");
  }

  /**
   * Splits an image into its individual letter images.
   * 
   * @param img the File handle to the scanned image.
   */
  private static void doImageSplit(File f) {

    // Read in the image file as a BufferedImage
    BufferedImage img = null;
    try {
      img = ImageIO.read(f);
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
      System.err.println("Failed to read the file as an image.");
      return;
    }

    // Split the image and save the result images
    final SplitImage[] splitImages = splitImage(img);
    for (SplitImage splImg : splitImages) {
      saveImage(splImg.getChar(), splImg.getImage());
    }

  }

  /**
   * Splits the image into an array of SplitImage objects.
   * @param sampleImg the original handwriting sample image.
   * @return an array of SplitImages, containing the image and its character.
   */
  private static SplitImage[] splitImage(BufferedImage sampleImg) {

    final SplitImage[] result = new SplitImage[26];
    
    
    
    return result;
  }

  /**
   * Saves the image to the disk.
   * 
   * @param ch the character this image contains. Will be saved to
   *          'res/tdata/CHAR/COUNT.jpg'.
   * @param img the BufferedImage object containing the character's image data.
   */
  private static void saveImage(char ch, BufferedImage img) {

    final File splImgFile = new File("res/img/" + ch + "/" + 0 + ".jpg");
    
    try {
      ImageIO.write(img, "jpg", splImgFile);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
  }

  /**
   * Checks if a passed file is a valid image.
   * 
   * @param scanFile the File handle to the image.
   * @return true if scanFile is an image, false if not.
   */
  private static boolean isFileValid(File scanFile) {

    // Check if the file exists
    if (!scanFile.exists()) {
      System.err.println("The system cannot find the file specified:  \n\""
          + scanFile.getAbsolutePath() + "\"");
      return false;
    }

    // Ensure the file is an image
    final String mimeType = new MimetypesFileTypeMap().getContentType(scanFile);
    if (!mimeType.startsWith("image/")) {
      return false;
    }

    return true;
  }
}
