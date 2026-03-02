package ejercicio4;

public class MyThread extends Thread{
    private GestionInventario inv;
    private String operacion;
    
    public MyThread(String name, GestionInventario inv, String operacion){
        super(name);
        this.inv=inv;
        this.operacion=operacion;
    }
    
    public void run(){
        System.out.println("Hello from thread "+getName());
        try{
            switch(operacion){
                case "anadir":
                    inv.anadirElemento(operacion);
                    break;
                case "borrar":
                    inv.eliminarElemento();
                    break;
                case "informe1":
                    inv.informeAtomico();
                    break;
                case "informe2":
                    inv.informeNoAtomico();
                    break;
            }
            Thread.sleep(100);
        }catch(InterruptedException err){
            Thread.currentThread().interrupt();
        }
        System.out.println("Bye from thread "+getName());
    }
}
