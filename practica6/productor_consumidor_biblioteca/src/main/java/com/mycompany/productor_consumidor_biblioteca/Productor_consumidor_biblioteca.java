package com.mycompany.productor_consumidor_biblioteca;

import java.util.LinkedList;
import java.util.List;

public class Productor_consumidor_biblioteca {

    public static void main(String[] args) {
        int numTerminales=5;
        int numEbooks=5;
        int numUsuarios=100*numTerminales;
        
        List<AccessTeerminal> listaTerminales=new LinkedList<>();
        for(int i=0; i<numTerminales; i++){
            listaTerminales.add(new AccessTeerminal(i));
        }
        List<Ebook> listaEbook=new LinkedList<>();
        for(int i=0; i<numEbooks; i++){
            listaEbook.add(new Ebook(i));
        }
     
        Recursos rec=new Recursos(listaTerminales, listaEbook);
        for(int i=0; i<numUsuarios; i++){
            new User(i,rec).start();
        }
    }
}
