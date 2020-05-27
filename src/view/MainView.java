package view;

import controller.STM;
import controller.helper.Resolution;
import controller.manager.FunctionManager;
import controller.manager.UserManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.components.ButtonSTM;
import view.components.OptionMenuSTM;
import view.pages.CRUDView;;

public class MainView extends JFrame {
    
    private static MainView instance = null;
    private final Point SIZEFRAME = Resolution.getInstance().getSizeWindowsFrame(100,100);
    
    private final JPanel bodyPanel = new JPanel();
    private final JPanel menuPanel = new JPanel();
    private final JLabel userLabel = new JLabel();
    private final JLabel nameUser = new JLabel();
    private final JLabel functionUser = new JLabel();
    private final JLabel myTaskLabel = new JLabel("Minhas Tarefas");
    private final JLabel reportsLabel = new JLabel("Task Manager");
    private final JLabel configLabel = new JLabel("Configurações");
    private final JLayeredPane pages = new JLayeredPane();
    private Point sizeCRUD;
    
    
    public MainView(){
        super("MainView");
        try{
            initComponents();
        }
        catch(Exception e)
        {
            System.out.println("OPA: " + e);
        } 
    }
    
    public static MainView getInstance() {
        if (instance == null) {
            instance = new MainView();
        }
        return instance;
    }
    
    private void initComponents(){
        
        this.setLocation(0,0);
        this.setSize(Resolution.getInstance().getDimension(SIZEFRAME));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
        //this.setUndecorated(false);
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        
        //bodyPanel
        bodyPanel.setBackground(new Color(200,200,200));
        bodyPanel.setLayout(null);
        this.add(bodyPanel);
        
        //menuPanel
        Point menuPanelSize = Resolution.getInstance().getPointMarginX(SIZEFRAME, 20);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBounds(0, 0, menuPanelSize.x, Short.MAX_VALUE);  
        menuPanel.setLayout(null);
        bodyPanel.add(menuPanel);
        
        //iconUser
        userLabel.setIcon(STM.getInstance().getIcon(128));
        Point userLabelLocation = new Point(0,0);
        Dimension userLabelSize = new Dimension(menuPanelSize.x,menuPanelSize.x-110);
        userLabel.setLocation(userLabelLocation);
        userLabel.setSize(userLabelSize);
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuPanel.add(userLabel);
        
        //nameUser  
        nameUser.setText(UserManager.getInstance().getUserLogged().getName());
        nameUser.setFont(new Font("Lucida Sans Unicode", 1, 16)); 
        nameUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Point nameUserLocation = new Point(0,userLabelSize.height-40);
        Dimension nameUserSize = new Dimension (menuPanelSize.x,20);
        nameUser.setLocation(nameUserLocation);
        nameUser.setSize(nameUserSize);
        menuPanel.add(nameUser);
        
        //functionUser        
        functionUser.setText(FunctionManager.getInstance().getFunction(UserManager.getInstance().getUserLogged().getFunctionId()).getName());
        functionUser.setFont(new Font("Lucida Sans Unicode", 0, 10)); 
        functionUser.setHorizontalAlignment(SwingConstants.CENTER);  
        Point functionUserLocation = new Point(0,nameUserLocation.y+25);
        Dimension functionUserSize = new Dimension (menuPanelSize.x,20);       
        functionUser.setLocation(functionUserLocation);
        functionUser.setSize(functionUserSize);
        menuPanel.add(functionUser);
        
        //MyTask
        
        myTaskLabel.setFont(new Font("Lucida Sans Unicode", 1, 14)); 
        myTaskLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Point myTaskLabelLocation = new Point(Resolution.getInstance().getPointMarginX(menuPanelSize, 15).x,functionUserLocation.y+45);
        Dimension myTaskLabelSize = new Dimension (menuPanelSize.x,16);
        myTaskLabel.setForeground(Color.BLUE);
        myTaskLabel.setLocation(myTaskLabelLocation);
        myTaskLabel.setSize(myTaskLabelSize);
        menuPanel.add(myTaskLabel);
        
        sizeCRUD = new Point(SIZEFRAME.x-menuPanelSize.x,SIZEFRAME.y);
           
        JPanel teste1 = new ButtonSTM().getButtonSTM("EM MANUTENÇÃO",0,1,Color.GRAY, new Point(500,500));        


        //OptionsMyTask
        Point sizeOption = new Point(menuPanelSize.x,(int)(menuPanelSize.x*0.13));
        OptionMenuSTM option1 = new OptionMenuSTM("Todas",sizeOption,new CRUDView("task",1,sizeCRUD));
        option1.setLocation(0,functionUserLocation.y+70);
        menuPanel.add(option1);  
        
        OptionMenuSTM option2 = new OptionMenuSTM("Em Processo",sizeOption,new CRUDView("task",2,sizeCRUD));
        option2.setLocation(0,functionUserLocation.y+70+sizeOption.y);
        menuPanel.add(option2);  
        
        OptionMenuSTM option3 = new OptionMenuSTM("Concluidas",sizeOption,new CRUDView("task",3,sizeCRUD));
        Point sizeOptions3Location = new Point(0,functionUserLocation.y+70+sizeOption.y+sizeOption.y);
        option3.setLocation(sizeOptions3Location);
        menuPanel.add(option3);  
        
        //ReportsLabel
        reportsLabel.setFont(new Font("Lucida Sans Unicode", 1, 14)); 
        reportsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Point reportsLabelLocation = new Point(Resolution.getInstance().getPointMarginX(menuPanelSize, 15).x,sizeOptions3Location.y+sizeOption.y+30);
        Dimension reportsLabelSize = new Dimension (menuPanelSize.x,16);
        reportsLabel.setForeground(Color.BLUE);
        reportsLabel.setLocation(reportsLabelLocation);
        reportsLabel.setSize(reportsLabelSize);
        menuPanel.add(reportsLabel);        
        
        //OptionsReportsLabel
        OptionMenuSTM option4 = new OptionMenuSTM("Relatórios",sizeOption,teste1);
        option4.setLocation(0,reportsLabelLocation.y+25);
        menuPanel.add(option4);  
        
        OptionMenuSTM option5 = new OptionMenuSTM("Consultar Tarefas",sizeOption,new CRUDView("task",0,sizeCRUD));
        option5.setLocation(0,reportsLabelLocation.y+25+sizeOption.y);
        menuPanel.add(option5);
        
        //ConfigLabel
        configLabel.setFont(new Font("Lucida Sans Unicode", 1, 14)); 
        configLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Point configLabelLocation = new Point(Resolution.getInstance().getPointMarginX(menuPanelSize, 15).x,reportsLabelLocation.y+25+sizeOption.y+sizeOption.y+30);
        Dimension configLabelSize = new Dimension (menuPanelSize.x,16);
        configLabel.setForeground(Color.BLUE);
        configLabel.setLocation(configLabelLocation);
        configLabel.setSize(configLabelSize);
        menuPanel.add(configLabel);   
        
        //OptionsReportsLabel
        OptionMenuSTM option6 = new OptionMenuSTM("Usuários",sizeOption,new CRUDView("user",sizeCRUD));
        option6.setLocation(0,configLabelLocation.y+25);
        menuPanel.add(option6); 
        
        OptionMenuSTM option7 = new OptionMenuSTM("Departamentos",sizeOption,new CRUDView("department",sizeCRUD));
        option7.setLocation(0,configLabelLocation.y+25+sizeOption.y);
        menuPanel.add(option7);
        
        OptionMenuSTM option8 = new OptionMenuSTM("Setores",sizeOption,new CRUDView("sector",sizeCRUD));
        option8.setLocation(0,configLabelLocation.y+25+sizeOption.y+sizeOption.y);
        menuPanel.add(option8);  
        
        OptionMenuSTM option9 = new OptionMenuSTM("Funções",sizeOption,new CRUDView("function",sizeCRUD));
        option9.setLocation(0,configLabelLocation.y+25+sizeOption.y+sizeOption.y+sizeOption.y);
        menuPanel.add(option9); 
        
        //Pages
        pages.setLocation(menuPanelSize.x, 0);
        pages.setSize(SIZEFRAME.x-menuPanelSize.x,SIZEFRAME.y);
        pages.setLayout(null);
        bodyPanel.add(pages);

    }
    
    
    //EVENTS
    public void switchPanels(JPanel panel) {
        pages.removeAll();
        pages.add(panel);
        pages.repaint();
        pages.revalidate();
    }
    
    public static void repaintPanels(JPanel jpanel){
        
        MainView.getInstance().pages.removeAll();
        MainView.getInstance().pages.add(jpanel);
        MainView.getInstance().pages.repaint();
        MainView.getInstance().pages.revalidate();
    }
    
    public Point getSizeCRUD(){
        return this.sizeCRUD;
    }
}
