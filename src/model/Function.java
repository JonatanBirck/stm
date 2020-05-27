package model;

import javax.swing.ImageIcon;

public class Function {
 
    private int id;
    private String name;
    private String description;
    private int sectorId;
    
    private final ImageIcon ICONFUNCTION24 =  new ImageIcon(getClass().getResource("/view/img/work24x24.png"));
    private final ImageIcon ICONFUNCTION64 =  new ImageIcon(getClass().getResource("/view/img/work64x64.png"));    

    public Function() {
        this.id = 0;
    }   

    public Function(String name, String description, int sectorId) {
        this.name = name;
        this.description = description;
        this.sectorId = sectorId;
    }

    public Function(int id, String name, String description, int sectorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sectorId = sectorId;
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

    public int getSectorId() {
        return sectorId;
    }
    
    public ImageIcon getIcon(int sizeIcon) {
        ImageIcon icon;
        
        switch(sizeIcon){
            case 24: icon = ICONFUNCTION24;
                break;
            case 64: icon = ICONFUNCTION64;
                break;
            default: icon = ICONFUNCTION24;
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

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public void update(int id, String name, String description, int sectorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sectorId = sectorId;
    }     
    
    @Override
    public String toString() {
        return "Function{" + "id=" + id + ", name=" + name + ", description=" + description + ", sectorId=" + sectorId + '}';
    }
    
}
