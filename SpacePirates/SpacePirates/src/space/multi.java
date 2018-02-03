
package space;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class multi extends JPanel {

    ArrayList<High> HighScores = new ArrayList();
    private Clip clip2;
    private Timer t1;
    private Timer t2;
    private Timer t3;
    private Timer t4;
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private ArrayList enemies, h;
    private Player1 p1;
    private Player2 p2;
    private final int Delay1 = 10;
    private int Delay2 = 1000;
    private final int Delay3 = 20000;
    private Image background;
    private Random myRand = new Random();
    private Image imag1;
    private ImageIcon Imgicn = new ImageIcon(getClass().getResource("/newpackage/4.jpg"));
    static int playerhealth = 20;
    static int player2health = 20;
    static int killcount = 0;
  
    Image PlayerExplosion;
    private JLabel lblScore;
    private JLabel health;
    private JLabel health2;
    public multi() {
   
        health = new JLabel("P1 Health: " + playerhealth);
        health2 = new JLabel("P2 Health "+ player2health);
        this.setLayout(null);
        lblScore = new JLabel("Score: " + 0);
        lblScore.setForeground(Color.white);
        lblScore.setBounds((screenHeight/2)+250, screenHeight - 100,150, 30);
        lblScore.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblScore.setVisible(true);
        this.add(lblScore);
        health.setForeground(Color.white);
        health.setBounds(screenWidth - 170, screenHeight - 120, 150, 30);
        health.setFont(new Font("Time New Roman", Font.BOLD, 24));
        health.setVisible(true);
        
        health2.setForeground(Color.white);
        health2.setBounds(5, screenHeight - 100, 150, 30);
        health2.setFont(new Font("Times New Roman", Font.BOLD, 24));
        health2.setVisible(true);
        this.add(health);
        this.add(health2);
        background = Imgicn.getImage();
        ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/1.png"));
        imag1 = A.getImage();
        this.setSize(screenWidth, screenHeight);
        p1 = new Player1(100, 200);
        p2 = new Player2(200, 300);
        this.setFocusable(true);
        enemies = new ArrayList();
        h = new ArrayList();
        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                p1.keyReleased(e);
                p2.keyReleased(e);

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    if (t1.isRunning()) {
                        t1.stop();
                        t2.stop();
                        t3.stop();
                        t4.stop();
                    } else {
                        t1.start();
                        t2.start();
                        t3.start();
                        t4.start();
                    }
                } else {
                    p1.keyPressed(e);
                    p2.keyPressed(e);
                }
            }
        });

        t1 = new Timer(Delay1, new ActionListener() {
            //move w collisions
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.move();
                p2.move();
                repaint();
                 //int a = myRand.nextInt(1);
                move_enemies();
                 
                 updatMissilesplayer2();
                //updatetimer();
                checkplayer2_misslecol();
                updateplayer2();
                updateplayer1Missiles();
               
                checkplayer1_misslecol();
                updatenemy2();
                update_enemey_missiles();
                updateplayer1();
                update_enemeycol();
                checkVisiblity();
                stop();
                stop2(); 
                 for (int i = 0; i < h.size(); i++) {
                    HealthItem z = (HealthItem) h.get(i);
                    z.move();}
            }
        });
        t2 = new Timer(Delay2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = myRand.nextInt(4);
                 
                if (a == 0) {
                    enemies.add(new Enemy2(screenWidth - 150, 20));
                    
                } else if (a == 1) {
                    enemies.add(new Enemy2(screenWidth - 150, screenHeight - 220));
                } else if (a == 2) {
                    enemies.add(new Enemy2(-10, 10));
                } else if (a == 3) {
                    enemies.add(new Enemy2(-10, screenHeight - 220));
                }
            }

        });
        
         t4 = new Timer(Delay2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = myRand.nextInt(4);
                 
                if (a == 0) {
                    enemies.add(new Enemy2(screenWidth - 150, 20));
                    
                } else if (a == 1) {
                    enemies.add(new Enemy2(screenWidth - 150, screenHeight - 220));
                } else if (a == 2) {
                    enemies.add(new Enemy2(-10, 10));
                } else if (a == 3) {
                    enemies.add(new Enemy2(-10, screenHeight - 220));
                }
            }

        });


        t3 = new Timer(Delay3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int a = myRand.nextInt(3);
                int a = myRand.nextInt(1000);
                h.add(new HealthItem(a, 0));

            }
        });

        t1.stop();
        t2.stop();
        t3.stop();
    }
  public void PlaySound2(){
               try {
   
         URL url =getClass().getResource("/newpackage/Health.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         
        clip2 = AudioSystem.getClip();
        
         clip2.open(audioIn);
         clip2.start();
        
      } catch (UnsupportedAudioFileException e) {
        
      } catch (IOException e) {
      
      } catch (LineUnavailableException e) {
         
      }
                }
    @Override

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw motherships
        g.drawImage(this.background, (screenWidth / 2) - (background.getWidth(null) / 2), screenHeight - (screenHeight - ((screenHeight) / 100)) - 10, background.getWidth(null), background.getHeight(null), this);
        Graphics2D momship = (Graphics2D) g;
        momship.drawImage(imag1, screenWidth - 180, 0, this);
        Graphics2D momship2 = (Graphics2D) g;
        momship2.drawImage(imag1, screenWidth - 180, screenHeight - 250, this);
        Graphics2D momship3 = (Graphics2D) g;
        momship3.drawImage(imag1, -20, screenHeight - 250, this);
        Graphics2D momship4 = (Graphics2D) g;
        momship4.drawImage(imag1, -20, 0, this);

        if (p1.isVisible()) {//draw player 1
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(p1.getImage(), p1.getX1(), p1.getY1(), this);
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy2 z = (Enemy2) enemies.get(i);
            if (z.isVisible()) {
                Graphics en = (Graphics2D) g;
                en.drawImage(z.getImage(), z.getX(), z.getY(), this);

            }
        }
          for (int i = 0; i < enemies.size(); i++) {
            Enemy2 z = (Enemy2) enemies.get(i);
            if (z.isVisible()) {
                Graphics en = (Graphics2D) g;
                en.drawImage(z.getImage(), z.getX(), z.getY(), this);

            }
        }
        if (p2.isVisible()) {//draw player 1
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(p2.getImage(), p2.getX1(), p2.getY1(), this);
        }
       
        ArrayList ms = p1.getMissiles();
        Graphics2D h2d = (Graphics2D) g;
        for (Object m1 : ms) {
            missles m = (missles) m1;
            h2d.drawImage(m.getImage(), m.getX(),
                    m.getY(), this);

        }

        ArrayList mt = p1.getMissiles2();
        Graphics2D cc = (Graphics2D) g;
        for (Object m1 : mt) {
            missles2 m = (missles2) m1;
            cc.drawImage(m.getImage(), m.getX(),
                    m.getY(), this); }
            
         ArrayList p = p2.getMissiles2();
        Graphics2D pp = (Graphics2D) g;
        for (Object m1 : p) {
            missles m = (missles) m1;
       
            pp.drawImage(m.getImage(), m.getX(),
                    m.getY(), this);

        }

        ArrayList qq = p2.getMissiles();
        Graphics2D dd = (Graphics2D) g;
        for (Object m1 : qq) {
        
            missles2 m = (missles2) m1;
             
            dd.drawImage(m.getImage(), m.getX(),
                    m.getY(), this);
               

            for (int i = 0; i < enemies.size(); i++) {
                Enemy2 z = (Enemy2) enemies.get(i);
                ArrayList v = z.getMissles2();
                for (int j = 0; j < v.size(); j++) {
                    missles o = (missles) v.get(j);

                    Graphics2D iu = (Graphics2D) g;

                    iu.drawImage(o.getImage(), o.getX(),
                            o.getY(), this);
                }
            }
        }
        for (int i = 0; i < h.size(); i++) {
            HealthItem z = (HealthItem) h.get(i);
            if (z.isVisible()) {
                Graphics dx = (Graphics2D) g;
                dx.drawImage(z.getImage(), z.getX(), z.getY(), this);

            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy2 z = (Enemy2) enemies.get(i);
            ArrayList v = z.getMissiles();
            for (int j = 0; j < v.size(); j++) {
                missles2 o = (missles2) v.get(j);

                Graphics2D iu = (Graphics2D) g;

                iu.drawImage(o.getImage(), o.getX(),
                        o.getY(), this);
            }
        }

    }
    
private void move_enemies(){
      if(p1.isVisible()&&p2.isVisible()){
                for (int i = 0; i < enemies.size()/2; i++) {
                    
                    Enemy2 z = (Enemy2) enemies.get(i);
                   z.move(p1.getX1(),p1 .getY1());
                }
                 for (int i = enemies.size()/2; i < enemies.size(); i++) {
                    Enemy2 z = (Enemy2) enemies.get(i);
                   z.move(p2.getX1(),p2.getY1());
                }
            
                 }
                 
                 if(p1.isVisible()&&!p2.isVisible()){
                      for (int i = 0; i < enemies.size(); i++) {
                    
                    Enemy2 z = (Enemy2) enemies.get(i);
                   z.move(p1.getX1(),p1 .getY1());
                }
                 }
                 
            
        
            if(!p1.isVisible()&&p2.isVisible()){
                      for (int i = 0; i < enemies.size(); i++) {
                    
                    Enemy2 z = (Enemy2) enemies.get(i);
                   z.move(p2.getX1(),p2 .getY1());} 
                }
                 
                 
}


    private void updateplayer1Missiles() {

        ArrayList ms = p1.getMissiles();
        for (int i = 0; i < ms.size(); i++) {
            missles m = (missles) ms.get(i);

            if (m.isVisible()) {

                m.move();
            } else {

                ms.remove(i);
            }

        }
        ArrayList mt = p1.getMissiles2();
        for (int i = 0; i < mt.size(); i++) {
            missles2 z = (missles2) mt.get(i);
            if (z.isVisible()) {
                z.move();

            } else {
                mt.remove(i);

            }
        }
       
    }
private void updatMissilesplayer2() {
   ArrayList ms = p2.getMissiles();
        for (int i = 0; i < ms.size(); i++) {
            missles2 m = (missles2) ms.get(i);

            if (m.isVisible()) {

                m.move();
            } else {

                ms.remove(i);
            }

        }
        ArrayList mt = p2.getMissiles2();
        for (int i = 0; i < mt.size(); i++) {
            missles z = (missles) mt.get(i);
            if (z.isVisible()) {
                z.move();

            } else {
                mt.remove(i);

            }
        }
       
    }

    public void updateplayer1() {
      

        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);
            ArrayList ms = z.getMissiles();
            for (int i = 0; i < ms.size(); i++) {
                Rectangle r1 = p1.getBounds();

                missles2 m = (missles2) ms.get(i);
                Rectangle r2 = m.getBounds();
                if (r1.intersects(r2)) {
                    playerhealth--;
                    health.setText("P1 Health: " + playerhealth);
                    if (playerhealth == 0) {
                            health.setText("P1 Health: " + 0);
                  
                        p1.setVisible(false);
                    }
                }
            }
        }
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);
            ArrayList y = z.getMissles2();
            for (int i = 0; i < y.size(); i++) {
                Rectangle r1 = p1.getBounds();

                missles m = (missles) y.get(i);
                Rectangle r2 = m.getBounds();
                if (r1.intersects(r2)) {
                    playerhealth--;
                    health.setText("P1 Health: " + playerhealth);
                    if (playerhealth == 0) {
                          health.setText("P1 Health: " + 0);
                        p1.setVisible(false);
                    }

                }
            }
           
        }
      
        for (int j = 0; j < h.size(); j++) {
            HealthItem z = (HealthItem) h.get(j);
            if (z.isVisible()) {
                Rectangle r1 = p1.getBounds();

                Rectangle r2 = z.getBounds();

                if (r1.intersects(r2)) {
                    if (r1.intersects(r2)) {
                         PlaySound2();
                        z.setVisible(false);
                        if (playerhealth < 20) {
                            for (int x = 0; x <= 5; x++) {
                                if (playerhealth == 20) {
                                    break;
                                } else {
                                    playerhealth++;
                                }

                            }

                            health.setText("P1 Health: " + playerhealth);
                        }

                        if (playerhealth == 0) {
                          
                           health.setText("P1 Health: " + 0);
                         
                            p1.setVisible(false);
                        }
                    }
                }
            }
        }
     
    }
    
    private void updateplayer2(){
           for (int j = 0; j < h.size(); j++) {
            HealthItem z = (HealthItem) h.get(j);
            if (z.isVisible()) {
                Rectangle r1 = p2.getBounds();

                Rectangle r2 = z.getBounds();

                if (r1.intersects(r2)) {
                    if (r1.intersects(r2)) {
                           PlaySound2();
                        z.setVisible(false);
                        if (player2health < 20) {
                            for (int x = 0; x < 5; x++) {
                                if (player2health == 20) {
                                    break;
                                } else {
                                    player2health++;
                                }

                            }

                            health2.setText("P2 Health: " + player2health);
                        }

                        if (playerhealth == 0) {
                            health2.setText("P2 Health: " + 0);
                            p2.setVisible(false);
                        }
                    }
                }
            }
        }
           
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);
            ArrayList ms = z.getMissiles();
            for (int i = 0; i < ms.size(); i++) {
                Rectangle r1 = p2.getBounds();

                missles2 m = (missles2) ms.get(i);
                Rectangle r2 = m.getBounds();
                if (r1.intersects(r2)) {
                    if(p2.isVisible())
                    player2health--;
                    health2.setText("P2 Health: " + player2health);
                    if (player2health == 0) {
                       health2.setText("P2 Health: " + 0);
                       
                        p2.setVisible(false);
                    }
                }
            }
        }
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);
            ArrayList y = z.getMissles2();
            for (int i = 0; i < y.size(); i++) {
                Rectangle r1 = p2.getBounds();

                missles m = (missles) y.get(i);
                Rectangle r2 = m.getBounds();
                if (r1.intersects(r2)) {
                    if(p2.isVisible())
                    player2health--;
                    health2.setText("P2 Health: " + player2health);
                    if (player2health == 0) {
                          p2.setVisible(false);
                        health2.setText("P2 Health: " + 0);
                      
                    }

                }
            }
           
        }
        
    }

    private void updatenemy2() {
        //nafs el kalam bs el missiles l player 2
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);
            ArrayList ms = p1.getMissiles();
            for (int i = 0; i < ms.size(); i++) {
                Rectangle r1 = z.getBounds();

                missles m = (missles) ms.get(i);
                Rectangle r2 = m.getBounds();
                if (r1.intersects(r2)) {
                    z.enemyhealth--;
                    if (z.enemyhealth == 0) {
                        z.setVisible(false);
                        increaseScore();
                    }
                }

            }
        }
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);
            ArrayList ms = p2.getMissiles();
            for (int i = 0; i < ms.size(); i++) {
                Rectangle r1 = z.getBounds();

                missles2 m = (missles2) ms.get(i);
                Rectangle r2 = m.getBounds();
                if (r1.intersects(r2)) {
                    z.enemyhealth--;
                     // m.setVisible(false);
                      //  ms.remove(i);
                    if (z.enemyhealth == 0) {
                        
                        z.setVisible(false);
                        increaseScore();
                    }
                }

            }
        }
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);
            ArrayList c = p1.getMissiles2();
            for (int i = 0; i < c.size(); i++) {
                Rectangle r1 = z.getBounds();

                missles2 m = (missles2) c.get(i);
                Rectangle r2 = m.getBounds();
                if (r1.intersects(r2)) {

                    z.enemyhealth--;
                    if (z.enemyhealth == 0) {
                        z.setVisible(false);
                        increaseScore();
                    }
                }

            }
        }
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);
            ArrayList c = p2.getMissiles2();
            for (int i = 0; i < c.size(); i++) {
                Rectangle r1 = z.getBounds();

                missles m = (missles) c.get(i);
                Rectangle r2 = m.getBounds();
                if (r1.intersects(r2)) {
                    //m.setVisible(false);
                      //  c.remove(i);
                    z.enemyhealth--;
                    if (z.enemyhealth == 0) {
                       
                        z.setVisible(false);
                        increaseScore();
                    }
                }

            }
        }
    }

    private void checkplayer1_misslecol() {
//same for player 2

        for (int i = 0; i < enemies.size(); i++) {
            Enemy2 z = (Enemy2) enemies.get(i);
            if (z.isVisible()) {
                ArrayList s = p1.getMissiles();
                for (int j = 0; j < s.size(); j++) {
                    Rectangle r1 = z.getBounds();

                    missles m = (missles) s.get(j);
                    Rectangle r2 = m.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);

                    }

                }
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy2 z = (Enemy2) enemies.get(i);
            if (z.isVisible()) {
                ArrayList u = p1.getMissiles2();
                for (int j = 0; j < u.size(); j++) {
                    Rectangle r1 = z.getBounds();

                    missles2 m = (missles2) u.get(j);
                    Rectangle r2 = m.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);

                    }

                }

            }
        }
    }

    private void checkplayer2_misslecol() {
         for (int i = 0; i < enemies.size(); i++) {
            Enemy2 z = (Enemy2) enemies.get(i);
            if (z.isVisible()) {
                ArrayList s = p2.getMissiles();
                for (int j = 0; j < s.size(); j++) {
                    Rectangle r1 = z.getBounds();

                    missles2 m = (missles2) s.get(j);
                    Rectangle r2 = m.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);
               

                    }

                }
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy2 z = (Enemy2) enemies.get(i);
            if (z.isVisible()) {
                ArrayList u = p2.getMissiles2();
                for (int j = 0; j < u.size(); j++) {
                    Rectangle r1 = z.getBounds();

                    missles m = (missles) u.get(j);
                    Rectangle r2 = m.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);

                    }

                }

            }
        }
    }

    private void update_enemeycol() {
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);

            if (p1.isVisible()) {
                ArrayList y = z.getMissiles();
                for (int i = 0; i < y.size(); i++) {
                    Rectangle r1 = p1.getBounds();

                    missles2 m = (missles2) y.get(i);
                    Rectangle r2 = m.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);

                    }

                }
            }
        }
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);

            if (p2.isVisible()) {
                ArrayList y = z.getMissiles();
                for (int i = 0; i < y.size(); i++) {
                    Rectangle r1 = p2.getBounds();

                    missles2 m = (missles2) y.get(i);
                    Rectangle r2 = m.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);

                    }

                }
            }
        }
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);

            ArrayList t = z.getMissles2();
            for (int i = 0; i < t.size(); i++) {
                Rectangle r1 = p1.getBounds();

                missles m = (missles) t.get(i);
                Rectangle r2 = m.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);

                }

            }
        }
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 z = (Enemy2) enemies.get(j);

            ArrayList t = z.getMissles2();
            for (int i = 0; i < t.size(); i++) {
                Rectangle r1 = p2.getBounds();

                missles m = (missles) t.get(i);
                Rectangle r2 = m.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);

                }

            }
        }
    }

    private void update_enemey_missiles() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy2 a = (Enemy2) enemies.get(i);
            ArrayList mx = a.getMissiles();
            for (int j = 0; j < mx.size(); j++) {
                missles2 z = (missles2) mx.get(j);
                if (z.isVisible()) {
                    z.move();
                } else {
                    mx.remove(j);
                }
            }
        }
        for (int j = 0; j < enemies.size(); j++) {
            Enemy2 a = (Enemy2) enemies.get(j);
            ArrayList mt = a.getMissles2();

            for (int i = 0; i < mt.size(); i++) {
                missles h = (missles) mt.get(i);
                if (h.isVisible()) {
                    h.move();

                } else {
                    mt.remove(i);

                }
            }
        }
    }

    public void increaseScore() {
        killcount += 10;
        lblScore.setText("Score: " + killcount);
    }

    public void gameOver() {
       JOptionPane.showMessageDialog(null, "Game Over!");
    }

    public void checkVisiblity() {
        if (!p1.isVisible() && !p2.isVisible()) {

            t1.stop();
            t2.stop();

            this.gameOver();
        }
    }

    public void stop() {
        //same player 2

        Rectangle r = p1.getBounds();
        if ((r.x - 1 < 0) || (r.x + r.width + 1 > screenWidth + 10)) {
            if ((r.y - 1 < 0) || r.y + r.height > screenHeight - 10) {
                p1.velx = 0;
                p1.vely = 0;
            }
            p1.velx = 0;
        } else if ((r.y - 1 < 0) || r.y + r.height > screenHeight - 10) {
            p1.vely = 0;
        }
    }

    public void stop2() {

        Rectangle r = p2.getBounds();
        if ((r.x - 1 < 0) || (r.x + r.width + 1 > screenWidth + 10)) {
            if ((r.y - 1 < 0) || r.y + r.height > screenHeight - 10) {
                p2.velx = 0;
                p2.vely = 0;
            }
            p2.velx = 0;
        } else if ((r.y - 1 < 0) || r.y + r.height > screenHeight - 10) {
            p2.vely = 0;
        }
    }
}