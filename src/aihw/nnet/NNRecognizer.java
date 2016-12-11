
package src.aihw.nnet;

import java.io.File;
import java.io.FilenameFilter;
import java.io.FileNotFoundException;

/**
 * This class handles recognizing characters from images via the neural network.
 * 
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug EMAIL_HERE
 *
 */
public class NNRecognizer {

  /** The handle to the neural net. */
  private final HWNeuralNet nnet;

  /**
   * Default constructor.
   */
  public NNRecognizer(HWNeuralNet n) {
    this.nnet = n;
  }

  /**
   * Gets a result from the neural net from a passed image.
   * 
   * @param image
   * @return
   */
  public NNetResult recognize(final File image) {
    return this.nnet.recognizeCharacter(image);
  }

 public void recognizeTrainingData() {
    // Get all files (directories only) in 'res/tdata'
    final File[] tdataDirs = new File("res/tdata").listFiles(new FilenameFilter() {

      @Override
      public boolean accept(File curr, String name) {
        return new File(curr, name).isDirectory();
      }
    });

    // For all directories in tdata
    for (int charNum = 0; charNum < tdataDirs.length; charNum++) {
      File charDir = tdataDirs[charNum];
      final File[] charFiles = charDir.listFiles();
      int recCount = 0;
      double certaintyTotal = 0.0D;

      // For all character images in each letter folder
      for (File charImg : charFiles) {
        final NNetResult res = this.recognize(charImg);

        // Count correctly recognized characters
        if (res.getCharacter() == (charNum + 'a')) {
          recCount++;
        }
        certaintyTotal += res.getCertainty();
      }

      System.out.println("Recognized " + recCount + "/" + charFiles.length + " correctly in " + (char) (charNum + 'a')
          + " with " + (certaintyTotal / charFiles.length) * 100 + "% certainty.");
    }
  }
}
