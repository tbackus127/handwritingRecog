
package aihw.nnet;

import java.io.File;
import java.io.FilenameFilter;

/**
 * This class handles recognizing characters from images via the neural network.
 * 
 * @author Tim Backus tbackus127@gmail.com
 *
 */
public class NNRecognizer {

  /** The handle to the neural net. */
  private final HWNeuralNet nnet;

  /**
   * Default constructor.
   */
  public NNRecognizer() {
    this.nnet = new HWNeuralNet(new File("res/tdata"));
    this.nnet.loadFromFile();
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

  /**
   * Main method.
   * 
   * @param args runtime args, ignored.
   */
  public static void main(String[] args) {
    final NNRecognizer rec = new NNRecognizer();

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
        final NNetResult res = rec.recognize(charImg);

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
