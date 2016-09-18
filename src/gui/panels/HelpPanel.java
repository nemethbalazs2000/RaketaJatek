/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panels;

import gui.frames.RocketFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author BALU-AMD
 */
public class HelpPanel extends JPanel
{
    public static final String HELP_FILE = "/res/help.txt";
    
    private RocketFrame rocketFrame;
    
    private JButton back;
    
    public HelpPanel(RocketFrame k)
    {
        rocketFrame = k;
        
        back = new JButton("Vissza");
        back.addActionListener(new BackActionListener());
        
        setLayout(new BorderLayout());
        
        JPanel p = new JPanel();
        BoxLayout b = new BoxLayout(p, BoxLayout.Y_AXIS);
        p.setLayout(b);
        readFile(p);
        
        add(p);
        add(back, BorderLayout.SOUTH);
    }

    private void readFile(JPanel p)
    {
        try
        {
            BufferedReader bi = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(HelpPanel.HELP_FILE), "UTF-8"));
        
            String s;
            
            while ((s = bi.readLine()) != null)
            {
                JLabel l = new JLabel(s);
                l.setAlignmentX(Component.CENTER_ALIGNMENT);
                p.add(l, BorderLayout.CENTER);
                p.add(Box.createVerticalGlue(), BorderLayout.CENTER);
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class BackActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            rocketFrame.show(rocketFrame.getMenuPanel());
        }
    }
}
