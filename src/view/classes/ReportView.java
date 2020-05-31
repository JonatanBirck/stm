package view.classes;

import controller.STM;
import controller.helper.Report;
import controller.helper.Resolution;
import controller.helper.Validation;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Task;
import model.User;
import view.components.ButtonSTM;
import view.components.DialogSTM;
import view.components.FieldSTM;

public class ReportView extends JFrame {

    private final int idReport;
    private final JPanel jpanel1 = new JPanel();
    private final JLabel jlabel1 = new JLabel();
    private JPanel buttonPanel1 = new JPanel();;
    private FieldSTM field3;
    private FieldSTM field4;
    private FieldSTM field5;
    private int userIdSelected = 0;
    private int idStateSelected = 0;
    private static ReportView instance = null;
    
    private final ImageIcon ICONREPORT64 =  new ImageIcon(getClass().getResource("/view/img/report64x64.png"));
    
    
    public ReportView(int idReport) {
        super("Relátorio");
        this.idReport = idReport;
        initComponents();
        this.setVisible(true);
        jlabel1.requestFocus();
        instance = this;
    }  
    
    public static ReportView getInstance() {
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
        
        //PANEL
        jpanel1.setLocation(0,0);
        jpanel1.setSize(Resolution.getInstance().getDimension(size));
        jpanel1.setLayout(null);
        this.add(jpanel1);      
        
        //ICONUSER
        jlabel1.setIcon(ICONREPORT64);
        jlabel1.setLocation(0, 0);
        jlabel1.setSize(size.x, 120);
        jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel1);
        
        Point sizeField =  new Point((int)(size.x*0.80),(int)(size.x*0.12));
        
        Point locationFinal = new Point((int)(size.x*0.10),130);
        
        //CASE
        switch (idReport)
        {
            case 2:
                
                break;
            case 3:
                //OPTION - Responsável
                field3 = new FieldSTM("Responsável *",2,sizeField,"report","user");
                field3.setLocation(locationFinal.x, locationFinal.y);
                locationFinal = new Point(locationFinal.x,locationFinal.y + sizeField.y + 10 );
                jpanel1.add(field3); 
                
                //OPTION - Data Inicio
                field4 = new FieldSTM("Data Início *",3, sizeField);
                field4.setLocation(locationFinal.x, locationFinal.y);
                locationFinal = new Point(locationFinal.x,locationFinal.y + sizeField.y + 10 );
                jpanel1.add(field4); 

                //OPTION - Data Final
                field5 = new FieldSTM("Data Final *",3, sizeField);
                field5.setLocation(locationFinal.x, locationFinal.y);
                locationFinal = new Point(locationFinal.x,locationFinal.y + sizeField.y + 10 );
                jpanel1.add(field5);                 
                break;
            case 4:
                //OPTION - Responsável
                field3 = new FieldSTM("Estado *",2,sizeField,"report","stateTask");
                field3.setLocation(locationFinal.x, locationFinal.y);
                locationFinal = new Point(locationFinal.x,locationFinal.y + sizeField.y + 10 );
                jpanel1.add(field3); 
                
                //OPTION - Data Inicio
                field4 = new FieldSTM("Data Início *",3, sizeField);
                field4.setLocation(locationFinal.x, locationFinal.y);
                locationFinal = new Point(locationFinal.x,locationFinal.y + sizeField.y + 10 );
                jpanel1.add(field4); 

                //OPTION - Data Final
                field5 = new FieldSTM("Data Final *",3, sizeField);
                field5.setLocation(locationFinal.x, locationFinal.y);
                locationFinal = new Point(locationFinal.x,locationFinal.y + sizeField.y + 10 );
                jpanel1.add(field5);                 
                break;           
        }
        
        //BUTTON - REGISTER
        String firstButtonName = "Gerar Relátorio";
        buttonPanel1 = new ButtonSTM().getButtonSTM(firstButtonName, 0,1, sizeField);
        Point buttonPanel1Location = new Point(locationFinal.x, locationFinal.y + 10);
        buttonPanel1.setLocation(buttonPanel1Location);
        buttonPanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                report();
            }});
        jpanel1.add(buttonPanel1);  

        size = new Point(size.x, (int)(buttonPanel1Location.y + (2*sizeField.y) + 20));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.repaint();
        
    }

    private void report() {
        switch(idReport)
        {
            case 1: 
                this.dispose();
                new Report().report1();
                break;
            case 2: 
                this.dispose();
                new Report().report2();
                break;
            case 3: 
                Validation validation = new Validation(true);
                if(userIdSelected == 0)
                {
                    validation.setValidated(false);
                    validation.addAnnotation("Verifique o Responsável");
                }
                boolean dataStartValid = new Validation().dateIsValid(field4.getTextField());
                if(!dataStartValid)
                {
                    validation.setValidated(false);
                    validation.addAnnotation("Verifique a Data Inicial");  
                }
                boolean dataEndValid = new Validation().dateIsValid(field5.getTextField());
                if(!dataEndValid)
                {
                    validation.setValidated(false);
                    validation.addAnnotation("Verifique a Data Final");  
                }                
                
                if(validation.isValidated())
                {
                    this.dispose();
                    new Report().report3(userIdSelected,field4.getTextField(),field5.getTextField());
                }
                else
                {
                    DialogSTM dialog = new DialogSTM(validation);
                    dialog.setVisible(true);
                }                        
                break;
            case 4:
                Validation validation1 = new Validation(true);
                if(idStateSelected == 0)
                {
                    validation1.setValidated(false);
                    validation1.addAnnotation("Verifique o estado Selecionado");
                }
                boolean dataStartValid1 = new Validation().dateIsValid(field4.getTextField());
                if(!dataStartValid1)
                {
                    validation1.setValidated(false);
                    validation1.addAnnotation("Verifique a Data Inicial");  
                }
                boolean dataEndValid2 = new Validation().dateIsValid(field5.getTextField());
                if(!dataEndValid2)
                {
                    validation1.setValidated(false);
                    validation1.addAnnotation("Verifique a Data Final");  
                }                
                
                if(validation1.isValidated())
                {
                    this.dispose();
                    new Report().report4(idStateSelected,field4.getTextField(),field5.getTextField());
                }
                else
                {
                    DialogSTM dialog = new DialogSTM(validation1);
                    dialog.setVisible(true);
                }                        
                break;
        }

    }
    
    public static void setIdStateSelected(int state){
        ReportView.getInstance().field3.setTextField(new Task().getStatus(state));
        ReportView.getInstance().idStateSelected = state; 
    }
    
    public static void setUserResponsability(User user){
        ReportView.getInstance().field3.setTextField(user.getName());
        ReportView.getInstance().userIdSelected = user.getId();
    }
    
    public static void disposePanel(){
        ReportView.getInstance().dispose();
    }
    
}


