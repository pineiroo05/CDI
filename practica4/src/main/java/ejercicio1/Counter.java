package ejercicio1;

//Aqui lo podria hacer con lock o AtomicInteger
public class Counter {
    private int contador;
    
    public Counter(){
        this.contador=0;
    }
    
    public synchronized int getContador(){
        return contador;
    }
    
    public synchronized void increment(){
        contador++;
    }
}
