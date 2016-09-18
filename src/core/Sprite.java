/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ASUS
 */
public class Sprite implements Drawable, Updateable
{
    protected int x;
    protected int y;
    
    protected BufferedImage image;
    
    public Sprite(int x, int y, String path)
    {
        this.x = x;
        this.y = y;
        
        try
        {
            image = ImageIO.read(getClass().getResource(path));
        }
        catch (IOException e) {}
    }
    
    public Sprite(int x, int y, BufferedImage i)
    {
        this.x = x;
        this.y = y;
        
        image = i;
    }

    @Override
    public void draw(Graphics2D g)
    {
        if (g != null)
        {
            g.drawImage(image, x, y, null);
            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void update(){}
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return image.getWidth();
    }
    
    public int getHeight()
    {
        return image.getHeight();
    }
}
