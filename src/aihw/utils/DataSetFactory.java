
package aihw.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

/**
 * This class creates a DataSet to be used with Neuroph's network.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant EMAIL_HERE
 * @author Tyler Fiacco EMAIL_HERE
 * @author Eric Sakshaug EMAIL_HERE
 *
 */
public class DataSetFactory {

  /**
   * Gets a DataSet built from training data.
   * 
   * @param tdataDir directory from which to build the DataSet from.
   * @return a DataSet object.
   * @throws FileNotFoundException
   */
  public static DataSet getDataSet(File tdataDir) throws FileNotFoundException {

    // Check if the directory exists
    if (!tdataDir.exists() || !tdataDir.isDirectory()) {
      throw new FileNotFoundException("Training data not found!");
    }

    // Create data set container
    final DataSet result = new DataSet(ImageSplitter.TRAINING_IMAGE_SIZE, 26);

    // Get a list of subdirectories
    final File[] tdataFolders = tdataDir.listFiles(new FilenameFilter() {

      @Override
      public boolean accept(File curr, String name) {
        return new File(curr, name).isDirectory();
      }
    });

    // For each of the letter folders
    for (int charNum = 0; charNum < tdataFolders.length; charNum++) {
      final File charFolder = tdataFolders[charNum];

      // And each of the files in the letter folder
      final File[] trainingImages = charFolder.listFiles();
      for (File imgFile : trainingImages) {

        final double[] pixelInputs = new double[ImageSplitter.TRAINING_IMAGE_SIZE];
        final double[] pixelOutputs = new double[26];

        // Read the image
        BufferedImage img;
        try {
          img = ImageIO.read(imgFile);
        }
        catch (IOException ioe) {
          ioe.printStackTrace();
          return null;
        }

        // Get the pixels of the image
        int[] imgPixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
        for (int i = 0; i < imgPixels.length; i++) {
          final int pxVal = imgPixels[i];

          // Map white to 1 and black to 0 for the training data
          final int bv = pxVal & 0x000000FF;
          pixelInputs[i] = (bv >= 127) ? 1.0 : 0.0;
        }

        // Pack up the data set row and add it.
        pixelOutputs[charNum] = 1.0;
        final DataSetRow dsRow = new DataSetRow(pixelInputs, pixelOutputs);
        result.addRow(dsRow);
      }
    }

    return result;
  }
}
