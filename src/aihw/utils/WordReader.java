package aihw.utils;

import java.io.File;
import java.util.Arrays;

import aihw.nnet.HWNeuralNet;
import aihw.nnet.NNRecognizer;
import aihw.nnet.NNetResult;
import aihw.autocorrect.AutoCorrect;

public class WordReader {

    private static String resPath = "res/data/word/";
    private static String HWPath = "res/tdata/";
    
  public static void main(String[] args) {
    System.out.println("Starting to read a String from saved data");
    if (args.length == 1) {
        resPath = args[0];
    }else if(args.length == 2){
        resPath = args[0];
        HWPath = args[1];
    } else {
      System.out.println("Your output is: \n" + readString());
    }
  }

    public static String readString() {
        NNRecognizer nNet = new NNRecognizer(new HWNeuralNet(new File(HWPath)));
        File resFolder = new File(resPath);
        File[] fileArray = resFolder.listFiles(new FileFilter(resFolder));
        Arrays.sort(fileArray, new NumericFileComparator()); //Sorts the files numericaly.
        for(int i = 0 ; i < fileArray.length ; i++){
            System.out.println(fileArray[i]);
        }
        String lastName = fileArray[fileArray.length-1].getName();
        lastName = lastName.substring(0, lastName.indexOf("."));
        int numOfChars = new Integer(lastName).intValue();
        String output = "";
        int j = 0;
        for(int i = 1 ; i <= numOfChars && j < fileArray.length ; i++){
            String currFileName = i + ".jpg";
            if(fileArray[j].getName().equals(currFileName)){
                NNetResult res = nNet.recognize(fileArray[j]);
                System.out.println("Recognized: " +
                                   res.getCharacter() +
                                   " With: " +
                                   (res.getCertainty()*100.0) +
                                   "% certainty");
                output += res.getCharacter();
                j++;
            }else{
                output += " ";
            }
        }    
    return AutoCorrect.checkString(output);
    }
}


