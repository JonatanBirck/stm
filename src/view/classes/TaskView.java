package view.classes;

import com.sun.glass.events.KeyEvent;
import controller.STM;
import controller.helper.Resolution;
import controller.helper.Validation;
import controller.manager.TaskManager;
import controller.manager.UserManager;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Task;
import model.User;
import view.MainView;
import view.components.ButtonSTM;
import view.components.DialogSTM;
import view.components.FieldSTM;
import view.pages.CRUDView;

public class TaskView extends JFrame{
    
    private Task task;
    private final JPanel jpanel1 = new JPanel();
    private final JLabel jlabel1 = new JLabel();
    private final JLabel jlabel2 = new JLabel();
    private JPanel buttonPanel1 = new JPanel();;
    private JPanel buttonPanel2 = new JPanel();;
    private FieldSTM field1;
    private FieldSTM field2;
    private FieldSTM field3;
    private FieldSTM field4;
    private FieldSTM field5;
    private int userResponsabilitySelected = 0;    
    private static TaskView instance = null;
    private boolean visualize = false;
    
    public TaskView(int idTask) {
        super("Tarefa");
        task = TaskManager.getInstance().getTask(idTask);
        initComponents();
        this.setVisible(true);
        jlabel1.requestFocus();
        instance = this;
    } 
    
    public TaskView(int idTask,boolean visualize) {
        super("Tarefa");
        task = TaskManager.getInstance().getTask(idTask);
        this.visualize = visualize;
        initComponents();
        this.setVisible(true);
        jlabel1.requestFocus();
        instance = this;
    }     
    
    public static TaskView getInstance() {
        return instance;
    }      
    
    private void initComponents(){
        switch (task.getState())
        {
            case -1: paintBoxStateA();
                break;
            case 0: paintBoxStateB();
                break;
            case 1: paintBoxStateC();
                break;
            case 2: paintBoxStateD();
                break;
            case 3: paintBoxStateE();
                break;    
            case 4: paintBoxStateF();
                break;                        
        }
    }
    
    private void paintBoxStateA(){
        if(visualize){
            
        }
        else
        {
            
        }
    }
    
    private void paintBoxStateB(){
        
        //FRAME
        Point sizeWindowns = Resolution.getInstance().getResolution();
        Point size = new Point((int) (sizeWindowns.x / 3.7), (int) (sizeWindowns.y / 1.4));
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
        
        //ICONTASK
        jlabel1.setIcon(task.getIconMap(task.getState()));
        jlabel1.setLocation(0, 0);
        jlabel1.setSize(size.x, 140);
        jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel1);
        
        //OPTION - LOGIN
        jlabel2.setText(getStatus(task.getState()));
        jlabel2.setFont(new Font("Lucida Sans Unicode", 1, 16)); 
        jlabel2.setLocation(0, 120);
        jlabel2.setSize(size.x, 20);
        jlabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel2);
        
        //OPTION - NOME
        Point sizeField =  new Point((int)(size.x*0.80),(int)(size.x*0.12));
        field1 = new FieldSTM("Nome da Tarefa *", sizeField);
        Point locationField1 = new Point((int)((size.x-sizeField.x)/2)-5,160);
        field1.setLocation(locationField1);
        jpanel1.add(field1);

        //OPTION - DESCRIÇÃO
        Point sizeFieldDesc =  new Point((int)(size.x*0.80),(int)(size.x*0.45));
        field2 = new FieldSTM("Descrição da Tarefa *", sizeFieldDesc);
        Point descLocation = new Point(locationField1.x, locationField1.y + 10 + (int)(size.x*0.12));
        field2.setLocation(descLocation);
        jpanel1.add(field2);  
        
        //OPTION - Responsability
        Point responLocation = new Point(descLocation.x,descLocation.y+sizeFieldDesc.y+10);
        field3 = new FieldSTM("Responsabilidade da Tarefa *",2,sizeField,"task","user");
        field3.setLocation(responLocation);
        jpanel1.add(field3);        
        
        //BUTTON REGISTER
        buttonPanel1 = new ButtonSTM().getButtonSTM("Registrar", 0,1, sizeField);
        Point buttonPanel1Location = new Point(responLocation.x, responLocation.y + sizeField.y + 10);
        buttonPanel1.setLocation(buttonPanel1Location);
        buttonPanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                register();
            }});
        jpanel1.add(buttonPanel1);          
        
        
    }

    private void paintBoxStateC(){
        if(visualize){
            
        }
        else
        {
            
        }        
    }

    private void paintBoxStateD(){
        if(visualize){
            
        }
        else
        {
            
        }        
    }

    private void paintBoxStateE(){
        if(visualize){
            
        }
        else
        {
            
        }        
    }

    private void paintBoxStateF(){
        if(visualize){
            
        }
        else
        {
            
        }        
    }
    
    
    private void register()
    {
        String name = field1.getTextField();
        String descriptionCreate = field2.getTextField();
        int userResponsability = userResponsabilitySelected;
          
        task.setUserCreatedId(UserManager.getInstance().getUserLogged().getId());
        task.setName(name);
        task.setDescriptionCreate(descriptionCreate);
        task.setUserResponsibility(userResponsability);
        
        Validation validate = TaskManager.getInstance().authenticateNew(task);
        if(validate.isValidated()){
            TaskManager.getInstance().addTask(task);
            JPanel jpanel = new CRUDView("task",MainView.getInstance().getSizeCRUD());
            MainView.getInstance().repaintPanels(jpanel);    
            this.dispose();
        }
        else
        {
            DialogSTM dialog = new DialogSTM(validate);
            dialog.setVisible(true);
        }        
    }
 
    private String getStatus(int state){
        switch (state)
        {
            case -1: return "Cancelada";
            case 0: return "Criação da Tarefa";
            case 1: return "Em análise";
            case 2: return "Em execução";
            case 3: return "Em verificação";  
            case 4: return "Concluída";                      
        }
        
        return "";
    }
    
    
    public static void disposePanel(){
        TaskView.getInstance().dispose();
    }
    
    public static void setUserResponsability(User user){
        TaskView.getInstance().field3.setTextField(user.getName());
        TaskView.getInstance().userResponsabilitySelected = user.getId();
    }    
    
    
}
