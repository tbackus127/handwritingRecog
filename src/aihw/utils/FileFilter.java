package aihw.utils;

import java.io.FilenameFilter;
import java.io.File;

public class FileFilter implements FilenameFilter{

    private File directory;
    
    public FileFilter(File dir){
        directory = dir;
    }
    
    public boolean accept(File dir, String name){
        return (dir.equals(directory) && name.endsWith(".jpg"));
    }
    
}
