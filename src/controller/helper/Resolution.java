package controller.helper;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class Resolution {
    
    private static Resolution instance = null;
    private static final Point RESOLUTION = getDimensionWindown();
    
    public static Resolution getInstance() 
    {
        if (instance == null) 
        {
            instance = new Resolution();
        }
        return instance;
    }    
    
    public Point getResolution()
    {
        return Resolution.RESOLUTION;
    }
    
    public Point getPointRezise(Point point,double percent){
        
        double xPoint = point.getX();
        double yPoint = point.getY();  
        
        int x = (int) (xPoint*(percent/100));
        int y = (int) (yPoint*(percent/100));
        
        return new Point(x,y);
    }
    
    public Point getPointRezise(Dimension dimension,double percent){
        
        double xDimension = dimension.getWidth();
        double yDimension = dimension.getHeight();  
        
        int x = (int) (xDimension*(percent/100));
        int y = (int) (yDimension*(percent/100));
        
        return new Point(x,y);
    }    
    
    public Dimension getDimensionRezise(Dimension dimension,double percent){
        
        double xDimension = dimension.getWidth();
        double yDimension = dimension.getHeight(); 
        
        int x = (int) (xDimension*(percent/100));
        int y = (int) (yDimension*(percent/100));
        
        return new Dimension(x,y);
    }    
    
    public Dimension getDimensionRezise(Point point,double percent){
        
        double xPoint = point.getX();
        double yPoint = point.getY(); 
        
        int x = (int) (xPoint*(percent/100));
        int y = (int) (yPoint*(percent/100));
        
        return new Dimension(x,y);
    }  
    
    public Point getPointMarginX(Point point,double percent){
       
        double xPoint = point.getX();
        
        int x = (int) (xPoint*(percent/100));
        
        return new Point(x,x);        
    }
    
    public Point getPointMarginX(Dimension dimension,double percent){
       
        double xDimension = dimension.getWidth();
        
        int x = (int) (xDimension*(percent/100));
        
        return new Point(x,x);        
    }   
    
    public Dimension getDimensionMarginX(Dimension dimension,double percent){
       
        double xDimension = dimension.getWidth();
        
        int x = (int) (xDimension*(percent/100));
        
        return new Dimension(x,x);        
    }   
    
    public Point subtractPoint(Point point1, Point point2){
        
        double xPoint1 = point1.getX();
        double yPoint1 = point1.getY(); 
        
        double xPoint2 = point2.getX();
        double yPoint2 = point2.getY(); 
        
        int x = (int) (xPoint1-xPoint2);
        int y = (int) (yPoint1-yPoint2);
        
        return new Point(x,y);        
    }
    
    public Dimension subtractDimension(Dimension dimension1, Dimension dimension2){
        
        double xDimension1 = dimension1.getWidth();
        double yDimension1 = dimension1.getHeight();  
        
        double xDimension2 = dimension2.getWidth();
        double yDimension2 = dimension2.getHeight(); 
        
        int x = (int) (xDimension1-xDimension2);
        int y = (int) (yDimension1-yDimension2);
        
        return new Dimension(x,y);        
    }    
    
    public Dimension subtractDimension(Point point, Dimension dimension){
        
        double xPoint = point.getX();
        double yPoint = point.getY();  
        
        double xDimension = dimension.getWidth();
        double yDimension = dimension.getHeight(); 
        
        int x = (int) (xPoint-xDimension);
        int y = (int) (yPoint-yDimension);
        
        return new Dimension(x,y);        
    }       
    
    public Point getSizeWindowsFrame(double xPercent, double yPercent){
        
        double xWindows = RESOLUTION.getX();
        double yWindows = RESOLUTION.getY();
        
        int x = (int) ((xPercent/100)*xWindows);
        int y = (int) ((yPercent/100)*yWindows);
            
        return new Point(x,y);
    }
    
    public Dimension getSizeFrame(Point point, double xPercent, double yPercent){
        
        double xPoint = point.getX();
        double yPoint = point.getY();
        
        int x = (int) ((xPercent/100)*xPoint);
        int y = (int) ((yPercent/100)*yPoint);
            
        return new Dimension(x,y);
    }
    
    public Dimension getSizeFrame(Dimension dimension, double xPercent, double yPercent){
        
        double xDimension = dimension.getWidth();
        double yDimension = dimension.getHeight();
        
        int x = (int) ((xPercent/100)*xDimension);
        int y = (int) ((yPercent/100)*yDimension);
            
        return new Dimension(x,y);
    }
    
    public Point getPoint(Dimension dimension){
        
        int x = (int) dimension.getWidth();
        int y = (int) dimension.getHeight();
        
        return new Point(x,y);
    }
    
    public Dimension getDimension(Point point){
        
        int x = (int) point.getX();
        int y = (int) point.getY();
        
        return new Dimension(x,y);
    }    
    
    public Point getCenterResolution(Point point){
        
        double xWindows = RESOLUTION.getX();
        double yWindows = RESOLUTION.getY();
        
        double xFrame = point.getX();
        double yFrame = point.getY();
        
        int x = (int) (xWindows/2-xFrame/2);
        int y = (int) (yWindows/2-yFrame/2);
        
        return new Point(x,y);        
    }
    
    private static Point getDimensionWindown()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension windowsDimension = tk.getScreenSize();
        
        int x = (int) windowsDimension.getWidth();
        int y = (int) windowsDimension.getHeight();
        
        return new Point(x,y);
    }
 
}
