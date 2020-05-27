package controller.manager;

import controller.helper.Validation;
import java.util.ArrayList;
import model.DAO.DepartmentDAO;
import model.Department;

public class DepartmentManager {
       
    private static DepartmentManager instance = null;
    
    public static DepartmentManager getInstance() {
        if (instance == null) {
            instance = new DepartmentManager();
        }
        return instance;
    }
    
    public void addDepartment(Department department){

        Validation validations = authenticateNew(department);
        boolean authenticate = validations.isValidated();
        
        if(authenticate){
            new DepartmentDAO().getInstance().insert(department);
        }
    }
    
    public void updateDepartment(Department department){
        
        Validation validations = authenticateUpdate(department);
        boolean authenticate = validations.isValidated();
        
        if(authenticate){
            new DepartmentDAO().getInstance().update(department);
        }
    }
    
    public static void deleteDepartment(Department department){
        new DepartmentDAO().getInstance().delete(department);
    }
    
    public Department getDepartment(int departmentId){
        Department department = new DepartmentDAO().getInstance().get(departmentId);
        return department;        
    }
    
    public ArrayList<Department> getDepartments(){
        ArrayList<Department> department = new DepartmentDAO().getInstance().getAll();
        return department;
    }
    
    public Validation authenticateNew(Department department){
 
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();  
        
        //Validate - NameDepartment
        ArrayList departments = new ArrayList();
        departments = new DepartmentDAO().getAll();
        
        //NameDepartment - 1
        if(department.getName().isEmpty() || department.getName().equals("Nome *")){
            validate.setValidated(false);
            annotations.add("O nome do departamento não pode ser vazio");
        }
        
        //NameDepartment - 2
        for(int i = departments.size(); i > 0; i--){
            Department departmentResult = (Department) departments.get(i-1);
            String nameActualDepartment = departmentResult.getName();
            if(nameActualDepartment.equals(department.getName())){
                validate.setValidated(false);
                annotations.add("O nome do departamento já existe");
            }
        }
        
        //Validate - DescriptionDepartment
        //DescriptionDepartment - 1
        if(department.getDescription().isEmpty() || department.getDescription().equals("Descrição *")){
            validate.setValidated(false);
            annotations.add("A descrição do departamento não pode ser vazia");
        } 
        
        //Validate - UserDepartment
        int userId = department.getManagerId();
        
        //UserDepartment - 1
        if(userId == 0){
            validate.setValidated(false);
            annotations.add("Verifique o usuário");  
        }
        
        validate.setAnnotations(annotations);
        return validate;
    }
    
    public Validation authenticateUpdate(Department department){
 
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();  
        
        //Validate - NameDepartment
        ArrayList departments = new ArrayList();
        departments = new DepartmentDAO().getAll();
        
        //NameDepartment - 1
        if(department.getName().isEmpty() || department.getName().equals("Nome *")){
            validate.setValidated(false);
            annotations.add("O nome do departamento não pode ser vazio");
        }
        
        //Validate - DescriptionDepartment
        //DescriptionDepartment - 1
        if(department.getDescription().isEmpty() || department.getDescription().equals("Descrição *")){
            validate.setValidated(false);
            annotations.add("A descrição do departamento não pode ser vazia");
        } 
        
        //Validate - UserDepartment
        int userId = department.getManagerId();
        
        //UserDepartment - 1
        if(userId == 0){
            validate.setValidated(false);
            annotations.add("Verifique o usuário");  
        }
        
        validate.setAnnotations(annotations);
        return validate;
    }

    private Validation authenticateDelete(Department department){
 
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();  
        
        //DELETE
        
        return validate;
    }    
}
