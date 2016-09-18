/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialogs;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author BALU-AMD
 */
public class EndOfGameDialog extends JDialog
{
    private String s;
    
    private JTextField t;
    private JButton b;
    
    public EndOfGameDialog(JFrame parent, int score)
    {
        super(parent, "RakétaJáték", true);
        
        setResizable(false);
        
        BoxLayout box = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        
        JLabel l1 = new JLabel("Játék vége. Pontszám: " + score);
        JLabel l2 = new JLabel("Kérlek add meg a neved: ");
        
        JPanel p = new JPanel(new FlowLayout());
        
        t = new JTextField(10);
        b = new JButton("Ok");
        
        p.add(t);
        p.add(b);
        
        getContentPane().setLayout(box);
        
        add(l1);
        add(l2);
        add(p);
        
        pack();
        
        b.addActionListener(new OkActionListener(this));
    }
    
    public String mutat()
    {
        setVisible(true);
        
        return s;
    }

    private class OkActionListener implements ActionListener
    {
        private EndOfGameDialog j;
        
        public OkActionListener(EndOfGameDialog j)
        {
            this.j = j;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            s = t.getText();
            
            setVisible(false);
        }
    }
}
