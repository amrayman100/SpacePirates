
package space;
import java.awt.*;//Container
import java.awt.event.*;//Action Listner//
import javax.swing.*;//GUI compomemts//

public class frame extends JFrame {
    private JButton b1;
    public frame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         this.setSize(1000,500);
           setLayout(null); 
        Container c = this.getContentPane();
        b1 = new JButton("Start");
        b1.setBounds(250, 60, 175,30);
           c.add(b1);
        b1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent evt){
           Level2 l = new Level2();
           l.setVisible(true);
        
           //c.add(l);
           /*Container x = getContentPane();
           x.add(l);*/
        }
    });
       
        
    }
}
