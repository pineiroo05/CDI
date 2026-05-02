package com.mycompany.cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try(
                Socket socket=new Socket("127.0.0.1", 8888);
                BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer=new PrintWriter(socket.getOutputStream(), true)
        ){
            Scanner scan=new Scanner(System.in);
            String opcion;
            System.out.println("Elige una opcion: A)index, B)noticia 1, C)noticia 2, D)noticia 3");
            opcion=scan.nextLine();
            
            writer.println(opcion);
            
            String respuesta=reader.readLine();
            System.out.println("-Respuesta del servidor: "+respuesta);
        }catch(IOException err){}
    }
}
