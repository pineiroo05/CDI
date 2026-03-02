package ejercicio3;

public class Mythread extends Thread{
    private Counter count;
    private boolean isIncrement;
    
    public Mythread(String name, Counter count, boolean isIncrement){
        super(name);
        this.count=count;
        this.isIncrement=isIncrement;
    }
    
    public void run(){
        System.out.println("Hello from the thread "+getName());
        for(int i=0; i<1000; i++){
            if(isIncrement==true){
                count.increment();
            }else{
                count.decrement();
            }
        }
        System.out.println("Bye from the thread "+getName());
    }
}
