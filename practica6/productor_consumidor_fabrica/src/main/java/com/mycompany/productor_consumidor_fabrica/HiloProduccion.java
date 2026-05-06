package com.mycompany.productor_consumidor_fabrica;

public class HiloProduccion extends Thread{
    private Fabrica fabrica;
    private int maxAlmacen;
    private int contador;
    
    public HiloProduccion(Fabrica fabrica, int maxAlmacen){
        this.fabrica=fabrica;
        this.maxAlmacen=maxAlmacen;
        this.contador=0;
    }
    
    @Override
    public void run(){
        try{
            while(true){
                synchronized(fabrica){
                    if(fabrica.getPedidos().isEmpty()){
                        System.out.println("No hay pedidos, no se produce");
                    }else if(fabrica.getAlmacen().size()<maxAlmacen){
                        String tipo = fabrica.getPedidos().peek().getTipo();
                        Articulo nuevo=new Articulo(contador++, tipo);
                        fabrica.getAlmacen().add(nuevo);
                        System.out.println("-Nuevo articulo en almacen --> ID: "+nuevo.getId()+", tipo: "+nuevo.getTipo());
                    }else{
                        System.out.println("-ALMACEN LLENO-");
                    }
                }
                Thread.sleep(500);
            }
        }catch(InterruptedException err){}
    }
}
