package space;
import java.awt.event.*;//Action Listner//
import javax.swing.*;//GUI compomemts//
import java.awt.*;
import java.util.ArrayList;
import java.lang.*;
import java.util.Random;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/*class High{
    public String name;
    public int s;
    public High(String name, int s) {
        this.name = name;
        this.s = s;
    }
}*/
public class PvP extends JPanel {
      
    private int screenWidth  = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
      private JLabel P1health;
    private JLabel P2health;
    private Player1 p1;
    private Player2 p2;
    private Timer t1;
    private Timer t2;
     public static int p1health = 20;
    public static int p2health = 20;
    private final int DELAY = 10;
    private final int DELAY2 = 3000;
    private ArrayList asteroids;
    private char coltag = 'N';
    private Image background;
    private Random myRand = new Random();
    private ImageIcon Imgicn = new ImageIcon(getClass().getResource("/newpackage/background_1.jpg"));
     ImageIcon PlayerD=new ImageIcon(getClass().getResource("/newpackage/explosion.gif"));
    Image PlayerExplosion;
    public PvP(){
        P1health=new JLabel("Player 1: "+p1health);
        this.setLayout(null);
        P2health = new JLabel("Player 2: "+p2health);
        P2health.setForeground(Color.white);
        P2health.setBounds(5, screenHeight-100,150 , 30);
        P2health.setFont(new Font("Times New Roman",Font.BOLD,24));
        P2health.setVisible(true);
        this.add(P2health);
        P1health.setForeground(Color.white);
        P1health.setBounds(screenWidth-155, screenHeight-100, 150 , 30);
        P1health.setFont(new Font("Times New Roman",Font.BOLD,24));
        P1health.setVisible(true);
        this.add(P1health);
        PlayerExplosion=PlayerD.getImage();
         background=Imgicn.getImage();
      this.setSize(1920, 1030);
         //setBackground(Color.BLACK);
         this.setFocusable(true);
   
 
         p1 = new Player1(500, 300);
         p2 = new Player2(100,620);
         
         asteroids = new ArrayList();
          addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
            if(key==KeyEvent.VK_ENTER){
                if(t1.isRunning()){
                //t1.stop();
                //t2.stop();
            }
                else {
                    t1.start();
                    t2.start();
                }
            }
                  else{ p1.keyPressed(e);
                        p2.keyPressed(e);
        }
            }
        @Override
        public void keyReleased(KeyEvent e) {
            p1.keyReleased(e);
            p2.keyReleased(e);
        }
        });
         t1 = new javax.swing.Timer(DELAY,new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 
                 p1.move();
                 p2.move();
                 
                  for (Object d : asteroids) {
              Enemy1 x = (Enemy1) d;
              x.move();
              }
            
          updateplayer1Missiles();
          updatMissilesplayer2();
          updateplayer1();
          updateplayer2();
        
          checkplayer1_misslecol();
            checkplayer2_misslecol();
          checkasteriod();
          checkVisiblity();
             stop();
        repaint();  
              
            }
        });
         
       //  t1.start();
         t2 =  new javax.swing.Timer(DELAY2,new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int a =  myRand.nextInt(1000);
                asteroids.add(new Enemy1(a,0));
              
            }
        });
       //  t2.start();
        
    }
    //@Override
    public void paintComponent(Graphics g) {
       
      super.paintComponent(g);
        g.drawImage(background, (screenWidth / 2) - (background.getWidth(null) / 2), screenHeight - (screenHeight - ((screenHeight) / 100))-10, background.getWidth(null), background.getHeight(null), null);
          if(p1.isVisible())
        {
        //Graphics2D g2d = (Graphics2D) g;
        g.drawImage(p1.getImage(), p1.getX1(),p1.getY1(), this);
        }
       
        if(p2.isVisible()){
        Graphics2D g2r = (Graphics2D) g;
        g2r.drawImage(p2.getImage(), p2.getX1(),p2.getY1(), this);  
        }
        
        ArrayList ms = p1.getMissiles();
         Graphics2D h2d = (Graphics2D) g;
       for (int i=0;i<ms.size();i++) {
            missles m = (missles) ms.get(i);
            h2d.drawImage(m.getImage(), m.getX(),
                    m.getY(), this);
          
    }
         
               ArrayList mt = p1.getMissiles2();
         Graphics2D cc = (Graphics2D) g;
        for (int i=0;i<mt.size();i++) {
            missles2 m = (missles2) mt.get(i);
            cc.drawImage(m.getImage(), m.getX(),
                    m.getY(), this);
          
    }
           
    
          ArrayList mx = p2.getMissiles();
           Graphics2D b = (Graphics2D) g;
        for (int i=0;i<mx.size();i++) {
            missles2 m = (missles2) mx.get(i);
            b.drawImage(m.getImage(), m.getX(),
                    m.getY(), this);
        }
            
            
          ArrayList aa = p2.getMissiles2();
           Graphics2D a = (Graphics2D) g;
        for (int i=0;i<aa.size();i++) {
            missles m = (missles) aa.get(i);
            a.drawImage(m.getImage(), m.getX(),
                    m.getY(), this);
    }
        
        for(int i=0;i<asteroids.size();i++){
                       Enemy1 z = (Enemy1) asteroids.get(i);
             if(z.isVisible()){
            Graphics en = (Graphics2D) g;
            en.drawImage(z.getImage(), z.getX(), z.getY(), this);
                
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

    for (int i = 0; i < mt.size(); i++){
         missles2 z = (missles2) mt.get(i);
 if (z.isVisible()) {
            z.move();
         
        } else {
            mt.remove(i);
        
        }
    }
    }
 

    private void updatMissilesplayer2() {

    ArrayList mx = p2.getMissiles();

    for (int i = 0; i < mx.size(); i++) {

       missles2 m = (missles2) mx.get(i);

        if (m.isVisible()) {

            m.move();
        } else {

            mx.remove(i);
        }
    }
    
      ArrayList z = p2.getMissiles2();

    for (int i = 0; i < z.size(); i++) {

       missles m = (missles) z.get(i);

        if (m.isVisible()) {

            m.move();
        } else {

            z.remove(i);
        }
    }
}
           
            
    private void updateplayer1(){
         if(p1.isVisible()){
       ArrayList ms = p2.getMissiles();
    for (int i = 0; i < ms.size(); i++) {
        Rectangle r1 = p1.getBounds();
    
       missles2 m = (missles2) ms.get(i);
        Rectangle r2 = m.getBounds();
       
     if(r1.intersects(r2)){
        p1health--;
         P1health.setText("Player1: "+p1health);
        if(p1health==0) {
            this.getGraphics().drawImage(PlayerExplosion, p1.getX1(),p1.getY1(), this);
            p1.setVisible(false);
        }
     }
     }
    
    ArrayList y = p2.getMissiles2();
     for (int i = 0; i < y.size(); i++) {
        Rectangle r1 = p1.getBounds();
    
       missles m = (missles) y.get(i);
        Rectangle r2 = m.getBounds();
        if(r1.intersects(r2)){
        p1health--;
         P1health.setText("Player1: "+p1health);
        if(p1health==0) {
            this.getGraphics().drawImage(PlayerExplosion, p1.getX1(),p1.getY1(), this);
            p1.setVisible(false);
}
     }
     }
    
      for(int j=0;j<asteroids.size();j++){
   Enemy1 z = (Enemy1) asteroids.get(j);
   if(z.isVisible()){
       Rectangle r1 = p1.getBounds();
    
      
        Rectangle r2 = z.getBounds();
       
     if(r1.intersects(r2)){
        p1health--;
         P1health.setText("Player1: "+p1health);
        asteroids.remove(j);
         }
               
        if(p1health==0) { p1.setVisible(false);
               
        }
     }
    }
    }
    }
     
        private void updateplayer2(){
         if(p2.isVisible()){
       ArrayList ms = p1.getMissiles();
    for (int i = 0; i < ms.size(); i++) {
        Rectangle r1 = p2.getBounds();
    
       missles m = (missles) ms.get(i);
        Rectangle r2 = m.getBounds();
       
     if(r1.intersects(r2)){
        p2health--;
         P2health.setText("Player2: "+p2health);
        if(p2health==0) {
            this.getGraphics().drawImage(PlayerExplosion, p2.getX1(),p2.getY1(), this);
            p2.setVisible(false);}
     }
     }
    
    ArrayList y = p1.getMissiles2();
     for (int i = 0; i < y.size(); i++) {
        Rectangle r1 = p2.getBounds();
    
       missles2 m = (missles2) y.get(i);
        Rectangle r2 = m.getBounds();
        if(r1.intersects(r2)){
        p2health--;
        P2health.setText("Player2: "+p2health);
        if(p2health==0) { 
            this.getGraphics().drawImage(PlayerExplosion, p2.getX1(),p2.getY1(), this);
            p2.setVisible(false);}
     }
     }
      for(int j=0;j<asteroids.size();j++){
   Enemy1 z = (Enemy1) asteroids.get(j);
       if(z.isVisible()){
           
       Rectangle r1 = p2.getBounds();
    
      
        Rectangle r2 = z.getBounds();
       
     if(r1.intersects(r2)){
        p2health--;
         P2health.setText("Player2: "+p2health);
          asteroids.remove(j);
        }
                
        if(p2health==0) { p2.setVisible(false);
              }
     }
    }
        }
        }
    
    private void checkplayer1_misslecol(){
         
         if(p2.isVisible()){     
     ArrayList s = p1.getMissiles();
     for (int i = 0; i < s.size(); i++) {
        Rectangle r1 = p2.getBounds();
    
       missles m = (missles) s.get(i);
        Rectangle r2 = m.getBounds();
        
    if(r1.intersects(r2)){
     m.setVisible(false);
    
     }
     
   
     }
     
      ArrayList u = p1.getMissiles2();
     for (int i = 0; i < u.size(); i++) {
        Rectangle r1 = p2.getBounds();
    
       missles2 m = (missles2) u.get(i);
        Rectangle r2 = m.getBounds();
        
    if(r1.intersects(r2)){
     m.setVisible(false);
    
     }
     
   
     }
        
     }
         
         
     }
    
     private void checkplayer2_misslecol(){
      
         if(p1.isVisible()){     
     ArrayList s = p2.getMissiles();
     for (int i = 0; i < s.size(); i++) {
        Rectangle r1 = p1.getBounds();
    
       missles2 m = (missles2) s.get(i);
        Rectangle r2 = m.getBounds();
        
    if(r1.intersects(r2)){
     m.setVisible(false);
    
     }
     
   
     }
     
      ArrayList u = p2.getMissiles2();
     for (int i = 0; i < u.size(); i++) {
        Rectangle r1 = p1.getBounds();
    
       missles m = (missles) u.get(i);
        Rectangle r2 = m.getBounds();
        
    if(r1.intersects(r2)){
     m.setVisible(false);
    
     }
     
   
     }
        
     }
     }
     private void checkasteriod(){
         ArrayList ms = p1.getMissiles2();
 for(int j=0;j<asteroids.size();j++){
   Enemy1 z = (Enemy1) asteroids.get(j);
    for (int i = 0; i < ms.size(); i++) {
        Rectangle r1 = z.getBounds();
       
       missles2 m = (missles2) ms.get(i);
        Rectangle r2 = m.getBounds();
        
     if(r1.intersects(r2)){
        z.setVisible(false);
     }}
     ArrayList g = p1.getMissiles();

    for (int i = 0; i < g.size(); i++) {
        Rectangle r1 = z.getBounds();
       
       missles m = (missles) g.get(i);
        Rectangle r2 = m.getBounds();
        
     if(r1.intersects(r2)){
        z.setVisible(false);
     }}
         
     }
     
       ArrayList mx = p2.getMissiles();
 for(int j=0;j<asteroids.size();j++){
   Enemy1 z = (Enemy1) asteroids.get(j);
    for (int i = 0; i < mx.size(); i++) {
        Rectangle r1 = z.getBounds();
       
       missles2 m = (missles2) ms.get(i);
        Rectangle r2 = m.getBounds();
        
     if(r1.intersects(r2)){
        z.setVisible(false);
     }}
     ArrayList g = p2.getMissiles2();

    for (int i = 0; i < g.size(); i++) {
        Rectangle r1 = z.getBounds();
       
       missles m = (missles) g.get(i);
        Rectangle r2 = m.getBounds();
        
     if(r1.intersects(r2)){
        z.setVisible(false);
     }}
         
     }
 
     }
     public void gameOver(){
         if(p1.isVisible())
        JOptionPane.showMessageDialog(null, "Game Over! Player 1 wins");
         else JOptionPane.showMessageDialog(null, "Game Over! Player 2 wins");
        System.exit(0);
       
    }
     public void checkVisiblity(){
         if(!this.p1.isVisible() || !this.p2.isVisible()){
             t1.stop();
         t2.stop();
             gameOver();}
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
        
        Rectangle y=p2.getBounds();
        if((y.x-1<0)||(y.x+y.width+1>screenWidth+10)) {
            if((y.y-1<0)||y.y+y.height>screenHeight-10){
            p2.velx = 0;p2.vely=0;}
            p2.velx = 0;
        }
        else if((y.y-1<0)||y.y+y.height>screenHeight-10)
            p2.vely=0;
    }
}
     
    

