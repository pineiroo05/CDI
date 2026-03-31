package parte2;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Caja {
    private int ventasPendientes;
    private Semaphore semaforo;
    private Lock lock;
    
    public Caja(int ventasPrevistas){
        this.ventasPendientes=ventasPrevistas;
        this.semaforo=new Semaphore(5, true); //Solo puede haber 5 trabajando simultaneamente
        this.lock=new ReentrantLock(true);
    }
    
    public synchronized int getVentasPendientes(){
        return ventasPendientes;
    }
    
    public void entradaTurno() throws InterruptedException{
        semaforo.acquire();
    }
    
    public void finTurno(){
        semaforo.release();
    }
    
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
    }
}
