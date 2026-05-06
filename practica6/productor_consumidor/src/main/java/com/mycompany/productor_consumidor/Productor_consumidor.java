package com.mycompany.productor_consumidor;

public class Productor_consumidor {

    public static void main(String[] args) {
        Barra barra=new Barra();
        int nCocineros=4;
        int nCamareros=6;
        
        for(int i=0; i<nCocineros; i++){
            new Chef(barra).start();
        }
        
        for(int i=0; i<nCamareros; i++){
            new Camarero(barra).start();
        }
    }
}
