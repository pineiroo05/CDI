package com.mycompany.servidor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        try(
            ServerSocket server=new ServerSocket(8888);
            Socket clientSocket=server.accept();
            BufferedReader reader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer=new PrintWriter(clientSocket.getOutputStream(), true)
        ){
            System.out.println("Cliente conectado desde "+clientSocket.getInetAddress());
            
            String respuesta = null;
            String peticion=reader.readLine();
            switch(peticion){
                case "a":
                    respuesta="index.html";
                    break;
                case "b":
                    respuesta="ver-noticias";
                    break;
                case "c":
                    respuesta="noticia-sobre-perrros";
                    break;
                case "d":
                    respuesta="noticia-sobre-gatos";
                    break;
            }
            writer.println(respuesta);
        }catch(IOException err){}
    }
}
