package ar.com.archivosconcommonsio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class App{
    
    private static final String MAIN_PATH = "C:\\Users\\cassisi\\Desktop\\GaliciaWM\\LiquidacionesGaliciaTest\\";
    

    public static void main(String[] args) throws IOException {
        
        // file1: "Hello Worlds!"
        // file2: "Hello World!!!"
        File file1 = FileUtils.getFile(MAIN_PATH + "cmpFile1.txt");
        File file2 = FileUtils.getFile(MAIN_PATH + "cmpFile2.txt");
        //Compara los dos archivos
        System.out.println("Son cmpFile1 y cmpFile2 iguales?: " + FileUtils.contentEquals(file1, file2));
        
        
        // Podemos copiar una ruta entera en algún lugar 
        //y comprobar si la operación se ha realizado correctamente .
        
        FileUtils.copyDirectory(
                FileUtils.getFile(MAIN_PATH),                   // path de origen
                FileUtils.getFile(MAIN_PATH + "copiedPath\\")); // path de destino
        
        System.out.println("Existe la carpeta copiedPath?: " +
                FileUtils.getFile(MAIN_PATH + "copiedPath\\").exists());
        
        
        // Copy a directory by filtering out all the txt files.
        // In the directory right now: cmpFile1.txt, cmpFile2.txt, zipFile.zip
        FileUtils.copyDirectory(
                FileUtils.getFile(MAIN_PATH),
                FileUtils.getFile(MAIN_PATH + "copiedFilterPath\\"),
                new SuffixFileFilter(".zip"));
        
        for (File f: FileUtils.getFile(MAIN_PATH + "copiedFilterPath\\").listFiles()) {
            System.out.println("Contents of copiedFilterPath: " + f.getName());
        }
        
        
        // Copy a file
        File copy = FileUtils.getFile(MAIN_PATH + "copyOfFile1.txt");
        FileUtils.copyFile(file1, copy);
        System.out.println("Are cmpFile1 and copyOfFile1 equal: " +
                FileUtils.contentEquals(file1, copy));
        
        
        // Right now there are these files in the MAIN_PATH.
        for (File f: FileUtils.getFile(MAIN_PATH).listFiles()) {
            System.out.println("Contents of MAIN_PATH: " + f.getName());
        }
        // Let's delete a directory and see the results.
        FileUtils.deleteDirectory(FileUtils.getFile(MAIN_PATH + "copiedFilterPath\\"));
        for (File f: FileUtils.getFile(MAIN_PATH).listFiles()) {
            System.out.println("Contents of MAIN_PATH after deletion: " + f.getName());
        }
        
        
        // Directories
        System.out.println("Temp Dir: " +
                FileUtils.getTempDirectory().getAbsolutePath());
        System.out.println("User Dir: " +
                FileUtils.getUserDirectory().getAbsolutePath());
        
        
        // Line Iterator
        LineIterator iter = FileUtils.lineIterator(file1);
        while (iter.hasNext()) {
            System.out.println("cmpFile1 lines: " + iter.next());
        }
        
        
        // Directory size
        System.out.println("Size of dir: " +
                FileUtils.sizeOfDirectory(FileUtils.getFile(MAIN_PATH)) +
                " bytes.");
        
        
        // Ways of writing lines to a file.
        File fileToWrite1 = FileUtils.getFile(MAIN_PATH + "fileToWrite1.txt");
        File fileToWrite2 = FileUtils.getFile(MAIN_PATH + "fileToWrite2.txt");
        
        Collection<String> lines = new ArrayList();
        lines.add("Written with FileUtils!");
        lines.add("For Java Code Geeks.");
        
        FileUtils.write(fileToWrite1, "Written with FileUtils!");
        FileUtils.writeLines(fileToWrite2, lines);
        
        iter = FileUtils.lineIterator(fileToWrite1);
        while (iter.hasNext()) {
            System.out.println("fileToWrite1 lines: " + iter.next());
        }
        iter = FileUtils.lineIterator(fileToWrite2);
        while (iter.hasNext()) {
            System.out.println("fileToWrite2 lines: " + iter.next());
        }
    }
}