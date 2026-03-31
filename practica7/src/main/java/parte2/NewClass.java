package parte2;

public class NewClass {
    public static void main(String[] args) {
        int nCorredores=4;
        Carril carril=new Carril();
        
        for(int i=1; i<=nCorredores; i++){
            boolean esUltimo=(i==nCorredores);
            Thread corredor=new Thread(new Corredor(""+i, esUltimo, carril));
            corredor.start();
            
            try{
                corredor.join();
            }catch(InterruptedException err){}
        }
        
        try{
            carril.esperarMeta();
        }catch(InterruptedException err){}

        System.out.println("Programa terminado");
    }
}
