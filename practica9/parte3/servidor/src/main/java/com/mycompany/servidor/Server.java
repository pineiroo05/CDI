package com.mycompany.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

public class Server extends Thread{
    private ServerSocket server;
    private List<ClientHandler> listaClientes;
    private volatile boolean isActivo;
    
    public Server(){
        this.listaClientes=new LinkedList<>();
        this.isActivo=true;
    }
    
    public List<ClientHandler> getListaClientes(){
        return listaClientes;
    }
    
    public void run(){
        try{
            server=new ServerSocket(8888);
            StopThread hiloParada=new StopThread(this, server);
            hiloParada.start();
            while(isActivo){
                try{
                    Socket clientSocket=server.accept();
                    System.out.println("Cliente conectado desde "+clientSocket.getInetAddress());
                    ClientHandler cliente=new ClientHandler(clientSocket, this);
                    listaClientes.add(cliente);
                    Thread hilo=new Thread(cliente);
                    hilo.start();
                }catch(SocketException err){
                    System.err.println("Servidor apagado...");
                }
            }
        }catch(IOException err){}
    }
    
    public void apagar(){
        isActivo=false;
        synchronized(listaClientes){
            for(ClientHandler c:listaClientes){
                c.notificar("EL SERVIDOR SE VA A APAGAR");
                c.cerrarConexion();
            }
            listaClientes.clear();
        }
        try{
            server.close();
        }catch(IOException err){}
    }
}
