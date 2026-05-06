package com.mycompany.productor_consumidor_fabrica;

public class Articulo {
    private int id;
    private String tipo;
    
    public Articulo(int id, String tipo){
        this.id=id;
        this.tipo=tipo;
    }
    
    public int getId(){
        return id;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public String toString(){
        return "Articulo "+getId()+". Tipo: "+getTipo();
    }
}
