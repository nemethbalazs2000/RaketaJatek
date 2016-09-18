/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.panels;

import core.Result;
import gui.frames.RocketFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import nu.xom.ValidityException;

/**
 *
 * @author BALU-AMD
 */
public class ResultPanel extends JPanel
{
    private ArrayList<Result> scores;
    private RocketFrame rocketFrame;
    private JTable table;
    private JButton back;
    private JScrollPane pane;
    private boolean isTableReady;
    
    public ResultPanel(RocketFrame k)
    {
        rocketFrame = k;
        
        isTableReady = false;
        
        scores = new ArrayList<>();
        
        back = new JButton("Vissza");
        back.addActionListener(new BackActionListener());
        
        setLayout(new BorderLayout());
        
        add(back, BorderLayout.SOUTH);
        
        loadResults();
        prepareTable();
        
        add(pane, BorderLayout.CENTER);
    }
    
    private void loadResults()
    {
        Builder parser = new Builder();
        Document doc   = null;
        
        try
        {
            doc = parser.build(new File("./pontok.xml"));
        }
        catch (ValidityException ex) {}
        catch (ParsingException  ex) {}
        catch (FileNotFoundException ex){return;}
        catch (IOException       ex) {}
        
        Element r = doc.getRootElement();
        Elements el = r.getChildElements();
        
        for (int i = 0; i < el.size(); ++i)
            scores.add(new Result(el.get(i)));
    }
    
    public void writeScores()
    {
        Element r = new Element("pontszamok");
        
        for (Result p : scores)
            r.appendChild(p.write());
        
        Document doc = new Document(r);
        
        try
        {
            File f = new File("./pontok.xml");
        
            if (!f.exists())
                f.createNewFile();
            
            Serializer s = new Serializer(new FileOutputStream(f));
            s.setIndent(4);
            s.setMaxLength(64);
            s.write(doc);
        }
        catch (IOException ex) {ex.printStackTrace();}
    }
    
    public void addScore(Result p)
    {
        scores.add(p);
     
        prepareTable();
    }

    private void prepareTable()
    {
        if (!isTableReady)
            makeJTable();
        
        int n = scores.size();
        
        Collections.sort(scores, new Result.ResultComparator());
        
        Object[][] data   = new Object[n][4];
        Object[]   header = {"Helyezés", "Név", "Pontszám", "Időpont"};
        
        for (int i = 0; i < n; ++i)
        {
            Result p = scores.get(i);
            
            data[i][0] = new Integer(i+1);
            data[i][1] = p.getName();
            data[i][2] = new Integer(p.getscore());
            data[i][3] = p.getFormattedDate();
        }
        
        table.setModel(new ScoreTableModel(data, header));
    }

    private void makeJTable()
    {
        table = new JTable();
        
        pane = new JScrollPane(table);
        
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setReorderingAllowed(false);
        
        isTableReady = true;
    }

    private class BackActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            rocketFrame.show(rocketFrame.getMenuPanel());
        }
    }
    
    private class ScoreTableModel extends DefaultTableModel
    {
       public ScoreTableModel(Object[][] data, Object[] header)
       {
           super(data, header);
       }
       
       @Override
       public boolean isCellEditable(int s, int o)
       {
           return false;
       }
    }
}
