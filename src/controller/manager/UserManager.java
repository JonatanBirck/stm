package controller.manager;

import controller.helper.Validation;
import java.util.ArrayList;
import model.DAO.UserDAO;
import model.User;
import model.helper.Encryption;

public class UserManager {
    
    private static UserManager instance = null;
    private static User userLogged = null;
    
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    
    public void addUser(User user){
        
        Validation validations = authenticateNewUser(user);
        boolean authenticate = validations.isValidated();
        
        if(authenticate){
            new UserDAO().getInstance().insert(user);
        } 
    }  
    
    public void updateUser(User user){
        
        Validation validations = authenticateUpdate(user);
        boolean authenticate = validations.isValidated();
        
        if(authenticate){
            new UserDAO().getInstance().update(user);
        }
    }
    
    public static void deleteUser(User user){
        new UserDAO().getInstance().delete(user);
    }
    
    public User getUser(int userId){
        User user = new UserDAO().getInstance().get(userId);
        return user;        
    }
    
    public User getUserByLogin(String login){
        User user = new UserDAO().getInstance().get(login);
        return user;        
    }
    
    public User getUserLogged(){
        return UserManager.userLogged;
    }
    
    public void setUserLogged(User user){
        UserManager.userLogged = user;
    }
    
    public ArrayList<User> getUsers(){
        ArrayList<User> user = new UserDAO().getInstance().getAll();
        return user;
    }      
    
    public Validation authenticateLogin(String login, String password){
        
            Validation validate = new Validation(true);
            ArrayList<String> annotations = new ArrayList(); 
            
            String passwordEncryption = new Encryption().getHash(password).toLowerCase();

            User user = new UserDAO().getInstance().get(login);
            
            
            if(user.getLogin().isEmpty()){
                validate.setValidated(false);
                annotations.add("O login está incorreto");  
            }
            if(!user.getPassword().equals(passwordEncryption)) 
            {
                validate.setValidated(false);
                annotations.add("A senha está incorreto");  
            }

            validate.setAnnotations(annotations);
            return validate;
    }
    
    public Validation authenticateNewUser(User user){
        
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();   
    
        //Validate - LoginUser
        ArrayList users = new UserDAO().getAll();     
    
        //LoginUser - 1 
        if(user.getLogin().isEmpty() || user.getLogin().equals("Login *") || user.getLogin().length()>3){
            validate.setValidated(false);
            annotations.add("O login não pode ser vazio");
        }
        
        //LoginUser - 2
        for(int i = users.size(); i > 0; i--){
            User userResult = (User) users.get(i-1);
            String nameActualUser = userResult.getLogin();
            
            if(nameActualUser.equals(user.getLogin())){
                validate.setValidated(false);
                annotations.add("Este login já está em uso");
            }
        }

        //Validate - PasswordUser
        //PasswordUser - 1 
        if(user.getPassword().isEmpty() || user.getPassword().equals("Senha *")){
            validate.setValidated(false);
            annotations.add("A senha não pode ser vazia"); 
        }

        //PasswordUser - 2
        if(user.getPassword().length() < 8){
            validate.setValidated(false);
            annotations.add("A senha não pode ter menos de 8 caracteres");           
        }
        
        //Validate - NameUser
        //NameUser - 1        
        if(user.getName().isEmpty() || user.getName().equals("Nome *")){
            validate.setValidated(false);
            annotations.add("O nome não pode ser vazio"); 
        }
        
        //NameUser - 2
        for(int i = users.size(); i > 0; i--){
            User userResult = (User) users.get(i-1);
            String nameActualUser = userResult.getName();
            
            if(nameActualUser.equals(user.getName())){
                validate.setValidated(false);
                annotations.add("Já possui este nome cadastrado"); 
            }
        }
    
        //Validate - EmailUser
        //EmailUser - 1
        if(user.getEmail().isEmpty() || user.getEmail().equals("Email *")){
            validate.setValidated(false);
            annotations.add("O email nao pode ser vazio"); 
        }        
        
        //EmailUser - 2
        if(!user.getEmail().contains("@")){
            validate.setValidated(false);
            annotations.add("O email nao é valido");      
        }

        validate.setAnnotations(annotations);
        return validate; 
    }  
    
    public Validation authenticateUpdate(User user){
        
        Validation validate = new Validation(true);
        ArrayList<String> annotations = new ArrayList();   
    
        //Validate - LoginUser
        ArrayList users = new UserDAO().getAll();     
    
        //LoginUser - 1 
        if(user.getLogin().isEmpty() || user.getLogin().equals("Login *") || user.getLogin().length()>3){
            validate.setValidated(false);
            annotations.add("O login não pode ser vazio");
        }
        

        //Validate - PasswordUser
        //PasswordUser - 1 
        if(user.getPassword().isEmpty() || user.getPassword().equals("Senha *")){
            validate.setValidated(false);
            annotations.add("A senha não pode ser vazia"); 
        }

        //PasswordUser - 2
        if(user.getPassword().length() < 8){
            validate.setValidated(false);
            annotations.add("A senha não pode ter menos de 8 caracteres");           
        }
        
        //Validate - NameUser
        //NameUser - 1        
        if(user.getName().isEmpty() || user.getName().equals("Nome *")){
            validate.setValidated(false);
            annotations.add("O nome não pode ser vazio"); 
        }
    
        //Validate - EmailUser
        //EmailUser - 1
        if(user.getEmail().isEmpty() || user.getEmail().equals("Email *")){
            validate.setValidated(false);
            annotations.add("O email nao pode ser vazio"); 
        }        
        
        //EmailUser - 2
        if(!user.getEmail().contains("@")){
            validate.setValidated(false);
            annotations.add("O email nao é valido");      
        }

        validate.setAnnotations(annotations);
        return validate; 
    }  
        
}
