package model;

import javax.swing.ImageIcon;

public class Sector {
    
    private int id;
    private String name;
    private String description;
    private int departmentId;
    private int leaderId;
    
    private final ImageIcon ICONSECTOR24 =  new ImageIcon(getClass().getResource("/view/img/group24x24.png"));
    private final ImageIcon ICONSECTOR64 =  new ImageIcon(getClass().getResource("/view/img/group64x64.png"));    

    public Sector() {
        this.id = 0;
    }
    
    public Sector(String name, String description, int departmentId, int leaderId) {
        this.name = name;
        this.description = description;
        this.departmentId = departmentId;
        this.leaderId = leaderId;
    }

    public Sector(int id, String name, String description, int departmentId, int leaderId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.departmentId = departmentId;
        this.leaderId = leaderId;
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

    public int getDepartmentId() {
        return departmentId;
    }

    public int getLeaderId() {
        return leaderId;
    }
    
    public ImageIcon getIcon(int sizeIcon) {
        ImageIcon icon;
        
        switch(sizeIcon){
            case 24: icon = ICONSECTOR24;
                break;
            case 64: icon = ICONSECTOR64;
                break;
            default: icon = ICONSECTOR24;
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

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }
    
    public void update(int id, String name, String description, int departmentId, int leaderId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.departmentId = departmentId;
        this.leaderId = leaderId;
    }

    @Override
    public String toString() {
        return "Sector{" + "id=" + id + ", name=" + name + ", description=" + description + ", departmentId=" + departmentId + ", leaderId=" + leaderId + '}';
    }
    
}
