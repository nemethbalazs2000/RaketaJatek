/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.frames;

import core.Player;
import gui.panels.ResultPanel;
import gui.panels.MenuPanel;
import gui.panels.HelpPanel;
import gui.panels.PlayPanel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author BALU-AMD
 */
public class RocketFrame extends JFrame
{
    private MenuPanel m;
    private ResultPanel r;
    private HelpPanel h;
    private PlayPanel playPanel;
    
    private Player p;
    
    public RocketFrame()
    {
        super("RakétaJáték");
        
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(RocketFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        p = new Player();
        
        m = new MenuPanel(this);
        r = new ResultPanel(this);
        h = new HelpPanel(this);
        
        playPanel = new PlayPanel(this);
        
        RocketWindowListener w = new RocketWindowListener();
        
        addWindowListener(w);
        addWindowFocusListener(w);
        setResizable(false);
        
        add(m);
        pack();
        setVisible(true);
    }
    
    public PlayPanel getPlayPanel()
    {
        return playPanel;
    }
    
    public MenuPanel getMenuPanel()
    {
        return m;
    }
    
    public ResultPanel getResultPanel()
    {
        return r;
    }
    
    public HelpPanel getHelpPanel()
    {
        return h;
    }

    public void show(JPanel p)
    {
        remove((JPanel)(getContentPane().getComponent(0)));
        add(p);
        setSize(p.getPreferredSize());
        pack();
        revalidate();
        repaint();
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                RocketFrame f = new RocketFrame();
            }
        });
    }

    public Player getPlayer()
    {
        return p;
    }
   
    private class RocketWindowListener implements WindowListener, WindowFocusListener
    {

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e)
        {
            r.writeScores();
            
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e)
        {
            if (!playPanel.isGameStopped())
                playPanel.pauseGame();
        }

        @Override
        public void windowDeiconified(WindowEvent e)
        {
            if (playPanel.isGameStopped())
                playPanel.continueGame();
        }

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}

        @Override
        public void windowGainedFocus(WindowEvent e)
        {
            if (playPanel.isGameStopped())
                playPanel.continueGame();
        }

        @Override
        public void windowLostFocus(WindowEvent e)
        {
            if (!playPanel.isGameStopped())
                playPanel.pauseGame();
        }
        
    }
}
