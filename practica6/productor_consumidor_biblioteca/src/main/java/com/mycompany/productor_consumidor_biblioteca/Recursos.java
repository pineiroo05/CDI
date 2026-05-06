package com.mycompany.productor_consumidor_biblioteca;

import java.util.List;

public class Recursos {
    private List<AccessTeerminal> terminales;
    private List<Ebook> ebooks;
    
    public Recursos(List<AccessTeerminal> terminales, List<Ebook> ebooks){
        this.terminales=terminales;
        this.ebooks=ebooks;
    }
    
    public synchronized AccessTeerminal accesoTerminales(int idUsuario)throws InterruptedException{
        while(true){
            for(AccessTeerminal t:terminales){
                if(!t.getIsOcupado()){
                    t.setIsOcupado(true);
                    System.out.println("-El usuario "+idUsuario+" ha seleccionado la terminal "+t.getId());
                    return t;
                }
            }
            wait();
        }
    }
    
    public synchronized void liberarTerminal(AccessTeerminal terminal){
        terminal.setIsOcupado(false);
        notifyAll();
    }
    
    public synchronized Ebook accesoEbooks(int idUsuario)throws InterruptedException{
        while(true){
            for(Ebook e:ebooks){
                if(!e.getIsOcupado()){
                    e.setIsOcupado(true);
                    System.out.println("-El usuario "+idUsuario+" ha seleccionado el ebook "+e.getId());
                    return e;
                }
            }
            wait();
        }
    }
    
    public synchronized void liberarEbook(Ebook ebook){
        ebook.setIsOcupado(false);
        notifyAll();
    }
}
