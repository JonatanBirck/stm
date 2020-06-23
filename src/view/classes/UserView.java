package view.classes;

import com.sun.glass.events.KeyEvent;
import controller.STM;
import controller.helper.Resolution;
import controller.helper.Validation;
import controller.manager.FunctionManager;
import controller.manager.UserManager;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import model.Function;
import model.User;
import view.MainView;
import view.components.ButtonSTM;
import view.components.DialogSTM;
import view.components.FieldSTM;
import view.pages.MainPageView;

public class UserView extends JFrame {

    private User user = new User();
    private final JPanel jpanel1 = new JPanel();
    private final JLabel jlabel1 = new JLabel();
    private JPanel buttonPanel1 = new JPanel();;
    private JPanel buttonPanel2 = new JPanel();;
    private FieldSTM field1;
    private FieldSTM field2;
    private FieldSTM field3;
    private FieldSTM field4;
    private FieldSTM field5;
    private int functionIdSelected = 0;
    private static UserView instance = null;

    public UserView() {
        super("Usuário");
        functionIdSelected = user.getFunctionId();
        initComponents();
        this.setVisible(true);
        jlabel1.requestFocus();
        instance = this;
    }       
    
    public UserView(int iduser) {
        super("Usuário");
        this.user = UserManager.getInstance().getUser(iduser);
        functionIdSelected = user.getFunctionId();
        initComponents();
        this.setVisible(true);
        jlabel1.requestFocus();
        instance = this;
    }  
    
    public static UserView getInstance() {
        return instance;
    }    
    
    private void initComponents() {

        //FRAME
        Point sizeWindowns = Resolution.getInstance().getResolution();
        Point size = new Point((int) (sizeWindowns.x / 4), (int) (sizeWindowns.y / 1.4));
        this.setLocation(Resolution.getInstance().getCenterResolution(size));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                register();
            }
        });
        
        //PANEL
        jpanel1.setLocation(0,0);
        jpanel1.setSize(Resolution.getInstance().getDimension(size));
        jpanel1.setLayout(null);
        this.add(jpanel1);
        jpanel1.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if( evt.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    register();
                }                
                if( evt.getKeyCode() == KeyEvent.VK_ESCAPE )
                {
                    dispose();
                }                
            }
        });        
        
        //ICONUSER
        jlabel1.setIcon(user.getIcon(64));
        jlabel1.setLocation(0, 0);
        jlabel1.setSize(size.x, 120);
        jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel1);
        
        //OPTION - LOGIN
        Point sizeField =  new Point((int)(size.x*0.80),(int)(size.x*0.12));
        field1 = new FieldSTM("Login *", sizeField);
        Point locationField1 = new Point((int)((size.x-sizeField.x)/2)-5,130);
        field1.setLocation(locationField1);
        jpanel1.add(field1);
       
        //OPTION - SENHA
        field2 = new FieldSTM("Senha *",1, sizeField);
        field2.setLocation(locationField1.x, locationField1.y + 10 + (int)(size.x*0.12));
        jpanel1.add(field2);
        
        //OPTION - NOME
        field3 = new FieldSTM("Nome *", sizeField);
        field3.setLocation(locationField1.x, locationField1.y + 20 + (int)((size.x*0.12)*2));
        jpanel1.add(field3);

        //OPTION - EMAIL
        field4 = new FieldSTM("E-mail *", sizeField);
        field4.setLocation(locationField1.x, locationField1.y + 30 + (int)((size.x*0.12)*3));
        jpanel1.add(field4);        
        
        //OPTION - FUNCTION
        field5 = new FieldSTM("Função",2,sizeField,"user","function");
        field5.setLocation(locationField1.x, locationField1.y + 40 + (int)((size.x*0.12)*4));
        jpanel1.add(field5);  

        //BUTTON - REGISTER
        String firstButtonName = "Registrar";
        boolean newUser = true;
        if(user.getId() != 0){
            newUser = false;
            firstButtonName = "Registrar Alterações";
        }
        buttonPanel1 = new ButtonSTM().getButtonSTM(firstButtonName, 0,1, sizeField);
        Point buttonPanel1Location = new Point(locationField1.x, locationField1.y + 50 + (int)((size.x*0.12)*5));
        buttonPanel1.setLocation(buttonPanel1Location);
        buttonPanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                register();
            }});
        jpanel1.add(buttonPanel1);  
        
        //BUTTON - DELETE
        if(!newUser)
        {
            buttonPanel2 = new ButtonSTM().getButtonSTM("Deletar", 0,1,Color.RED, sizeField);
            buttonPanel1Location = new Point(buttonPanel1Location.x, buttonPanel1Location.y + 10 + (int)((size.x*0.12)));
            buttonPanel2.setLocation(buttonPanel1Location);
            buttonPanel2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    try {
                        delete();
                    } catch (Exception ex) {
                        Logger.getLogger(UserView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }});
            jpanel1.add(buttonPanel2);  
        }
       
        size = new Point(size.x, buttonPanel1Location.y + 20 + sizeField.y + (int)((size.x*0.12)));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.repaint();
        
        
        if(user.getId() != 0)
        {
            field1.setTextField(user.getLogin());
            field2.setTextField(user.getPassword());
            
            JPasswordField password = (JPasswordField) field2.getField();
            password.setEchoChar('*');

            field3.setTextField(user.getName());
            field4.setTextField(user.getEmail()); 
            
            if(user.getFunctionId() != 0)
            {
                String functionName = FunctionManager.getInstance().getFunction(user.getFunctionId()).getName();
                field5.setTextField(functionName);
            }
        }
    }

    private void register() {
        
        String login = field1.getTextField();
        String password = field2.getTextField();
        String name = field3.getTextField();
        String email = field4.getTextField();
        int functionId = functionIdSelected;
                
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setFunctionId(functionId);
        
        if(user.getId() != 0){
            Validation validate = UserManager.getInstance().authenticateUpdate(user);
            if(validate.isValidated()){
                UserManager.getInstance().updateUser(user);
                JPanel jpanel = new MainPageView("user",MainView.getInstance().getSizeCRUD());
                MainView.getInstance().repaintPanels(jpanel);    
                this.dispose();
            }
            else
            {
                DialogSTM dialog = new DialogSTM(validate);
                dialog.setVisible(true);
            }
        }  
        else
        {
            Validation validate = UserManager.getInstance().authenticateNewUser(user);
            if(validate.isValidated()){
                UserManager.getInstance().addUser(user);
                JPanel jpanel = new MainPageView("user",MainView.getInstance().getSizeCRUD());
                MainView.getInstance().repaintPanels(jpanel);  
                this.dispose();
            }
            else
            {               
                DialogSTM dialog = new DialogSTM(validate);
                dialog.setVisible(true);
            }
        }

    }

    private void delete() throws Exception {
        ArrayList<String> messages = new ArrayList();
        messages.add("Você tem certeza que quer excluir o Usuário:");
        messages.add(user.getName());
        
        Method[] methods = {null,null,null};
        Object[] objects = {null,null};
        
        methods[0] = UserManager.class.getDeclaredMethod("deleteUser", User.class);
        methods[1] = UserView.class.getDeclaredMethod("disposePanel");
        methods[2] = MainView.class.getDeclaredMethod("repaintPanels", JPanel.class);
                
        objects[0] = user;
        objects[1] = "user";
        
        DialogSTM dialog = new DialogSTM(1,messages,methods, objects);
        dialog.setVisible(true);

    }
    
    public static void disposePanel(){
        UserView.getInstance().dispose();
    }
    
    public static void setFunction(Function function){
        UserView.getInstance().field5.setTextField(function.getName());
        UserView.getInstance().functionIdSelected = function.getId();
    }
}


