package controller.manager;

import controller.helper.Validation;
import java.util.ArrayList;
import model.DAO.FunctionDAO;
import model.Function;

public class FunctionManager {
    
    private static FunctionManager instance = null;
    
    public static FunctionManager getInstance() {
        if (instance == null) {
            instance = new FunctionManager();
        }
        return instance;
    }
    
    public void addFunction(Function function){
        
        Validation validations = authenticateNew(function);
        boolean authenticate = validations.isValidated();
        
        if(authenticate){
            new FunctionDAO().getInstance().insert(function);
        } 
    }  
    
    public void updateFunction(Function function){
        
        Validation validations = authenticateUpdate(function);
        boolean authenticate = validations.isValidated();
        
        if(authenticate){
            new FunctionDAO().getInstance().update(function);
        }
    }
    
    public static void deleteFunction(Function function){
        new FunctionDAO().getInstance().delete(function);
    }
    
    public Function getFunction(int functionId){
        Function function = new FunctionDAO().getInstance().get(functionId);
        return function;        
    }
    
    public ArrayList<Function> getFunctions(){
        ArrayList<Function> functions = new FunctionDAO().getInstance().getAll();
        return functions;
    }   
 
    public Validation authenticateNew(Function function){
        
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();  
        
        //Validate - NameFunction
        //NameFunction - 1
        if(function.getName().isEmpty() || function.getName().equals("Nome *")){
            validate.setValidated(false);
            annotations.add("O nome da função não pode ser vazio");
        }      
        
        //Validate - DescriptionFunction
        //DescriptionFunction - 1 
        if(function.getDescription().isEmpty() || function.getDescription().equals("Descrição *")){
            validate.setValidated(false);
            annotations.add("A descrição da função não pode ser estar vazia");
        }   
        
        //Validate - SectorFunction
        int userId = function.getSectorId();
        
        //SectorFunction - 1 
        if(userId == 0){
            validate.setValidated(false);
            annotations.add("Verifique o setor");
        }  
        
        validate.setAnnotations(annotations);
        return validate;
    }
    
    public Validation authenticateUpdate(Function function){
        
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();  
        
        //Validate - NameFunction
        //NameFunction - 1
        if(function.getName().isEmpty() || function.getName().equals("Nome *")){
            validate.setValidated(false);
            annotations.add("O nome da função não pode ser vazio");
        }      
        
        //Validate - DescriptionFunction
        //DescriptionFunction - 1 
        if(function.getDescription().isEmpty() || function.getDescription().equals("Descrição *")){
            validate.setValidated(false);
            annotations.add("A descrição da função não pode ser estar vazia");
        }   
        
        //Validate - SectorFunction
        int userId = function.getSectorId();
        
        //SectorFunction - 1 
        if(userId == 0){
            validate.setValidated(false);
            annotations.add("Verifique o setor");
        }  
        
        validate.setAnnotations(annotations);
        return validate;
    }    
    
    private Validation authenticateDelete(Function function){
        
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();  
        
        //DELETE
        
        return validate;
    }    
    
}
