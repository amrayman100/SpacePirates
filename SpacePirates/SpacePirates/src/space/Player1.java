package space;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.applet.*;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import  sun.audio.*;    //import the sun.audio package
import  java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Player1 {
     private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	    public int velx;
	    public int vely;
	    private int x;
	    private int y;
            public char DirectionTag = 'R';
           
        
	    private Image image;
            private ArrayList m;
            private ArrayList n;
            protected int width;
            protected int height;
            protected boolean vis;
          
            
         public Player1(int x, int y){
             ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/bgbattleship.png"));
	        image = A.getImage();
	        this.x=x;
                this.y = y;
                m = new ArrayList();
                n = new ArrayList();
                vis = true;
               
                
               
         }
          
         
          public void move() {
               /*if(x>0&&x<1250){
                   
                   
                    
               }*/
	         x += velx;
	        y += vely;
               
	    }
          
          public void fire_rockets(){
              if(!vis)return;
              if(DirectionTag =='R'){
                   m.add(new missles(x +10 , y+50));
              }
              else if(DirectionTag =='L'){
              n.add(new missles2(x +10 , y+50));
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
	        if (key == KeyEvent.VK_LEFT) {
                    if(x>0){
	           velx = -8;
                    ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/bgbattleship2.png"));
	        image = A.getImage();
                DirectionTag = 'L';}
                else velx=0;
	        }
	        if (key == KeyEvent.VK_RIGHT) {
                       if(x<1250){
	           velx = 8;
                     ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/bgbattleship.png"));
	        image = A.getImage();
                 DirectionTag = 'R';}
                       else velx=0;
	        }
	        if (key == KeyEvent.VK_UP) {
                   if(y>0){
	            vely= -8;}
                    /*ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/bgbattleship3.png"));
	        image = A.getImage();*/
                    else vely=0;
	       }
	        if (key == KeyEvent.VK_DOWN) {
                    if(y<620){
	            vely = 8;} 
                      /*ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/bgbattleship4.png"));
	        image = A.getImage();*/
                    else vely=0;
	        }
                 if (key == KeyEvent.VK_SPACE) {
                   
	              fire_rockets();
                  PlaySound();
       
    
                  
	        
                 
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
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
                }
            
           public ArrayList getMissiles() {
        return m;
        }
              public ArrayList getMissiles2() {
        return n;
        }
           
	    public void keyReleased(KeyEvent e) {
	        
	        int key = e.getKeyCode();
	        if (key == KeyEvent.VK_LEFT) {
	            velx = 0;
	        }
	        if (key == KeyEvent.VK_RIGHT) {
	            velx = 0;
	        }
	        if (key == KeyEvent.VK_UP) {
	            vely = 0;
	        }
	        if (key == KeyEvent.VK_DOWN) {
	            vely = 0;
	        }
	    }
            
             public boolean isVisible() {
        return vis;
    }
         /*public void setVisible(int hit) {
             if(hit!=3){
                  vis = true;
             }
             else if(hit ==3){
                  vis = false;
             }
             
       
    }*/
            /* public void sethit(){
                 health--;
             }*/
         
         
         public void setVisible(Boolean visible) {
        vis = visible;
    }
                public Rectangle getBounds() {
        return new Rectangle(x, y,  image.getWidth(null),image.getHeight(null));
    }
             
	}