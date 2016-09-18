/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

/**
 *
 * @author ASUS
 */
public abstract class Constants
{
    public static final String HEART_FILE   = "/res/heart.png";
    public static final String SPACE_FILE   = "/res/space.jpg";
    public static final String ROCKET_FILE = "/res/rocket.png";
    public static final String BULLET_FILE = "/res/bullet.png";
    public static final String PLAYER_SPRITE_FILE = "/res/player.png";
    public static final String ENEMY_FILE = "/res/enemy.png";
    
    public static final String PLAY_BUTTON_FILE = "/res/play.png";
    public static final String SCORES_BUTTON_FILE = "/res/results.png";
    public static final String HELP_BUTTON_FILE = "/res/help.png";
    public static final String EXIT_BUTTON_FILE = "/res/exit.png";
    
    public static final String PLAY_BUTTON_CAPTION   = "JÁTÉK";
    public static final String SCORES_BUTTON_CAPTION = "PONTOK";
    public static final String HELP_BUTTON_CAPTION   = "SEGÍTSÉG";
    public static final String EXIT_BUTTON_CAPTION   = "KILÉPÉS";
    
    public static final Point PLAY_BUTTON_POSITION = new Point(300,170);
    public static final Point SCORES_BUTTON_POSITION = new Point(200,280);
    public static final Point HELP_BUTTON_POSITION = new Point(100,390);
    public static final Point EXIT_BUTTON_POSITION = new Point(0,500);
    
    public static final double MENU_ITEM_ANGLE   = 45.0f;
    public static final int MENU_ITEM_SIDE       = 400;
    public static final int MENU_ITEM_ALTITUDE   = 100;
    public static final int MENU_ITEM_NUM_POINTS = 4;
    
    public static final Color MENU_ITEM_COLOR = new Color(246,153,0);
    public static final Color MENU_ITEM_CAPTION_COLOR = new Color(246,77,0);
    public static final Font  MENU_ITEM_CAPTION_FONT = new Font("Segoe UI",Font.BOLD, 48);
    
    public static final int MENU_ITEM_CAPTION_X_CORRECTION = 100;
    public static final int MENU_ITEM_CAPTION_Y_CORRECTION = 65;
    
    public static final Point  CAPTION_POSITION = new Point(75,100);
    public static final Color  CAPTION_COLOR    = new Color(246,77,0);
    public static final Font   CAPTION_FONT     = new Font("Segoe UI", Font.BOLD|Font.ITALIC, 96);
    public static final String CAPTION_STRING   = "RakétaJáték"; 
    
    public static final Color BACKGROUND_COLOR = new Color(0xf6bb00);
    
    public static final int ROCKET_DX = 7;
    public static final int ROCKET_DY = 0;
    
    public static final int FRIENDLYBULLET_DY = -9;
    public static final int FRIENDLYBULLET_DX = 0;
    
    public static final int MENU_WIDTH   = 800;
    public static final int MENU_HEIGHT  = 600;
    
    public static final int GAME_WIDTH  = 600;
    public static final int GAME_HEIGHT = 600;
    
    public static final int STATUS_PANEL_WIDTH  = 200;
    public static final int STATUS_PANEL_HEIGHT = 600;
    
    public static final int NO_DELAYS_PER_YIELD = 4;
    public static final int MAX_FRAME_SKIPS = 2;
    
    public static final long PERIOD = 35000000L;
}
