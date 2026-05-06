package com.mycompany.productor_consumidor_biblioteca;

public class AccessTeerminal {
    private int id;
    private boolean isOcupado;
    
    public AccessTeerminal(int id){
        this.id=id;
        isOcupado=false;
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
