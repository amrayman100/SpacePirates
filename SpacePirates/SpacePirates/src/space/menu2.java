
package space;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

 class menu2 extends JFrame {
     private Clip clip;
     private Clip clip2;
     private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
     private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
     private Image background;
     public JLabel Single;
     private JLabel SingleH;
     private JLabel Multi;
     private JLabel MultiH;
     private JLabel High;
     private JLabel HighH;
     private JLabel BG;
     private JLabel Ititle;
     private ImageIcon title;
     private JLabel P2;
     private JLabel pvp;
     private JLabel P21;
     private JLabel pvp1;
     ImageIcon gameImg;
     ImageIcon S1;
     ImageIcon M1;
     ImageIcon H1;
     ImageIcon S2;
     ImageIcon M2;
     ImageIcon H2;

     public menu2() {
         Container c = this.getContentPane();
         this.setSize(screenWidth, screenHeight);
         c.setBackground(Color.black);
         this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         PlaySound();
         PlaySound2();
         BG = new JLabel();
         Ititle = new JLabel();
         title = new ImageIcon(getClass().getResource("/newpackage/SALAH WARS.png"));
         Ititle.setIcon(title);
        /*SingleButton = new JButton();
        MultiButton = new JButton();
        HighButton = new JButton();
        SingleButton.setBounds(buttonX - 7, SingleButtonY - 23, 170, 32);
        MultiButton.setBounds(buttonX, MultiButtonY - 23, 155, 32);
        HighButton.setBounds(buttonX, HighButtonY - 23, 150, 32);*/
         Single = new JLabel();
         Multi = new JLabel();
         High = new JLabel();
         SingleH = new JLabel();
         MultiH = new JLabel();
         HighH = new JLabel();
         gameImg = new ImageIcon(getClass().getResource("/newpackage/gameMenu2.png"));
         S1 = new ImageIcon(getClass().getResource("/newpackage/SINGLE PLAYER1.png"));
         S2 = new ImageIcon(getClass().getResource("/newpackage/SINGLE PLAYER HIGHLIGHTED1.png"));
         M1 = new ImageIcon(getClass().getResource("/newpackage/MULTIPLAYER1.png"));
         M2 = new ImageIcon(getClass().getResource("/newpackage/MULTIPLAYER HIGHLIGHTED1.png"));
         H1 = new ImageIcon(getClass().getResource("/newpackage/HIGHSCORES1.png"));
         H2 = new ImageIcon(getClass().getResource("/newpackage/HIGHSCORES HIGHLIGHTED1.png"));
         background = gameImg.getImage();
         BG.setIcon(gameImg);
         //BG.setBounds(0 , 0 ,screenWidth ,screenHeight);
        BG.setBounds((screenWidth / 2) - (background.getWidth(null) / 2),(screenHeight / 2) - (background.getHeight(null) / 2)-20, background.getWidth(null), background.getHeight(null));

         Single.setIcon(S1);
         Single.setBounds(screenWidth - 1050, screenHeight - 700, 528, 40);
         Single.setVisible(true);
         SingleH.setIcon(S2);
         SingleH.setVisible(false);
         BG.add(SingleH);
         SingleH.setBounds(screenWidth - 1050, screenHeight - 700, 528, 40);
         Single.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseEntered(MouseEvent e) {
                 SingleH.setVisible(true);
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 Single.setVisible(true);
                 SingleH.setVisible(false);
             }

             @Override
             public void mouseClicked(MouseEvent e) {
                 clip2.start();
                 clip.stop();
                 level2frame p = new level2frame();
                 p.setVisible(true);
                 dispose();
             }
         });
         SingleH.setIcon(S2);
         Multi.setIcon(M1);
         MultiH.setIcon(M2);
         Multi.setBounds(screenWidth - 1015, screenHeight - 600, 460, 40);
         MultiH.setBounds(screenWidth - 1015, screenHeight - 600, 460, 40);
         Multi.setVisible(true);
         MultiH.setVisible(false);
         Ititle.setVisible(true);
         Ititle.setBounds(screenWidth - 1200, screenHeight - 930, 838, 95);
         BG.add(Ititle);
         BG.add(MultiH);
         BG.add(Multi);
         P2 = new JLabel();
         P21 = new JLabel();
         P2.setIcon(new ImageIcon(getClass().getResource("/newpackage/2 Players.png")));
         P2.setVisible(false);
         P2.setBounds(Multi.getX()+Multi.getWidth()+20,Multi.getY()-50,290,65);
         P21.setIcon(new ImageIcon(getClass().getResource("/newpackage/2 Players1.png")));
         P21.setVisible(false);
         P21.setBounds(Multi.getX()+Multi.getWidth()+20,Multi.getY()-50,290,65);
         pvp = new JLabel();
         pvp.setIcon(new ImageIcon(getClass().getResource("/newpackage/PvP.png")));
         pvp.setVisible(false);
         pvp.setBounds(Multi.getX()+Multi.getWidth()+30,Multi.getY()+30,285,65);
         pvp1 = new JLabel();
         pvp1.setIcon(new ImageIcon(getClass().getResource("/newpackage/PvP1.png")));
         pvp1.setVisible(false);
         pvp1.setBounds(Multi.getX()+Multi.getWidth()+30,Multi.getY()+30,285,65);
         High.setIcon(H1);
         High.setBounds(screenWidth - 980, screenHeight - 500, 395, 40);
         High.setVisible(true);
         HighH.setIcon(H2);
         HighH.setBounds(screenWidth - 980, screenHeight - 500, 395, 40);
         HighH.setVisible(false);
         BG.add(Single);
         BG.add(HighH);
         BG.add(High);
         BG.add(P21);
         BG.add(P2);
         BG.add(pvp1);
         BG.add(pvp);
         this.add(BG);
         this.setBackground(Color.black);
         this.setLayout(null);
         this.setSize(screenWidth, screenHeight);
         this.setVisible(true);
         Multi.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 clip2.start();
                 clip.stop();
                 if(!pvp.isVisible()||!P2.isVisible()) {
                     P2.setVisible(true);
                     pvp.setVisible(true);
                 }
                 else {
                     P2.setVisible(false);
                     pvp.setVisible(false);
                 }
             }

             public void mouseEntered(MouseEvent e) {
                 MultiH.setVisible(true);
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 Multi.setVisible(true);
                 MultiH.setVisible(false);
             }
         });
         High.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseEntered(MouseEvent e) {
                 HighH.setVisible(true);
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 High.setVisible(true);
                 HighH.setVisible(false);
             }

             @Override
             public void mouseClicked(MouseEvent e) {
                 clip2.start();
                 clip.stop();
                 HighScores h1 = new HighScores();
                 h1.setVisible(true);
                 pvp.setVisible(false);
                 P2.setVisible(false);
             }
         });
         pvp.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 clip2.start();
                 clip.stop();
                 PvPFrame x = new PvPFrame();
                 x.setVisible(true);
                 dispose();
             }
             public void mouseEntered(MouseEvent e) {pvp1.setVisible(true);
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 pvp1.setVisible(false);
             }
         });
         P2.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 clip2.start();
                 clip.stop();
                  multiframe m = new multiframe();
                  m.setVisible(true);
                 //------------------------------------------------------------------------------------------------------------
                 //7ot loay's multiplayer frame henaa
                 //------------------------------------------------------------------------------------------------------------
                 pvp.setVisible(false);
                 P2.setVisible(false);
             }

             public void mouseEntered(MouseEvent e) {P21.setVisible(true);
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 P21.setVisible(false);
             }
         });
         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
       
     }

    public void PlaySound(){
        try {
            // Open an audio input stream.
            URL url =getClass().getResource("/newpackage/Title.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
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
    public void PlaySound2(){
        try {
            // Open an audio input stream.
            URL url =getClass().getResource("/newpackage/k");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip2 = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip2.open(audioIn);

            // clip2.start();


        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
