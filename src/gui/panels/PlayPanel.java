/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panels;

import core.Constants;
import core.Player;
import core.Result;
import core.bullets.BulletHandler;
import core.bullets.EnemyAdder;
import gui.dialogs.EndOfGameDialog;
import gui.frames.RocketFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Balázs
 */
public class PlayPanel extends JPanel implements Runnable
{
    protected Dimension gameDimension;
    
    protected Thread t;
    
    protected volatile boolean gameOver;
    protected volatile boolean gameRunning;
    protected volatile boolean gamePaused;
    
    protected int delay = 1500;
    
    protected Image buffer;
    protected BufferedImage heart;
    protected BufferedImage background;
    protected Graphics2D bufferGraphics;
    
    protected EndOfGameDialog endOfGameDialog;
    
    protected Timer enemyAdderTimer;
    protected EnemyAdder enemyAdder;

    protected BulletHandler bulletHandler;
    
    protected Player player;
    
    protected HashMap<Integer, Boolean> keyMap;
    
    protected JPanel gamePanel;
    protected GameStatusPanel gameStatusPanel;
    
    protected RocketFrame frame;
    
    public PlayPanel(RocketFrame frame)
    {
        this.frame = frame;
    
        gameDimension = new Dimension(Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
        
        gamePanel       = new JPanel();
        gamePanel.setPreferredSize(gameDimension);
        gameStatusPanel = new GameStatusPanel();
    
        player = frame.getPlayer();
        
        gameOver    = false;
        gameRunning = false;
        gamePaused  = false;
        
        buffer = null;
        
        try
        {
            heart      = ImageIO.read(getClass().getResource(Constants.HEART_FILE));
            background = ImageIO.read(getClass().getResource(Constants.SPACE_FILE)); 
        }
        catch (IOException ex) {} 
        
        initalizeMap();
        
        addKeyListener(new GameKeyListener());
        
        add(gamePanel);
        add(gameStatusPanel);
    }
    
    @Override
    public void addNotify()
    {
        super.addNotify();
        requestFocus();
        beginGame();
    }
    
    @Override
    public void run()
    {
        gameRunning = true;
        gamePaused = false;
        
        long beforeTime, timeDiff, sleepTime, afterTime, overSleepTime, excess;
        
        int noDelays = 0;
        
        excess = 0L;
        overSleepTime = 0L;
        beforeTime = System.nanoTime();
        
        while (gameRunning)
        {
            updateGame();
            renderGame();
            drawGame();
            
            afterTime = System.nanoTime();
            timeDiff  = afterTime-beforeTime;
            
            sleepTime = (Constants.PERIOD-timeDiff)-overSleepTime; 
            
            if (sleepTime > 0)
            {
                try
                {
                    Thread.sleep(sleepTime/1000000L);
                }
                catch (InterruptedException ex) {}
                
                overSleepTime = (System.nanoTime()-afterTime)-sleepTime;
            }
            else
            {
                excess += (-sleepTime);
                overSleepTime = 0L;
                
                if (++noDelays > Constants.NO_DELAYS_PER_YIELD)
                {
                    Thread.yield();
                    noDelays = 0;
                }
            }
            
            beforeTime = System.nanoTime();
        
            int skips = 0;
            
            while ((excess>Constants.PERIOD) && (skips<Constants.MAX_FRAME_SKIPS))
            {
                excess -= Constants.PERIOD;
                updateGame();
                skips++;
            }
        }
        
        stopGame();
    }

    public void beginGame()
    {
        if (!gameRunning)
        {   
            bulletHandler = new BulletHandler(gameDimension, player); 
            
            enemyAdder = new EnemyAdder(bulletHandler, gameDimension);
            
            enemyAdderTimer = new Timer(delay, enemyAdder);
            enemyAdderTimer.start();
            
            t = new Thread(this);
            t.start();
        }
    }
    
    private void stopGame()
    {
        enemyAdderTimer.stop();
        
        endOfGameDialog = new EndOfGameDialog(frame, player.getScore());
        
        frame.getResultPanel().addScore(new Result(endOfGameDialog.mutat(), player.getScore()));
        
        frame.show(frame.getMenuPanel());
    }
    
    public void pauseGame()
    {
        gamePaused = true;
        
        enemyAdderTimer.stop();
    }
    
    public void continueGame()
    {
        gamePaused = false;
        
        enemyAdderTimer.restart();
    }
    
    public boolean isGameStopped()
    {
        return gamePaused;
    }

    private void updateGame()
    {
        if (!gamePaused && !gameOver)
        {
            bulletHandler.update();
        
            if (keyMap.get(KeyEvent.VK_A) || keyMap.get(KeyEvent.VK_LEFT))
                player.moveLeft();
            if (keyMap.get(KeyEvent.VK_D) || keyMap.get(KeyEvent.VK_RIGHT))
                player.moveRight();
            if (keyMap.get(KeyEvent.VK_SPACE))
                player.shoot(bulletHandler);
            
            if (!player.isAlive())
                gameRunning = false;
        }
    }

    private void renderGame()
    {
        if (buffer == null)
        {
            buffer = createImage((int)gameDimension.getWidth(), (int)gameDimension.getHeight());
            
            if (buffer == null)
            {
                System.out.println("Could not create the image buffer");
            }
        }
        else
        {
            bufferGraphics = (Graphics2D) buffer.getGraphics();

            bufferGraphics.drawImage(background, null, 0, 0);

            drawText();
            
            player.drawRocket(bufferGraphics);
            bulletHandler.draw(bufferGraphics);
        }
    }
    
    private void drawGame()
    {
        Graphics g;
        
        try
        {
            g = gamePanel.getGraphics();
            
            if (g != null && buffer != null)
            {
                g.drawImage(buffer, 0, 0, null);
            }
            else
            {
                System.out.println("Something is null!");
            }
                
            Toolkit.getDefaultToolkit().sync();
        }
        catch (Exception e)
        {
        }
    }

    private void drawText()
    {
        bufferGraphics.setColor(Color.RED);
        bufferGraphics.setFont(new Font("consolas", Font.BOLD, 20));
        bufferGraphics.drawString("Pont: " + player.getScore(), 0, 17);
        bufferGraphics.drawString("Élet: ", (int)gameDimension.getWidth() - player.getLifeCount()*heart.getWidth() - 60, 17);
    
        for (int i = 0; i < player.getLifeCount(); ++i)
        {
            bufferGraphics.drawImage(heart, (int)gameDimension.getWidth() - player.getLifeCount()*heart.getWidth() + i*heart.getWidth(), 0, null);
        }
    }

    private void initalizeMap()
    {
        keyMap = new HashMap<>();
        
        keyMap.put(KeyEvent.VK_RIGHT, false);
        keyMap.put(KeyEvent.VK_LEFT, false);
        
        keyMap.put(KeyEvent.VK_A, false);
        keyMap.put(KeyEvent.VK_D, false);
       
        keyMap.put(KeyEvent.VK_SPACE, false);
    }

    private class GameKeyListener implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e)
        {   
            keyMap.put(e.getKeyCode(), true);
        }
        
        @Override
        public void keyTyped(KeyEvent e) {}
        
        @Override
        public void keyReleased(KeyEvent e)
        {
            keyMap.put(e.getKeyCode(), false);
        }
    }
}
