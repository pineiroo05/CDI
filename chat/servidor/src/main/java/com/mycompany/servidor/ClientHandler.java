package com.mycompany.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private PrintWriter writer;
    private String nombreUsuario;
    
    public ClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
    }
    
    @Override
    public void run(){
        try(
            BufferedReader reader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            writer=new PrintWriter(clientSocket.getOutputStream(), true);
            nombreUsuario=reader.readLine();
            String mensaje;
            while((mensaje=reader.readLine())!=null){
                String mensajeCompleto=nombreUsuario+": "+mensaje;
                System.out.println("Mensaje recibido de : "+nombreUsuario+mensaje);
                Servidor.broadcast(mensajeCompleto);
            }
        }catch(IOException err){
            System.out.println("Cliente desconectado");
        }
    }
    
    public void sendMessage(String mensaje){
        writer.println(mensaje);
    }
}
