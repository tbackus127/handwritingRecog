
package aihw.utils;

import java.awt.image.BufferedImage;

/**
 * This class acts as a struct for a split image, containing the image as a BufferedImage, and a char representing what
 * character the image is of.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco EMAIL_HERE
 * @author Eric Sakshaug EMAIL_HERE
 * 
 */
public class SplitImage {

  /** The character the image is of. */
  private final char ch;

  /** The image itself. */
  private final BufferedImage img;

  /**
   * Default constructor.
   * 
   * @param ch the character the image contains.
   * @param img the image data as a BufferedImage.
   */
  public SplitImage(char ch, BufferedImage img) {
    this.ch = ch;
    this.img = img;
  }

  /**
   * Gets the character in the image.
   * 
   * @return a char from 'a' to 'z'.
   */
  public char getChar() {
    return ch;
  }

  /**
   * Gets the image.
   * 
   * @return the image data as a BufferedImage.
   */
  public BufferedImage getImage() {
    return img;
  }
}
