package com.mycompany.productor_consumidor;

import java.util.List;
import java.util.LinkedList;

public class Barra {
    private List<Integer> listaPlatos;
    private static final int MAX_PLATOS_BARRA=5;
    private static final int MAX_PLATOS_TOTALES=40;
    private int platosTotal;
    
    public Barra(){
        this.listaPlatos=new LinkedList<>();
        this.platosTotal=0;
    }
    
    public synchronized void producir()throws InterruptedException{
        while(listaPlatos.size()==MAX_PLATOS_BARRA){
            if(platosTotal>=MAX_PLATOS_TOTALES){
                return;
            }
            System.out.println("-La barra esta llena, el cocinero espera-");
            wait();
        }
        platosTotal++;
        listaPlatos.add(platosTotal);
        System.out.println("El chef cocina el plato numero "+platosTotal);
        notifyAll();
    }
    
    public synchronized void consumir()throws InterruptedException{
        while(listaPlatos.isEmpty()){
            if(platosTotal>=MAX_PLATOS_TOTALES){
                return;
            }
            System.out.println("-La barra esta vacia, el camarero espera-");
            wait();
        }
        listaPlatos.remove(0);
        System.out.println("El camarero entrega el plato "+platosTotal);
        notifyAll();
    }
    
    public boolean acabado(){
        return platosTotal>=MAX_PLATOS_TOTALES;
    }
}
