package com.mycompany.practica1;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

//Importante. Revisar statics

public class Practica1{
    
    public static void main(String[] args) {
        Thread hilo1R=new Thread(new hiloRunnable(), "0");
        Thread hilo2R=new Thread(new hiloRunnable(), "1");
        Thread hilo3R=new Thread(new hiloRunnable(), "2");
        
        Thread hilo1T=new hiloThread("3");
        Thread hilo2T=new hiloThread("4");
        Thread hilo3T=new hiloThread("5");
        
        hilo1R.start();
        hilo2R.start();
        hilo3R.start();
        
        hilo1T.start();
        hilo2T.start();
        hilo3T.start();
        
        //Con thread compartido
        hiloRunnable hiloCompartido=new hiloRunnable();
        Thread hilo4R=new Thread(hiloCompartido, "6");
        Thread hilo5R=new Thread(hiloCompartido, "7");
        
        hilo4R.start();
        hilo5R.start();
        
        //Crear varios hilos de golpe
        Scanner scan=new Scanner(System.in);
        System.out.println("-Numero de hilos-");
        int nHilos=scan.nextInt();
        
        Thread listaThreads[]=new Thread[nHilos];
        for(int i=1;i<nHilos;i++){
            listaThreads[i]=new Thread(new hiloRunnable(),"0"+i);
            listaThreads[i].start();
        }
        
        Thread threadsR[]=new Thread[nHilos];
        hiloRunnable myThreadsR[]=new hiloRunnable[nHilos];
        for(int i=1;i<nHilos;i++){
            myThreadsR[i]=new hiloRunnable();
            threadsR[i]=new Thread(myThreadsR[i],"1"+i);
            listaThreads[i].start();
        }
        
        for(int i=0;i<nHilos;i++){
            try{
                listaThreads[i].join();
                threadsR[i].join();
            }catch(InterruptedException err){}
        }
        //Ponerlo siempre
        System.out.println("Programa terminado");
    }
}

class hiloRunnable implements Runnable {
    public void run(){
        System.out.println("Hello world, I’m the java thread number "+Thread.currentThread().getName());
        try{
            Thread.sleep(20);
        }catch (InterruptedException err){}
        System.out.println("Bye from thread number "+Thread.currentThread().getName());
    }
}

class hiloThread extends Thread{
    public hiloThread(String name){
        super(name);
    }

    public void run(){
        System.out.println("Hello world, I’m the java thread number "+getName());
        try {
            Thread.sleep(20);
        } catch (InterruptedException err){}
        System.out.println("Bye from thread number "+getName());
    }
}

/*
    ver que todos los hilos compartan el mismo runnable.
    con varios:
    - h1 -> runnable -> cont
    - h2 -> runnable -> cont
    - h3 -> runnable -> cont
    de la otra forma todos los hilos van al mismo runnable y comparten el contador
    ----------
    ver join() e isAlive(C), y las diferencias
    - isAlive() espera a que el hilo a terminado de ejecutarse. Hay que preguntar a todos los hilos si han acabado
        - h1 acaba? voy al 2 --> h2 acaba? voy al 3 ...
    - join() el proceso está dormido hasta que acabe
*/