package parte1;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class Caja {
    private int ventasPendientes;
    private Semaphore semaforo;
    //private Lock lock;
    
    public Caja(int ventasPrevistas){
        this.ventasPendientes=ventasPrevistas;
        //this.lock=new ReentrantLock(true);
        this.semaforo=new Semaphore(1, true);
    }
    
    public synchronized int getVentasPendientes(){
        return ventasPendientes;
    }
    
    //USANDO SEMAPHORE -> me permite elegir cuantos hilos entran a esta zona
    public void venta(String empleado){
        try{
            semaforo.acquire();
            if(ventasPendientes > 0){
                System.out.println("El empleado "+empleado+" utiliza la caja para una una venta");
                ventasPendientes--;
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException err){}
                System.out.println("El empleado "+empleado+" ha cerrado la caja. Ventas pendientes: "+ventasPendientes);
            }
        }catch(InterruptedException err){
            
        }finally{
            semaforo.release();
        }
    }
    
    /*USANDO FAIR LOCK -> va dejando pasar hilo a hilo en base a una FIFO (por lo q entendi xd)
    public void venta(String empleado){
        lock.lock();
        try{
            if(ventasPendientes > 0){
                System.out.println("El empleado "+empleado+" utiliza la caja para una una venta");
                ventasPendientes--;
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException err){}
                System.out.println("El empleado "+empleado+" ha cerrado la caja. Ventas pendientes: "+ventasPendientes);
            }
        }finally{
            lock.unlock();
        }
    }*/
    
    /*
    //USANDO SYNCHRONIZED
    public synchronized void venta(String empleado){
        if(ventasPendientes > 0){
            System.out.println("El empleado "+empleado+" utiliza la caja para una una venta");
            ventasPendientes--;
            try{
                Thread.sleep(2000);
            }catch(InterruptedException err){}
            System.out.println("El empleado "+empleado+" ha cerrado la caja. Ventas pendientes: "+ventasPendientes);
        }
    }
    */
}
