package model;

import java.sql.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Task {
    
    private int id;
    private String name;
    private int userCreatedId;
    private int userAnalyzeId;
    private int userDoitId;
    private int userCheckId;
    private Date dateCreate;
    private Date dateAnalyze;
    private Date dateDoit;
    private Date dateCheck;
    private String descriptionCreate;
    private String descriptionAnalyze;
    private String descriptionDoit;
    private String descriptionCheck;
    private int userResponsibility;
    /*
    state -1 = cancelada
    state 0 = criada, não salva
    state 1 = analise
    state 2 = execução
    state 3 = verificação
    state 4 = concluida
    */
    private int state;
       
    //STATE MAPS
    private final ImageIcon ICONSTATEMAPTASKA =  new ImageIcon(getClass().getResource("/view/img/stateA_300x76.png")); //-1
    private final ImageIcon ICONSTATEMAPTASKB =  new ImageIcon(getClass().getResource("/view/img/stateB_300x76.png")); //0
    private final ImageIcon ICONSTATEMAPTASKC =  new ImageIcon(getClass().getResource("/view/img/stateC_300x76.png")); //1
    private final ImageIcon ICONSTATEMAPTASKD =  new ImageIcon(getClass().getResource("/view/img/stateD_300x76.png")); //2
    private final ImageIcon ICONSTATEMAPTASKE =  new ImageIcon(getClass().getResource("/view/img/stateE_300x76.png")); //3
    private final ImageIcon ICONSTATEMAPTASKF =  new ImageIcon(getClass().getResource("/view/img/stateF_300x76.png")); //4
    
    //STATE -1 = cancelada
    private final ImageIcon ICONTASKCANCELED16 =  new ImageIcon(getClass().getResource("/view/img/taskCanceled16x16.png"));
    private final ImageIcon ICONTASKCANCELED24 =  new ImageIcon(getClass().getResource("/view/img/taskCanceled24x24.png"));
    private final ImageIcon ICONTASKCANCELED32 =  new ImageIcon(getClass().getResource("/view/img/taskCanceled32x32.png"));
    private final ImageIcon ICONTASKCANCELED64 =  new ImageIcon(getClass().getResource("/view/img/taskCanceled64x64.png"));
    private final ImageIcon ICONTASKCANCELED128 =  new ImageIcon(getClass().getResource("/view/img/taskCanceled128x128.png"));
    private final ImageIcon ICONTASKCANCELED256 =  new ImageIcon(getClass().getResource("/view/img/taskCanceled256x256.png")); 
    
    //STATE 0 = criada, não salva
    private final ImageIcon ICONTASKREGISTER16 =  new ImageIcon(getClass().getResource("/view/img/nota16x16.png"));
    private final ImageIcon ICONTASKREGISTER24 =  new ImageIcon(getClass().getResource("/view/img/nota24x24.png"));
    private final ImageIcon ICONTASKREGISTER32 =  new ImageIcon(getClass().getResource("/view/img/nota32x32.png"));
    private final ImageIcon ICONTASKREGISTER64 =  new ImageIcon(getClass().getResource("/view/img/nota64x64.png"));
    private final ImageIcon ICONTASKREGISTER128 =  new ImageIcon(getClass().getResource("/view/img/nota128x128.png"));
    private final ImageIcon ICONTASKREGISTER256 =  new ImageIcon(getClass().getResource("/view/img/nota256x256.png"));
    
    //STATE 1 - analise
    private final ImageIcon ICONTASKANALYZE16 =  new ImageIcon(getClass().getResource("/view/img/analisar16x16.png"));
    private final ImageIcon ICONTASKANALYZE24 =  new ImageIcon(getClass().getResource("/view/img/analisar24x24.png"));
    private final ImageIcon ICONTASKANALYZE32 =  new ImageIcon(getClass().getResource("/view/img/analisar32x32.png"));
    private final ImageIcon ICONTASKANALYZE64 =  new ImageIcon(getClass().getResource("/view/img/analisar64x64.png"));
    private final ImageIcon ICONTASKANALYZE128 =  new ImageIcon(getClass().getResource("/view/img/analisar128x128.png"));
    private final ImageIcon ICONTASKANALYZE256 =  new ImageIcon(getClass().getResource("/view/img/analisar256x256.png"));
    
    //STATE 2 - execução
    private final ImageIcon ICONTASKEXECUTION16 =  new ImageIcon(getClass().getResource("/view/img/mecanico16x16.png"));
    private final ImageIcon ICONTASKEXECUTION24 =  new ImageIcon(getClass().getResource("/view/img/mecanico24x24.png"));
    private final ImageIcon ICONTASKEXECUTION32 =  new ImageIcon(getClass().getResource("/view/img/mecanico32x32.png"));
    private final ImageIcon ICONTASKEXECUTION64 =  new ImageIcon(getClass().getResource("/view/img/mecanico64x64.png"));
    private final ImageIcon ICONTASKEXECUTION128 =  new ImageIcon(getClass().getResource("/view/img/mecanico128x128.png"));
    private final ImageIcon ICONTASKEXECUTION256 =  new ImageIcon(getClass().getResource("/view/img/mecanico256x256.png"));
 
    //STATE 3 - verificação
    private final ImageIcon ICONTASKCHECKED16 =  new ImageIcon(getClass().getResource("/view/img/testador16x16.png")); 
    private final ImageIcon ICONTASKCHECKED24 =  new ImageIcon(getClass().getResource("/view/img/testador24x24.png"));
    private final ImageIcon ICONTASKCHECKED32 =  new ImageIcon(getClass().getResource("/view/img/testador32x32.png"));
    private final ImageIcon ICONTASKCHECKED64 =  new ImageIcon(getClass().getResource("/view/img/testador64x64.png"));
    private final ImageIcon ICONTASKCHECKED128 =  new ImageIcon(getClass().getResource("/view/img/testador128x128.png"));
    private final ImageIcon ICONTASKCHECKED256 =  new ImageIcon(getClass().getResource("/view/img/testador256x256.png"));
    
    //STATE 4 - concluida
    private final ImageIcon ICONTASKDONE16 =  new ImageIcon(getClass().getResource("/view/img/taskDone16x16.png"));
    private final ImageIcon ICONTASKDONE24 =  new ImageIcon(getClass().getResource("/view/img/taskDone24x24.png"));
    private final ImageIcon ICONTASKDONE32 =  new ImageIcon(getClass().getResource("/view/img/taskDone32x32.png"));
    private final ImageIcon ICONTASKDONE64 =  new ImageIcon(getClass().getResource("/view/img/taskDone64x64.png"));
    private final ImageIcon ICONTASKDONE128 =  new ImageIcon(getClass().getResource("/view/img/taskDone128x128.png"));
    private final ImageIcon ICONTASKDONE256 =  new ImageIcon(getClass().getResource("/view/img/taskDone256x256.png")); 


    public Task() {
        this.id = 0;
    }
    
    public Task(String name, int userCreatedId, String descriptionCreate, int userResponsibility) {
        this.name = name;
        this.userCreatedId = userCreatedId;
        this.descriptionCreate = descriptionCreate;
        this.userResponsibility = userResponsibility;
    }

    public Task(int id, String name, int userCreatedId, int userAnalyzeId, int userDoitId, int userCheckId, Date dateCreate, Date dateAnalyze, Date dateDoit, Date dateCheck, String descriptionCreate, String descriptionAnalyze, String descriptionDoit, String descriptionCheck, int userResponsibility, int state) {
        this.id = id;
        this.name = name;
        this.userCreatedId = userCreatedId;
        this.userAnalyzeId = userAnalyzeId;
        this.userDoitId = userDoitId;
        this.userCheckId = userCheckId;
        this.dateCreate = dateCreate;
        this.dateAnalyze = dateAnalyze;
        this.dateDoit = dateDoit;
        this.dateCheck = dateCheck;
        this.descriptionCreate = descriptionCreate;
        this.descriptionAnalyze = descriptionAnalyze;
        this.descriptionDoit = descriptionDoit;
        this.descriptionCheck = descriptionCheck;
        this.userResponsibility = userResponsibility;
        this.state = state;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }  

    public int getUserCreatedId() {
        return userCreatedId;
    }

    public int getUserAnalyzeId() {
        return userAnalyzeId;
    }

    public int getUserDoitId() {
        return userDoitId;
    }

    public int getUserCheckId() {
        return userCheckId;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public Date getDateAnalyze() {
        return dateAnalyze;
    }

    public Date getDateDoit() {
        return dateDoit;
    }

    public Date getDateCheck() {
        return dateCheck;
    }

    public String getDescriptionCreate() {
        return descriptionCreate;
    }

    public String getDescriptionAnalyze() {
        return descriptionAnalyze;
    }

    public String getDescriptionDoit() {
        return descriptionDoit;
    }

    public String getDescriptionCheck() {
        return descriptionCheck;
    }
    
    public int getUserResponsibility() {
        return userResponsibility;
    }

    public int getState() {
        return state;
    }

    
    public ImageIcon getIconMap(int state){
        
        ImageIcon icon;
        
        switch(state){
            case -1: icon = ICONSTATEMAPTASKA;
                break;
            case 0: icon = ICONSTATEMAPTASKB;
                break;                
            case 1: icon = ICONSTATEMAPTASKC;
                break;
            case 2: icon = ICONSTATEMAPTASKD;
                break;
            case 3: icon = ICONSTATEMAPTASKE;
                break;
            case 4: icon = ICONSTATEMAPTASKF;
                break;
            default: icon = ICONSTATEMAPTASKA;
        }
        
        return icon;
    }
    
    public ImageIcon getIcon(int state, int sizeImage){
        
        ImageIcon icon = new ImageIcon();
        
        switch(state){
            case -1: 
                switch(sizeImage){
                    case 16: icon = ICONTASKCANCELED16;
                        break;
                    case 24: icon = ICONTASKCANCELED24;
                        break;
                    case 32: icon = ICONTASKCANCELED32;
                        break;
                    case 64: icon = ICONTASKCANCELED64;
                        break;
                    case 128: icon = ICONTASKCANCELED128;
                        break;
                    case 256: icon = ICONTASKCANCELED256;
                        break;                        
                    default: icon = ICONTASKCANCELED16;
                }
                break;
            case 0: 
                switch(sizeImage){
                    case 16: icon = ICONTASKREGISTER16;
                        break;
                    case 24: icon = ICONTASKREGISTER24;
                        break;
                    case 32: icon = ICONTASKREGISTER32;
                        break;
                    case 64: icon = ICONTASKREGISTER64;
                        break;
                    case 128: icon = ICONTASKREGISTER128;
                        break;
                    case 256: icon = ICONTASKREGISTER256;
                        break;                       
                    default: icon = ICONTASKREGISTER16;
                }
                break;
            case 1:
                switch(sizeImage){
                    case 16: icon = ICONTASKANALYZE16;
                        break;
                    case 24: icon = ICONTASKANALYZE24;
                        break;
                    case 32: icon = ICONTASKANALYZE32;
                        break;
                    case 64: icon = ICONTASKANALYZE64;
                        break;
                    case 128: icon = ICONTASKANALYZE128;
                        break;
                    case 256: icon = ICONTASKANALYZE256;
                        break;                       
                    default: icon = ICONTASKANALYZE16;
                }                
                break;
            case 2:
                switch(sizeImage){
                    case 16: icon = ICONTASKEXECUTION16;
                        break;
                    case 24: icon = ICONTASKEXECUTION24;
                        break;
                    case 32: icon = ICONTASKEXECUTION32;
                        break;
                    case 64: icon = ICONTASKEXECUTION64;
                        break;
                    case 128: icon = ICONTASKEXECUTION128;
                        break;
                    case 256: icon = ICONTASKEXECUTION256;
                        break;                       
                    default: icon = ICONTASKEXECUTION16;
                }                  
                break;
            case 3:
                switch(sizeImage){
                    case 16: icon = ICONTASKCHECKED16;
                        break;
                    case 24: icon = ICONTASKCHECKED24;
                        break;
                    case 32: icon = ICONTASKCHECKED32;
                        break;
                    case 64: icon = ICONTASKCHECKED64;
                        break;
                    case 128: icon = ICONTASKCHECKED128;
                        break;
                    case 256: icon = ICONTASKCHECKED256;
                        break;                       
                    default: icon = ICONTASKCHECKED16;
                }                   
                break;
            case 4:
                switch(sizeImage){
                    case 16: icon = ICONTASKDONE16;
                        break;
                    case 24: icon = ICONTASKDONE24;
                        break;
                    case 32: icon = ICONTASKDONE32;
                        break;
                    case 64: icon = ICONTASKDONE64;
                        break;
                    case 128: icon = ICONTASKDONE128;
                        break;
                    case 256: icon = ICONTASKDONE256;
                        break;                       
                    default: icon = ICONTASKDONE16;
                }                  
                break;
        }
        
        return icon;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }  
    
    public void setUserCreatedId(int userCreatedId) {
        this.userCreatedId = userCreatedId;
    }

    public void setUserAnalyzeId(int userAnalyzeId) {
        this.userAnalyzeId = userAnalyzeId;
    }

    public void setUserDoitId(int userDoitId) {
        this.userDoitId = userDoitId;
    }

    public void setUserCheckId(int userCheckId) {
        this.userCheckId = userCheckId;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDateAnalyze(Date dateAnalyze) {
        this.dateAnalyze = dateAnalyze;
    }

    public void setDateDoit(Date dateDoit) {
        this.dateDoit = dateDoit;
    }

    public void setDateCheck(Date dateCheck) {
        this.dateCheck = dateCheck;
    }

    public void setDescriptionCreate(String descriptionCreate) {
        this.descriptionCreate = descriptionCreate;
    }

    public void setDescriptionAnalyze(String descriptionAnalyze) {
        this.descriptionAnalyze = descriptionAnalyze;
    }

    public void setDescriptionDoit(String descriptionDoit) {
        this.descriptionDoit = descriptionDoit;
    }

    public void setDescriptionCheck(String descriptionCheck) {
        this.descriptionCheck = descriptionCheck;
    }
    
    public void setUserResponsibility(int userResponsibility) {
        this.userResponsibility = userResponsibility;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void update(int id, String name, int userCreatedId, int userAnalyzeId, int userDoitId, int userCheckId, Date dateCreate, Date dateAnalyze, Date dateDoit, Date dateCheck, String descriptionCreate, String descriptionAnalyze, String descriptionDoit, String descriptionCheck, int userResponsibility, int state) {
        this.id = id;
        this.name = name;
        this.userCreatedId = userCreatedId;
        this.userAnalyzeId = userAnalyzeId;
        this.userDoitId = userDoitId;
        this.userCheckId = userCheckId;
        this.dateCreate = dateCreate;
        this.dateAnalyze = dateAnalyze;
        this.dateDoit = dateDoit;
        this.dateCheck = dateCheck;
        this.descriptionCreate = descriptionCreate;
        this.descriptionAnalyze = descriptionAnalyze;
        this.descriptionDoit = descriptionDoit;
        this.descriptionCheck = descriptionCheck;
        this.userResponsibility = userResponsibility;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", name=" + name + ", userCreatedId=" + userCreatedId + ", userAnalyzeId=" + userAnalyzeId + ", userDoitId=" + userDoitId + ", userCheckId=" + userCheckId + ", dateCreate=" + dateCreate + ", dateAnalyze=" + dateAnalyze + ", dateDoit=" + dateDoit + ", dateCheck=" + dateCheck + ", descriptionCreate=" + descriptionCreate + ", descriptionAnalyze=" + descriptionAnalyze + ", descriptionDoit=" + descriptionDoit + ", descriptionCheck=" + descriptionCheck + ", userResponsibility=" + userResponsibility + ", state=" + state + '}';
    }    

    public Icon getIcon(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
