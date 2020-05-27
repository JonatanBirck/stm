package view;

import com.sun.glass.events.KeyEvent;
import controller.STM;
import controller.helper.Resolution;
import controller.helper.Validation;
import controller.manager.UserManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.User;
import view.components.ButtonSTM;
import view.components.FieldSTM;
import view.components.RoundedPanel;

public class LoginView extends JFrame{
    
    private final Point SIZEFRAME = Resolution.getInstance().getSizeWindowsFrame(27,60);
    
    private final ImageIcon EXITICON16 = new ImageIcon(getClass().getResource("/view/img/close16x16.png"));
    private final ImageIcon EXITICONRED16 = new ImageIcon(getClass().getResource("/view/img/closered16x16.png"));  
    
    private final JPanel marginOutPainel = new RoundedPanel(20, Color.WHITE);
    private final JPanel marginPainel = new RoundedPanel(10, new Color(220,220,220));
    private final JPanel marginInPainel = new RoundedPanel(10, Color.WHITE);  
    private final JLabel exitLabel = new JLabel();  
    private final JLabel logoIconLabel = new JLabel();
    private final JLabel titleLabel = new JLabel("Login");
    private final JLabel subtitleLabel = new JLabel("Acessar Simple Task Manager"); 
    private FieldSTM loginPanel = new FieldSTM();
    private FieldSTM passwordPanel = new FieldSTM();
    
    public LoginView(){
        initComponents();
    }
    
    public void initComponents(){
        
        //FRAME
        this.setLocation(Resolution.getInstance().getCenterResolution(SIZEFRAME));
        this.setSize(Resolution.getInstance().getSizeFrame(SIZEFRAME,100,100));
        this.setUndecorated(true);
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeyPressed(evt);
            }
        });
                
        //marginOutPainel
        marginOutPainel.setOpaque(false);
        marginOutPainel.setLayout(null);
        this.add(marginOutPainel);
        
        //marginPainel      
        marginPainel.setOpaque(false);
        Point marginPainelSizeMargin = Resolution.getInstance().getPointMarginX(SIZEFRAME, 3);
        marginPainel.setLocation(marginPainelSizeMargin);
        Point marginPainelSizeMarginSub = new Point(marginPainelSizeMargin.x*2,marginPainelSizeMargin.y*2);
        Point marginPainelSizePoint = Resolution.getInstance().subtractPoint(SIZEFRAME, marginPainelSizeMarginSub);
        marginPainel.setSize(Resolution.getInstance().getDimension(marginPainelSizePoint));
        marginPainel.setLayout(null);
        marginOutPainel.add(marginPainel);
        
        //marginInPainel
        marginInPainel.setOpaque(false);
        Point marginInPainelSizeMargin = Resolution.getInstance().getPointMarginX(SIZEFRAME, 0.45);
        marginInPainel.setLocation(marginInPainelSizeMargin);
        Point marginInPainelSizeMarginSub = new Point(marginInPainelSizeMargin.x*2,marginInPainelSizeMargin.y*2);
        Point marginInPainelSizePoint = Resolution.getInstance().subtractPoint(marginPainelSizePoint, marginInPainelSizeMarginSub);
        marginInPainel.setSize(Resolution.getInstance().getDimension(marginInPainelSizePoint));
        marginInPainel.setLayout(null);
        marginPainel.add(marginInPainel);
        marginInPainel.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeyPressed(evt);
            }
        });
        
        //closeOption   
        exitLabel.setIcon(EXITICON16);
        Point closeOptionLocation = new Point(marginInPainelSizePoint.x-28,12);
        exitLabel.setLocation(closeOptionLocation);
        exitLabel.setSize(16,16);
        marginInPainel.add(exitLabel);
        exitLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {               
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent evt) {
                exitLabel.setIcon(EXITICONRED16);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                exitLabel.setIcon(EXITICON16);
            }
        });
        
        //iconLabel
        logoIconLabel.setIcon(STM.getInstance().getIcon(128));
        logoIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoIconLabel.setLocation(0,0);
        logoIconLabel.setSize(marginInPainelSizePoint.x,(int) (marginInPainelSizePoint.y*0.3));
        marginInPainel.add(logoIconLabel);
                
        //titleLabel
        titleLabel.setFont(new Font("Lucida Sans Unicode", 1, 24)); 
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setLocation(0, (int) (marginInPainelSizePoint.y*0.25));
        titleLabel.setSize(marginInPainelSizePoint.x, 28);
        marginInPainel.add(titleLabel);      
        titleLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeyPressed(evt);
            }
        });
             
        //subtitleLabel  
        subtitleLabel.setFont(new Font("Lucida Sans Unicode", 0, 12)); 
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setLocation(0, (int) (marginInPainelSizePoint.y*0.27+28));
        subtitleLabel.setSize(marginInPainelSizePoint.x, 16);                
        marginInPainel.add(subtitleLabel);    
        
        //loginPanel        
        Point loginPanelPointX = Resolution.getInstance().getPointRezise(marginInPainelSizePoint, 80);
        Point loginPanelPointY = Resolution.getInstance().getPointRezise(marginInPainelSizePoint, 13);     
        Point loginFieldSize = new Point(loginPanelPointX.x,loginPanelPointY.y);
        loginPanel = new FieldSTM("Login *", loginFieldSize);
        Point loginPanelLocationX = Resolution.getInstance().getPointRezise(marginInPainelSizePoint, 10);
        loginPanel.setLocation(loginPanelLocationX.x, (int) (marginInPainelSizePoint.y*0.42));
        marginInPainel.add(loginPanel);  
        loginPanel.getField().addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeyPressed(evt);
            }
        });
        
        //passwordPanel        
        Point passwordPanelPointX = Resolution.getInstance().getPointRezise(marginInPainelSizePoint, 80);
        Point passwordPanelPointY = Resolution.getInstance().getPointRezise(marginInPainelSizePoint, 13);     
        Point passwordFieldSize = new Point(passwordPanelPointX.x,passwordPanelPointY.y);
        passwordPanel = new FieldSTM("Senha *", 1, passwordFieldSize);
        Point passwordPanelLocationX = Resolution.getInstance().getPointRezise(marginInPainelSizePoint, 10);
        passwordPanel.setLocation(passwordPanelLocationX.x, (int) (marginInPainelSizePoint.y*0.44+loginPanelPointY.y));
        marginInPainel.add(passwordPanel); 
        passwordPanel.getField().addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeyPressed(evt);
            }
        });
        
        //buttonLogin
        Point buttonX = Resolution.getInstance().getPointRezise(marginInPainelSizePoint, 24);
        Point buttonY = Resolution.getInstance().getPointRezise(marginInPainelSizePoint, 10);      
        Point buttonSize = new Point(buttonX.x,buttonY.y);
        JPanel buttonPanel = new ButtonSTM().getButtonSTM("Entrar", 0,10, buttonSize);
        Point buttonPanelLocationX = Resolution.getInstance().getPointRezise(marginInPainelSizePoint, 90);
        buttonPanel.setLocation(buttonPanelLocationX.x-buttonSize.x, (int) (marginInPainelSizePoint.y*0.76));      
        buttonPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                login();
            }});
                
        marginInPainel.add(buttonPanel);  
        
        titleLabel.setFocusable(true);
 
    }
    
    private void login(){
        String login = (loginPanel.getTextField() == null ? " " : loginPanel.getTextField());
        String password = (passwordPanel.getTextField() == null ? " " : passwordPanel.getTextField());
        Validation loginValidation = UserManager.getInstance().authenticateLogin(login, password);
        if(loginValidation.isValidated()){
            System.out.println("PASS - LOGIN");
            User userLogged = UserManager.getInstance().getUserByLogin(login);
            UserManager.getInstance().setUserLogged(userLogged);
            MainView mainView = MainView.getInstance();
            dispose();
            mainView.setVisible(true);
        }else{
        ArrayList alerts = loginValidation.getAnnotations();
                    
        for(Object alert : alerts)
        {
            String alert1 = (String) alert;
                        
            if(alert1.equals("O login está incorreto")){
                loginPanel.alertState(true, true);
            }
            if(alert1.equals("A senha está incorreto")){
                passwordPanel.alertState(true, true);
            }
        }
        }           
    }
    
    //keyEvents
    private void KeyPressed(java.awt.event.KeyEvent evt) {                                     
        if( evt.getKeyCode() == KeyEvent.VK_ENTER )
        {
            login();
        }
        if( evt.getKeyCode() == KeyEvent.VK_ESCAPE )
        {
            System.exit(0);
        }
    }  
 
}
