package com.mycompany.productor_consumidor_fabrica;

public class HiloLogistica extends Thread{
    private Fabrica fabrica;
    
    public HiloLogistica(Fabrica fabrica){
        this.fabrica=fabrica;
    }
    
    @Override
    public void run(){
        try{
            while(true){
                synchronized(fabrica){
                    if(fabrica.getPedidos().isEmpty()){
                        Articulo nuevoPedido=new Articulo((int)(Math.random()*1000),"Tipo-"+(int)(Math.random()*3));
                        fabrica.getPedidos().push(nuevoPedido);
                        System.out.println("Nuevo pedido generado");
                    }
                    
                    if(!fabrica.getAlmacen().isEmpty() && !fabrica.getPedidos().isEmpty()){
                        fabrica.getPedidos().pop();
                        Articulo art=fabrica.getAlmacen().remove(0);
                        System.out.println("-Se ha enviado el articulo "+art);
                    }else{
                        System.out.println("NO HAY NADA EN LAS LISTAS...");
                    }
                }
                Thread.sleep(700);
            }
        }catch(InterruptedException err){}
    }
}
