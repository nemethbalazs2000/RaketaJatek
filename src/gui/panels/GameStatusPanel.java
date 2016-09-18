/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panels;

import core.Constants;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Balázs
 */
public class GameStatusPanel extends JPanel
{
    public GameStatusPanel()
    {
        setPreferredSize(new Dimension(Constants.STATUS_PANEL_WIDTH, Constants.STATUS_PANEL_HEIGHT));
    }
}
