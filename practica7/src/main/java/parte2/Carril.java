package parte2;
import java.util.concurrent.Semaphore;
import java.util.concurrent.CountDownLatch;
import java.util.Random;

//CountDownLatch es una vuelta atras, al llegar a 0 los hilos q estaban esperando continuan su ejecucion

public class Carril {
    private Semaphore semaforo;
    private CountDownLatch meta;
    private Random rnd;
    
    public Carril(){
        this.semaforo=new Semaphore(1);
        this.meta=new CountDownLatch(1);
        this.rnd=new Random();
    }
    
    public void carrera(String nombreCorredor, boolean isUltimoCorredor){
        try{
            semaforo.acquire();
            int tiempo=rnd.nextInt(5000, 10000);
            System.out.println(">>"+nombreCorredor+" ha iniciado su turno...");
            Thread.sleep(tiempo);
            System.out.println("<<"+nombreCorredor+" ha terminado su turno...");
            if(isUltimoCorredor){
                System.out.println("--El ultimo corredor ha pasado la meta--");
                meta.countDown();
            }else{
                System.out.println("--"+nombreCorredor+" le da el testigo al siguiente corredor--");
            }
        }catch(InterruptedException err){
            
        }finally{
            semaforo.release();
        }
    }
    
    public void esperarMeta()throws InterruptedException{
        meta.await();
    }
}