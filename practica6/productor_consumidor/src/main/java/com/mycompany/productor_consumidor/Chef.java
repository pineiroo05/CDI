package com.mycompany.productor_consumidor;

public class Chef extends Thread{
    private Barra barra;
    
    public Chef(Barra barra){
        this.barra=barra;
    }
    
    @Override
    public void run(){
        try{
            while(true){
                barra.producir();
                Thread.sleep(500);
                if(barra.acabado()){
                    break;
                }
            }
        }catch(InterruptedException err){}
    }
}
