package parte2;

public class Corredor implements Runnable {
    private String idCorredor;
    private boolean esUltimo;
    private Carril carril;
    
    public Corredor(String idCorredor, boolean esUltimo, Carril carril){
        this.idCorredor=idCorredor;
        this.esUltimo=esUltimo;
        this.carril=carril;
        
    }
    
    @Override
    public void run(){
        carril.carrera(idCorredor, esUltimo);
    }
}
