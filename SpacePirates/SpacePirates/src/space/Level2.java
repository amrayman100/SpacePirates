package space;
import java.awt.event.*;//Action Listner//
import javax.swing.*;//GUI compomemts//
import java.awt.*;
import java.util.ArrayList;
import java.lang.*;
import java.util.Random;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
class High{
    public String name;
    public int s;
    public High(String name, int s) {
        this.name = name;
        this.s = s;
    }
}
public class Level2 extends JPanel {
    ArrayList<High> HighScores = new ArrayList();
    public Timer t1;
    private Timer t2;
    private Timer t3;
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private ArrayList enemies, h;
    private Player1 p1;
    private final int Delay1 = 10;
    private final int Delay2 = 800;
    private final int Delay3 = 20000;
    private Image background;
    private Random myRand = new Random();
    private Image imag1;
    private Clip clip;
    private Clip clip2;
    private ImageIcon Imgicn = new ImageIcon(getClass().getResource("/newpackage/4.jpg"));
    static int playerhealth = 20;
    static int killcount = 0;
    ImageIcon PlayerD = new ImageIcon(getClass().getResource("/newpackage/explosion.gif"));
    Image PlayerExplosion;
    private JLabel lblScore;
    private JLabel health;
    private JLabel Controls;
//    ImageIcon arrows=new ImageIcon(getClass().getResource("/newpackage/arrow-sprite.png"));
    public Level2() {
       
        health = new JLabel("Health: " + playerhealth);
        this.setLayout(null);
        lblScore = new JLabel("Score: " + 0);
        lblScore.setForeground(Color.white);
        lblScore.setBounds(5, screenHeight - 100, 200, 30);
        lblScore.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblScore.setVisible(true);
        this.add(lblScore);
        health.setForeground(Color.white);
        health.setBounds(screenWidth - 150, screenHeight - 120, 150, 30);
        health.setFont(new Font("Times New Roman", Font.BOLD, 24));
        health.setVisible(true);
        this.add(health);
        PlayerExplosion = PlayerD.getImage();
        background = Imgicn.getImage();
        ImageIcon A = new ImageIcon(getClass().getResource("/newpackage/1.png"));
        imag1 = A.getImage();
        this.setSize(screenWidth, screenHeight);
        p1 = new Player1(100, 200);
        this.setFocusable(true);
        enemies = new ArrayList();
        h = new ArrayList();
        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                p1.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    if (t1.isRunning()) {
                        t1.stop();
                        t2.stop();
                        t3.stop();
                    } else {
                        t1.start();
                        t2.start();
                        t3.start();
                    }
                } else p1.keyPressed(e);
            }
        });

        t1 = new Timer(Delay1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.move();
                repaint();
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy2 z = (Enemy2) enemies.get(i);
                    z.move(p1.getX1(), p1.getY1());

                }
                for (int i = 0; i < h.size(); i++) {
                    HealthItem z = (HealthItem) h.get(i);
                    z.move();

                }

                updateplayer1Missiles();
                checkplayer1_misslecol();
                updatenemy2();
                update_enemey_missiles();
                updateplayer1();
                stop();
                update_enemeycol();
                checkVisiblity();
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
         // Open an audio input stream.
         URL url =getClass().getResource("/newpackage/Health.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
        clip2 = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
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
        g.drawImage(this.background, (screenWidth / 2) - (background.getWidth(null) / 2), screenHeight - (screenHeight - ((screenHeight) / 100)) - 10, background.getWidth(null), background.getHeight(null), this);
        Graphics2D momship = (Graphics2D) g;
        momship.drawImage(imag1, screenWidth - 180, 0, this);
        Graphics2D momship2 = (Graphics2D) g;
        momship2.drawImage(imag1, screenWidth - 180, screenHeight - 250, this);
        Graphics2D momship3 = (Graphics2D) g;
        momship3.drawImage(imag1, -20, screenHeight - 250, this);
        Graphics2D momship4 = (Graphics2D) g;
        momship4.drawImage(imag1, -20, 0, this);

        if (p1.isVisible()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(p1.getImage(), p1.getX1(), p1.getY1(), this);
        }
        else g.drawImage(PlayerExplosion,p1.getX1(),p1.getY1(),PlayerExplosion.getWidth(null),PlayerExplosion.getHeight(null),null);
        
        
        for (int i = 0; i < enemies.size(); i++) {
            Enemy2 z = (Enemy2) enemies.get(i);
            if (z.isVisible()) {
                Graphics en = (Graphics2D) g;
                en.drawImage(z.getImage(), z.getX(), z.getY(), this);

            }
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
                Graphics dd = (Graphics2D) g;
                dd.drawImage(z.getImage(), z.getX(), z.getY(), this);

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
                    health.setText("Health: " + playerhealth);
                    if (playerhealth == 0) {
                        this.getGraphics().drawImage(PlayerExplosion, p1.getX1(), p1.getY1(), this);
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
                    health.setText("Health: " + playerhealth);
                    if (playerhealth == 0)
                        p1.setVisible(false);

                }
            }
        }
     /*for(int i =0;i<enemies.size();i++){
           Enemy2 z = (Enemy2) enemies.get(i);
           Rectangle r1 = z.getBounds();
           Rectangle r2 = p1.getBounds();
           if(r1.intersects(r2)){
               playerhealth--;
               if(playerhealth==0){p1.setVisible(false);}
           }
     }*/
        for (int j = 0; j < h.size(); j++) {
            HealthItem z = (HealthItem) h.get(j);
            if (z.isVisible()) {
                Rectangle r1 = p1.getBounds();


                Rectangle r2 = z.getBounds();

                if (r1.intersects(r2)) {
                    if (r1.intersects(r2))
                    {
                        PlaySound2();
                        z.setVisible(false);
                        if (playerhealth < 20) {
                            for (int x = 0; x < 5; x++) {
                                if (playerhealth == 20) {
                                    break;
                                } else
                                    playerhealth++;

                            }

                            health.setText("Health: " + playerhealth);
                        }

                        if (playerhealth == 0) {
                            this.getGraphics().drawImage(PlayerExplosion, p1.getX1(), p1.getY1(), this);
                            p1.setVisible(false);
                        }
                    }
                }
            }
        }
    }


    private void updatenemy2() {
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
    }

    private void checkplayer1_misslecol() {


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
        Graphics g=this.getGraphics();
        g.drawImage(PlayerExplosion,p1.getX1(),p1.getY1(),PlayerExplosion.getWidth(null),PlayerExplosion.getHeight(null),null);
        String name = JOptionPane.showInputDialog(null, "Game Over !\nYour Score is " + killcount + "\nPlease Enter Your Name");
        System.out.println(name);
        try {
            FileReader Hs = new FileReader("High Scores.txt");
            BufferedReader br = new BufferedReader(Hs);
            String str;
            int s;
            while ((str = br.readLine()) != null) {
                s = Integer.parseInt(br.readLine());
                HighScores.add(new High(str, s));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
        if (name != null) {
            if (HighScores.isEmpty())
                HighScores.add(new High(name, killcount));
            else {
boolean found = false;
for (int i = 0; i < HighScores.size(); i++) {
                if (killcount > HighScores.get(i).s) {
                    HighScores.add(i, new High(name, killcount));
found = true;
                    break;
                }
            }
if(!found) HighScores.add(new High(name, killcount));
}
        }
        try {
            FileWriter Hs1 = new FileWriter("High Scores.txt");
            PrintWriter Pw = new PrintWriter(Hs1);
            for (int i = 0; i < HighScores.size(); i++) {
                Pw.println(HighScores.get(i).name);
                Pw.println(HighScores.get(i).s);
            }
            Pw.close();
        } catch (IOException e) {
            System.out.println("ERROR!!");
        }
        System.exit(0);
    }

    public void checkVisiblity() {
        if (!p1.isVisible()) {
            t1.stop();
            t2.stop();
            this.gameOver();
        }
    }

    public void stop() {
        Rectangle r=p1.getBounds();
        if((r.x-1<0)||(r.x+r.width+1>screenWidth+10)) {
            if((r.y-1<0)||r.y+r.height>screenHeight-10){
            p1.velx = 0;p1.vely=0;}
            p1.velx = 0;
        }
        else if((r.y-1<0)||r.y+r.height>screenHeight-10)
            p1.vely=0;
    }
}