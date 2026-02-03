package parte2;
import java.util.Scanner;

public class Practica2_2 {
    public static void main(String[] args) {
        var scan=new Scanner(System.in);
        System.out.println("Numero de hilos");
        int nHilos=scan.nextInt();
        
        Thread listaThreads[]=new Thread[nHilos];
        myThread hilosCompartidos=new myThread();
        for(int i=0; i<nHilos; i++){
            listaThreads[i]=new Thread(hilosCompartidos, ""+i);
            listaThreads[i].start();
        }

        System.out.println("Hilo a interrumpir");
        int hiloInt=scan.nextInt();

        if(hiloInt>=0 && hiloInt<nHilos){
            listaThreads[hiloInt].interrupt();;
        }
        
        for(int i=0; i<nHilos; i++){
            try{
                listaThreads[i].join();
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
        System.out.println("Hello world, I'm the java thread "+Thread.currentThread().getName()+". Valor de mySum: "+getMySum());
        if(Thread.currentThread().isInterrupted()){
            System.out.println("Hilo "+Thread.currentThread().getName()+" interrumpido");
            return;
        }

        for(int i=0; i<10; i++){
            this.mySum.set(getMySum()+1);
            try{
                Thread.sleep(500);
            }catch(InterruptedException err){}
        }    
        System.out.println("Bye from the thread number "+Thread.currentThread().getName()+". Valor de mySum: "+getMySum());
    }
    
    public int getMySum(){
        return mySum.get();
    }
}

/*
- interrupt() -> mira de interrumpir el hilo.
- interrupted() -> mira si el hilo ha sido interrumpido.
- isInterrupted() -> mira si el 
*/