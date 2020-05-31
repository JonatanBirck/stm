package view.classes;

import com.sun.glass.events.KeyEvent;
import controller.STM;
import controller.helper.Resolution;
import controller.helper.Validation;
import controller.manager.TaskManager;
import controller.manager.UserManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
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
import view.pages.MainPageView;

public class TaskView extends JFrame {

    private Task task;
    private final JPanel jpanel1 = new JPanel();
    private final JPanel jpanel2 = new JPanel();
    private final JPanel jpanel3 = new JPanel();
    private final JPanel jpanel4 = new JPanel();
    private final JPanel jpanel5 = new JPanel();
    private final JLabel jlabel1 = new JLabel();
    private final JLabel jlabel2 = new JLabel();
    private final JLabel jlabel3 = new JLabel();
    private final JLabel jlabel4 = new JLabel();
    private final JLabel jlabel5 = new JLabel();
    private final JLabel jlabel6 = new JLabel();   
    private final JLabel jlabel7 = new JLabel();    
    private final JLabel jlabel8 = new JLabel();    
    private JPanel buttonPanel1 = new JPanel();
    private JPanel buttonPanel2 = new JPanel();
    private FieldSTM field1;
    private FieldSTM field2;
    private FieldSTM field3;
    private FieldSTM field4;
    private FieldSTM field5;
    private FieldSTM field6;
    private FieldSTM field7;
    private FieldSTM field8;
    private FieldSTM field9;
    private FieldSTM field10;
    private FieldSTM field11;
     private FieldSTM field12;
    
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

    public TaskView(int idTask, boolean visualize) {
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

    private void initComponents() {
        switch (task.getState()) {
            case -1:
                paintBoxStateA();
                break;
            case 0:
                paintBoxStateB();
                break;
            case 1:
                paintBoxStateC();
                break;
            case 2:
                paintBoxStateD();
                break;
            case 3:
                paintBoxStateE();
                break;
            case 4:
                paintBoxStateA();
                break;
        }
    }

    private void paintBoxStateA() {
        
        //FRAME
        Point sizeWindowns = Resolution.getInstance().getResolution();
        Point size = new Point((int) (sizeWindowns.x / 1.03), (int) (sizeWindowns.y / 1.50));
        this.setLocation(Resolution.getInstance().getCenterResolution(size));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                register(task.getState());
            }
        });

        //PANEL
        jpanel1.setLocation(0, 0);
        jpanel1.setSize(Resolution.getInstance().getDimension(size));
        jpanel1.setLayout(null);
        this.add(jpanel1);
        jpanel1.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    register(task.getState());
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }
        });     
        
        //PANEL CREATE
        jpanel2.setLocation(0, 0);
        Dimension sizePainel2 = new Dimension((int)(size.x/4),size.y);
        jpanel2.setSize(sizePainel2);
        jpanel2.setLayout(null);
        jpanel1.add(jpanel2);
        
        //ICONTASK
        jlabel1.setIcon(task.getIconMap(1));
        jlabel1.setLocation(0, 0);
        jlabel1.setSize(sizePainel2.width, 140);
        jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel2.add(jlabel1);    
        
        //OPTION - CADASTRADA
        jlabel2.setText("Cadastrada");
        jlabel2.setFont(new Font("Lucida Sans Unicode", 1, 16));
        jlabel2.setLocation(0, 120);
        jlabel2.setSize(sizePainel2.width, 20);
        jlabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel2.add(jlabel2);
        
        //DESCRIÇÃO CREATE
        String descField1 = (task.getDescriptionCreate() == null ? "n/d" : task.getDescriptionCreate());
        Point sizeField1 = new Point((int) (sizePainel2.width * 0.86), (int) (sizePainel2.width * 0.55));
        field1 = new FieldSTM(descField1,4, sizeField1, true);
        Point locationField1 = new Point((int) (sizePainel2.width * 0.07), 150);
        field1.setLocation(locationField1);
        jpanel2.add(field1);
        
        //USER CREATE
        String userField2 = (task.getUserCreatedId() == 0 ? "n/d" : UserManager.getInstance().getUser(task.getUserCreatedId()).getName());
        Point sizeField2 = new Point((int) (sizePainel2.width * 0.86), (int) (sizePainel2.width * 0.12));
        field2 = new FieldSTM(userField2, sizeField2, true);
        Point locationField2 = new Point((int) (sizePainel2.width * 0.07), sizeField1.y + 160);
        field2.setLocation(locationField2);
        jpanel2.add(field2);
        
        //DATA CREATE
        String dataField3 = (task.getDateCreate() == null ? "n/d" : task.getDateCreate().toString());
        field3 = new FieldSTM(dataField3, sizeField2, true);
        Point locationField3 = new Point((int) (sizePainel2.width * 0.07), sizeField1.y + sizeField2.y + 170);
        field3.setLocation(locationField3);
        jpanel2.add(field3);
        
        
        //PANEL ANALISY
        jpanel3.setLocation((int)(size.x/4), 0);
        Dimension sizePainel3 = new Dimension((int)(size.x/4),size.y);
        jpanel3.setSize(sizePainel3);
        jpanel3.setLayout(null);
        jpanel1.add(jpanel3);      
        
        //ICONTASK
        jlabel3.setIcon(task.getIconMap(2));
        jlabel3.setLocation(0, 0);
        jlabel3.setSize(sizePainel3.width, 140);
        jlabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel3.add(jlabel3);    
        
        //OPTION - Analisada
        jlabel4.setText("Analisada");
        jlabel4.setFont(new Font("Lucida Sans Unicode", 1, 16));
        jlabel4.setLocation(0, 120);
        jlabel4.setSize(sizePainel3.width, 20);
        jlabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel3.add(jlabel4);        
        
        //DESCRIÇÃO ANALISY
        String descField4 = (task.getDescriptionAnalyze() == null ? "n/d" : task.getDescriptionAnalyze());
        field4 = new FieldSTM(descField4,4, sizeField1, true);
        Point locationField4 = new Point((int) (sizePainel3.width * 0.07), 150);
        field4.setLocation(locationField4);
        jpanel3.add(field4);   
        
        //USER ANALISY
        String userField5 = (task.getUserAnalyzeId() == 0 ? "n/d" : UserManager.getInstance().getUser(task.getUserAnalyzeId()).getName());
        field5 = new FieldSTM(userField5, sizeField2, true);
        Point locationField5 = new Point((int) (sizePainel3.width * 0.07), sizeField1.y + 160);
        field5.setLocation(locationField5);
        jpanel3.add(field5);
        
        //DATA ANALISY
        String dataField6 = (task.getDateAnalyze() == null ? "n/d" : task.getDateAnalyze().toString());
        field6 = new FieldSTM(dataField6, sizeField2, true);
        Point locationField6 = new Point((int) (sizePainel3.width * 0.07), sizeField1.y + sizeField2.y + 170);
        field6.setLocation(locationField6);
        jpanel3.add(field6);        
      
        //PANEL EXECUTE
        jpanel4.setLocation((int)(size.x/2), 0);
        Dimension sizePainel4 = new Dimension((int)(size.x/4),size.y);
        jpanel4.setSize(sizePainel4);
        jpanel4.setLayout(null);
        jpanel1.add(jpanel4); 
        
        //ICONTASK
        jlabel5.setIcon(task.getIconMap(3));
        jlabel5.setLocation(0, 0);
        jlabel5.setSize(sizePainel4.width, 140);
        jlabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel4.add(jlabel5);   
        
        //OPTION - Executada
        jlabel8.setText("Executada");
        jlabel8.setFont(new Font("Lucida Sans Unicode", 1, 16));
        jlabel8.setLocation(0, 120);
        jlabel8.setSize(sizePainel4.width, 20);
        jlabel8.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel4.add(jlabel8);          

        //DESCRIÇÃO EXECUTE
        String descField7 = (task.getDescriptionDoit() == null ? "n/d" : task.getDescriptionDoit());
        field7 = new FieldSTM(descField7,4, sizeField1, true);
        Point locationField7 = new Point((int) (sizePainel4.width * 0.07), 150);
        field7.setLocation(locationField7);
        jpanel4.add(field7); 

        //USER EXECUTE
        String userField8 = (task.getUserDoitId() == 0 ? "n/d" : UserManager.getInstance().getUser(task.getUserDoitId()).getName());
        field8 = new FieldSTM(userField8, sizeField2, true);
        Point locationField8 = new Point((int) (sizePainel4.width * 0.07), sizeField1.y + 160);
        field8.setLocation(locationField8);
        jpanel4.add(field8);
        
        //DATA EXECUTE
        String dataField9 = (task.getDateDoit() == null ? "n/d" : task.getDateDoit().toString());
        field9 = new FieldSTM(dataField9, sizeField2, true);
        Point locationField9 = new Point((int) (sizePainel4.width * 0.07), sizeField1.y + sizeField2.y + 170);
        field9.setLocation(locationField9);
        jpanel4.add(field9);    

        
        //PANEL VERIFY
        jpanel5.setLocation((int)((size.x/4)*3), 0);
        Dimension sizePainel5 = new Dimension((int)(size.x/4),size.y);
        jpanel5.setSize(sizePainel5);
        jpanel5.setLayout(null);
        jpanel1.add(jpanel5);   
        
        //ICONTASK
        jlabel7.setIcon(task.getIconMap(4));
        jlabel7.setLocation(0, 0);
        jlabel7.setSize(sizePainel5.width, 140);
        jlabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel5.add(jlabel7);        
        
        //OPTION - Verificada
        jlabel6.setText("Verificada");
        jlabel6.setFont(new Font("Lucida Sans Unicode", 1, 16));
        jlabel6.setLocation(0, 120);
        jlabel6.setSize(sizePainel5.width, 20);
        jlabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel5.add(jlabel6);          

        //DESCRIÇÃO VERIFY
        String descField10 = (task.getDescriptionCheck() == null ? "n/d" : task.getDescriptionCheck());
        field10 = new FieldSTM(descField10,4, sizeField1, true);
        Point locationField10 = new Point((int) (sizePainel5.width * 0.07), 150);
        field10.setLocation(locationField10);
        jpanel5.add(field10); 

        //USER VERIFY
        String userField11 = (task.getUserCheckId()== 0 ? "n/d" : UserManager.getInstance().getUser(task.getUserCheckId()).getName());
        field11 = new FieldSTM(userField11, sizeField2, true);
        Point locationField11 = new Point((int) (sizePainel5.width * 0.07), sizeField1.y + 160);
        field11.setLocation(locationField11);
        jpanel5.add(field11);
        
        //DATA VERIFY
        String dataField12 = (task.getDateCheck()== null ? "n/d" : task.getDateCheck().toString());
        field12 = new FieldSTM(dataField12, sizeField2, true);
        Point locationField12 = new Point((int) (sizePainel5.width * 0.07), sizeField1.y + sizeField2.y + 170);
        field12.setLocation(locationField12);
        jpanel5.add(field12);  
    }

    private void paintBoxStateB() {
        //FRAME
        Point sizeWindowns = Resolution.getInstance().getResolution();
        Point size = new Point((int) (sizeWindowns.x / 3.7), (int) (sizeWindowns.y / 1.4));
        this.setLocation(Resolution.getInstance().getCenterResolution(size));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                register(task.getState());
            }
        });

        //PANEL
        jpanel1.setLocation(0, 0);
        jpanel1.setSize(Resolution.getInstance().getDimension(size));
        jpanel1.setLayout(null);
        this.add(jpanel1);
        jpanel1.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    register(task.getState());
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
        jlabel2.setText(task.getStatus(task.getState()));
        jlabel2.setFont(new Font("Lucida Sans Unicode", 1, 16));
        jlabel2.setLocation(0, 120);
        jlabel2.setSize(size.x, 20);
        jlabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel2);

        //OPTION - NOME
        Point sizeField = new Point((int) (size.x * 0.80), (int) (size.x * 0.12));
        field1 = new FieldSTM("Nome da Tarefa *", sizeField);
        Point locationField1 = new Point((int) ((size.x - sizeField.x) / 2) - 5, 160);
        field1.setLocation(locationField1);
        jpanel1.add(field1);

        //OPTION - DESCRIÇÃO
        Point sizeFieldDesc = new Point((int) (size.x * 0.80), (int) (size.x * 0.45));
        field2 = new FieldSTM("Descrição da Tarefa *",4, sizeFieldDesc);
        Point descLocation = new Point(locationField1.x, locationField1.y + 10 + (int) (size.x * 0.12));
        field2.setLocation(descLocation);
        jpanel1.add(field2);

        //OPTION - Responsability
        Point responLocation = new Point(descLocation.x, descLocation.y + sizeFieldDesc.y + 10);
        field3 = new FieldSTM("Responsabilidade da Tarefa *", 2, sizeField, "task", "user");
        field3.setLocation(responLocation);
        jpanel1.add(field3);

        //BUTTON REGISTER
        buttonPanel1 = new ButtonSTM().getButtonSTM("Registrar", 0, 1, sizeField);
        Point buttonPanel1Location = new Point(responLocation.x, responLocation.y + sizeField.y + 10);
        buttonPanel1.setLocation(buttonPanel1Location);
        buttonPanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                register(task.getState());
            }
        });
        jpanel1.add(buttonPanel1);

    }

    private void paintBoxStateC() {
        //FRAME
        Point sizeWindowns = Resolution.getInstance().getResolution();
        Point size = new Point((int) (sizeWindowns.x / 3.7), (int) (sizeWindowns.y / 1.05));
        this.setLocation(Resolution.getInstance().getCenterResolution(size));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                register(task.getState());
            }
        });

        //PANEL
        jpanel1.setLocation(0, 0);
        jpanel1.setSize(Resolution.getInstance().getDimension(size));
        jpanel1.setLayout(null);
        this.add(jpanel1);
        jpanel1.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    register(task.getState());
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
        jlabel2.setText(task.getStatus(task.getState()));
        jlabel2.setFont(new Font("Lucida Sans Unicode", 1, 16));
        jlabel2.setLocation(0, 120);
        jlabel2.setSize(size.x, 20);
        jlabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel2);

        //OPTION - DESCRIÇÃO ANTERIOR
        Point sizeFieldDesc = new Point((int) (size.x * 0.80), (int) (size.x * 0.45));
        field1 = new FieldSTM(task.getDescriptionCreate(),4, sizeFieldDesc, true);
        Point locationField1 = new Point((int) ((size.x - sizeFieldDesc.x) / 2) - 5, 160);
        field1.setLocation(locationField1);
        jpanel1.add(field1);

        //OPTION - DESCRIÇÃO
        field2 = new FieldSTM("Descrição da Analise *",4, sizeFieldDesc);
        Point descLocation = new Point(locationField1.x, locationField1.y + 10 + sizeFieldDesc.y);
        field2.setLocation(descLocation);
        jpanel1.add(field2);

        //OPTION - Responsability
        Point sizeField = new Point((int) (size.x * 0.80), (int) (size.x * 0.12));
        Point responLocation = new Point(descLocation.x, descLocation.y + sizeFieldDesc.y + 10);
        field3 = new FieldSTM("Responsabilidade da Tarefa *", 2, sizeField, "task", "user");
        field3.setLocation(responLocation);
        jpanel1.add(field3);

        //BUTTON REGISTER
        buttonPanel1 = new ButtonSTM().getButtonSTM("Registrar", 0, 1, sizeField);
        Point buttonPanel1Location = new Point(responLocation.x, responLocation.y + sizeField.y + 10);
        buttonPanel1.setLocation(buttonPanel1Location);
        buttonPanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                register(task.getState());
            }
        });
        jpanel1.add(buttonPanel1);

        //BUTTON CANCELAR
        buttonPanel2 = new ButtonSTM().getButtonSTM("Encerrar Tarefa", 0, 1, Color.RED, sizeField);
        Point buttonPanel21Location = new Point(buttonPanel1Location.x, buttonPanel1Location.y + sizeField.y + 10);
        buttonPanel2.setLocation(buttonPanel21Location);
        buttonPanel2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                cancel();
            }
        });
        jpanel1.add(buttonPanel2);
    }

    private void paintBoxStateD() {
        //FRAME
        Point sizeWindowns = Resolution.getInstance().getResolution();
        Point size = new Point((int) (sizeWindowns.x / 3.7), (int) (sizeWindowns.y / 1.05));
        this.setLocation(Resolution.getInstance().getCenterResolution(size));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                register(task.getState());
            }
        });

        //PANEL
        jpanel1.setLocation(0, 0);
        jpanel1.setSize(Resolution.getInstance().getDimension(size));
        jpanel1.setLayout(null);
        this.add(jpanel1);
        jpanel1.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    register(task.getState());
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
        jlabel2.setText(task.getStatus(task.getState()));
        jlabel2.setFont(new Font("Lucida Sans Unicode", 1, 16));
        jlabel2.setLocation(0, 120);
        jlabel2.setSize(size.x, 20);
        jlabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel2);

        //OPTION - DESCRIÇÃO ANTERIOR
        Point sizeFieldDesc = new Point((int) (size.x * 0.80), (int) (size.x * 0.45));
        field1 = new FieldSTM(task.getDescriptionAnalyze(),4, sizeFieldDesc, true);
        Point locationField1 = new Point((int) ((size.x - sizeFieldDesc.x) / 2) - 5, 160);
        field1.setLocation(locationField1);
        jpanel1.add(field1);

        //OPTION - DESCRIÇÃO
        field2 = new FieldSTM("Descrição da Execução *",4, sizeFieldDesc);
        Point descLocation = new Point(locationField1.x, locationField1.y + 10 + sizeFieldDesc.y);
        field2.setLocation(descLocation);
        jpanel1.add(field2);

        //OPTION - Responsability
        Point sizeField = new Point((int) (size.x * 0.80), (int) (size.x * 0.12));
        Point responLocation = new Point(descLocation.x, descLocation.y + sizeFieldDesc.y + 10);
        field3 = new FieldSTM("Responsabilidade da Tarefa *", 2, sizeField, "task", "user");
        field3.setLocation(responLocation);
        jpanel1.add(field3);

        //BUTTON REGISTER
        buttonPanel1 = new ButtonSTM().getButtonSTM("Registrar", 0, 1, sizeField);
        Point buttonPanel1Location = new Point(responLocation.x, responLocation.y + sizeField.y + 10);
        buttonPanel1.setLocation(buttonPanel1Location);
        buttonPanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                register(task.getState());
            }
        });
        jpanel1.add(buttonPanel1);

        //BUTTON CANCELAR
        buttonPanel2 = new ButtonSTM().getButtonSTM("Encerrar Tarefa", 0, 1, Color.RED, sizeField);
        Point buttonPanel21Location = new Point(buttonPanel1Location.x, buttonPanel1Location.y + sizeField.y + 10);
        buttonPanel2.setLocation(buttonPanel21Location);
        buttonPanel2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                cancel();
            }
        });
        jpanel1.add(buttonPanel2);
    }

    private void paintBoxStateE() {
        //FRAME
        Point sizeWindowns = Resolution.getInstance().getResolution();
        Point size = new Point((int) (sizeWindowns.x / 3.7), (int) (sizeWindowns.y / 1.15));
        this.setLocation(Resolution.getInstance().getCenterResolution(size));
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setIconImage(STM.getInstance().getIcon(32).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                register(task.getState());
            }
        });

        //PANEL
        jpanel1.setLocation(0, 0);
        jpanel1.setSize(Resolution.getInstance().getDimension(size));
        jpanel1.setLayout(null);
        this.add(jpanel1);
        jpanel1.addKeyListener(new KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    register(task.getState());
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
        jlabel2.setText(task.getStatus(task.getState()));
        jlabel2.setFont(new Font("Lucida Sans Unicode", 1, 16));
        jlabel2.setLocation(0, 120);
        jlabel2.setSize(size.x, 20);
        jlabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel1.add(jlabel2);

        //OPTION - DESCRIÇÃO ANTERIOR
        Point sizeFieldDesc = new Point((int) (size.x * 0.80), (int) (size.x * 0.45));
        field1 = new FieldSTM(task.getDescriptionDoit(),4, sizeFieldDesc, true);
        Point locationField1 = new Point((int) ((size.x - sizeFieldDesc.x) / 2) - 5, 160);
        field1.setLocation(locationField1);
        jpanel1.add(field1);

        //OPTION - DESCRIÇÃO
        field2 = new FieldSTM("Descrição da Verificação *",4, sizeFieldDesc);
        Point descLocation = new Point(locationField1.x, locationField1.y + 10 + sizeFieldDesc.y);
        field2.setLocation(descLocation);
        jpanel1.add(field2);

        //BUTTON REGISTER
        Point sizeField = new Point((int) (size.x * 0.80), (int) (size.x * 0.12));
        buttonPanel1 = new ButtonSTM().getButtonSTM("Registrar", 0, 1, sizeField);
        Point buttonLocation = new Point(descLocation.x, descLocation.y + sizeFieldDesc.y + 10);
        buttonPanel1.setLocation(buttonLocation);
        buttonPanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                register(task.getState());
            }
        });
        jpanel1.add(buttonPanel1);

        //BUTTON CANCELAR
        buttonPanel2 = new ButtonSTM().getButtonSTM("Encerrar Tarefa", 0, 1, Color.RED, sizeField);
        Point buttonPanel1Location = new Point(locationField1.x, buttonLocation.y + sizeField.y + 10);
        buttonPanel2.setLocation(buttonPanel1Location);
        buttonPanel2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                cancel();
            }
        });
        jpanel1.add(buttonPanel2);
    }

    private void register(int state) {

        String name;
        String descriptionCreate;
        String descriptionAnalyze;
        String descriptionDoit;
        String descriptionCheck;
        int userResponsability;
        Validation validate;
        int oldUserResponsability;

        Date today = new Date();

        switch (state) {
            case -1:
                break;
            case 0:
                name = field1.getTextField();
                descriptionCreate = field2.getTextField();
                userResponsability = userResponsabilitySelected;

                task.setState(1);
                task.setDateCreate(today);
                task.setUserCreatedId(UserManager.getInstance().getUserLogged().getId());
                task.setName(name);
                task.setDescriptionCreate(descriptionCreate);
                task.setUserResponsibility(userResponsability);

                validate = TaskManager.getInstance().authenticateNew(task);
                if (validate.isValidated()) {
                    TaskManager.getInstance().addTask(task);
                    JPanel jpanel = new MainPageView("task", MainView.getInstance().getLastStagePage(), MainView.getInstance().getSizeCRUD());
                    MainView.getInstance().repaintPanels(jpanel);
                    this.dispose();
                } else {
                    task.setState(0);
                    task.setDateCreate(null);
                    task.setName(null);
                    task.setDescriptionCreate(null);
                    task.setUserResponsibility(0);
                    
                    DialogSTM dialog = new DialogSTM(validate);
                    dialog.setVisible(true);
                }
                break;
            case 1:
                oldUserResponsability = task.getUserResponsibility();
                
                descriptionAnalyze = field2.getTextField();
                userResponsability = userResponsabilitySelected;

                task.setState(2);
                task.setDateAnalyze(today);
                task.setUserAnalyzeId(UserManager.getInstance().getUserLogged().getId());
                task.setDescriptionAnalyze(descriptionAnalyze);
                task.setUserResponsibility(userResponsability);

                validate = TaskManager.getInstance().authenticateNextStage(task);
                if (validate.isValidated()) {
                    TaskManager.getInstance().updateTask(task);
                    JPanel jpanel = new MainPageView("task", MainView.getInstance().getLastStagePage(), MainView.getInstance().getSizeCRUD());
                    MainView.getInstance().repaintPanels(jpanel);
                    this.dispose();
                } else {
                    task.setState(1);
                    task.setDateAnalyze(null);
                    task.setUserAnalyzeId(0);
                    task.setDescriptionAnalyze(null);
                    task.setUserResponsibility(oldUserResponsability);                    
                    
                    DialogSTM dialog = new DialogSTM(validate);
                    dialog.setVisible(true);
                }
                break;
            case 2:
                oldUserResponsability = task.getUserResponsibility();
                descriptionDoit = field2.getTextField();
                userResponsability = userResponsabilitySelected;

                task.setState(3);
                task.setUserDoitId(UserManager.getInstance().getUserLogged().getId());
                task.setDateDoit(today);
                task.setDescriptionDoit(descriptionDoit);
                task.setUserResponsibility(userResponsability);

                validate = TaskManager.getInstance().authenticateNextStage(task);
                if (validate.isValidated()) {
                    TaskManager.getInstance().updateTask(task);
                    JPanel jpanel = new MainPageView("task", MainView.getInstance().getLastStagePage(), MainView.getInstance().getSizeCRUD());
                    MainView.getInstance().repaintPanels(jpanel);
                    this.dispose();
                } else {
                    
                    task.setState(2);
                    task.setUserDoitId(0);
                    task.setDateDoit(null);
                    task.setDescriptionDoit(null);
                    task.setUserResponsibility(oldUserResponsability);                    

                    DialogSTM dialog = new DialogSTM(validate);
                    dialog.setVisible(true);
                }
                break;
            case 3:
                oldUserResponsability = task.getUserResponsibility();
                descriptionCheck = field2.getTextField();

                task.setState(4);
                task.setUserCheckId(UserManager.getInstance().getUserLogged().getId());
                task.setDateCheck(today);
                task.setDescriptionCheck(descriptionCheck);

                validate = TaskManager.getInstance().authenticateNextStage(task);
                if (validate.isValidated()) 
                {
                    TaskManager.getInstance().updateTask(task);
                    JPanel jpanel = new MainPageView("task", MainView.getInstance().getLastStagePage(), MainView.getInstance().getSizeCRUD());
                    MainView.getInstance().repaintPanels(jpanel);
                    this.dispose();
                } else {
                    
                    task.setState(3);
                    task.setUserCheckId(0);
                    task.setDateCheck(null);
                    task.setDescriptionCheck(null);                    
                    
                    DialogSTM dialog = new DialogSTM(validate);
                    dialog.setVisible(true);
                }         
                
                
                break;
            case 4:
                break;
        }

    }

    private void cancel() {
        task.setState(-1);
        TaskManager.getInstance().updateTask(task);
        JPanel jpanel = new MainPageView("task", MainView.getInstance().getLastStagePage(), MainView.getInstance().getSizeCRUD());
        MainView.getInstance().repaintPanels(jpanel);
        this.dispose();
    }

    public static void disposePanel() {
        TaskView.getInstance().dispose();
    }

    public static void setUserResponsability(User user) {
        TaskView.getInstance().field3.setTextField(user.getName());
        TaskView.getInstance().userResponsabilitySelected = user.getId();
    }

}
