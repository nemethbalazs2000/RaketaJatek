/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.bullets;

import java.awt.image.BufferedImage;

/**
 *
 * @author BALU-AMD
 */
public class EnemyBullet extends Bullet
{
    public EnemyBullet(int x, int y, BufferedImage kep)
    {   
        super(x, y, kep);
        
        dx = 0;
        dy = 4;
    }
}
