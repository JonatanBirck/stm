package view.classes;

import com.sun.glass.events.KeyEvent;
import controller.STM;
import controller.helper.Resolution;
import controller.helper.Validation;
import controller.manager.DepartmentManager;
import controller.manager.SectorManager;
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
import javax.swing.SwingConstants;
import model.Department;
import model.Sector;
import model.User;
import view.MainView;
import view.components.ButtonSTM;
import view.components.DialogSTM;
import view.components.FieldSTM;
import view.pages.MainPageView;

public class SectorView extends JFrame {

    private Sector sector = new Sector();
    private final JPanel jpanel1 = new JPanel();
    private final JLabel jlabel1 = new JLabel();
    private JPanel buttonPanel1 = new JPanel();;
    private JPanel buttonPanel2 = new JPanel();;
    private FieldSTM field1;
    private FieldSTM field2;
    private FieldSTM field3;
    private FieldSTM field5;
    private int departmentIdSelected = 0;
    private int userIdSelected = 0;
    private static SectorView instance = null;

    public SectorView() {
        super("Setores");
        departmentIdSelected = sector.getDepartmentId();
        userIdSelected = sector.getLeaderId();
        initComponents();
        this.setVisible(true);
        jlabel1.requestFocus();
        instance = this;
    }       
    
    public SectorView(int idSector) {
        super("Setores");
        this.sector = SectorManager.getInstance().getSector(idSector);
        departmentIdSelected = sector.getDepartmentId();
        userIdSelected = sector.getLeaderId();
        initComponents();
        this.setVisible(true);
        jlabel1.requestFocus();
        instance = this;
    }  
    
    public static SectorView getInstance() {
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
        jlabel1.setIcon(sector.getIcon(64));
        jlabel1.setLocation(0, 0);
        jlabel1.setSize(size.x, 120);
        jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel1);
        
        //OPTION - Nome
        Point sizeField =  new Point((int)(size.x*0.80),(int)(size.x*0.12));
        field1 = new FieldSTM("Nome *", sizeField);
        Point locationField1 = new Point((int)((size.x-sizeField.x)/2)-5,130);
        field1.setLocation(locationField1);
        jpanel1.add(field1);

       
        //OPTION - Descrição
        field2 = new FieldSTM("Descrição *", sizeField);
        field2.setLocation(locationField1.x, locationField1.y + 10 + (int)(size.x*0.12));
        jpanel1.add(field2);

        //OPTION - Departamento
        field3 = new FieldSTM("Departamento *",2,sizeField,"sector","department");
        field3.setLocation(locationField1.x, locationField1.y + 20 + (int)((size.x*0.12)*2));
        jpanel1.add(field3);       
        
        //OPTION - Líder
        field5 = new FieldSTM("Líder *",2,sizeField,"sector","user");
        field5.setLocation(locationField1.x, locationField1.y + 30 + (int)((size.x*0.12)*3));
        jpanel1.add(field5);  

        //BUTTON - REGISTER
        String firstButtonName = "Registrar";
        boolean newSector = true;
        if(sector.getId() != 0){
            newSector = false;
            firstButtonName = "Registrar Alterações";
        }
        buttonPanel1 = new ButtonSTM().getButtonSTM(firstButtonName, 0,1, sizeField);
        Point buttonPanel1Location = new Point(locationField1.x, locationField1.y + 40 + (int)((size.x*0.12)*4));
        buttonPanel1.setLocation(buttonPanel1Location);
        buttonPanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                register();
            }});
        jpanel1.add(buttonPanel1);  
        
        //BUTTON - DELETE
        if(!newSector)
        {
            buttonPanel2 = new ButtonSTM().getButtonSTM("Deletar", 0,1,Color.RED, sizeField);
            buttonPanel1Location = new Point(buttonPanel1Location.x, buttonPanel1Location.y + 10 + (int)((size.x*0.12)));
            buttonPanel2.setLocation(buttonPanel1Location);
            buttonPanel2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    try {
                        delete();
                    } catch (Exception ex) {
                        Logger.getLogger(SectorView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }});
            jpanel1.add(buttonPanel2);  
        }
       
        size = new Point(size.x, buttonPanel1Location.y + 20 + sizeField.y + (int)((size.x*0.12)));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.repaint();
        
        
        if(sector.getId() != 0){
            field1.setTextField(sector.getName());
            field2.setTextField(sector.getDescription());
            if(sector.getDepartmentId() != 0)
            {
                String departmentName = DepartmentManager.getInstance().getDepartment(sector.getDepartmentId()).getName();
                field3.setTextField(departmentName);
            }
            if(sector.getLeaderId() != 0)
            {
                String leaderName = UserManager.getInstance().getUser(sector.getLeaderId()).getName();
                field5.setTextField(leaderName);
            }            
        }
        
    }

    private void register() {
        
        String name = field1.getTextField();
        String description = field2.getTextField();
        int departmentId = departmentIdSelected;
        int userId = userIdSelected;
                
        sector.setName(name);
        sector.setDescription(description);
        sector.setDepartmentId(departmentId);
        sector.setLeaderId(userId);
        
        if(sector.getId() != 0){
            Validation validate = SectorManager.getInstance().authenticateUpdate(sector);
            if(validate.isValidated()){
                SectorManager.getInstance().updateSector(sector);
                JPanel jpanel = new MainPageView("sector",MainView.getInstance().getSizeCRUD());
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
            Validation validate = SectorManager.getInstance().authenticateNew(sector);
            if(validate.isValidated()){
                SectorManager.getInstance().addSector(sector);
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
        messages.add("Você tem certeza que quer excluir o Setor:");
        messages.add(sector.getName());
        
        Method[] methods = {null,null,null};
        Object[] objects = {null,null};
        
        methods[0] = SectorManager.class.getDeclaredMethod("deleteSector", Sector.class);
        methods[1] = SectorView.class.getDeclaredMethod("disposePanel");
        methods[2] = MainView.class.getDeclaredMethod("repaintPanels", JPanel.class);
                
        objects[0] = sector;
        objects[1] = "sector";
        
        DialogSTM dialog = new DialogSTM(1,messages,methods, objects);
        dialog.setVisible(true);

    }

    public static void disposePanel(){
        SectorView.getInstance().dispose();
    }
    
    public static void setDepartment(Department department){
        SectorView.getInstance().departmentIdSelected = department.getId();
        SectorView.getInstance().field3.setTextField(department.getName());
    }
    
    public static void setUser(User user){
        SectorView.getInstance().userIdSelected = user.getId();
        SectorView.getInstance().field5.setTextField(user.getName());
    }    
    
}


