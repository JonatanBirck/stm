package view.components;

import controller.helper.Resolution;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.classes.DepartmentView;
import view.classes.FunctionView;
import view.classes.ReportView;
import view.classes.SectorView;
import view.classes.TaskView;
import view.classes.UserView;

public class BoxSTM extends JPanel {

    private int idObject = 0;
    private String classObject = "";

    private String header = "";
    private String title = "";
    private String subTitle = "";
    private final Point size;
    private Color stateColor = Color.BLUE;
    private boolean select = false;
    private Method methodSelect[];
    private Object objectSelect[];

    private final JPanel jpanel1 = new RoundedPanel(18);
    private final JPanel jpanel2 = new RoundedPanel(8);
    private final JPanel jpanel3 = new JPanel();
    private final JLabel jlabel1 = new JLabel();
    private final JLabel jlabel2 = new JLabel();
    private final JLabel jlabel3 = new JLabel();
    private final JLabel jlabel4 = new JLabel();
    private final JLabel jlabel5 = new JLabel();
    private final JLabel jlabel6 = new JLabel();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

    private final ImageIcon ICONADICIONAR32 = new ImageIcon(getClass().getResource("/view/img/adicionar32x32.png"));
    private final ImageIcon ICONADICIONAR64 = new ImageIcon(getClass().getResource("/view/img/adicionar64x64.png"));
    private final ImageIcon ICONADICIONAR128 = new ImageIcon(getClass().getResource("/view/img/adicionar128x128.png"));

    public BoxSTM(int idObject, String classObject, String header, String title, String subTitle, Color stateColor, Point size) {
        this.idObject = idObject;
        this.classObject = classObject;
        this.header = header;
        this.title = title;
        this.subTitle = subTitle;
        this.size = size;
        this.stateColor = stateColor;

        createBox(size);
    }

    public BoxSTM(boolean select, Method[] method, Object[] object, int idObject, String classObject, String header, String title, String subTitle, Color stateColor, Point size) {
        this.idObject = idObject;
        this.classObject = classObject;
        this.header = header;
        this.title = title;
        this.subTitle = subTitle;
        this.size = size;
        this.stateColor = stateColor;
        this.select = true;
        this.methodSelect = method;
        this.objectSelect = object;

        createBox(size);
    }

    public BoxSTM(int idObject, String classObject, boolean isNew, Point size) {
        this.idObject = idObject;
        this.classObject = classObject;
        this.size = size;

        createNewBox(size);
    }
    
    public String getClassObject()
    {
        return this.classObject;
    }

    private JPanel createNewBox(Point size) {

        //PAINELOUT
        this.setOpaque(false);
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setLayout(null);
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

        //PAINEL CENTRAL
        jpanel1.setBackground(new Color(201, 212, 239));
        jpanel1.setOpaque(false);
        Point jpanel1Location = Resolution.getInstance().getPointMarginX(size, 6);
        jpanel1.setLocation(jpanel1Location);
        Dimension jpanel1Sub = new Dimension((jpanel1Location.x * 2), (jpanel1Location.y * 2));
        Dimension sizeJpanel2 = Resolution.getInstance().subtractDimension(size, jpanel1Sub);
        jpanel1.setSize(sizeJpanel2);
        jpanel1.setLayout(null);
        this.add(jpanel1);
        jpanel1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jpanel1.requestFocus();
                switch (classObject) {
                    case "user":
                        UserView userView = new UserView(idObject);
                        break;
                    case "department":
                        DepartmentView departmentView = new DepartmentView(idObject);
                        break;
                    case "function":
                        FunctionView functionView = new FunctionView(idObject);
                        break;
                    case "sector":
                        SectorView sectorView = new SectorView(idObject);
                        break;
                    case "task":
                        TaskView taskView = new TaskView(idObject);
                        break;
                }
            }
        });
        //ICON
        jlabel1.setIcon(ICONADICIONAR32);
        Point jlabel1Location = new Point(jpanel1Location.x - 16, jpanel1Location.y - 16);
        jlabel1.setLocation(jlabel1Location);
        jlabel1.setSize(sizeJpanel2);
        jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jlabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jpanel1.add(jlabel1);

        return this;
    }
    
    public String getTitle()
    {
        return this.title;
    }

    private JPanel createBox(Point size) {

        //PAINELOUT
        this.setOpaque(false);
        this.setSize(Resolution.getInstance().getDimension(size));
        this.setLayout(null);
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));

        //PAINEL CENTRAL
        jpanel1.setBackground(Color.WHITE);
        jpanel1.setOpaque(false);
        Point jpanel1Location = Resolution.getInstance().getPointMarginX(size, 6);
        jpanel1.setLocation(jpanel1Location);
        Dimension jpanel1Sub = new Dimension((jpanel1Location.x * 2), (jpanel1Location.y * 2));
        Dimension sizeJpanel2 = Resolution.getInstance().subtractDimension(size, jpanel1Sub);
        jpanel1.setSize(sizeJpanel2);
        jpanel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jpanel1.setLayout(null);
        jpanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                jpanel1.requestFocus();
                Object nullObject = new Object();
                if (select) {
                    try {
                        switch (classObject) {
                            case "user":
                                methodSelect[0].invoke(null, objectSelect[0]);
                                methodSelect[1].invoke(null);
                                break;
                            case "department":
                                methodSelect[0].invoke(null, objectSelect[0]);
                                methodSelect[1].invoke(null);
                                break;
                            case "function":
                                methodSelect[0].invoke(null, objectSelect[0]);
                                methodSelect[1].invoke(null);
                                break;
                            case "sector":
                                methodSelect[0].invoke(null, objectSelect[0]);
                                methodSelect[1].invoke(null);
                                break;
                            case "task":
                                methodSelect[0].invoke(null, objectSelect[0]);
                                methodSelect[1].invoke(null);
                                break;
                            case "stateTask":
                                methodSelect[0].invoke(null, objectSelect[0]);
                                methodSelect[1].invoke(null);
                                break;  
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        System.out.println("ERRO: " + e);
                    }
                } else {
                    switch (classObject) {
                        case "user":
                            UserView userView = new UserView(idObject);
                            break;
                        case "department":
                            DepartmentView departmentView = new DepartmentView(idObject);
                            break;
                        case "function":
                            FunctionView functionView = new FunctionView(idObject);
                            break;
                        case "sector":
                            SectorView sectorView = new SectorView(idObject);
                            break;
                        case "task":
                            TaskView taskView = new TaskView(idObject);
                            break;
                        case "report":
                            ReportView reportView = new ReportView(idObject);
                            break;
                    }
                }

            }
        });
        jpanel1.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent evt) {
                jpanel1.setBackground(new Color(230, 230, 230));
            }

            public void focusLost(FocusEvent evt) {
                jpanel1.setBackground(Color.WHITE);
            }
        });
        jpanel1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jpanel1.setBackground(new Color(230, 230, 230));
            }

            public void mouseExited(MouseEvent evt) {
                if (!jpanel1.isFocusOwner()) {
                    jpanel1.setBackground(Color.WHITE);
                }
            }
        });

        this.add(jpanel1);

        //HEADER
        jpanel2.setBackground(stateColor);
        jpanel2.setOpaque(false);
        Point jpanel2Location = Resolution.getInstance().getPointMarginX(size, 5);
        jpanel2.setLocation(jpanel2Location);
        Dimension sizeJPanel2 = new Dimension((int) (size.x / 2 + 15), 30);
        jpanel2.setSize(sizeJPanel2);
        jpanel2.setLayout(null);
        jpanel1.add(jpanel2);

        jlabel2.setText(header);
        jlabel2.setFont(new Font("Lucida Sans Unicode", 1, 14));
        jlabel2.setForeground(Color.WHITE);
        jlabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jlabel2.setBorder(null);
        jlabel2.setLocation(14, 0);
        jlabel2.setSize(sizeJPanel2);
        jpanel2.add(jlabel2);

        //TITLE
        String[] titleVetor = {"", "", "", "", ""};
        String[] titleSplit = title.split(" ");
        if (title.length() > 18) {
            int count = 0;
            for (Object title1 : titleSplit) {
                titleVetor[count] = (String) title1;
                count++;
            }
            String firstLabel = titleVetor[0] + " " + titleVetor[1];
            String secondLabel = titleVetor[2] + " " + titleVetor[3] + " " + titleVetor[4];
            if (firstLabel.length() > 18) {
                firstLabel = titleVetor[0];
                secondLabel = titleVetor[1] + " " + secondLabel;
                if (secondLabel.length() > 18) {
                    secondLabel = secondLabel.substring(0, 17);
                }
            }
            jlabel3.setText(firstLabel);
            jlabel3.setFont(new Font("Lucida Sans Unicode", 1, 16));
            jlabel3.setForeground(Color.GRAY);
            jlabel3.setHorizontalAlignment(SwingConstants.LEFT);
            jlabel3.setBorder(null);
            jlabel3.setLocation(jpanel2Location.x, jpanel2Location.y + 40);
            jlabel3.setSize((int) (size.x - jpanel2Location.x - jpanel2Location.x), (int) (size.y / 3));
            jpanel1.add(jlabel3);

            jlabel4.setText(secondLabel);
            jlabel4.setFont(new Font("Lucida Sans Unicode", 1, 18));
            jlabel4.setForeground(Color.GRAY);
            jlabel4.setHorizontalAlignment(SwingConstants.LEFT);
            jlabel4.setBorder(null);
            jlabel4.setLocation(jpanel2Location.x, jpanel2Location.y + 60);
            jlabel4.setSize((int) (size.x - jpanel2Location.x - jpanel2Location.x), (int) (size.y / 3));
            jpanel1.add(jlabel4);
        } else {
            jlabel3.setText(title);
            jlabel3.setFont(new Font("Lucida Sans Unicode", 1, 18));
            jlabel3.setForeground(Color.GRAY);
            jlabel3.setHorizontalAlignment(SwingConstants.LEFT);
            jlabel3.setBorder(null);
            jlabel3.setLocation(jpanel2Location.x, jpanel2Location.y + 40);
            jlabel3.setSize((int) (size.x - jpanel2Location.x - jpanel2Location.x), (int) (size.y / 3));
            jpanel1.add(jlabel3);
        }

        //SUBTITLE
        jpanel3.setBackground(stateColor);
        Point jpanel3Location = Resolution.getInstance().getPointMarginX(size, 5);
        Point jPanel3Location = new Point(jpanel3Location.x, jpanel2Location.y + 50 + (int) (size.y / 3));
        jpanel3.setLocation(jPanel3Location);
        Dimension sizeJPanel3 = new Dimension((int) (jpanel3Location.x / 1.4), 40);
        jpanel3.setSize(sizeJPanel3);
        jpanel3.setLayout(null);
        jpanel1.add(jpanel3);

        String[] subtitleVetor = {"", "", "", ""};
        subTitle = (subTitle == null ? "n/d" : subTitle);
        if (subTitle.length() > 18) {
            String[] subTitleSplit = subTitle.split(" ");
            int count = 0;
            for (Object subTitleSpli : subTitleSplit) {
                subtitleVetor[count] = (String) subTitleSpli;
                count++;
            }

            jlabel5.setText(subtitleVetor[0]);
            jlabel5.setFont(new Font("Lucida Sans Unicode", 1, 14));
            jlabel5.setForeground(Color.GRAY);
            jlabel5.setHorizontalAlignment(SwingConstants.LEFT);
            jlabel5.setBorder(null);
            Point jLabel5Location = new Point(jPanel3Location.x + sizeJPanel3.width + 10, jPanel3Location.y);
            jlabel5.setLocation(jLabel5Location);
            jlabel5.setSize((int) (size.x - jLabel5Location.x - jLabel5Location.x), sizeJPanel3.height / 2);
            jpanel1.add(jlabel5);

            jlabel6.setText(subtitleVetor[1] + " " + subtitleVetor[2] + " " + subtitleVetor[3]);
            jlabel6.setFont(new Font("Lucida Sans Unicode", 1, 14));
            jlabel6.setForeground(Color.GRAY);
            jlabel6.setHorizontalAlignment(SwingConstants.LEFT);
            jlabel6.setBorder(null);
            Point jLabel6Location = new Point(jPanel3Location.x + sizeJPanel3.width + 10, jPanel3Location.y + sizeJPanel3.height / 2);
            jlabel6.setLocation(jLabel6Location);
            jlabel6.setSize((int) (size.x - jLabel5Location.x - jLabel5Location.x), sizeJPanel3.height / 2);
            jpanel1.add(jlabel6);

        } else {
            jlabel5.setText(subTitle);
            jlabel5.setFont(new Font("Lucida Sans Unicode", 1, 14));
            jlabel5.setForeground(Color.GRAY);
            jlabel5.setHorizontalAlignment(SwingConstants.LEFT);
            jlabel5.setBorder(null);
            Point jLabel5Location = new Point(jPanel3Location.x + sizeJPanel3.width + 10, jPanel3Location.y);
            jlabel5.setLocation(jLabel5Location);
            jlabel5.setSize((int) (size.x - jLabel5Location.x - jLabel5Location.x), sizeJPanel3.height);
            jpanel1.add(jlabel5);
        }

        return this;

    }

}
