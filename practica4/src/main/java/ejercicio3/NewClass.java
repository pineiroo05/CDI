package ejercicio3;

public class NewClass {
    public static void main(String[] args) {
        Counter count=new Counter();
        Mythread incremento=new Mythread("incremento", count, true);
        Mythread decremento=new Mythread("decremento", count, false);
        
        incremento.start();
        decremento.start();
        
        try{
            incremento.join();
            decremento.join();
        }catch(InterruptedException err){}
        
        count.imprimirLog();
        
        System.out.println("\nPrograma terminado.");
    }
}
