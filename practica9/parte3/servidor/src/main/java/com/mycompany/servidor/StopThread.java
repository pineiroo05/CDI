package com.mycompany.servidor;
import java.net.ServerSocket;
import java.util.Scanner;

public class StopThread extends Thread{
    private Server server;
    private ServerSocket serverSocket;
    
    public StopThread(Server server, ServerSocket serverSocket){
        this.server=server;
        this.serverSocket=serverSocket;
    }
    
    public void run(){
        Scanner scan=new Scanner(System.in);
        while(true){
            String comando=scan.nextLine();
            if(comando.equalsIgnoreCase("apagar")){
                server.apagar();
                break;
            }
        }
    }
}
