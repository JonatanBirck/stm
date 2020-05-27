
package view.components;

import com.sun.glass.events.KeyEvent;
import controller.STM;
import controller.helper.Resolution;
import controller.manager.DepartmentManager;
import controller.manager.FunctionManager;
import controller.manager.SectorManager;
import controller.manager.TaskManager;
import controller.manager.UserManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Department;
import model.Function;
import model.Sector;
import model.Task;
import model.User;
import view.MainView;
import view.classes.DepartmentView;
import view.classes.FunctionView;
import view.classes.SectorView;
import view.classes.TaskView;
import view.classes.UserView;

public class ClassSelector extends JFrame {
    
    private final JPanel jpanel0 = new JPanel();
    private final JPanel jpanel1 = new JPanel();
    private final JPanel jpanel2 = new JPanel();
    private final JPanel jpanel4 = new JPanel();
    private final JPanel jpanel5 = new JPanel();
    private final JPanel jpanel6 = new RoundedPanel(10);
    private final JPanel jpanel7 = new RoundedPanel(10);
    private FieldSTM searchPanel = new FieldSTM();
    private ArrayList<JPanel> jpanelBoxes = new ArrayList();
    private static ClassSelector instance = null;    
    
    private String classNameOrigin = "";
    private String classNameDestiny = "";
    private int stateTask = 0;
    private Point size;     
    
    public ClassSelector(String classNameOrigin, String classNameDestiny) {
        super("Selecionar");
        this.classNameOrigin = classNameOrigin;
        this.classNameDestiny = classNameDestiny;
        this.size = MainView.getInstance().getSizeCRUD();
        try {
            initComponents();
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ClassSelector.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance = this;
    } 
    
    public static ClassSelector getInstance() {
        return instance;
    }     
    
    private void initComponents() throws NoSuchMethodException{
        
        //FRAME
        this.setLocation(Resolution.getInstance().getCenterResolution(size));
        Dimension sizeFrame = new Dimension((int)(size.x*1.01),size.y);
        this.setSize(sizeFrame);
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if( evt.getKeyCode() == KeyEvent.VK_ESCAPE )
                {
                    dispose();
                }   
            }
        });
        
        //PAINELOUT
        jpanel0.setOpaque(false);
        jpanel0.setSize(Resolution.getInstance().getDimension(size));
        jpanel0.setLayout(null);
        jpanel0.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int xOld4 = jpanel4.getLocation().x;
                int xOld7 = jpanel7.getLocation().x;   
                int yOld4 = jpanel4.getLocation().y;
                int yOld7 = jpanel7.getLocation().y;
                        
                int yMinPanel = 0;
                int yMaxPanel = (jpanel4.getSize().height-jpanel1.getSize().height) * -1;
                int yMinBar = 0;
                int yMaxbar = (int)(jpanel6.getSize().height-jpanel7.getSize().height);
                int speed = (int)(jpanel4.getSize().height*0.03);

                yMaxPanel = (yMaxPanel == 0 ? -1 : yMaxPanel);
                yOld4 = (yOld4 == 0 ? 1 : yOld4);
                int statePanel = (((yOld4*100)/yMaxPanel) > 1 ? ((yOld4*100)/yMaxPanel) : 1);

                if(e.getWheelRotation() == 1)
                {
                    if(yOld4-speed > yMaxPanel)
                    {
                        
                        jpanel4.setLocation(xOld4,yOld4-speed);
                        jpanel7.setLocation(xOld7,(int)((statePanel*yMaxbar)/100));
                    }
                    else
                    {
                        jpanel4.setLocation(xOld4,yMaxPanel);
                        jpanel7.setLocation(xOld7,yMaxbar);
                    }
                }
                else
                {     
                    if(yOld4+speed < yMinPanel)
                    {
                       jpanel4.setLocation(xOld4,yOld4+speed);
                       jpanel7.setLocation(xOld7,(int)((statePanel*yMaxbar)/100));
                    }
                    else
                    {
                       jpanel4.setLocation(xOld4,yMinPanel);
                       jpanel7.setLocation(xOld7,yMinBar);
                    }
                }                
            }
        });   
        this.add(jpanel0);
        
        //PAINEL BODY
        jpanel1.setBackground(new Color(200,200,200));
        jpanel1.setLocation(0,(int)(size.x*0.06));
        Dimension sizePainel1 = new Dimension((int)(size.x*0.98),(int)(size.y*0.86));
        jpanel1.setSize(sizePainel1);
        jpanel1.setLayout(null);
        jpanel0.add(jpanel1);
        
        //CONTEUDO
        jpanel4.setBackground(new Color(200,200,200));
        jpanel4.setLocation((int)(size.x*0.03),0);
        Dimension sizePainel4 = new Dimension(sizePainel1.width,sizePainel1.height);
        jpanel4.setSize(sizePainel4);
        jpanel4.setLayout(null);
        jpanel1.add(jpanel4);
        
        //BAR RIGHT
        jpanel5.setLocation(sizePainel1.width,(int)(size.x*0.06));
        jpanel5.setBackground(new Color(200,200,200));
        Dimension sizePainel5 = new Dimension(Resolution.getInstance().subtractDimension(size, sizePainel1).width,(int)(size.y*0.80));
        jpanel5.setSize(sizePainel5);
        jpanel5.setLayout(null);
        this.add(jpanel5);
        
        jpanel6.setBackground(new Color(210,210,230));
        jpanel6.setOpaque(false);
        jpanel6.setLocation(0,0);
        Dimension sizePainel6 = new Dimension((int)(sizePainel5.width/2),sizePainel5.height);
        jpanel6.setSize(sizePainel6);
        jpanel6.setLayout(null);
        jpanel5.add(jpanel6);

        jpanel7.setBackground(Color.BLUE);
        jpanel7.setOpaque(false);
        jpanel7.setLocation(0,0);
        Dimension sizePainel7 = new Dimension(sizePainel6.width,sizePainel5.height);
        jpanel7.setSize(sizePainel7);
        jpanel7.setLayout(null);
        jpanel6.add(jpanel7);    
        
        //PAINEL TOP
        jpanel2.setLocation(0,0);
        jpanel2.setBackground(new Color(200,200,200));
        Dimension sizePainel2 = new Dimension(size.x,(int)(size.x*0.06));
        jpanel2.setSize(sizePainel2);
        jpanel2.setLayout(null);
        this.add(jpanel2);
        
        Point searchSize = new Point((int)(size.x*0.25),(int)(size.x*0.03)); 
        searchPanel = new FieldSTM("Buscar", searchSize);
        searchPanel.setLocation(size.x-searchSize.x-50, (int)(size.x*0.02));
        jpanel2.add(searchPanel);
                
        //LOAD DATA OF CRUD
        switch(classNameDestiny){
            case "user" : 
                ArrayList<User> users = UserManager.getInstance().getUsers();
                for(User user : users)
                {
                    Method[] methods = {null,null};
                    Object[] objects = {null};
                    switch(classNameOrigin)
                    {
                        case "department" :
                            methods[0] = DepartmentView.class.getDeclaredMethod("setUser",User.class);
                            methods[1] = ClassSelector.class.getDeclaredMethod("disposePainel");
                            objects[0] = user;
                            break;
                        case "sector" :
                            methods[0] = SectorView.class.getDeclaredMethod("setUser",User.class);
                            methods[1] = ClassSelector.class.getDeclaredMethod("disposePainel");
                            objects[0] = user;
                            break;
                        case "task" :
                            methods[0] = TaskView.class.getDeclaredMethod("setUserResponsability",User.class);
                            methods[1] = ClassSelector.class.getDeclaredMethod("disposePainel");
                            objects[0] = user;
                            break;                           
                    }                    
                    jpanelBoxes.add(new BoxSTM(true,methods,objects,user.getId(),"user","Usuários",user.getName(),SectorManager.getInstance().getSector(user.getFunctionId()).getName(), Color.BLUE,new Point(250,250)));
                }
                break;
            case "department" : 
                ArrayList<Department> departments = DepartmentManager.getInstance().getDepartments();
                for(Department department : departments)
                {
                    //CASE SECTOR ORIGIN
                    Method[] methods = {null,null};
                    Object[] objects = {null};
                    methods[0] = SectorView.class.getDeclaredMethod("setDepartment",Department.class);
                    methods[1] = ClassSelector.class.getDeclaredMethod("disposePainel");
                    objects[0] = department;
                    
                    jpanelBoxes.add(new BoxSTM(true,methods,objects,department.getId(),"department","Departamentos",department.getName(),UserManager.getInstance().getUser(department.getManagerId()).getName(), Color.BLUE,new Point(250,250)));
                }
                break;
            case "function" : 
                ArrayList<Function> functions = FunctionManager.getInstance().getFunctions();
                for(Function function : functions)
                {
                    //CASE USER ORIGIN
                    Method[] methods = {null,null};
                    Object[] objects = {null};
                    methods[0] = UserView.class.getDeclaredMethod("setFunction",Function.class);
                    methods[1] = ClassSelector.class.getDeclaredMethod("disposePainel");
                    objects[0] = function;
                    
                    jpanelBoxes.add(new BoxSTM(true,methods,objects,function.getId(),"function","Funções",function.getName(),SectorManager.getInstance().getSector(function.getSectorId()).getName(), Color.BLUE,new Point(250,250)));
                }
                break;
            case "sector" : 
                ArrayList<Sector> sectors = SectorManager.getInstance().getSectors();
                for(Sector sector : sectors)
                {
                    //CASE FUNCTION ORIGIN
                    Method[] methods = {null,null};
                    Object[] objects = {null};
                    methods[0] = FunctionView.class.getDeclaredMethod("setSector",Sector.class);
                    methods[1] = ClassSelector.class.getDeclaredMethod("disposePainel");
                    objects[0] = sector;
                    
                    jpanelBoxes.add(new BoxSTM(true,methods,objects,sector.getId(),"sector","Setores",sector.getName(),UserManager.getInstance().getUser(sector.getLeaderId()).getName(), Color.BLUE,new Point(250,250)));
                }
                break;
            case "task" : 
                ArrayList<Task> tasks = TaskManager.getInstance().getTasks();
                for(Task task : tasks)
                {
                    String state = "";
                    Color stateColor = Color.BLUE;
                    switch(task.getState()){
                        case -1 : 
                            state = "Cancelada";
                            stateColor = Color.RED;
                            break;
                        case 0 : 
                            state = "Criada";
                            stateColor = Color.ORANGE;
                            break;
                        case 1 : 
                            state = "Em Análise";
                            stateColor = Color.GRAY;
                            break;
                        case 2 : 
                            state = "Em Execução";
                            stateColor = Color.BLUE;
                            break;
                        case 3 : 
                            state = "EM Verificação";
                            stateColor = Color.YELLOW;
                            break;     
                        case 4 : 
                            state = "Concluída";
                            stateColor = Color.GREEN;
                            break;                               
                    }
                    //jpanelBoxes.add(new BoxSTM(true,task.getId(),"task",state,task.getName(),task.getDateCreate().toString(),stateColor,new Point(250,250)));
                }
                break;  
        }
        
        //PAINT CRUD
        int rowBoxes = 0;
        int columnBoxes = 0;
        for(JPanel jpanelBox : jpanelBoxes)
        {
            if( rowBoxes == 4 )
            {
                rowBoxes = 0;
                jpanel4.setSize((int) sizePainel4.getWidth(), (int) (sizePainel4.getHeight()+jpanelBox.getSize().height*columnBoxes));                
                jpanel4.repaint();
                columnBoxes++;
            } 
            
            Point boxLocation = new Point(rowBoxes*jpanelBox.getSize().width,columnBoxes*jpanelBox.getSize().height);
            jpanelBox.setLocation(boxLocation);
            jpanelBox.setOpaque(false);
            jpanelBox.setLayout(null);
            jpanel4.add(jpanelBox);

            rowBoxes++;

            //bar
            if(columnBoxes > 2){
                int norY = (int) sizePainel1.getHeight();
                int maxY = (int) (sizePainel4.getHeight()+jpanelBox.getSize().height*columnBoxes);
                int porcentSize = (int)((norY*100)/maxY);
                int sizeBar = (int)((norY*porcentSize)/100);
                sizePainel7 = new Dimension(sizePainel6.width,sizeBar);
                jpanel7.setSize(sizePainel7);     
                jpanel7.repaint();
            }
            
        }
                
    } 
    
    public static void disposePainel(){
        ClassSelector.getInstance().dispose();
    }
    
}
