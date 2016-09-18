/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.bullets;

import core.Constants;
import java.awt.image.BufferedImage;


/**
 *
 * @author BALU-AMD
 */
public class FriendlyBullet extends Bullet
{    
    public FriendlyBullet(int x, int y, BufferedImage image)
    {
        super(x, y, image);
        
        dy = Constants.FRIENDLYBULLET_DY;
        dx = Constants.FRIENDLYBULLET_DX;
    }
    
    public FriendlyBullet(int x, int y, String path)
    {
        super(x, y, path);
        
        dy = Constants.FRIENDLYBULLET_DY;
        dx = Constants.FRIENDLYBULLET_DX;
    }
}
