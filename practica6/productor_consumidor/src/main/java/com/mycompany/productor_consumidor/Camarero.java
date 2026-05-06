package com.mycompany.productor_consumidor;

public class Camarero extends Thread{
    private Barra barra;
    
    public Camarero(Barra barra){
        this.barra=barra;
    }
    
    public void run(){
        try{
            while(true){
                barra.consumir();
                Thread.sleep(800);
                if(barra.acabado()){
                    break;
                }
            }
        }catch(InterruptedException err){}
    }
}
