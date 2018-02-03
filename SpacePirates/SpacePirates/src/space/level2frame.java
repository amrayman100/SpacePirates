package space;

import java.awt.Toolkit;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.lang.*;
import java.awt.Image;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//implements WindowListener,WindowFocusListener
public class level2frame extends JFrame   {
  // private Level2 p;


   private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
  // private int x =1;
    private Clip clip;

    private boolean gamerunning = false;
   public level2frame (){
         //gamerunning = true;
      
        Level2 p = new Level2();
   
           this.setResizable(false);
         this.setSize(screenWidth,screenHeight);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = this.getContentPane();
    
    PlaySound();
    /*
      addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                //dispose();
                clip.stop();
                 p.stoptimers();
                 
              /*
               
                   setnull(p);
                gamerunning = false;
             
               //startmusic();
               System.exit(0);
           
               
         
       
                

                }
        });*/
      
      
    

        
   // c.add()
       c.add(p);
   }
   public void setnull(Level2 x){
             x= null;
        
   }
    public void PlaySound(){
               try {
         // Open an audio input stream.
         URL url =getClass().getResource("/newpackage/s1.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
        clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
         
         
      } catch (IOException e) {
         
      } catch (LineUnavailableException e) {
        
      }
                }
   
   }

 

