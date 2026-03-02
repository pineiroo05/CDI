package ejercicio3;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import java.util.ArrayList;

public class Counter {
    private AtomicInteger contador;
    private Lock lock;
    private final int MIN=-10;
    private final int MAX=10;
    private List<String> logs;
    
    public Counter(){
        this.contador=new AtomicInteger(0);
        this.lock=new ReentrantLock();
        this.logs=new ArrayList<>();
    }
    
    public synchronized int getContador(){
        return contador.get();
    }
    
    public void increment(){
        boolean flagLimites=false;
        while(flagLimites==false){
            lock.lock();
            try{
                if(getContador()<MAX){
                    contador.getAndIncrement();
                    guardarLog("Incremento");
                    flagLimites=true;
                }
            }finally{
                lock.unlock();
            }
        }
        if(!flagLimites){
            try{
                Thread.sleep(10);
            }catch(InterruptedException err){
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public void decrement(){
        boolean flagLimites=false;
        while(flagLimites==false){
            lock.lock();
            try{
                if(getContador()>MIN){
                    contador.getAndDecrement();
                    guardarLog("Decremento");
                    flagLimites=true;                    
                }
            }finally{
                lock.unlock();
            }            
        }
        if(!flagLimites){
            try{
                Thread.sleep(10);
            }catch(InterruptedException err){
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public synchronized void guardarLog(String operacion){
        String log="Operacion "+operacion+" hecha por el hilo "+Thread.currentThread().getName()+". Valor del contador: "+getContador();
        logs.add(log);
    }
    
    public synchronized void imprimirLog(){
        System.out.println("\n---LOGS---");
        for(String log: logs){
            System.out.println(log);
        }
    }
}
