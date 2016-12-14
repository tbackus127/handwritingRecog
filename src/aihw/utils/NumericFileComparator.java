package aihw.utils;

import java.io.File;
import java.util.Comparator;

/**
 *
 * @author eric
 */
public class NumericFileComparator implements Comparator{
    
    public int compare(Object obj1, Object obj2){
        int toReturn = 0;
        if(obj1 instanceof File && obj2 instanceof File){
            File file1 = (File)obj1;
            File file2 = (File)obj2;
            String name1 = file1.getName().substring(0,file1.getName().indexOf("."));
            String name2 = file2.getName().substring(0,file2.getName().indexOf("."));
            int num1 = new Integer(name1).intValue();
            int num2 = new Integer(name2).intValue();
            toReturn = num1 < num2 ? -1 : num1 == num2 ? 0 : 1;
        }else{
            throw new ClassCastException();
        }
        return toReturn;
    }
    
}
