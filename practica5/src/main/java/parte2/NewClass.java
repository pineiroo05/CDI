package parte2;

public class NewClass {
    public static void main(String[] args) {
        int nVentasIniciales=100;
        int nHilos=20;
        
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

/*
Misma simulacion de caja que antes, pero ahora solo 5 empleados trabajan a la vez. Cada uno solo hace 3 ventas en su turno
*/