package aihw.autocorrect;

import aihw.autocorrect.AutoCorrect;

public class AutoCorrectTester{

    public static void main(String[] args){
        System.out.println("Number of words: " + args.length);
        if(args.length > 1){
            String toTest = "";
            for(int i = 0 ; i < args.length ; i++){
                toTest += args[i];
            }
            System.out.println("Autocorrected " +
                               toTest +
                               " to " +
                               AutoCorrect.checkString(toTest));
        }else if(args.length == 1){
            System.out.println("Autocorrected " +
                               args[0] +
                               " to " +
                               AutoCorrect.checkWord(args[0]));
        }else{
            System.out.println("You must have an argument for testing!");
        }
    }
}
