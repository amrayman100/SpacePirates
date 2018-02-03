
package space;
import java.awt.*;//Container
import java.awt.event.*;//Action Listner//
import java.util.ArrayList;
import javax.swing.*;//GUI components
import javax.swing.Timer;
import java.awt.Image;

public class HealthItem {
    private int x;
    private int y;
    private int velx = 3;
    private int vely = 3;
    private Image a;
    boolean vis;
    
   public HealthItem(int x, int y){
       ImageIcon ia =new ImageIcon(getClass().getResource("/newpackage/Health.png"));
       a = ia.getImage();
       this.x = x;
       this.y = y;
       vis = true;
   }
      public Image getImage() {
	        return a;
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
        
       if (x<0 || x > Toolkit.getDefaultToolkit().getScreenSize().width){
            vis = false;
        }
        if(y<0||y>Toolkit.getDefaultToolkit().getScreenSize().height){
            vis =false;
        }
        
        x+=velx;
        y+=vely;
        
       
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, a.getWidth(null),a.getHeight(null));
    }
}
   

