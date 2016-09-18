/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.bullets;

import core.Sprite;
import java.awt.image.BufferedImage;

/**
 *
 * @author BALU-AMD
 */
public abstract class Bullet extends Sprite
{  
    protected int dx;
    protected int dy;
    
    public Bullet(int x, int y, String path)
    {
        super(x, y, path);
    }
    
    public Bullet(int x, int y, BufferedImage image)
    {
        super(x, y, image);
    }
    
    public int getDeltaX()
    {
        return dx;
    }
    
    public int getDeltaY()
    {
        return dy;
    }
    
    @Override
    public void update()
    {
        x += dx;
        y += dy;
    }
}