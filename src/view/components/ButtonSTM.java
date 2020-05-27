package view.components;

import controller.helper.Resolution;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ButtonSTM {
    
    private String placeHolder = "";
    private int type = 0;
    private int borderRadius = 10;
    private Color color = new Color(26, 115, 232);
    private Point size;
    
    private final JPanel jpanel = new JPanel();
    private JPanel jpanel2 = new JPanel();
    private JPanel jpanel3 = new JPanel();
    private final JLabel placeHolderLabel = new JLabel();
    
    public JPanel getButtonSTM(String placeHolder,int type,int borderRadius, Point size){
        this.placeHolder = placeHolder;
        this.type = type;
        this.borderRadius = borderRadius;
        this.size = size;
        
        JPanel buttonResult = createButton();  
        
        return buttonResult;
    }
    
    public JPanel getButtonSTM(String placeHolder,int type,int borderRadius, Color color, Point size){
        this.placeHolder = placeHolder;
        this.type = type;
        this.borderRadius = borderRadius;
        this.color = color;
        this.size = size;
        
        JPanel buttonResult = createButton();  
        
        return buttonResult;
    }
    
    
    private JPanel createButton(){
        
        //PAINELOUT
        jpanel.setOpaque(false);
        jpanel.setSize(Resolution.getInstance().getDimension(size));
        jpanel.setLayout(null);
        jpanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        
        //PAINEL CENTRAL
        jpanel2 = new RoundedPanel(borderRadius);
        jpanel2.setBackground(color);
        jpanel2.setOpaque(false);
        Point jpanel2location = Resolution.getInstance().getPointMarginX(size, 1);
        jpanel2.setLocation(jpanel2location);
        Dimension jpanel2sub = new Dimension((jpanel2location.x*2),(jpanel2location.y*2));
        Dimension sizeJpanel2 = Resolution.getInstance().subtractDimension(size, jpanel2sub);
        jpanel2.setSize(sizeJpanel2);
        jpanel2.setLayout(null);
        jpanel.add(jpanel2);   
        
        //PAINELMARGIN
        jpanel3 = new RoundedPanel(borderRadius);
        jpanel3.setBackground(Color.GRAY);
        jpanel3.setOpaque(false);
        Point jpanel3location = Resolution.getInstance().getPointMarginX(size, 0.65);
        jpanel3.setLocation(jpanel3location);
        Dimension jpanel3sub = new Dimension((jpanel3location.x*2),(jpanel3location.y*2));
        Dimension sizeJpanel3 = Resolution.getInstance().subtractDimension(size, jpanel3sub);
        jpanel3.setSize(sizeJpanel3);
        jpanel3.setLayout(null);
        jpanel.add(jpanel3);          
        
        //PLACEHOLDER
        placeHolderLabel.setText(placeHolder);
        placeHolderLabel.setFont(new Font("Lucida Sans Unicode", 1, 14));
        placeHolderLabel.setForeground(Color.WHITE);
        placeHolderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        placeHolderLabel.setBorder(null);
        placeHolderLabel.setLocation(0,0);
        placeHolderLabel.setSize(sizeJpanel2);
        placeHolderLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jpanel2.add(placeHolderLabel);
        
        return jpanel;
    }
    
}
