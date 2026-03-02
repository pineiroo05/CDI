package ejercicio4;

public class NewClass {

    public static void main(String[] args) {
        int nHilos=8;
        
        GestionInventario inv=new GestionInventario();
        Thread threads[]=new Thread[nHilos];
        
        for(int i=0; i<3; i++){
            threads[i]=new MyThread("inc"+i, inv, "anadir");
        }
        
        for(int i=3; i<6; i++){
            threads[i]=new MyThread("del"+i, inv, "borrar");
        }
        threads[6]=new MyThread("informeAtomico", inv, "informe1");
        threads[7]=new MyThread("informeNoAtomico", inv, "informe2");
        
        for(int i=0; i<nHilos; i++){
            threads[i].start();
        }
        
        for(int i=0; i<nHilos; i++){
            try{
                threads[i].join();
            }catch(InterruptedException err){
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("Programa terminado.");
    }
}
