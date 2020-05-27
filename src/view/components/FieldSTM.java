package view.components;

import controller.helper.Resolution;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FieldSTM extends JPanel {
    
    private String text;
    private ImageIcon icon;
    private Point size = new Point();
    /*
    TYPE = 0 NORMAL
    TYPE = 1 PASSWORD
    TYPE = 2 SELECTOR
    */
    private int type = 0;
    private String classNameOrigin = "";
    private String classNameDestiny = "";
     
    private final JPanel jpanel2 = new RoundedPanel(18); 
    private final JPanel jpanel3 = new RoundedPanel(20);
    private final JLabel iconLabel = new JLabel();
    private final JTextField inputField = new JTextField();
    private final JPasswordField inputFieldPassword = new JPasswordField();
    private final JTextField inputFieldSelector = new JTextField();
    
    private final ImageIcon ICON_SEARCH = new ImageIcon(getClass().getResource("/view/img/procurar24x24.png"));
    private final ImageIcon ICON_HIDE = new ImageIcon(getClass().getResource("/view/img/hide24x24.png"));
    private final ImageIcon ICON_OPEN = new ImageIcon(getClass().getResource("/view/img/eye24x24.png"));
    private ImageIcon PASSWORD_ICON = ICON_HIDE;

    public FieldSTM(){
        
    }
    
    public FieldSTM(String placeHolder, Point size){
        this.text = placeHolder;
        this.size = size;
       
        createLabel(size);
    }
    
    public FieldSTM(String placeHolder,ImageIcon icon, Point size){
        this.text = placeHolder;
        this.icon = icon;
        this.size = size;

        createLabel(size);  
    }
    
    public FieldSTM(String placeHolder,int type, Point size){
        this.text = placeHolder;
        this.type = type;
        this.size = size;
        
        createLabel(size); 
    }    
    
    public FieldSTM(String placeHolder,int type, Point size,String classNameOrigin,String classNameDestiny){
        this.text = placeHolder;
        this.type = type;
        this.size = size;
        this.classNameOrigin = classNameOrigin;
        this.classNameDestiny = classNameDestiny;
        
        createLabel(size); 
    }
    
    private JPanel createLabel(Point size){
        
        //PAINELOUT
        this.setOpaque(false);
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setLayout(null);
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        
        //PAINEL CENTRAL
        jpanel2.setBackground(Color.WHITE);
        jpanel2.setOpaque(false);
        Point jpanel2location = Resolution.getInstance().getPointMarginX(size, 1);
        jpanel2.setLocation(jpanel2location);
        Dimension jpanel2sub = new Dimension((jpanel2location.x*2),(jpanel2location.y*2));
        Dimension sizeJpanel2 = Resolution.getInstance().subtractDimension(size, jpanel2sub);
        jpanel2.setSize(sizeJpanel2);
        jpanel2.setLayout(null);
        this.add(jpanel2);
        
        //PAINELMARGIN
        jpanel3.setBackground(Color.GRAY);
        jpanel3.setOpaque(false);
        Point jpanel3location = Resolution.getInstance().getPointMarginX(size, 0.65);
        jpanel3.setLocation(jpanel3location);
        Dimension jpanel3sub = new Dimension((jpanel3location.x*2),(jpanel3location.y*2));
        Dimension sizeJpanel3 = Resolution.getInstance().subtractDimension(size, jpanel3sub);
        jpanel3.setSize(sizeJpanel3);
        jpanel3.setLayout(null);
        this.add(jpanel3);  
        
        //ICON
        Dimension sizeIconLabel = new Dimension(0,0);
        if(icon != null){
            iconLabel.setIcon(icon);
            iconLabel.setLocation(0,0);     
            sizeIconLabel = new Dimension((int) (sizeJpanel2.width*0.15),sizeJpanel2.height);
            iconLabel.setSize(sizeIconLabel);
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jpanel2.add(iconLabel);
        }
        
        //TEXT - PLACEHOLDER
        if(type == 0)
        {
            inputField.setFont(new Font("Lucida Sans Unicode", 0, 14));
            inputField.setForeground(new Color(120, 120, 120));
            inputField.setText(text);
            inputField.setBorder(null);
            inputField.setLocation(sizeIconLabel.width + (int) (sizeJpanel2.width*0.03),1);
            Dimension sizeText = new Dimension((int)(sizeJpanel2.width*0.94)-sizeIconLabel.width,sizeJpanel2.height-3);
            inputField.setSize(sizeText);
            if(icon != null){
                inputField.setFocusable(false);
            }
            inputField.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent evt) {
                    String textInputField = inputField.getText();
                    if (textInputField.equals(text)) {
                        inputField.setText("");
                    }

                    alertState(true,false);
                }
                public void focusLost(FocusEvent evt) {
                    String textInputField = inputField.getText();
                    if (textInputField.equals("")) {
                        inputField.setText(text);
                    }

                    alertState(false,false);
                }
            });
            inputField.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    jpanel2.setBackground(new Color(245, 245, 245));
                    inputField.setBackground(new Color(245, 245, 245));
                }
                public void mouseExited(MouseEvent evt) {
                    jpanel2.setBackground(new Color(255, 255, 255));
                    inputField.setBackground(new Color(255, 255, 255));
                }
            });
            jpanel2.add(inputField); 
        }
        
        //PASSWORDFIELD
        Dimension sizeTextPassword = new Dimension((int)(sizeJpanel2.width*0.94)-sizeIconLabel.width,sizeJpanel2.height-3);
        if(type == 1)
        {
            inputFieldPassword.setFont(new Font("Lucida Sans Unicode", 0, 14));
            inputFieldPassword.setForeground(new Color(120, 120, 120));
            inputFieldPassword.setText(text);
            inputFieldPassword.setEchoChar((char) 0);
            inputFieldPassword.setBorder(null);
            inputFieldPassword.setLocation(sizeIconLabel.width + (int) (sizeJpanel2.width*0.03),1);
            inputFieldPassword.setSize(sizeTextPassword);
            if(icon != null){
                inputFieldPassword.setFocusable(false);
            }
            inputFieldPassword.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent evt) {
                    String textInputField = new String(inputFieldPassword.getPassword());
                    if (textInputField.equals(text)) {
                        inputFieldPassword.setText("");
                        inputFieldPassword.setEchoChar('*');
                    }

                    alertState(true,false);
                }
                public void focusLost(FocusEvent evt) {
                    String textInputField = new String(inputFieldPassword.getPassword());
                    if (textInputField.equals("")) {
                        inputFieldPassword.setText(text);
                        inputFieldPassword.setEchoChar((char) 0);
                    }
                    alertState(false,false);
                }
            });
            inputFieldPassword.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    jpanel2.setBackground(new Color(245, 245, 245));
                    inputFieldPassword.setBackground(new Color(245, 245, 245));
                }
                public void mouseExited(MouseEvent evt) {
                    jpanel2.setBackground(new Color(255, 255, 255));
                    inputFieldPassword.setBackground(new Color(255, 255, 255));
                }
            });
            jpanel2.add(inputFieldPassword); 
        }
        
        
        //PASSWORDLABEL
        if(type == 1 )
        {
            JLabel iconPasswordLabel = new JLabel();
            iconPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
            iconPasswordLabel.setIcon(PASSWORD_ICON);
            iconPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            iconPasswordLabel.setFocusable(false);
            iconPasswordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            sizeIconLabel = new Dimension((int) (sizeJpanel2.width*0.15),sizeJpanel2.height);
            iconPasswordLabel.setLocation(sizeJpanel2.width-sizeIconLabel.width, 0);
            iconPasswordLabel.setSize(sizeIconLabel);
            jpanel2.add(iconPasswordLabel);
            Dimension newSizeText = new Dimension(sizeTextPassword.width-sizeIconLabel.width,sizeTextPassword.height);
            inputFieldPassword.setSize(newSizeText);
            iconPasswordLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (PASSWORD_ICON.equals(ICON_HIDE)) {
                        PASSWORD_ICON = ICON_OPEN;
                        inputFieldPassword.setEchoChar((char) 0);
                    } else {
                        PASSWORD_ICON = ICON_HIDE;
                        inputFieldPassword.setEchoChar('*');
                    }
                    iconPasswordLabel.setIcon(PASSWORD_ICON);
                }
            });
        } 
        
        //SELECTORFIELD
        if(type == 2)
        {
            inputFieldSelector.setFont(new Font("Lucida Sans Unicode", 0, 14));
            inputFieldSelector.setForeground(new Color(120, 120, 120));
            inputFieldSelector.setEditable(false);
            inputFieldSelector.setBackground(Color.WHITE);
            inputFieldSelector.setText(text);
            inputFieldSelector.setBorder(null);
            inputFieldSelector.setLocation(sizeIconLabel.width + (int) (sizeJpanel2.width*0.03),1);
            Dimension sizeInputFieldSelector = new Dimension((int)(sizeJpanel2.width*0.94)-sizeIconLabel.width-25,sizeJpanel2.height-3);
            inputFieldSelector.setSize(sizeInputFieldSelector);
            if(icon != null){
                inputFieldSelector.setFocusable(false);
            }
            inputFieldSelector.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent evt) {
                    alertState(true,false);
                }
                public void focusLost(FocusEvent evt) {
                    alertState(false,false);
                }
            });
            inputFieldSelector.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    jpanel2.setBackground(new Color(245, 245, 245));
                    inputFieldSelector.setBackground(new Color(245, 245, 245));
                }
                public void mouseExited(MouseEvent evt) {
                    jpanel2.setBackground(new Color(255, 255, 255));
                    inputFieldSelector.setBackground(new Color(255, 255, 255));
                }
            });
            jpanel2.add(inputFieldSelector); 
        }
        
        
        //SELECTORLABEL
        if(type == 2)
        {
            JLabel iconSelectorLabel = new JLabel();
            iconSelectorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            iconSelectorLabel.setIcon(ICON_SEARCH);
            iconSelectorLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            iconSelectorLabel.setFocusable(false);
            iconSelectorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            sizeIconLabel = new Dimension((int) (sizeJpanel2.width*0.15),sizeJpanel2.height);
            iconSelectorLabel.setLocation(sizeJpanel2.width-sizeIconLabel.width, 0);
            iconSelectorLabel.setSize(sizeIconLabel);
            jpanel2.add(iconSelectorLabel);
            Dimension newSizeText = new Dimension(sizeTextPassword.width-sizeIconLabel.width,sizeTextPassword.height);
            inputFieldPassword.setSize(newSizeText);
            iconSelectorLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    ClassSelector selector = new ClassSelector(classNameOrigin,classNameDestiny);
                    selector.setVisible(true);
                }
            });
        }       

        return this;
   
    }
    
    public void alertState(boolean alert, boolean error){
        if(alert){
            jpanel3.setLocation(0,0);
            jpanel3.setSize(Resolution.getInstance().getDimension(size));
            if(error){
                jpanel3.setBackground(Color.RED);
            }
            else{
                jpanel3.setBackground(Color.BLUE);
            }
        }  
        else
        {
            Point jpanel3location = Resolution.getInstance().getPointMarginX(size, 0.65);
            jpanel3.setLocation(jpanel3location);
            Dimension jpanel3sub = new Dimension((jpanel3location.x*2),(jpanel3location.y*2));
            Dimension sizeJpanel3 = Resolution.getInstance().subtractDimension(size, jpanel3sub);
            jpanel3.setSize(sizeJpanel3);
            jpanel3.setBackground(Color.GRAY);
        }
    }
    
    public String getTextField(){
        String text = "";
        if(type == 0){
           text = inputField.getText();
        }else{
            text = new String (inputFieldPassword.getPassword());
        }
        return text;
    }
    
    
    public JTextField getField(){
        if(type == 0){
            return inputField;
        }
        if(type == 1){
            return inputFieldPassword;
        }
        return null;
    }
    
    public void setTextField(String text){
        inputField.setText(text);
        inputFieldSelector.setText(text);
        
    }
}
