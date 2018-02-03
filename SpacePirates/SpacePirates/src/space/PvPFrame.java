
package space;
import java.awt.event.*;//Action Listner//
import javax.swing.*;//GUI compomemts//
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PvPFrame extends JFrame {
   private PvP h;
   private Clip clip;
   private multi my;
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
   public PvPFrame(){
        PlaySound();
      h = new PvP();
      my = new multi();
         this.setSize(1920, 1030);
         
               this.setResizable(false);
         //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container c = this.getContentPane();
       c.add(h);
       //c.add(my);
      addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                /*dispose();
                clip.stop();
              
                p.stoptimers();
                   setnull(p);
                gamerunning = false;
             
               //startmusic();*/
                System.exit(0);
         
       
                

                }
        });
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
