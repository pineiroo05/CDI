package ejercicio2;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class NewClass {
    public static void main(String[] args) {
        int nHilos=5;
        String fichero="archivo.txt";
        
        try{
            List<String> contInicial=Arrays.asList("Linea0", "Linea1", "Linea2", "Linea3", "Linea4", "Linea5");
            Files.write(Paths.get(fichero), contInicial);
        }catch(IOException err){
            System.err.println("Error");
        }
        
        Thread threads[]=new Thread[nHilos];
        AdminFile admin=new AdminFile(fichero);
        
        for(int i=0; i<nHilos; i++){
            threads[i]=new MyThread(""+i, i, "Linea modificada por thread "+i, admin);
            threads[i].start();
        }
        
        for(int i=0; i<nHilos; i++){
            try{
                threads[i].join();
            }catch(InterruptedException err){}
        }
        
        System.out.println("Programa terminado.");
    }
}
