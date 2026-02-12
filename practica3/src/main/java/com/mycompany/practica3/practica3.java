package com.mycompany.practica3;
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;
import java.util.Scanner;

//NOTA: La foto la guarda en el el directorio donde está metido el pom.xml no se muy bien pq...

public class practica3 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.print("Numero de hilos: ");
        int num_threads=scan.nextInt();
        System.out.print("Ruta de la imagen: ");
        String img_name=scan.next();
        BufferedImage img = null;        
        
        try{
            img=ImageIO.read(new File(img_name));
        }catch(Exception e){
            System.exit(-1);
        }
        
        BufferedImage resultImg_1 = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        BufferedImage resultImg_2 = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        System.out.println("---USANDO BIWORKER---");
        BiWorker workers[]=new BiWorker[num_threads];
        for(int i=0; i<num_threads; i++){
            workers[i]=new BiWorker(""+i, img, resultImg_1, num_threads);
            workers[i].start();
        }
        
        for(int i=0; i<num_threads; i++){
            while(workers[i].isAlive()){
                try{
                    Thread.sleep(10);
                }catch(InterruptedException err){}
            }
        }
        
        try{ 
            ImageIO.write(resultImg_1, "png", new File("result1.png"));
        }catch(Exception e){
            System.exit(-1);
        }
        
        System.out.println("---USANDO DISTWORKER---");
        DistWorker workers2[]=new DistWorker[num_threads];
        for(int i=0; i<num_threads; i++){
            workers2[i]=new DistWorker(""+i, img, resultImg_2, num_threads, "filas");
            workers2[i].start();
        }
        
        for(int i=0; i<num_threads; i++){
            while(workers2[i].isAlive()){
                try{
                    Thread.sleep(10);
                }catch(InterruptedException err){}
            }
        }
        
        try{
            ImageIO.write(resultImg_2, "png", new File("result2.png"));
        }catch(Exception e){
            System.exit(-1);
        }
        
        System.out.println("Programa terminado");
        scan.close();
    }
}

class BiWorker extends Thread{
    private BufferedImage img_or;
    private BufferedImage img_out;
    private int num_threads;
    
    public BiWorker(String name, BufferedImage img_or, BufferedImage img_out, int num_threads){
        super(name);
        this.img_or=img_or;
        this.img_out=img_out;
        this.num_threads=num_threads;
    }
    
    @Override
    public void run(){
        System.out.println("Hello world, I'm the java thread number "+getName());
        int id=Integer.parseInt(getName());
        for(int row=id; row<img_or.getHeight(); row+=num_threads){
            for(int col=0; col<img_or.getWidth(); col++){
                int rgbOriginal=img_or.getRGB(col, row);
                int alpha=(rgbOriginal >> 24)&0xFF;
                int red=(rgbOriginal >> 16)&0xFF;
                int green=(rgbOriginal >> 8)&0xFF;
                int blue=(rgbOriginal)&0xFF;
                
                int avg=(red+green+blue)/3;
                int newColor=(alpha << 24)|(avg << 16)|(avg << 8)|(avg);
                img_out.setRGB(col, row, newColor);
            }
        }
        System.out.println("Bye from the java thread "+getName());
    }  
}

class DistWorker extends Thread{
    private BufferedImage img_or;
    private BufferedImage img_out;
    private int num_threads;
    private String strategy;
    
    public DistWorker(String name, BufferedImage img_or, BufferedImage img_out, int num_threads, String strategy){
        super(name);
        this.img_or=img_or;
        this.img_out=img_out;
        this.num_threads=num_threads;
        this.strategy=strategy;
    }
    
    @Override
    public void run(){
        System.out.println("Hello world, I'm the java thread number "+getName()+". Strategy: "+strategy);
        int id=Integer.parseInt(getName());
        if(strategy.equalsIgnoreCase("filas")){
            int rowsPerThread=img_or.getHeight()/num_threads;
            /*Con esta linea me aseguro de que se cojan todas las filas/columnas. Tengo 3 hilos y 10 filas (3 para cada uno) problema-> hay una fila sin procesar.
            Con esa condicion me aseguro que al ultimo hilo se le asigne todo lo que sobre*/
            int endRow=(id==num_threads-1)?img_or.getHeight():(id + 1)*rowsPerThread;
            for(int row=id*rowsPerThread; row<endRow; row++){
                for(int col=0; col<img_or.getWidth(); col++){
                    int rgbOriginal=img_or.getRGB(col, row);
                    int alpha=(rgbOriginal >> 24)&0xFF;
                    int red=(rgbOriginal >> 16)&0xFF;
                    int green=(rgbOriginal >> 8)&0xFF;
                    int blue=(rgbOriginal)&0xFF;
                
                    int avg=(red+green+blue)/3;
                    int newColor=(alpha << 24)|(avg << 16)|(avg << 8)|(avg);
                    img_out.setRGB(col, row, newColor);
                }
            }
        }else{
            int colsPerThread=img_or.getWidth()/num_threads;
            int endCol=(id==num_threads-1)?img_or.getWidth():(id+1)*colsPerThread;
            for(int row=id*colsPerThread; row<endCol; row++){
                for(int col=0; col<img_or.getWidth(); col++){
                    int rgbOriginal=img_or.getRGB(col, row);
                    int alpha=(rgbOriginal >> 24)&0xFF;
                    int red=(rgbOriginal >> 16)&0xFF;
                    int green=(rgbOriginal >> 8)&0xFF;
                    int blue=(rgbOriginal)&0xFF;
                
                    int avg=(red+green+blue)/3;
                    int newColor=(alpha << 24)|(avg << 16)|(avg << 8)|(avg);
                    img_out.setRGB(col, row, newColor);
                }
            }            
        }
        System.out.println("By from the java thread "+getName()+". Strategy: "+ strategy);
    }
}

class MyImage{
    BufferedImage img;
    String strategy;
    
    public MyImage(String img_name, String strategy){
        this.strategy=strategy;
    }
    
    public MyImage(int width, int height, int type){
        img =  new BufferedImage(width, height, type);
    }
    
    public int geFirstRow(int id, int num_threads){
        int toret=0;
        if(this.strategy.equals("filas")){
            int numRowsPerThread=img.getHeight()/num_threads;
            toret=numRowsPerThread*id;
        }else{
            toret=0;
        }
        return toret;
    }
    
    public int getLastRow(int id, int num_threads){
        int toret=0;
        if(this.strategy.equals("filas")){
            int numRowsPerThread=img.getHeight()/num_threads;
            toret=numRowsPerThread*(id+1)-1;
        }else{
            toret=img.getHeight()-1;
        }
        return toret; 
    }
    
    public int getFirstColumn(int id, int num_threads){
        int toret=0;
        if(this.strategy.equals("filas")){
            toret=0;
        }else{
            int numColumnsPerThread=img.getWidth()/num_threads;
            toret=numColumnsPerThread*(id);
        }
        return toret;
    }
    
    public int getLastColumn(int id, int num_threads){
        int toret=0;
        if(this.strategy.equals("filas")){
            toret=img.getWidth()-1;
        }else{
            int numColumnsPerThread=img.getWidth()/num_threads;
            toret=numColumnsPerThread*(id+1)-1;
        }
        return toret;
    }
}