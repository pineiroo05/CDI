package ejercicio1;

public class Practica4 {
    public static void main(String[] args) {
        int num_threads=3;
        Thread threads[] = new Thread[num_threads];
        Counter counter = new Counter();
        
        for(int i=0; i<num_threads; i++){
            threads[i]=new Thread(new MyTask(counter), ""+i);
            threads[i].start();
        }
        
        for(int i=0; i<num_threads; i++){
            try{
                threads[i].join();
            }catch(InterruptedException err){}
        }
        
        System.out.println("Programa terminado. Contador "+counter.getContador());
        
        /*Lo ejecuto varias veces, me da valores diferentes -> varios hilos pueden acceder a la vez pq esta sin proteger -> hay que usar threadlocal o syncronized
        Un hilo solo puede ejecutar esa funcion de cada vez. Los metodos tienen un monitor, que se usa para ver si el hilo tiene control sobre el metodo.
        NO USAR SYNCHRONIZED CON STATIC
        - 1º metodos synchronized
        - 2º bloques. Tiene 2 formas
        */
    }
}
