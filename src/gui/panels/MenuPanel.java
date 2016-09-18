/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panels;

import core.Constants;
import core.MenuItem;
import gui.frames.RocketFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author BALU-AMD
 */
public class MenuPanel extends JPanel
{
    public static final String BACKGROUND_FILE = "/res/background.png";
    
    private Dimension dimension;
    
    private ArrayList<MenuItem> menuItems;
    
    private MenuItem play;
    private MenuItem scores;
    private MenuItem help;
    private MenuItem exit;
    
    private BufferedImage background;
    
    private RocketFrame frame;
    
    private BoxLayout layout;
    
    public MenuPanel(RocketFrame keret)
    {   
        makeButtons();
        initialize();
        
        this.frame = keret;
    }
    
    private void makeButtons()
    {
        menuItems = new ArrayList<>();
        
        menuItems.add(new MenuItem(new PlayActionListener(), Constants.PLAY_BUTTON_POSITION, Constants.PLAY_BUTTON_CAPTION));
        menuItems.add(new MenuItem(new ScoresActionListener(), Constants.SCORES_BUTTON_POSITION, Constants.SCORES_BUTTON_CAPTION));
        menuItems.add(new MenuItem(new HelpActionListener(), Constants.HELP_BUTTON_POSITION, Constants.HELP_BUTTON_CAPTION));
        menuItems.add(new MenuItem(new ExitActionListener(), Constants.EXIT_BUTTON_POSITION, Constants.EXIT_BUTTON_CAPTION));
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Constants.BACKGROUND_COLOR);
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);
        g.setColor(Constants.CAPTION_COLOR);
        g.setFont(Constants.CAPTION_FONT);
        g.drawString(Constants.CAPTION_STRING, Constants.CAPTION_POSITION.x, Constants.CAPTION_POSITION.y);
        
        for (MenuItem m:menuItems)
            m.draw(g);
    }

    private void initialize()
    {
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        
        addMouseListener(new MenuPanelMenuItemMouseListener());
       
        setPreferredSize(new Dimension(Constants.MENU_WIDTH, Constants.MENU_HEIGHT));
    }

    private class MenuPanelMenuItemMouseListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent me)
        {
            for (MenuItem m:menuItems)
                m.handleMouseClickEvent(me);
        }

        @Override
        public void mousePressed(MouseEvent me) {}

        @Override
        public void mouseReleased(MouseEvent me) {}

        @Override
        public void mouseEntered(MouseEvent me) {}

        @Override
        public void mouseExited(MouseEvent me) {}
        
    }
    
    private class HelpActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            frame.show(frame.getHelpPanel());
        }
    }

    private class ExitActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            frame.getResultPanel().writeScores();
            
            System.exit(0);
        }
    }

    private class ScoresActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            frame.show(frame.getResultPanel());
        }
    }
    
    private class PlayActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            frame.show(frame.getPlayPanel());
        }
    }
}
