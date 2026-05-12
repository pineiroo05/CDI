package com.mycompany.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Lector implements Runnable{
    private Socket socket;
    
    public Lector(Socket socket){
        this.socket=socket;
    }
    
    @Override
    public void run(){
        try(
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            String mensaje;
            while((mensaje=reader.readLine())!=null){
                System.out.println(mensaje);
            }
        }catch(IOException err){}
    }
}
