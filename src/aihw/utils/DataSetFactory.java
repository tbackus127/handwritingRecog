
package aihw.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

public class DataSetFactory {
  
  public static DataSet getDataSet(File tdataDir) throws FileNotFoundException {
    if (!tdataDir.exists() || !tdataDir.isDirectory()) {
      throw new FileNotFoundException("Training data not found!");
    }
    
    final DataSet result = new DataSet(ImageSplitter.TRAINING_IMAGE_SIZE, 26);
    final File[] tdataFolders = tdataDir.listFiles(new FilenameFilter() {
      
      @Override
      public boolean accept(File curr, String name) {
        return new File(curr, name).isDirectory();
      }
      
    });
    
    for (int charNum = 0; charNum < tdataFolders.length; charNum++) {
      final File charFolder = tdataFolders[charNum];
      
      final File[] trainingImages = charFolder.listFiles();
      
      for (File imgFile : trainingImages) {
        
        final double[] pixelInputs = new double[ImageSplitter.TRAINING_IMAGE_SIZE];
        final double[] pixelOutputs = new double[26];
        int inputCount = 0;
        
        // Read the image
        BufferedImage img;
        try {
          img = ImageIO.read(imgFile);
        } catch (IOException ioe) {
          ioe.printStackTrace();
          return null;
        }
        
        int[] imgPixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
        for (int i = 0; i < imgPixels.length; i++) {
          final int pxVal = imgPixels[i];
          final int bv = pxVal & 0x000000FF;
          pixelInputs[i] = (bv >= 127) ? 1.0 : 0.0;
        }
        pixelOutputs[charNum] = 1.0;
        final DataSetRow dsRow = new DataSetRow(pixelInputs, pixelOutputs);
        result.addRow(dsRow);
      }
    }
    
    return result;
  }
}
