package space;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;


public class missles {
   private int x2;
    private int y2;
    protected Image image;
    private final int Board_width = 1920;
    private final int velmissle = 15;
    protected int width2;
    protected int height2;
    protected boolean vis;
    public missles(int x,int y){
        this.x2 = x;
        this.y2 = y;
        vis = true;
         ImageIcon B = new ImageIcon(getClass().getResource("/newpackage/laserRed.png"));
	      image = B.getImage();
              
        /*width = image.getWidth(null);
        height = image.getHeight(null);*/
    }
    
   public void getdimensions2(){
       width2 = image.getWidth(null);
        height2 = image.getHeight(null);
   }
    public Image getImage() {
        return image;
    }

    public int getX() {
        return x2;
    }

    public int getY() {
        return y2;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }
            public void move() {
       {
        x2 += velmissle;
        
        if (x2> Board_width) {
            vis = false;
        }
        }
       
        }
    
            public Rectangle getBounds() {
        return new Rectangle(x2, y2,  image.getWidth(null),image.getHeight(null));
    }
}

