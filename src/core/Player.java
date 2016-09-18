/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.bullets.BulletHandler;
import java.awt.Graphics2D;

/**
 *
 * @author ASUS
 */
public class Player
{
    public int DEFAULT_LIVES = 5;
    public long SHOOT_DELAY = 500000000L;
    
    private PlayerSprite rocket;
    private int lives;
    private int score;
    
    private long lastShot;
    
    public Player()
    {
        rocket = new PlayerSprite(Constants.GAME_HEIGHT);
        
        lives = DEFAULT_LIVES;
        score = 0;
        lastShot = 0;
    }
    
    public void loseLife()
    {
        lives--;
    }
    
    public int getLifeCount()
    {
        return lives;
    }
    
    public boolean isAlive()
    {
        return (lives>0);
    }

    public void increaseScore()
    {
        score++;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void drawRocket(Graphics2D g)
    {
        rocket.draw(g);
    }
    
    public void moveLeft()
    {
        rocket.moveLeft();
    }
    
    public void moveRight()
    {
        rocket.moveRight();
    }

    public void shoot(BulletHandler bulletHandler)
    {
        long currentTime = System.nanoTime();
        
        if (lastShot == 0)
        {
            lastShot = currentTime;
            rocket.shoot(bulletHandler);
        }
        else
        {
            long diff = currentTime-lastShot;
            
            if (diff >= SHOOT_DELAY)
            {
                lastShot = currentTime;
                rocket.shoot(bulletHandler);
            }
        }
    }
}
