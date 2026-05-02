package com.mycompany.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private Server server;
    private PrintWriter writer;
    
    public ClientHandler(Socket clientSocket, Server server){
        this.server=server;
        this.clientSocket=clientSocket;
    }

    public void notificar(String mensaje){
        writer.println(mensaje);
    }
    
    public void cerrarConexion(){
        try{
            clientSocket.close();
        }catch(IOException err){}
    }
    
    @Override
    public void run() {
        try(
            BufferedReader reader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writerLocal=new PrintWriter(clientSocket.getOutputStream(), true)
        ){
            this.writer=writerLocal;
            String peticion;
            while((peticion=reader.readLine())!=null){
                String respuesta="Opcion no valida";
                switch(peticion.toLowerCase()){
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
                    case "s":
                        respuesta="Saliendo...";
                        break;
                }
                writer.println(respuesta);
            }
        }catch(IOException err){
        
        }finally{
            server.getListaClientes().remove(this);
            cerrarConexion();
        }
    }
}