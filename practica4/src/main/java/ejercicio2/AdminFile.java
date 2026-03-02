package ejercicio2;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AdminFile {
    private String archivo;
    private Lock lock;
    
    public AdminFile(String archivo){
        this.archivo=archivo;
        this.lock=new ReentrantLock();
    }
    
    public void modificar(int linea, String contenido){
        lock.lock();
        try{
            Path path=Paths.get(archivo);
            List<String> lineas=Files.readAllLines(path);
            if(linea>=0 && linea<lineas.size()){
                lineas.set(linea, contenido);
                Files.write(path, lineas);
            }
        } catch (IOException ex) {
            System.err.println("Error");
        }finally{
            lock.unlock();
        }
    }
}
