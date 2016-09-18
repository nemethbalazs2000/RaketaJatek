/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.bullets.BulletHandler;
import core.bullets.FriendlyBullet;

/**
 *
 * @author BALU-AMD
 */
public class PlayerSprite extends Sprite
{
    public PlayerSprite(int h)
    {
        super(0, 0, Constants.PLAYER_SPRITE_FILE);
        
        y = h-getHeight();
    }

    public void shoot(BulletHandler l)
    {        
        l.addFriendlyBullet(new FriendlyBullet(x+getWidth()/2, y, Constants.BULLET_FILE));
    }

    public void moveRight()
    {
        if (x <= Constants.GAME_WIDTH-getWidth()-Constants.ROCKET_DX)
            x += Constants.ROCKET_DX;
    }

    public void moveLeft()
    {
        if (x >= Constants.ROCKET_DX)
            x -= Constants.ROCKET_DX;
    }
}
