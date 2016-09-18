/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author ASUS
 */
public class MenuItem
{
    //protected BufferedImage image;
    protected ActionListener action;
    
    protected Point point;
    protected Polygon poly;
    protected String capt;
    
    public MenuItem(ActionListener a, Point p, String c)
    {
        action = a;
        point  = p;
        capt = c;
        
        initShape();
    }
    
    private void initShape()
    {
        int[] xPoints = new int[Constants.MENU_ITEM_NUM_POINTS];
        int[] yPoints = new int[Constants.MENU_ITEM_NUM_POINTS];
        
        xPoints[0] = 0;
        yPoints[0] = Constants.MENU_ITEM_ALTITUDE;
        xPoints[1] = Constants.MENU_ITEM_SIDE;
        yPoints[1] = Constants.MENU_ITEM_ALTITUDE;
        xPoints[3] = (int)((double)Constants.MENU_ITEM_ALTITUDE/Math.tan(Math.toRadians(Constants.MENU_ITEM_ANGLE)));
        yPoints[3] = 0;
        xPoints[2] = xPoints[3]+Constants.MENU_ITEM_SIDE;
        yPoints[2] = 0;
        
        poly = new Polygon(xPoints, yPoints, Constants.MENU_ITEM_NUM_POINTS);
        
        poly.translate((int)point.getX(), (int)point.getY());
    }
    
    public void handleMouseClickEvent(MouseEvent e)
    {
        if (poly.contains((double)e.getX(), (double)e.getY()))
        {
            action.actionPerformed(null);
        }
    } 
    
    public void draw(Graphics g)
    {
        g.setColor(Constants.MENU_ITEM_COLOR);
        g.fillPolygon(poly);
        g.setColor(Constants.MENU_ITEM_CAPTION_COLOR);
        g.setFont(Constants.MENU_ITEM_CAPTION_FONT);
        g.drawString(capt, (int)point.getX()+Constants.MENU_ITEM_CAPTION_X_CORRECTION, (int)point.getY()+Constants.MENU_ITEM_CAPTION_Y_CORRECTION);
    }
}
