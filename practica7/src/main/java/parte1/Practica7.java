package parte1;
import java.util.concurrent.CyclicBarrier;
import java.util.List;
import java.util.ArrayList;

public class Practica7 {
    public static void main(String[] args) {
        int numEquipos=3;
        int numExploradoresPorEquipo=3;
        Tesoro tesoro=new Tesoro();
        List<Thread> hilos=new ArrayList<>();
        
        for(int i=0; i<numEquipos; i++){
            final String nombreEquipo="equipo "+i;
            CyclicBarrier barrier=new CyclicBarrier(numExploradoresPorEquipo, ()->{
                System.out.println(">"+nombreEquipo+" ha llegado a un punto de control");
            });
                    
                    
            for(int j=0; j<numExploradoresPorEquipo; j++){
                Thread thread=new Thread(new Explorador(j, "equipo "+i, barrier, tesoro));
                hilos.add(thread);
                thread.start();
            }
        }
        
        for(Thread thread:hilos){
            try{
                thread.join();
            }catch(InterruptedException err){}
        }
        
        System.out.println("Programa terminado");
    }
}
