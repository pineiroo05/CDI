package parte2;
import java.util.Scanner;

public class Practica2b {
    public static void main(String[] args) {
        var scan = new Scanner(System.in);
        System.out.println("Numero de hilos");
        int nHilos = scan.nextInt();

        Thread listaThread[] = new Thread[nHilos];
        myThread hilosCompartidos = new myThread();
        for(int i=0; i<nHilos; i++){
            listaThread[i]=new Thread(hilosCompartidos, ""+i);
            listaThread[i].start();
        }

        //Apartado 1
        System.out.println("Hilo que quieres interrumpir");
        int hiloInterrumpir=scan.nextInt();
        if(hiloInterrumpir >= 0 && hiloInterrumpir < nHilos){
            listaThread[hiloInterrumpir].interrupt();
        }

        for(int i=0; i<nHilos; i++){
            try{
                listaThread[i].join();
            }catch(InterruptedException err){}
        }
        System.out.println("Programa terminado");
        scan.close();
    }
}

class myThread implements Runnable{
    ThreadLocal<Integer> mySum;

    public myThread(){
        this.mySum = ThreadLocal.withInitial(()->0);
    }

    public void run(){
        System.out.println("Hello world, I'm the java thread number "+Thread.currentThread().getName());
        for(int i=0; i<10; i++){
            this.mySum.set(getMySum()+1);
            try{
                Thread.sleep(500);
            }catch(InterruptedException err){
                System.err.println("Hilo "+Thread.currentThread().getName()+" interrumpido mientras dormia");
                return;
            }
        }
        System.out.println("Bye from the java thread number "+Thread.currentThread().getName());
    }

    public int getMySum(){
        return mySum.get();
    }
}
