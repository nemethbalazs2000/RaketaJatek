/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.bullets;

import core.Constants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author BALU-AMD
 */
public class EnemyAdder implements ActionListener
{
    private BulletHandler l;
    private Dimension d;
    private BufferedImage k;
    
    public EnemyAdder(BulletHandler l, Dimension d)
    {
        this.l = l;
        this.d = d;
        
        try
        {
            k = ImageIO.read(getClass().getResource(Constants.ENEMY_FILE));
        }
        catch (IOException ex) {}
    }
    
    public void setDimension(Dimension d)
    {
        this.d = d;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        l.addEnemyBullet(new EnemyBullet((int)(Math.random()*(d.getWidth()-k.getWidth())), 0, k));
    }
}
