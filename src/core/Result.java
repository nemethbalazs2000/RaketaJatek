/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import nu.xom.Element;

/**
 *
 * @author BALU-AMD
 */
public class Result
{
    private String name;
    private int score;
    private Date date;
    private SimpleDateFormat df;
    
    public Result(String name, int point)
    {
        this.name      = name;
        this.score    = point;
        this.df        = new SimpleDateFormat("yyyy. MMMMM d. HH:mm:ss");
        this.date      = Calendar.getInstance().getTime();
    }

    public Result(Element e)
    {   
        String[] s = e.getValue().trim().split("\n");
        
        for (int i = 0; i < s.length; ++i)
            s[i] = s[i].trim();
        
        this.name  = s[0];
        try
        {
            this.score = Integer.parseInt(s[1]);
        }
        catch (NumberFormatException ex) {ex.printStackTrace();}
        
        this.df   = new SimpleDateFormat("yyyy. MMMMM d. HH:mm:ss");
        
        try
        {
            this.date = df.parse(s[2]);
        }
        catch (ParseException ex) {ex.printStackTrace();}
    }
    
    public Element write()
    {
        Element e = new Element("result");
        
        Element n = new Element("name");
        n.appendChild(name);
        
        Element p = new Element("score");
        p.appendChild(Integer.toString(this.score));
        
        Element d = new Element("date");
        d.appendChild(df.format(date));
        
        e.appendChild(n);
        e.appendChild(p);
        e.appendChild(d);
        
        return e;
    }
    
    public Object[] getTableRowFormat(int place)
    {
        return new Object[]{new Integer(place), name, new Integer(score), getFormattedDate()};
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getscore()
    {
        return score;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public String getFormattedDate()
    {
        return df.format(date);
    }
    
    public static class ResultComparator implements Comparator
    {
        @Override
        public int compare(Object o1, Object o2) 
        {
            Result p1 = (Result) o1;
            Result p2 = (Result) o2;
            
            return (p2.getscore() - p1.getscore());
        }
    }
}
