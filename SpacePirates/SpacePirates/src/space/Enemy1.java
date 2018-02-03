package space;

import java.awt.Image;

import java.awt.*;//Container
import java.awt.event.*;//Action Listner//
import javax.swing.*;//GUI compomemts//


public class Enemy1  {
            public int velx = 2;
            public int vely= 2;
            private int x ;
	    private int y ;
            boolean vis;
	    private Image image1;
           
            
            public Enemy1(int x, int y){
                
             ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/aestroid_dark.png"));
	      image1 = A.getImage();
	      this.x =x;
              this.y = y;
               vis = true;
         
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
    public void move()
    {
        
        if (x<0 || x > 1000){
            velx = -velx;
        }
        if(y<0||y>500){
            vely =-vely;
        }
        
        x+=velx;
        y+=vely;
        
       
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, image1.getWidth(null),image1.getHeight(null));
    }
}
 