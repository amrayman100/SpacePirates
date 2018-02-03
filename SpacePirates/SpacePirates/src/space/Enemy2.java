package space;
import java.awt.Image;

import java.awt.*;//Container
import java.awt.event.*;//Action Listner//
import java.util.ArrayList;
import javax.swing.*;//GUI components
import javax.swing.Timer;

public class Enemy2  {
            private double velx = 2;
            private double vely = 2;
            public int x ;
	    public int y ;
            boolean vis;
	    private Image image1;
            private ArrayList z,f;
            public char DirectionTag = 'L';
            public  int enemyhealth = 1;
            private Timer t1;
            private final int Delay = 2000;
            
            public Enemy2(int x,int y){
                
             ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/heavyfreighter22.png"));
	      image1 = A.getImage();
	       this.x = x;
	       this.y = y;  
               vis = true;
                z = new ArrayList();
                f = new ArrayList();
               t1= new javax.swing.Timer(Delay,new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
                 firerockets2();
              
            }
        });
         t1.start();
            }
            public void firerockets2(){
                 if(!vis)return;
                if(DirectionTag=='L'){
              z.add(new missles2(x + 10 , y+50));
                }
                else if(DirectionTag=='R')
                {
                    f.add(new missles(x+10,y+50));
                }
                    
             
              
          }
              public ArrayList getMissiles() {
        return z;
        }
              public ArrayList getMissles2(){
                  return f;
              }
              public Image getImage() {
	        return image1;
	    }
              
            
              public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }
 
    public void setVisible(Boolean visible) {
        vis = visible;
    }
    public boolean isVisible(){
        return vis;
        
    }
    /*public void move()
    {
        
        if (x<0 || x > 1000){
            velx = -velx;
        }
        if(y<0||y>500){
            vely =-vely;
        }
        
        x+=velx;
        y+=vely;
        
       
    }*/
   
    public void move(int x1 , int y1){
         if(x1!=this.x&&y1!=this.y){
         if(y1<y&&x1<x){
             move1(y1,x1);
         }
         else if(y1>y&&x1>x){
             
             move2(y1,x1);

                     }
         else if(y>y1&&x1>x){
             move3(y1,x1);
         }
         else if(x>x1&&y1>y){
             move4(y1,x1);
             
         }
         else if(x1==x&&y<y1){
             vely=6;
             velx=0;
             this.x+=0;
             this.y+=vely;
             
         }
             else if(x1==x&&y<y1){
             vely=6;
             velx=0;
              this.x+=0;
             this.y-=vely;
         }
          else if(y1==y&&x1>x){
             vely=0;
             velx=4;
             this.y+=vely;
             this.x+=velx;
             ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/heavyfreighter11"));
	      image1 = A.getImage();
                 DirectionTag = 'L';
         }
          else if(y1==y&&x>x1){
              vely=0;
              velx =4;
              this.y+=vely;
              this.x-=velx;
              ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/heavyfreighter22"));
	      image1 = A.getImage();
                 DirectionTag = 'R';
          }
         
             
       
     }
    }
  
    public void move1(int y1,int x1){
        double angle = Math.atan((y1-y)/(x1-x));
          velx =Math.abs(2*Math.cos(angle));
          vely =Math.abs(2* Math.sin(angle));  
            this.x-=velx;
            this.y-=vely;
            ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/heavyfreighter22.png"));
	      image1 = A.getImage();
              DirectionTag = 'L';
              
    }
    
    
    public void move2(int y1, int x1){
          double angle = Math.atan((x-x1)/(y-y1));
          velx =2*Math.sin(angle);
          vely= 2* Math.cos(angle);  
            this.x+=velx;
            this.y+=vely;
            ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/heavyfreighter11.png"));
	      image1 = A.getImage();
                DirectionTag = 'R';
              
    }
    
    public void move3(int y1,int x1){
        double angle = Math.atan((y1-y)/(x1-x));
        velx =Math.abs(2*Math.cos(angle));
          vely =Math.abs(2* Math.sin(angle));  
            this.x+=velx;
            this.y-=vely;
            ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/heavyfreighter11.png"));
	      image1 = A.getImage();
                DirectionTag = 'R';
              
    }
    
    public void move4(int y1, int x1){
        double angle = Math.atan((x1-x)/(y1-y));
        velx =Math.abs(2*Math.sin(angle));
          vely =Math.abs(2* Math.cos(angle));  
            this.x-=velx;
            this.y+=vely;
            ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/heavyfreighter22.png"));
	      image1 = A.getImage();
                 DirectionTag = 'L';
    }
              
         
         
   

    
    public Rectangle getBounds() {
        return new Rectangle(x, y, image1.getWidth(null),image1.getHeight(null));
    }
}
