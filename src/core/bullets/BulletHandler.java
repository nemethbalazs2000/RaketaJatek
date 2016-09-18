/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.bullets;

import core.Updateable;
import core.Drawable;
import core.Player;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author BALU-AMD
 */
public class BulletHandler implements Drawable, Updateable
{
    private ArrayList<Bullet> friendlyBullets;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Bullet> removeBullets;
    
    private Player player;
    
    private Dimension dimension;
    
    public BulletHandler(Dimension d, Player p)
    {
        friendlyBullets = new ArrayList<>();
        enemyBullets    = new ArrayList<>();
        removeBullets   = new ArrayList<>();
        
        player = p;
        
        dimension = d;
    }
    
    public synchronized void addFriendlyBullet(Bullet l)
    {
        friendlyBullets.add(l);
    }
    
    public synchronized void addEnemyBullet(Bullet l)
    {
        enemyBullets.add(l);
    }
    
    @Override
    public synchronized void update()
    {
        updateFriendlyBullets();
        updateEnemyBullets();
        checkCollision();
        removeBullets();
    }
    
    @Override
    public synchronized void draw(Graphics2D g)
    {
        for (Bullet b : friendlyBullets)
            b.draw(g);
        
        for (Bullet e : enemyBullets)
            e.draw(g);
    }

    private void updateFriendlyBullets()
    {
        for (Bullet l : friendlyBullets)
        {
            l.update();
            if (l.getY() <= 0)
                removeBullets.add(l);
        }
    }
    
    private void updateEnemyBullets()
    {
        for (Bullet l : enemyBullets)
        {
            l.update();
            if (l.getY() >= dimension.getHeight())
            {
                removeBullets.add(l);
                player.loseLife();
            }
        }
    }

    private void checkCollision()
    {
        for (Bullet b : friendlyBullets)
        {
            for (Bullet e : enemyBullets)
            {
                int xdiff  = b.getX() - e.getX();
                int wdiff  = Math.abs(e.getWidth() - b.getWidth());
                int ydiff  = Math.abs(b.getY() - e.getY());
                int dydiff = Math.abs(b.getDeltaY() - e.getDeltaY()); 
                
                if (xdiff >= 0 && xdiff <= wdiff && ydiff <= dydiff)
                {
                    removeBullets.add(b);
                    removeBullets.add(e);
                    
                    player.increaseScore();
                }
            }
        }
    }

    private void removeBullets()
    {
        for (Bullet l : removeBullets)
        {
            if (friendlyBullets.contains(l))
                friendlyBullets.remove(l);
            else if (enemyBullets.contains(l))
                enemyBullets.remove(l);
        }
    }
    
    public void setDimension(Dimension d)
    {
        dimension = d;
    }
}
