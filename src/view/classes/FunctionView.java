package view.classes;

import com.sun.glass.events.KeyEvent;
import controller.STM;
import controller.helper.Resolution;
import controller.helper.Validation;
import controller.manager.FunctionManager;
import controller.manager.SectorManager;
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
import model.Function;
import model.Sector;
import view.MainView;
import view.components.ButtonSTM;
import view.components.DialogSTM;
import view.components.FieldSTM;
import view.pages.MainPageView;

public class FunctionView extends JFrame {

    private Function function = new Function();
    private final JPanel jpanel1 = new JPanel();
    private final JLabel jlabel1 = new JLabel();
    private JPanel buttonPanel1 = new JPanel();;
    private JPanel buttonPanel2 = new JPanel();;
    private FieldSTM field1;
    private FieldSTM field2;
    private FieldSTM field3;
    private int setorIdSelected = 0;
    private static FunctionView instance = null;

    public FunctionView() {
        super("Funções");
        setorIdSelected = function.getSectorId();
        initComponents();
        this.setVisible(true);
        jlabel1.requestFocus();
        instance = this;
    }       
    
    public FunctionView(int idFunction) {
        super("Funções");
        this.function  = FunctionManager.getInstance().getFunction(idFunction);
        setorIdSelected = function.getSectorId();
        initComponents();
        this.setVisible(true);
        jlabel1.requestFocus();
        instance = this;
    }  
    
    public static FunctionView getInstance() {
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
        jlabel1.setIcon(function .getIcon(64));
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
        field3 = new FieldSTM("Setor *",2,sizeField,"function","sector");
        field3.setLocation(locationField1.x, locationField1.y + 20 + (int)((size.x*0.12)*2));
        jpanel1.add(field3);       
        
        //BUTTON - REGISTER
        String firstButtonName = "Registrar";
        boolean newFunction = true;
        if(function.getId() != 0){
            newFunction = false;
            firstButtonName = "Registrar Alterações";
        }
        buttonPanel1 = new ButtonSTM().getButtonSTM(firstButtonName, 0,1, sizeField);
        Point buttonPanel1Location = new Point(locationField1.x, locationField1.y + 30 + (int)((size.x*0.12)*3));
        buttonPanel1.setLocation(buttonPanel1Location);
        buttonPanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                register();
            }});
        jpanel1.add(buttonPanel1);  
        
        //BUTTON - DELETE
        if(!newFunction)
        {
            buttonPanel2 = new ButtonSTM().getButtonSTM("Deletar", 0,1,Color.RED, sizeField);
            buttonPanel1Location = new Point(buttonPanel1Location.x, buttonPanel1Location.y + 10 + (int)((size.x*0.12)));
            buttonPanel2.setLocation(buttonPanel1Location);
            buttonPanel2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    try {
                        delete();
                    } catch (Exception ex) {
                        Logger.getLogger(FunctionView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }});
            jpanel1.add(buttonPanel2);  
        }
       
        size = new Point(size.x, buttonPanel1Location.y + 20 + sizeField.y + (int)((size.x*0.12)));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.repaint();
        
        
        if(function.getId() != 0){
            field1.setTextField(function.getName());
            field2.setTextField(function.getDescription());
            if(function.getSectorId()!= 0)
            {
                String sectorName = SectorManager.getInstance().getSector(function.getSectorId()).getName();
                field3.setTextField(sectorName);
            }       
        }
        
    }

    private void register() {
        
        String name = field1.getTextField();
        String description = field2.getTextField();
        int sectorId = setorIdSelected;
          
        function.setName(name);
        function.setDescription(description);
        function.setSectorId(sectorId);

        
        if(function.getId() != 0){
            Validation validate = FunctionManager.getInstance().authenticateUpdate(function);
            if(validate.isValidated()){
                FunctionManager.getInstance().updateFunction(function);
                JPanel jpanel = new MainPageView("function",MainView.getInstance().getSizeCRUD());
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
            Validation validate = FunctionManager.getInstance().authenticateNew(function);
            if(validate.isValidated()){
                FunctionManager.getInstance().addFunction(function);
                JPanel jpanel = new MainPageView("function",MainView.getInstance().getSizeCRUD());
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
        messages.add("Você tem certeza que quer excluir a Função:");
        messages.add(function.getName());
        
        Method[] methods = {null,null,null};
        Object[] objects = {null,null};
        
        methods[0] = FunctionManager.class.getDeclaredMethod("deleteFunction", Function.class);
        methods[1] = FunctionView.class.getDeclaredMethod("disposePanel");
        methods[2] = MainView.class.getDeclaredMethod("repaintPanels", JPanel.class);
                
        objects[0] = function;
        objects[1] = "function";
        
        DialogSTM dialog = new DialogSTM(1, messages, methods, objects);
        dialog.setVisible(true);

    }

    public static void disposePanel(){
        System.out.println("TENTOU");
        FunctionView.getInstance().dispose();
    }
    
    public static void setSector(Sector sector){
        FunctionView.getInstance().setorIdSelected = sector.getId();
        FunctionView.getInstance().field3.setTextField(sector.getName());
    }
      
}