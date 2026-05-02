package com.mycompany.servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        try(
            ServerSocket server=new ServerSocket(8888);
        ){
            while(true){
                Socket clientSocket=server.accept();
                System.out.println("Cliente conectado desde "+clientSocket.getInetAddress());
                ClientHandler client=new ClientHandler(clientSocket);
                Thread hilo=new Thread(client);
                hilo.start();
            }
        }catch(IOException err){}
    }
}