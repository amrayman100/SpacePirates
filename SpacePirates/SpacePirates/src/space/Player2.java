package space;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Player2  {
    
	    public int velx;
	    public int vely;
	    private int x;
	    private int y;
            private int rx;
            private int ry;
            private int rvelx;
	    public Image image;
            private ArrayList m,n;
            protected int width;
            protected int height;
            protected boolean vis;
            public char DirectionTag;
            
         public Player2(int x, int y){
             ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/bgspeedship1.png"));
	        image = A.getImage();
	       this.x = x;
               this.y = y;
                m = new ArrayList();
                n = new ArrayList();
                vis = true;
                DirectionTag = 'L';
               
         }
         
         
          public void move() {
	        x += velx;
	        y += vely;
	    }
          
          public void fire_rockets(){
                if(!vis)return;
                if(DirectionTag =='L')
              m.add(new missles2(x + 10 , y+50));
                else if(DirectionTag == 'R'){
                    n.add(new missles(x +10,y+50));
                }
          }

	    public int getX1() {
	        return x;
                
	    }

	    public int getY1() {
	        return y;
	    }

	    public Image getImage() {
	        return image;
	    }

	    public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_A) {
                      if(x>0){
	           velx = -8;

                   ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/bgspeedship1.png"));
	        image = A.getImage();
                DirectionTag='L';}
                      else velx = 0;
                         
	        }

	        if (key == KeyEvent.VK_D) {
                        if(x<1200){
	           velx = 8;
                    ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/bgspeedship2.png"));
	        image = A.getImage();
                DirectionTag='R';}
                else velx=0;
	        }

	        if (key == KeyEvent.VK_W) {
                        if(y>0)
	            vely= -8;
                        else vely=0;
	        }

	        if (key == KeyEvent.VK_S) {
                     if(y<620){
	            vely = 8;}
                     else vely=0;
	        }
                 if (key == KeyEvent.VK_SHIFT) {
	              fire_rockets();
                      PlaySound();
	        }
	    }
           public ArrayList getMissiles() {
        return m;
        }
           public ArrayList getMissiles2(){
               return n;
           }
	    public void keyReleased(KeyEvent e) {
	        
	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_A) {
	            velx = 0;
                    
	        }

	        if (key == KeyEvent.VK_D) {
	            velx = 0;
	        }

	        if (key == KeyEvent.VK_W) {
	            vely = 0;
	        }

	        if (key == KeyEvent.VK_S) {
	            vely = 0;
	        }
	    }
            
            public void PlaySound(){
               try {
         // Open an audio input stream.
         URL url =getClass().getResource("/newpackage/XWing-Laser.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
        
      } catch (IOException e) {
        
      } catch (LineUnavailableException e) {
         
      }
                }
                   public boolean isVisible() {
        return vis;
    }
               public void setVisible(Boolean visible) {
        vis = visible;
    }
                public Rectangle getBounds() {
        return new Rectangle(x, y,  image.getWidth(null),image.getHeight(null));
    }
	}

