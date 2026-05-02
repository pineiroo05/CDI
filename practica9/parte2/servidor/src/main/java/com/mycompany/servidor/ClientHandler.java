package com.mycompany.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    
    public ClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
    }

    @Override
    public void run() {
        try(
            BufferedReader reader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer=new PrintWriter(clientSocket.getOutputStream(), true)
        ){
            String respuesta = null;
            String peticion=reader.readLine();
            switch(peticion){
                case "a":
                    respuesta="index.html "+Thread.currentThread().getName();
                    break;
                case "b":
                    respuesta="ver-noticias "+Thread.currentThread().getName();
                    break;
                case "c":
                    respuesta="noticia-sobre-perrros "+Thread.currentThread().getName();
                    break;
                case "d":
                    respuesta="noticia-sobre-gatos "+Thread.currentThread().getName();
                    break;
            }
            writer.println(respuesta);
        }catch(IOException err){}
    }
}