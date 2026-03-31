package parte2;

public class Empleado extends Thread{
    private Caja caja;
    private int contadorVentas;
    private final int MAXVENTAS=3;
    
    public Empleado(String name, Caja caja){
        super(name);
        this.caja=caja;
        this.contadorVentas=0;
    }
    
    public void run(){
        System.out.println("Hello from the java thread "+getName());
        try{
            caja.entradaTurno();
            System.out.println("El empleado "+getName()+" ha entrado en la caja");
            while(caja.getVentasPendientes()>0 && contadorVentas<MAXVENTAS){
                caja.venta(getName());
                contadorVentas++;
            }
            System.out.println("El empleado "+getName()+" sale de la caja");
            caja.finTurno();
        }catch(InterruptedException err){
            
        }
    }
}
