package model;

import javax.swing.ImageIcon;

public class Department {
    
    private int id;
    private String name;
    private String description;
    private int managerId;
    
    private final ImageIcon ICONDEPARTMENT24 =  new ImageIcon(getClass().getResource("/view/img/department24x24.png"));
    private final ImageIcon ICONDEPARTMENT64 =  new ImageIcon(getClass().getResource("/view/img/department64x64.png"));       

    public Department() {
        this.id = 0;
    }
    
    public Department(String name, String description, int managerId){
        this.name = name;
        this.description = description;
        this.managerId = managerId;
    }

    public Department(int id, String name, String description, int managerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getManagerId() {
        return managerId;
    }

    public ImageIcon getIcon(int sizeIcon) {
        ImageIcon icon;
        
        switch(sizeIcon){
            case 24: icon = ICONDEPARTMENT24;
                break;
            case 64: icon = ICONDEPARTMENT64;
                break;
            default: icon = ICONDEPARTMENT24;
        }
        
        return icon;
    }      
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    
    public void update(int id, String name, String description, int managerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.managerId = managerId;
    }   

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", description=" + description + ", managerId=" + managerId + '}';
    }
    
}
