package model;

import java.util.Date;
import javax.swing.ImageIcon;

public class User {

    private int id;
    private String login = "";
    private String password = "";
    private String name = "";
    private String email = "";
    private int functionId;
    private Date dateCreate;
    /*
    state -1 = inativo
    state 1 = normal
    state 5 = root
    */
    private int state;
    
    private final ImageIcon ICONUSER16 =  new ImageIcon(getClass().getResource("/view/img/account16x16.png"));
    private final ImageIcon ICONUSER24 =  new ImageIcon(getClass().getResource("/view/img/account24x24.png"));
    private final ImageIcon ICONUSER64 =  new ImageIcon(getClass().getResource("/view/img/worker64x64.png"));


    public User() {
        this.id = 0;
    } 
    
    public User(String login, String password, String name, String email){
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
    }
  
    public User(String login, String password, String name, String email,int functionId){
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.functionId = functionId;
    }
    
    public User(int id, String login, String password, String name, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
    }
    
    public User(int id, String login, String password, String name, String email, int functionId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.functionId = functionId;
    }

    public User(int id, String login, String password, String name, String email, int functionId, int functionSecondId, Date dateCreate, int state) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.functionId = functionId;
        this.dateCreate = dateCreate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getFunctionId() {
        return functionId;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public int getState() {
        return state;
    }
    
    public ImageIcon getIcon(int sizeIcon) {
        ImageIcon icon;
        
        switch(sizeIcon){
            case 16: icon = ICONUSER16;
                break;
            case 24: icon = ICONUSER24;
                break;
            case 64: icon = ICONUSER64;
                break;  
            default: icon = ICONUSER16;
        }
        
        return icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    public void update(int id, String login, String password, String name, String email, int functionId, Date dateCreate, int state) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.functionId = functionId;
        this.dateCreate = dateCreate;
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", password=" + password + ", name=" + name + ", email=" + email + ", functionId=" + functionId + ", dateCreate=" + dateCreate + ", state=" + state + '}';
    }
        
}
