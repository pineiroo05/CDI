package parte1;

public class Practica5 {

    public static void main(String[] args) {
        int nVentasIniciales = 50;
        int nHilos=5; //numero de empleados
        Caja caja=new Caja(nVentasIniciales);
        
        Thread threads[]=new Thread[nHilos];
        for(int i=0; i<nHilos; i++){
            threads[i]=new Empleado(""+i, caja);
            threads[i].start();
        }
        
        for(int i=0; i<nHilos; i++){
            try{
                threads[i].join();
            }catch(InterruptedException err){}
        }
        
        System.out.println("Programa terminado. Ventas pendientes en la caja: "+caja.getVentasPendientes());
    }
}
