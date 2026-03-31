package parte1;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.Random;

public class Explorador  implements Runnable{
    private int idHilo;
    private String nombreEquipo;
    private CyclicBarrier barrier;
    private Tesoro tesoro;
    private Random rnd;
    
    public Explorador(int idHilo, String nombreEquipo, CyclicBarrier barrier, Tesoro tesoro){
        this.idHilo=idHilo;
        this.nombreEquipo=nombreEquipo;
        this.barrier=barrier;
        this.tesoro=tesoro;
        this.rnd=new Random();
    }
    
    @Override
    public void run(){
        try{
            for(int i=0; i<3; i++){
                int tiempoLlegada=rnd.nextInt(2000, 6000);
                Thread.sleep(tiempoLlegada);
                System.out.println("-Hilo "+idHilo+" del "+nombreEquipo+" ha tardado en llegar a "+i+" "+tiempoLlegada+" mseg-");
                barrier.await();
            }
             tesoro.reclamarTesoro(nombreEquipo);
        }catch(InterruptedException | BrokenBarrierException err){}
    }
}
