package com.mycompany.productor_consumidor_biblioteca;

public class User extends Thread{
    private int id;
    private Recursos rec;
    
    public User(int id, Recursos rec){
        this.id=id;
        this.rec=rec;
    }
    
    @Override
    public void run(){
        try{
            AccessTeerminal terminal=rec.accesoTerminales(id);
            Ebook ebook=rec.accesoEbooks(id);
            System.out.println("Usuario "+id+" leyendo libro "+ebook.getId());
            Thread.sleep((int)(Math.random())*1000);
            rec.liberarTerminal(terminal);
            rec.liberarEbook(ebook);
        }catch(InterruptedException err){}
    }
}
