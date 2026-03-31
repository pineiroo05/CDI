package parte1;
import java.util.concurrent.Semaphore;

public class Tesoro {
    private Semaphore semaforo;
    private boolean tesoroReclamado;
    
    public Tesoro(){
        this.semaforo=new Semaphore(1);
        this.tesoroReclamado=false;
    }
    
    public void reclamarTesoro(String nombreEquipo){
        try{
            semaforo.acquire();
            if(tesoroReclamado == false){
                tesoroReclamado=true;
                System.out.println("EL TESORO HA SIDO RECLAMADO POR "+nombreEquipo);
            }else{
                System.out.println(nombreEquipo+" HA LLEGADO, PERO EL TESORO YA ESTABA RECLAMADO");
            }
        }catch(InterruptedException err){
            
        }finally{
            semaforo.release();
        }
    }
}
