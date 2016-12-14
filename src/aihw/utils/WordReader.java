package aihw.utils;

import aihw.nnet;
import java.io.File;

public class WordReader{

    public static void main(String[] args){
        System.out.println("Starting to read a String from saved data");
        if(args.length > 0){
            //Come back to this
        }else{
            System.out.println(readString());
        }
    }

    public static String readString(){
        NNRecognizer nNet = new NNRecognizer(new HWNeuralNet(new File("res/tdata/")));
        File resFolder = new File();
    }
    
}
