package space;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
class HighScores extends JFrame{
    JButton ok;
    JTextArea top;
    public HighScores() {
        int count=0;
        this.setBounds(400,300,400,600);
        try {
            top=new JTextArea("Top 10 Scores:");
            FileReader Hs = new FileReader("High Scores.txt");
            BufferedReader br = new BufferedReader(Hs);
            String str1;String str2;
            while (((str1 = br.readLine()) != null)&&count<10) {
                str2=br.readLine();
                top.setText(top.getText()+"\n"+str1+"\t"+str2);
                count++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
        top.setEditable(false);
        top.setSize(300,400);
        ok=new JButton("OK");
        ok.setPreferredSize(new Dimension(80,40));
        top.setVisible(true);
        ok.setVisible(true);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(ok,BorderLayout.SOUTH);
        this.add(top,BorderLayout.CENTER);
        this.setTitle("Top Scores");
        top.setFont(new Font("Arial",Font.CENTER_BASELINE,20));
ok.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
});
    }
}

class Gamespace extends JFrame {
    private PvP Multi;
    //public Level2 l;
    public menu2 m1;
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private boolean Menu = true;
    private boolean Single =false;

    public Gamespace() {
        Container c = this.getContentPane();
        c.setLayout(null);
        Multi = new PvP();
        Multi.setVisible(!Menu && !Single);
        //l = new Level2();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(screenWidth, screenHeight);
        this.setBackground(Color.BLACK);
        //l.setVisible(Single);
        m1 = new menu2();
        m1.setVisible(Menu);
        Level2 l =new Level2();
        l.setVisible(true);
        l.setFocusable(true);
        c.add(m1);
        c.add(Multi);
        c.add(l);
        m1.Single.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Level2 l =new Level2();
                l.setVisible(true);
                l.setFocusable(true);
                m1.setVisible(false);
                m1.setFocusable(false);
                c.add(l);
            }

        });
  
    }

    public static void main(String args[]){
        menu2 m = new menu2();
        m.setVisible(true);
       
    }
}
