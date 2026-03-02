package ejercicio4;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GestionInventario {
    private Lock lock;
    private List<String> inventario;
    private List<String> logs;
    
    public GestionInventario(){
        this.lock=new ReentrantLock();
        this.inventario=new ArrayList<>();
        this.logs=new ArrayList<>();
    }
    
    public void anadirElemento(String elemento){
        lock.lock();
        try{
            inventario.add(elemento);
            anadirLog(elemento+" anadido por el hilo "+Thread.currentThread().getName());
        }finally{
            lock.unlock();
        }
    }
    
    public void eliminarElemento(){
        lock.lock();
        try{
            if(inventario.isEmpty()){
                System.out.println("El inventario esta vacio");
            }else{
                String elemento=inventario.remove(0);
                anadirLog(elemento+" eliminado por el hilo "+Thread.currentThread().getName());
            }
        }finally{
            lock.unlock();
        }
    }
    
    public synchronized void anadirLog(String log){
        logs.add(log);
    }
    
    public void informeNoAtomico(){
        System.out.println("\n--Informe no atomico. Tamano: "+inventario.size());
    }
    
    public void informeAtomico(){
        lock.lock();
        try{
            System.out.println("\n--Informe atomico. Tamano: "+inventario.size());
        }finally{
            lock.unlock();
        }
    }
}