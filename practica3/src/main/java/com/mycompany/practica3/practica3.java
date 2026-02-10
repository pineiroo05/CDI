package com.mycompany.practica3;
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;

public class practica3 {
    public static void main(String[] args) {
        int num_threads=Integer.parseInt(args[0]);
        String img_name=args[1];
        BufferedImage img = null;
        
        try{
            img=ImageIO.read(new File(img_name));
        }catch(Exception e){
            System.exit(-1);
        }
        
        BufferedImage resultImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        BiWorker workers[]=new BiWorker[num_threads];
        for(int i=0; i<num_threads; i++){
            workers[i]=new BiWorker(""+i, img, resultImg, num_threads);
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
            ImageIO.write(resultImg, "png", new File("result.png"));
        }catch(Exception e){
            System.exit(-1);
        }
    }
}

class BiWorker extends Thread{
    
}

class DistWorker extends Thread{
    
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