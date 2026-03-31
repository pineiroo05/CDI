package parte1;

public class Empleado extends Thread{
    private Caja caja;
    private int contadorVentas;
    
    public Empleado(String name, Caja caja){
        super(name);
        this.caja=caja;
        this.contadorVentas=0;
    }
    
    public void run(){
        System.out.println("Hello from the java thread "+getName());
        while(caja.getVentasPendientes() > 0){
            caja.venta(getName());
            contadorVentas++;
        }
        System.out.println("Bye from the java thread "+getName());
    }
}
