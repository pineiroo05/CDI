package ejercicio1;
import java.util.Random;

public class MyTask implements Runnable{
    private Counter count;
    private Random rnd;
    
    public MyTask(Counter count){
        this.count=count;
        this.rnd=new Random();
    }
    
    public void run(){
        System.out.println("Hello from the java thread "+Thread.currentThread().getName()+". Contador: "+count.getContador());
        for(int i=0; i<10000000; i++){
            //try{
                //int tiempo=rnd.nextInt(100);
                count.increment();
                //Thread.sleep(tiempo);
            //}catch(InterruptedException err){}
        }
        System.out.println("Bye from the java thread "+Thread.currentThread().getName()+". Contador: "+count.getContador());
    }
}
