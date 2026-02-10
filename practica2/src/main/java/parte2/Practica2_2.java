package parte2;
import java.util.Scanner;
import java.util.Random;

public class Practica2_2 {
    public static void main(String[] args) {
        var scan = new Scanner(System.in);
        var rnd = new Random();

        System.out.println("Numero de hilos");
        int nHilos = scan.nextInt();

        Thread listaThread[]=new Thread[nHilos];
        myThread hilosCompartidos = new myThread();
        for(int i=0; i<nHilos; i++){
            listaThread[i]=new Thread(hilosCompartidos, ""+i);
            listaThread[i].start();
        }

        try{
            int tiempoInt = rnd.nextInt(2500)+500;
            System.out.println("Durmiendo por "+tiempoInt+" antes de interrumpir");
            Thread.sleep(tiempoInt);
        }catch(InterruptedException err){}

        System.out.println("INTERRUMPCIONES");

        System.out.println("Hilo a interrumpir");
        int hiloInt=scan.nextInt();
        if(hiloInt >= 0 && hiloInt <nHilos){
            listaThread[hiloInt].interrupt();
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
        this.mySum=ThreadLocal.withInitial(()->0);
    }

    public void run(){
        System.out.println("Hello world, I'm the java thread number "+Thread.currentThread().getName());
        for(int i=0; i<10; i++){
            this.mySum.set(getMySum()+1);
            try{
                Thread.sleep(500);
            }catch(InterruptedException err){
                System.err.println("Hilo "+Thread.currentThread().getName()+" interrumpido");
                break;
            }
        }
    }

    public int getMySum(){
        return mySum.get();
    }
}