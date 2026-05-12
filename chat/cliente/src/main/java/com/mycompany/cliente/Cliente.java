package com.mycompany.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final int MAX_INTENTOS=5;

    public static void main(String[] args) {
        int intentos=0;
        boolean conectado=false;
        while (!conectado && intentos<MAX_INTENTOS) {
            try (
                Socket socket = new Socket("127.0.0.1", 8888); 
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); 
                Scanner scan = new Scanner(System.in)
            ) {
                //conectado=true;
                intentos=0;
                
                System.out.println("-Nombre de usuario-");
                String nombreUsuario = scan.nextLine();
                writer.println(nombreUsuario);

                Lector lector = new Lector(socket);
                Thread hiloLector = new Thread(lector);
                hiloLector.start();

                while (true) {
                    String mensaje = scan.nextLine();
                    writer.println(mensaje);
                }
            } catch (IOException err) {
                intentos++;
                System.out.println("Conexion perdida o no disponible");
                System.out.println("Intento de reconexion " + intentos + " de " + MAX_INTENTOS);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Reconexion interrumpida");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}

//Necesito 1 hilo receptor, y otro que lea de teclado
//Mejora 2: no dejar pasar un numero det de clientes
//Mejora 3: reconexion automatica