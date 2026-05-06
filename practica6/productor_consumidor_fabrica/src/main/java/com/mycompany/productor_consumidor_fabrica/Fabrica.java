package com.mycompany.productor_consumidor_fabrica;

import java.util.List;
import java.util.LinkedList;
import java.util.Stack;

public class Fabrica{
    private List<Articulo> almacen;
    private Stack<Articulo> pedidos;
    
    public Fabrica(){
        this.almacen=new LinkedList<>();
        this.pedidos=new Stack<>();
    }
    
    public List<Articulo> getAlmacen(){
        return almacen;
    }
    
    public Stack<Articulo> getPedidos(){
        return pedidos;
    }
}