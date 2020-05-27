package controller.manager;

import controller.helper.Validation;
import java.util.ArrayList;
import model.DAO.SectorDAO;
import model.Sector;

public class SectorManager {

    private static SectorManager instance = null;

    public static SectorManager getInstance() {
        if (instance == null) {
            instance = new SectorManager();
        }
        return instance;
    }

    public void addSector(Sector sector) {

        Validation validations = authenticateNew(sector);
        boolean authenticate = validations.isValidated();

        if (authenticate) {
            new SectorDAO().getInstance().insert(sector);
        }
    }

    public void updateSector(Sector sector) {

        Validation validations = authenticateUpdate(sector);
        boolean authenticate = validations.isValidated();

        if (authenticate) {
            new SectorDAO().getInstance().update(sector);
        }
    }

    public static void deleteSector(Sector sector) {
        new SectorDAO().getInstance().delete(sector);
    }

    public Sector getSector(int sectorId) {
        Sector sector = new SectorDAO().getInstance().get(sectorId);
        return sector;
    }

    public ArrayList<Sector> getSectors() {
        ArrayList<Sector> sectors = new SectorDAO().getInstance().getAll();
        return sectors;
    }

    public Validation authenticateNew(Sector sector) {

        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();

        //Validate - NameSector
        //NameSector - 1   
        if (sector.getName().isEmpty() || sector.getName().equals("Nome *")) {
            validate.setValidated(false);
            annotations.add("O nome do setor não pode ser vazio");
        }

        //Validate - DescriptionSector
        //DescriptionSector - 1         
        if (sector.getDescription().isEmpty() || sector.getDescription().equals("Descrição *")) {
            validate.setValidated(false);
            annotations.add("A descrição do setor não pode ser vazia");
        }

        //Validate - DepartmentSector
        int departmentId = sector.getDepartmentId();

        //DepartmentSector - 1      
        if (departmentId == 0) {
            validate.setValidated(false);
            annotations.add("Verifique o departamento");
        }

        //Validate - UserSector
        int leaderId = sector.getLeaderId();

        //UserSector - 1 
        if (leaderId == 0) {
            validate.setValidated(false);
            annotations.add("Verifique o usuário");
        }

        validate.setAnnotations(annotations);
        return validate;
    }

    public Validation authenticateUpdate(Sector sector) {

        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();

        //Validate - NameSector
        //NameSector - 1   
        if (sector.getName().isEmpty() || sector.getName().equals("Nome *")) {
            validate.setValidated(false);
            annotations.add("O nome do setor não pode ser vazio");
        }

        //Validate - DescriptionSector
        //DescriptionSector - 1         
        if (sector.getDescription().isEmpty() || sector.getDescription().equals("Descrição *")) {
            validate.setValidated(false);
            annotations.add("A descrição do setor não pode ser vazia");
        }

        //Validate - DepartmentSector
        int departmentId = sector.getDepartmentId();

        //DepartmentSector - 1      
        if (departmentId == 0) {
            validate.setValidated(false);
            annotations.add("Verifique o departamento");
        }

        //Validate - UserSector
        int leaderId = sector.getLeaderId();

        //UserSector - 1 
        if (leaderId == 0) {
            validate.setValidated(false);
            annotations.add("Verifique o usuário");
        }

        validate.setAnnotations(annotations);
        return validate;
    }

    public Validation authenticateDelete(Sector sector) {

        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();

        //VERIFICAR SE DA PARA DELETAR
        
        return validate;
    }

}
