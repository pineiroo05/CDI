package com.mycompany.productor_consumidor_fabrica;

public class Productor_consumidor_fabrica {

    public static void main(String[] args) {
        Fabrica fabrica=new Fabrica();
        int nArticulos=6;
        int maxAlmacen=10;
        
        for(int i=0; i<nArticulos; i++){
            fabrica.getPedidos().add(new Articulo(i, "Tipo-"+i));
        }
        HiloProduccion prod=new HiloProduccion(fabrica,maxAlmacen);
        HiloLogistica log=new HiloLogistica(fabrica);
        prod.start();
        log.start();
    }
}
