
package aihw.utils;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * This class will take a scanned image with 26 lowercase letters hand-written into its respective boxes and split it
 * into 26 separate images to be later fed into a neural network for handwriting recognition.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug Eric.Sakshaug11@gmail.com
 * 
 */
public class ImageSplitter {

  /** How many boxes are in a column on the scanned image. */
  private static final int BOXES_PER_COLUMN = 21;

  /** How many different characters we're looking for. */
  private static final int GLYPH_COUNT = 26;

  /** The desired image size, in pixels. */
  private static final Dimension SCALED_IMAGE_SIZE = new Dimension(1402, 1052);

  /** The width in pixels of the final image to be split's box borders. */
  private static final int BOX_BORDER_WIDTH = 3;

  /** The width in pixels of each box in the final image after scaling. */
  private static final int BOX_SIZE = 47;

  /** The amount of pixels to crop a sample image from the inside. */
  private static final int CROP_AMOUNT = 4;

  /** The length and width of the training images. */
  public static final int TRAINING_IMAGE_SIZE = (BOX_SIZE - (CROP_AMOUNT << 1)) * (BOX_SIZE - (CROP_AMOUNT << 1));

  /** An image counter to prevent overwrites. */
  private static int IMAGE_COUNTER = 0;

  /** The amount of color a pixel needs to be turned white by the threshold operation. */
  private static int COLOR_THRESHOLD = 215;

  /**
   * Main method.
   * 
   * @param args args[0]: the scanned image file location.
   */
  public static void main(String[] args) {

    // Check against invalid runtime params and print usage message
    if (args.length != 1) {
      System.err.println("Invalid runtime arguments. Usage:\n  " + "\"java ImageSplitter <scanned image file>\"");
      return;
    }

    // Create file handle and check if it can be used
    File scanFile = new File(args[0]);
    System.out.println("Scanning \"" + scanFile.getAbsolutePath() + "\"...");
    if (!isFileValid(scanFile)) {
      System.err.println("Not a valid image file.");
      return;
    }

    doImageSplit(scanFile);

  }

  /**
   * Splits an image into its individual letter images.
   * 
   * @param img the File handle to the scanned image.
   */
  private static final void doImageSplit(File f) {

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

    final BufferedImage threshImg = doThreshold(img, COLOR_THRESHOLD);

    // Split the image and save the result images
    final SplitImage[] splitImages = splitImage(threshImg);
    for (SplitImage splImg : splitImages) {
      if (splImg == null) break;
      saveImage(splImg.getChar(), splImg.getImage(), IMAGE_COUNTER++);
    }
    System.out.println("Image split successfully.");

  }

  /**
   * Splits the image into an array of SplitImage objects.
   * 
   * @param sampleImg the original handwriting sample image.
   * @return an array of SplitImages, containing the image and its character.
   */
  private static final SplitImage[] splitImage(BufferedImage sampleImg) {

    final SplitImage[] result = new SplitImage[GLYPH_COUNT * BOXES_PER_COLUMN];

    final int scw = (int) SCALED_IMAGE_SIZE.getWidth();
    final int sch = (int) SCALED_IMAGE_SIZE.getHeight();
    final int imgW = sampleImg.getWidth();
    final int imgH = sampleImg.getHeight();

    // Scale the image if needed (aspect ratio must be equal to 1402/1052)
    if (imgW != scw || imgH != sch) {

      System.out.println("Scaling image...");
      // Scale by Affine Transform
      AffineTransform aTrans = new AffineTransform();
      final double scaleRatio = (double) scw / (double) imgW;
      aTrans.scale(scaleRatio, scaleRatio);
      AffineTransformOp scaleOp = new AffineTransformOp(aTrans, AffineTransformOp.TYPE_BICUBIC);

      // Create scaled image and set the scanned image to the scaled image
      final BufferedImage scaledImage = scaleOp.filter(sampleImg,
          new BufferedImage(scw, sch, BufferedImage.TYPE_BYTE_GRAY));
      sampleImg = scaledImage;
    }

    // Iterate through rows
    int imgCount = 0;
    for (int row = 0; row < BOXES_PER_COLUMN; row++) {
      final int originY = row * BOX_SIZE + BOX_BORDER_WIDTH * (row + 1) + CROP_AMOUNT;
      for (int col = 0; col < GLYPH_COUNT; col++) {
        System.out.println("Fetching " + row + "," + col);
        final int originX = col * BOX_SIZE + BOX_BORDER_WIDTH * (col + 1) + CROP_AMOUNT;
        System.out.println("Glyph@(" + originX + "," + originY + ")");

        // Create cropped sub-image
        final BufferedImage glyphImage = sampleImg.getSubimage(originX, originY, BOX_SIZE - (CROP_AMOUNT * 2),
            BOX_SIZE - (CROP_AMOUNT * 2));
        result[imgCount] = new SplitImage((char) (imgCount % 26 + 'a'), glyphImage);
        imgCount++;
      }
    }

    return result;
  }

  /**
   * Performs a threshold operation on a BufferedImage
   * 
   * @param original the image to threshold.
   * @param thresh the threshold of color.
   * @return the new threshold-ed image.
   */
  private static final BufferedImage doThreshold(BufferedImage original, int thresh) {

    System.out.println("Creating black and white image...");

    // Create a new BufferedImage that will hold our threshold-ed data
    final BufferedImage resImg = new BufferedImage(original.getWidth(), original.getHeight(),
        BufferedImage.TYPE_BYTE_GRAY);
    final int imgWidth = original.getWidth();
    final int imgHeight = original.getHeight();

    // Iterate through the image's pixels and make them either black or white
    final WritableRaster origRaster = original.getRaster();
    final WritableRaster threshRaster = resImg.getRaster();
    final int numBands = origRaster.getNumBands();
    final int[] origPixels = new int[imgWidth * numBands];
    final int[] threshPixels = new int[imgWidth];

    // Iterate over each row
    for (int i = 0; i < imgHeight; i++) {

      // Get the entire row of pixels
      origRaster.getPixels(0, i, imgWidth, 1, origPixels);

      // Set each pixel to either black or white
      for (int j = 0; j < threshPixels.length; j++) {

        final int avgColor = (origPixels[numBands * j] + origPixels[numBands * j + 1] + origPixels[numBands * j + 2])
            / numBands;
        if (avgColor < thresh) {
          threshPixels[j] = 0;
        } else {
          threshPixels[j] = 255;
        }
      }
      threshRaster.setPixels(0, i, imgWidth, 1, threshPixels);
    }

    return resImg;
  }

  /**
   * Saves a timestamped image of a character to the disk.
   * 
   * @param ch the character this image contains. Will be saved to 'res/tdata/CHAR/COUNT.jpg'.
   * @param img the BufferedImage object containing the character's image data.
   * @param tCount a counter to prevent overwrites.
   */
  private static final void saveImage(char ch, BufferedImage img, int tCount) {

    final String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-ss").format(new Date());

    System.out.println("Saving image #" + tCount);
    File splImgFile = new File("res/tdata/" + ch + "/" + tCount + "-" + timestamp + ".png");
    splImgFile.getParentFile().mkdirs();

    try {
      splImgFile.createNewFile();
      ImageIO.write(img, "png", splImgFile);
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
  private static final boolean isFileValid(File scanFile) {

    // Check if the file exists
    if (!scanFile.exists()) {
      System.err.println("The system cannot find the file specified:  \n\"" + scanFile.getAbsolutePath() + "\"");
      return false;
    }

    final String[] exts = { "png", "jpg", "jpeg", "gif" };
    for (int i = 0; i < exts.length; i++) {
      if (scanFile.getName().endsWith(exts[i])) {
        return true;
      }
    }

    System.err.println("Image file must be .png, .jpg, .jpeg, or .gif");
    return false;
  }

}
