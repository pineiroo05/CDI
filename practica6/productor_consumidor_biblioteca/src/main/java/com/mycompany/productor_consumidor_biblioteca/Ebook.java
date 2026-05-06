package com.mycompany.productor_consumidor_biblioteca;

public class Ebook {
    private int id;
    private boolean isOcupado;
    
    public Ebook(int id){
        this.id=id;
        this.isOcupado=false;
    }
    
    public int getId(){
        return id;
    }
    
    public boolean getIsOcupado(){
        return isOcupado;
    }
    
    public void setIsOcupado(boolean ocupado){
        this.isOcupado=ocupado;
    }
}
