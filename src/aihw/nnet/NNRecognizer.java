
package aihw.nnet;

import java.io.File;
import java.io.FilenameFilter;

public class NNRecognizer {
  
  private final HWNeuralNet nnet;
  
  public NNRecognizer() {
    this.nnet = new HWNeuralNet(new File("res/tdata"));
    this.nnet.loadFromFile();
  }
  
  // arg0 = char ID, arg1 = certainty
  public NNetResult recognize(final File image) {
    return this.nnet.recognizeCharacter(image);
  }
  
  public static void main(String[] args) {
    final NNRecognizer rec = new NNRecognizer();
    final File[] tdataDirs = new File("res/tdata").listFiles(new FilenameFilter() {
      
      @Override
      public boolean accept(File curr, String name) {
        return new File(curr, name).isDirectory();
      }
    });
    
    for (int charNum = 0; charNum < tdataDirs.length; charNum++) {
      File charDir = tdataDirs[charNum];
      final File[] charFiles = charDir.listFiles();
      int recCount = 0;
      double certaintyTotal = 0.0D;
      
      for (File charImg : charFiles) {
        final NNetResult res = rec.recognize(charImg);
        
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
