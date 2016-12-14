package aihw.utils;

import java.io.File;

import aihw.nnet.HWNeuralNet;
import aihw.nnet.NNRecognizer;

public class WordReader {

  public static void main(String[] args) {
    System.out.println("Starting to read a String from saved data");
    if (args.length > 0) {
      // Come back to this
    } else {
      System.out.println(readString());
    }
  }

  public static String readString() {
    NNRecognizer nNet = new NNRecognizer(new HWNeuralNet(new File("res/tdata/")));
    File resFolder = new File("res/data/word/");
    return null;
  }

}
