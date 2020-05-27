package view.components;

import controller.STM;
import controller.helper.Resolution;
import controller.helper.Validation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.MainView;
import view.pages.CRUDView;

public class DialogSTM extends JFrame {
    /*
    TYPE 0 = JUST ALERT BUTTONS(ACEPT)
    TYPE 1 = BUTTONS(ACEPT,CANCEL)
    */
    private int type = 0;
    private final String[] menssages = {"","","","","","","","","","","","","","","","","","","","","","",""};
    private Point size = Resolution.getInstance().getSizeWindowsFrame(35, 5);
    
    private final JPanel jpanel1 = new JPanel();
    private final JLabel jlabel1 = new JLabel();
    private JPanel buttonPanel1 = new JPanel();
    private JPanel buttonPanel2 = new JPanel();
    private Method[] methods;
    private Object[] objects;
    
    //private final ImageIcon ICONALERTA32 =  new ImageIcon(getClass().getResource("/view/img/alerta32x32.png"));
    private final ImageIcon ICONALERTA64 =  new ImageIcon(getClass().getResource("/view/img/alerta64x64.png"));
    //private final ImageIcon ICONALERTA128 =  new ImageIcon(getClass().getResource("/view/img/alerta128x128.png"));
    
    public DialogSTM(int type, String menssage) {
        super("Alerta");
        this.type = type;
        this.menssages[0] = menssage;
        initComponents();
    }
    
    public DialogSTM(int type, ArrayList<String> menssages, Method[] methods, Object[] objects) {
        super("Alerta");
        this.type = type;
        this.methods = methods;
        this.objects = objects;
        int count = 0;
        for(String menssage : menssages){
            this.menssages[count] = "* " + menssage;
            count++;
        }
        initComponents();
    }    
    
    public DialogSTM(Validation validate) {
        super("Alerta");
        this.type = 0;
        int count = 0;
        for(String annotation : validate.getAnnotations()){
            this.menssages[count] = "* " + annotation;
            count++;
        }
        initComponents();
    }    

    public void initComponents() {
        
        //FRAME
        this.setLocation(Resolution.getInstance().getCenterResolution(size));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        
        //PANEL
        jpanel1.setBackground(new Color(255,255,150));
        jpanel1.setLocation(0, 0);
        jpanel1.setSize(Resolution.getInstance().getDimension(size));
        jpanel1.setLayout(null);
        this.add(jpanel1);
        
        //ICON
        jlabel1.setIcon(ICONALERTA64);
        jlabel1.setLocation(0, 0);
        jlabel1.setSize(size.x, 120);
        jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel1);
        
        //LABEL - ANNOTATIONS
        int countMenssage = 0;
        for(String menssage : menssages){
            JLabel jlabelMenssage = new JLabel(menssage);
            jlabelMenssage.setFont(new Font("Lucida Sans Unicode", 0, 16));
            jlabelMenssage.setLocation(20, 120 + countMenssage * 20);
            jlabelMenssage.setSize(size.x,20);
            jpanel1.add(jlabelMenssage);
            countMenssage++;
            if(menssage.isEmpty())
            {
                break;
            }
        }
        
        Point buttonSize = new Point(size.x/3,size.x/3/4);
        if(type == 0){
            //BUTTON - OK
            buttonPanel1 = new ButtonSTM().getButtonSTM("OK", 0,1,Color.GREEN, buttonSize);
            buttonPanel1.setLocation((size.x/2-size.x/3/2), 120 + countMenssage * 20);
            buttonPanel1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {                
                    dispose();
                }});
            jpanel1.add(buttonPanel1); 
            
        }else if( type == 1){
            
            //BUTTONS - ACCEPT - CANCEL
            Point locationButton1 = new Point((size.x-(2*buttonSize.x))/3,120 + countMenssage * 20);
            buttonPanel1 = new ButtonSTM().getButtonSTM("Aceitar", 0,1,Color.GREEN, buttonSize);
            buttonPanel1.setLocation(locationButton1);
            buttonPanel1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    try {
                        methods[0].invoke(null,objects[0]);
                        methods[1].invoke(null);
                        JPanel jpanel = new CRUDView((String) objects[1],MainView.getInstance().getSizeCRUD());
                        methods[2].invoke(null,jpanel);
                    } catch (Exception ex) {
                        System.out.println("ERRO: " + ex);;
                    }
                    dispose();
                }});
            jpanel1.add(buttonPanel1);
            
            //BUTTONS - ACCEPT - CANCEL
            Point locationButton2 = new Point(((size.x-(2*buttonSize.x))/3)*2+buttonSize.x,120 + countMenssage * 20);
            buttonPanel2 = new ButtonSTM().getButtonSTM("Cancelar", 0,1,Color.RED, buttonSize);
            buttonPanel2.setLocation(locationButton2);
            buttonPanel2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    dispose();
                }});
            jpanel1.add(buttonPanel2);             
    
        }
        
        size = new Point(size.x, 180 + countMenssage * 20 + size.x/3/4);

        this.setSize(Resolution.getInstance().getDimension(size));
        this.setLocation(Resolution.getInstance().getCenterResolution(size));
        jpanel1.setSize(Resolution.getInstance().getDimension(size));
        this.repaint();
        jpanel1.repaint();
        
    }
    
}
