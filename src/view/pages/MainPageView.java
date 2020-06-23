package view.pages;

import controller.helper.Resolution;
import controller.manager.DepartmentManager;
import controller.manager.FunctionManager;
import controller.manager.SectorManager;
import controller.manager.TaskManager;
import controller.manager.UserManager;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Department;
import model.Function;
import model.Sector;
import model.Task;
import model.User;
import view.MainView;
import view.components.BoxSTM;
import view.components.FieldSTM;
import view.components.RoundedPanel;

public class MainPageView extends JPanel {

    private final JPanel jpanel1 = new JPanel();
    private final JPanel jpanel2 = new JPanel();
    private final JPanel jpanel4 = new JPanel();
    private final JPanel jpanel5 = new JPanel();
    private final JPanel jpanel6 = new RoundedPanel(10);
    private final JPanel jpanel7 = new RoundedPanel(10);
    private Dimension sizePainel1 = null;
    private Dimension sizePainel2 = null;
    private Dimension sizePainel3 = null;
    private Dimension sizePainel4 = null;
    private Dimension sizePainel5 = null;
    private Dimension sizePainel6 = null;
    private Dimension sizePainel7 = null;
    private Dimension sizePainel8 = null;
    private Dimension sizePainel9 = null;
    private FieldSTM searchPanel = new FieldSTM();
    private ArrayList<BoxSTM> jpanelBoxes = new ArrayList();
    private ArrayList<BoxSTM> hiddenJpanelBoxes = new ArrayList();
    private boolean search = false;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private final ImageIcon ICONALLDONE = new ImageIcon(getClass().getResource("/view/img/allDone321x340.png"));

    private String object = "";
    /*
    stateTask = 0 || All Task
    stateTask = 1 || Owner Task
    stateTask = 2 || Owner Task in process
    stateTask = 3 || Owner Task complete
     */
    private int stageTask = 0;

    private Point size;

    public MainPageView(String object, Point size) {
        this.object = object;
        this.size = size;
        initComponents();
    }

    public MainPageView(String object, int stageTask, Point size) {
        this.object = object;
        this.stageTask = stageTask;
        this.size = size;
        initComponents();
    }

    public MainPageView(String object, int stageTask, Point size, ArrayList<BoxSTM> boxes) {
        this.object = object;
        this.stageTask = stageTask;
        this.size = size;
        this.jpanelBoxes = boxes;
        initComponents();
    }
    
    public MainPageView(String object, int stageTask, Point size, ArrayList<BoxSTM> boxes, boolean search) {
        this.object = object;
        this.stageTask = stageTask;
        this.size = size;
        this.jpanelBoxes = boxes;
        this.search = search;
        initComponents();
    }    

    public int getStageTask() {
        return stageTask;
    }

    public Point getSizeCrud() {
        return size;
    }

    public String getObject() {
        return this.object;
    }

    private void initComponents() {

        //PAINELOUT
        this.setOpaque(false);
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setLayout(null);
        this.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int xOld4 = jpanel4.getLocation().x;
                int xOld7 = jpanel7.getLocation().x;
                int yOld4 = jpanel4.getLocation().y;
                int yOld7 = jpanel7.getLocation().y;

                int yMinPanel = 0;
                int yMaxPanel = (jpanel4.getSize().height - jpanel1.getSize().height) * -1;
                int yMinBar = 0;
                int yMaxbar = (int) (jpanel6.getSize().height - jpanel7.getSize().height);
                int speed = (int) (jpanel4.getSize().height * 0.03);

                yMaxPanel = (yMaxPanel == 0 ? -1 : yMaxPanel);
                yOld4 = (yOld4 == 0 ? 1 : yOld4);
                int statePanel = (((yOld4 * 100) / yMaxPanel) > 1 ? ((yOld4 * 100) / yMaxPanel) : 1);

                if (e.getWheelRotation() == 1) {
                    if (yOld4 - speed > yMaxPanel) {

                        jpanel4.setLocation(xOld4, yOld4 - speed);
                        jpanel7.setLocation(xOld7, (int) ((statePanel * yMaxbar) / 100));
                    } else {
                        jpanel4.setLocation(xOld4, yMaxPanel);
                        jpanel7.setLocation(xOld7, yMaxbar);
                    }
                } else {
                    if (yOld4 + speed < yMinPanel) {
                        jpanel4.setLocation(xOld4, yOld4 + speed);
                        jpanel7.setLocation(xOld7, (int) ((statePanel * yMaxbar) / 100));
                    } else {
                        jpanel4.setLocation(xOld4, yMinPanel);
                        jpanel7.setLocation(xOld7, yMinBar);
                    }
                }
            }
        });

        //PAINEL BODY
        jpanel1.setBackground(new Color(200, 200, 200));
        jpanel1.setLocation(0, (int) (size.x * 0.06));
        sizePainel1 = new Dimension((int) (size.x * 0.98), (int) (size.y * 0.86));
        jpanel1.setSize(sizePainel1);
        jpanel1.setLayout(null);
        this.add(jpanel1);

        //CONTEUDO
        jpanel4.setBackground(new Color(200, 200, 200));
        jpanel4.setLocation((int) (size.x * 0.03), 0);
        sizePainel4 = new Dimension(sizePainel1.width, sizePainel1.height);
        jpanel4.setSize(sizePainel4);
        jpanel4.setLayout(null);
        jpanel1.add(jpanel4);

        //BAR RIGHT
        jpanel5.setLocation(sizePainel1.width, (int) (size.x * 0.06));
        jpanel5.setBackground(new Color(200, 200, 200));
        sizePainel5 = new Dimension(Resolution.getInstance().subtractDimension(size, sizePainel1).width, (int) (size.y * 0.80));
        jpanel5.setSize(sizePainel5);
        jpanel5.setLayout(null);
        this.add(jpanel5);

        jpanel6.setBackground(new Color(210, 210, 230));
        jpanel6.setOpaque(false);
        jpanel6.setLocation(0, 0);
        sizePainel6 = new Dimension((int) (sizePainel5.width / 2), sizePainel5.height);
        jpanel6.setSize(sizePainel6);
        jpanel6.setLayout(null);
        jpanel5.add(jpanel6);

        jpanel7.setBackground(Color.BLUE);
        jpanel7.setOpaque(false);
        jpanel7.setLocation(0, 0);
        sizePainel7 = new Dimension(sizePainel6.width, sizePainel5.height);
        jpanel7.setSize(sizePainel7);
        jpanel7.setLayout(null);
        jpanel6.add(jpanel7);

        //PAINEL TOP
        jpanel2.setLocation(0, 0);
        jpanel2.setBackground(new Color(200, 200, 200));
        sizePainel2 = new Dimension(size.x, (int) (size.x * 0.06));
        jpanel2.setSize(sizePainel2);
        jpanel2.setLayout(null);
        this.add(jpanel2);

        if ( jpanelBoxes.isEmpty() && !search ) {
            jpanelBoxes = loadDate();
        }

        //FILTRO
        if(false)
        {
        if (object.equals("task")) {
            Checkbox box1 = new Checkbox("Em análise");
            Checkbox box2 = new Checkbox("Em execução");
            Checkbox box3 = new Checkbox("Em verificação");
            Checkbox box4 = new Checkbox("Concluída");

            box1.setSize(120, 20);
            box2.setSize(120, 20);
            box3.setSize(120, 20);
            box4.setSize(120, 20);

            box1.setLocation(20, 10);
            box2.setLocation(20, 30);
            box3.setLocation(140, 10);
            box4.setLocation(140, 30);

            jpanel2.add(box1);
            jpanel2.add(box2);
            jpanel2.add(box3);
            jpanel2.add(box4);

            box1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (box1.getState()) {
                        change("Em análise");
                    }
                }
            });
            box2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (box1.getState()) {
                        change("Em execução");
                    }
                }
            });
            box3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (box1.getState()) {
                        change("Em verificação");
                    }
                }
            });
            box4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (box1.getState()) {
                        change("Concluída");
                    }
                }
            });
        }
        }

        Point searchSize = new Point((int) (size.x * 0.25), (int) (size.x * 0.03));
        searchPanel = new FieldSTM("Buscar", searchSize);
        searchPanel.setLocation(size.x - searchSize.x - 50, (int) (size.x * 0.02));
        jpanel2.add(searchPanel);

        searchPanel.getField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("2");
                    change();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            public void change() {
                hiddenJpanelBoxes.clear();
                    
                jpanelBoxes.clear();
                jpanelBoxes = loadDate();
                
                boolean search2 = true;

                String textInput = searchPanel.getTextField().toLowerCase();

                for (BoxSTM jpanelBox : jpanelBoxes) {
                    String title = jpanelBox.getTitle().toLowerCase();

                    if (title.contains(textInput)) {
                        hiddenJpanelBoxes.add(jpanelBox);
                    }
                }
                jpanelBoxes.clear();
                jpanelBoxes = hiddenJpanelBoxes;
                
                if(textInput.isEmpty())
                {
                    search2 = false;
                }

                MainPageView jpanelCrudNew = new MainPageView(getObjectS(), getStageTask(), getSizeCrud(), jpanelBoxes, search2);

                MainView.getInstance().switchPanelsCrud(jpanelCrudNew);

                jpanelCrudNew.searchPanel.setTextField(textInput);

            }
        });

        repaintBoxes();

    }

    private String getObjectS() {
        return this.object;
    }

    private void repaintBoxes() {
        //PAINT CRUD
        int rowBoxes = 0;
        int columnBoxes = 0;

        if(!jpanelBoxes.isEmpty())
        {
            for (BoxSTM jpanelBox : jpanelBoxes) {
                if (rowBoxes == 4) {
                    rowBoxes = 0;
                    jpanel4.setSize((int) sizePainel4.getWidth(), (int) (sizePainel4.getHeight() + jpanelBox.getSize().height * columnBoxes));
                    jpanel4.repaint();
                    columnBoxes++;
                }

                Point boxLocation = new Point(rowBoxes * jpanelBox.getSize().width, columnBoxes * jpanelBox.getSize().height);
                jpanelBox.setLocation(boxLocation);
                jpanelBox.setOpaque(false);
                jpanelBox.setLayout(null);
                jpanel4.add(jpanelBox);

                rowBoxes++;

                //bar
                if (columnBoxes > 2) {
                    int norY = (int) sizePainel1.getHeight();
                    int maxY = (int) (sizePainel4.getHeight() + jpanelBox.getSize().height * columnBoxes);
                    int porcentSize = (int) ((norY * 100) / maxY);
                    int sizeBar = (int) ((norY * porcentSize) / 100);
                    sizePainel7 = new Dimension(sizePainel6.width, sizeBar);
                    jpanel7.setSize(sizePainel7);
                    jpanel7.repaint();
                }
            }
        }
        if (jpanelBoxes.isEmpty() && object.equals("task")) {
            JLabel alldone = new JLabel();
            alldone.setIcon(ICONALLDONE);
            alldone.setLocation(0, 0);
            alldone.setHorizontalAlignment(SwingConstants.CENTER);
            alldone.setSize(sizePainel1.width, (int) (sizePainel1.height * 0.9));
            jpanel4.add(alldone);

            JLabel alldone2 = new JLabel("Tudo Pronto!");
            alldone2.setFont(new Font("Lucida Sans Unicode", 1, 38));
            alldone2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            alldone2.setLocation(0, 510);
            alldone2.setSize(sizePainel1.width, 50);
            jpanel4.add(alldone2);
        }
    }

    private ArrayList<BoxSTM> loadDate() {
        //LOAD DATA OF CRUD
        switch (object) {
            case "user":
                ArrayList<User> users = UserManager.getInstance().getUsers();
                for (User user : users) {
                    jpanelBoxes.add(new BoxSTM(user.getId(), "user", "Usuário", user.getName(), FunctionManager.getInstance().getFunction(user.getFunctionId()).getName(), Color.BLUE, new Point(250, 250)));
                }
                jpanelBoxes.add(new BoxSTM(0, "user", true, new Point(250, 250)));
                break;
            case "department":
                ArrayList<Department> departments = DepartmentManager.getInstance().getDepartments();
                for (Department department : departments) {
                    jpanelBoxes.add(new BoxSTM(department.getId(), "department", "Departamento", department.getName(), UserManager.getInstance().getUser(department.getManagerId()).getName(), Color.BLUE, new Point(250, 250)));
                }
                jpanelBoxes.add(new BoxSTM(0, "department", true, new Point(250, 250)));
                break;
            case "function":
                ArrayList<Function> functions = FunctionManager.getInstance().getFunctions();
                for (Function function : functions) {
                    jpanelBoxes.add(new BoxSTM(function.getId(), "function", "Funções", function.getName(), SectorManager.getInstance().getSector(function.getSectorId()).getName(), Color.BLUE, new Point(250, 250)));
                }
                jpanelBoxes.add(new BoxSTM(0, "function", true, new Point(250, 250)));
                break;
            case "sector":
                ArrayList<Sector> sectors = SectorManager.getInstance().getSectors();
                for (Sector sector : sectors) {
                    jpanelBoxes.add(new BoxSTM(sector.getId(), "sector", "Setores", sector.getName(), DepartmentManager.getInstance().getDepartment(sector.getDepartmentId()).getName(), Color.BLUE, new Point(250, 250)));
                }
                jpanelBoxes.add(new BoxSTM(0, "sector", true, new Point(250, 250)));
                break;
            case "task":
                ArrayList<Task> tasks = new ArrayList();
                switch (stageTask) {
                    case 0:
                        tasks = TaskManager.getInstance().getTasks();
                        for (Task task : tasks) {
                            String state = "";
                            Color stateColor = Color.BLUE;
                            switch (task.getState()) {
                                case -1:
                                    state = "Cancelada";
                                    stateColor = Color.RED;
                                    break;
                                case 0:
                                    state = "Criada";
                                    stateColor = Color.ORANGE;
                                    break;
                                case 1:
                                    state = "Em Análise";
                                    stateColor = Color.GRAY;
                                    break;
                                case 2:
                                    state = "Em Execução";
                                    stateColor = Color.BLUE;
                                    break;
                                case 3:
                                    state = "EM Verificação";
                                    stateColor = Color.YELLOW;
                                    break;
                                case 4:
                                    state = "Concluída";
                                    stateColor = Color.GREEN;
                                    break;
                            }
                            jpanelBoxes.add(new BoxSTM(task.getId(), "task", state, task.getName(), dateFormat.format(task.getDateCreate()), stateColor, new Point(250, 250)));
                        }
                        jpanelBoxes.add(new BoxSTM(0, "task", true, new Point(250, 250)));
                        break;

                    case 1:
                        tasks = TaskManager.getInstance().getTasksByUser(UserManager.getInstance().getUserLogged());
                        for (Task task : tasks) {
                            if (task.getState() != -1 && task.getState() != 4) {
                                String state = "";
                                Color stateColor = Color.BLUE;
                                switch (task.getState()) {

                                    case 0:
                                        state = "Criada";
                                        stateColor = Color.ORANGE;
                                        break;
                                    case 1:
                                        state = "Em Análise";
                                        stateColor = Color.GRAY;
                                        break;
                                    case 2:
                                        state = "Em Execução";
                                        stateColor = Color.BLUE;
                                        break;
                                    case 3:
                                        state = "EM Verificação";
                                        stateColor = Color.YELLOW;
                                        break;
                                }
                                jpanelBoxes.add(new BoxSTM(task.getId(), "task", state, task.getName(), dateFormat.format(task.getDateCreate()), stateColor, new Point(250, 250)));
                            }
                        }
                        break;
                    case 2:
                        tasks = TaskManager.getInstance().getTasksByUserAndState(UserManager.getInstance().getUserLogged(), 2);
                        for (Task task : tasks) {
                            String state = "";
                            Color stateColor = Color.BLUE;
                            switch (task.getState()) {
                                case -1:
                                    state = "Cancelada";
                                    stateColor = Color.RED;
                                    break;
                                case 0:
                                    state = "Criada";
                                    stateColor = Color.ORANGE;
                                    break;
                                case 1:
                                    state = "Em Análise";
                                    stateColor = Color.GRAY;
                                    break;
                                case 2:
                                    state = "Em Execução";
                                    stateColor = Color.BLUE;
                                    break;
                                case 3:
                                    state = "EM Verificação";
                                    stateColor = Color.YELLOW;
                                    break;
                                case 4:
                                    state = "Concluída";
                                    stateColor = Color.GREEN;
                                    break;
                            }
                            jpanelBoxes.add(new BoxSTM(task.getId(), "task", state, task.getName(), dateFormat.format(task.getDateCreate()), stateColor, new Point(250, 250)));
                        }
                        break;
                    case 3:
                        tasks = TaskManager.getInstance().getTasksByUserAndState(UserManager.getInstance().getUserLogged(), 4);
                        for (Task task : tasks) {
                            String state = "";
                            Color stateColor = Color.BLUE;
                            switch (task.getState()) {
                                case -1:
                                    state = "Cancelada";
                                    stateColor = Color.RED;
                                    break;
                                case 0:
                                    state = "Criada";
                                    stateColor = Color.ORANGE;
                                    break;
                                case 1:
                                    state = "Em Análise";
                                    stateColor = Color.GRAY;
                                    break;
                                case 2:
                                    state = "Em Execução";
                                    stateColor = Color.BLUE;
                                    break;
                                case 3:
                                    state = "EM Verificação";
                                    stateColor = Color.YELLOW;
                                    break;
                                case 4:
                                    state = "Concluída";
                                    stateColor = Color.GREEN;
                                    break;
                            }
                            jpanelBoxes.add(new BoxSTM(task.getId(), "task", state, task.getName(), dateFormat.format(task.getDateCreate()), stateColor, new Point(250, 250)));
                        }
                        break;
                }
                break;
            case "report":
                jpanelBoxes.add(new BoxSTM(1, "report", "Relátorio", "Usuários por Departamento", "v1.0", Color.BLUE, new Point(250, 250)));
                jpanelBoxes.add(new BoxSTM(2, "report", "Relátorio", "Vinculos por Usuário", "v1.0", Color.BLUE, new Point(250, 250)));
                jpanelBoxes.add(new BoxSTM(3, "report", "Relátorio", "Tarefas por Responsavel", "v1.0", Color.BLUE, new Point(250, 250)));
                jpanelBoxes.add(new BoxSTM(4, "report", "Relátorio", "Situação das Tarefas por Estado", "v1.0", Color.BLUE, new Point(250, 250)));
                break;
        }

        return jpanelBoxes;
    }

    private void repaintMainPageView() {
        this.repaint();
        this.revalidate();
    }

    private void change( String typeTask ) 
    {
        
        for (BoxSTM jpanelBox : jpanelBoxes) 
        {
            String header = jpanelBox.getHeader();

            if (header.equals(typeTask)) {
                hiddenJpanelBoxes.add(jpanelBox);
            }
        }
        jpanelBoxes.clear();
        jpanelBoxes = hiddenJpanelBoxes;

        MainPageView jpanelCrudNew = new MainPageView(getObjectS(), getStageTask(), getSizeCrud(), jpanelBoxes);

        MainView.getInstance().switchPanelsCrud(jpanelCrudNew);
        
        repaintBoxes();
    }

}
