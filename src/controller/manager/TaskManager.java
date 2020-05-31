package controller.manager;

import controller.helper.Validation;
import java.util.ArrayList;
import model.DAO.TaskDAO;
import model.Task;
import model.User;

public class TaskManager {
    
    private static TaskManager instance = null;
    
    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }
    
    public void addTask(Task task){
        
        Validation validations = authenticateNew(task);
        boolean authenticate = validations.isValidated();
        
        if(authenticate){
            new TaskDAO().getInstance().insert(task);
        } 
    }  
    
    public void updateTask(Task task){
        
        Validation validations = authenticateNextStage(task);
        boolean authenticate = validations.isValidated();
        
        if(authenticate){
            new TaskDAO().getInstance().update(task);
        }
    }
    
    public static void deleteTask(Task task){
        new TaskDAO().getInstance().delete(task);
    }
    
    public Task getTask(int taskId){
        Task task = new TaskDAO().getInstance().get(taskId);
        return task;        
    }
    
    public ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new TaskDAO().getInstance().getAll();
        return tasks;
    }   
    
    public ArrayList<Task> getTasksByUser(User user){
        ArrayList<Task> tasks = new ArrayList();
        for(Task task : new TaskDAO().getInstance().getAll()){
            if(task.getUserResponsibility() == user.getId())
            {
                tasks.add(task);
            }
        } 
        return tasks;
    }    
    
    public ArrayList<Task> getTasksByUserAndState(User user, int state){
        ArrayList<Task> tasks = new ArrayList();
        for(Task task : new TaskDAO().getInstance().getAll()){
            if(task.getUserResponsibility() == user.getId() && state == task.getState())
            {
                tasks.add(task);
            }
        } 
        return tasks;
    }         

    public Validation authenticateNew(Task task){
        
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();  
        
        //Validate - NameTask
        //NameTask - 1           
        if(task.getName().isEmpty() || task.getName().equals("Nome da Tarefa *")){
            validate.setValidated(false);
            annotations.add("O nome da tarefa não pode ser vazio");
        } 

        //Validate - DescriptionTask
        //DescriptionTask - 1          
        if(task.getDescriptionCreate().isEmpty() || task.getDescriptionCreate().equals("Descrição da Tarefa *")){
            validate.setValidated(false);
            annotations.add("A descrição da tarefa não pode ser vazia");
        }           
   
        //Validate - TaskResponsability
        int userResponsability = task.getUserResponsibility();
        
        //TaskResponsability - 1 
        if(userResponsability == 0){
            validate.setValidated(false);
            annotations.add("Verifique o responsável");
        }    
        
        validate.setAnnotations(annotations);
        return validate;  
    }        

    public Validation authenticateNextStage(Task task){
        
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();         
        int userResponsability = task.getUserResponsibility();
        
        int state = task.getState()-1;
        switch (state)
        {
            case 0: 
                if(task.getName().isEmpty() || task.getName().equals("Nome da Tarefa *")){
                    validate.setValidated(false);
                    annotations.add("O nome da tarefa não pode ser vazio");
                }      
                if(task.getDescriptionCreate().isEmpty() || task.getDescriptionCreate().equals("Descrição da Tarefa *")){
                    validate.setValidated(false);
                    annotations.add("A descrição da tarefa não pode ser vazia");
                }  
                if(userResponsability == 0){
                    validate.setValidated(false);
                    annotations.add("Verifique o responsável");
                }                 
                break;
            case 1:
                if(task.getDescriptionAnalyze().isEmpty() || task.getDescriptionAnalyze().equals("Descrição da Analise *")){
                    validate.setValidated(false);
                    annotations.add("A descrição da analise da tarefa não pode ser vazia");
                }  
                if(userResponsability == 0){
                    validate.setValidated(false);
                    annotations.add("Verifique o responsável");
                }                   
                break;
            case 2:
                if(task.getDescriptionDoit().isEmpty() || task.getDescriptionDoit().equals("Descrição da Execução *")){
                    validate.setValidated(false);
                    annotations.add("A descrição da execução da tarefa não pode ser vazia");
                }  
                if(userResponsability == 0){
                    validate.setValidated(false);
                    annotations.add("Verifique o responsável");
                }                   
                break;      
            case 3:
                if(task.getDescriptionCheck().isEmpty() || task.getDescriptionCheck().equals("Descrição da Verificação *")){
                    validate.setValidated(false);
                    annotations.add("A descrição da verificação da tarefa não pode ser vazia");
                }                  
                break;                     
        }
        validate.setAnnotations(annotations);
        return validate;  
    } 
}
