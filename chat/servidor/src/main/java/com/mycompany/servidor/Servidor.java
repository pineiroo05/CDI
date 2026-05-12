package com.mycompany.servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

//EL SERVIDOR TIENE Q ENVIAR A TODOS LOS CLIENTES
public class Servidor {
    private static final List<ClientHandler> listaClientes=new LinkedList<>();
    private static final int NUM_MAX_CLIENTES=2;

    public static void main(String[] args) {
        try(
            ServerSocket server=new ServerSocket(8888);
        ){
            while(true){
                Socket clientSocket=server.accept();
                if(listaClientes.size()<NUM_MAX_CLIENTES){
                    System.out.println("Cliente conectado desde "+clientSocket.getInetAddress());
                    ClientHandler cliente=new ClientHandler(clientSocket);
                    listaClientes.add(cliente);
                    Thread hilo=new Thread(cliente);
                    hilo.start();
                }else{
                    System.out.println("SERVIDOR LLENO!!!!!!");
                    PrintWriter writer=new PrintWriter(clientSocket.getOutputStream(), true);
                    writer.println("Servidor lleno. Espere...");
                    writer.close();
                    clientSocket.close();
                }
            }
        }catch(IOException err){}
    }
    
    public static void broadcast(String mensaje){
        for(ClientHandler cliente:listaClientes){
            cliente.sendMessage(mensaje);
        }
    }
}
