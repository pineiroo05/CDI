package ejercicio2;

public class MyThread extends Thread{
    private int numLinea;
    private String nuevoContenido;
    private AdminFile admin;
    
    public MyThread(String name, int numLinea, String nuevoContenido, AdminFile admin){
        super(name);
        this.numLinea=numLinea;
        this.nuevoContenido=nuevoContenido;
        this.admin=admin;
    }
    
    public void run(){
        System.out.println("Hello from the thread "+getName());
        admin.modificar(numLinea, nuevoContenido);
        System.out.println("Bye from the thread "+getName());
    }
}
