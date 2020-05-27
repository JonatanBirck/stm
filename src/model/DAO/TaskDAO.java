package model.DAO;

import model.DB.ConnectionDB;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Task;
import model.User;

public class TaskDAO implements DAO<Task>{

    private static TaskDAO instance = null;

    public TaskDAO getInstance() {
        if (instance == null) {
            instance = new TaskDAO();
        }
        return instance;
    }    
    
    @Override
    public boolean insert(Task task) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
                    
        
            String sql = "INSERT INTO tasks (name,ref_user_created,description_create_task,ref_user_responsibility) VALUES "
                    + "('" + task.getName() + "'," + task.getUserCreatedId() + ",'" + task.getDescriptionCreate() + "'," + task.getUserResponsibility() + ")";
              
            statement.execute(sql);
            
            System.out.println("PASS - INSERT TASK: " + task.getName() );
            
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - INSERT TASK: " + task.getName() + " ERRO: " + e );
                return false;
            }
    }

    @Override
    public boolean update(Task task) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String ref_user_analyze = "";
            String ref_user_doit = "";
            String ref_user_check = "";
            String date_analyze = "";
            String date_doit = "";
            String date_check = "";
            String description_analyze_task = "";
            String description_doit_task = "";
            String description_check_task = ""; 
            String ref_user_responsibility = "";
            
            if(task.getUserAnalyzeId() != 0){
                ref_user_analyze = ", ref_user_analyze = " + task.getUserAnalyzeId();
            }
            if(task.getUserDoitId() != 0){
                ref_user_doit = ", ref_user_doit = " + task.getUserDoitId();
            }
            if(task.getUserCheckId() != 0){
                ref_user_check = ", ref_user_check = " + task.getUserCheckId();
            }
            if(task.getDateAnalyze() != null){
                date_analyze = ", date_analyze = " + task.getDateAnalyze().toString();
            }
            if(task.getDateDoit() != null){
                date_doit = ", date_doit = " + task.getDateDoit().toString();
            }
            if(task.getDateCheck() != null){
                date_check = ", date_check = " + task.getDateCheck().toString();
            }     
            if(task.getDescriptionAnalyze().equals(" ")){
                description_analyze_task = ", description_analyze_task = '" + task.getDescriptionAnalyze() + "'";
            }
            if(task.getDescriptionDoit().equals(" ")){
                description_doit_task = ", description_doit_task = '" + task.getDescriptionDoit() + "'";
            } 
            if(task.getDescriptionCheck().equals(" ")){
                description_check_task = ", description_check_task = '" + task.getDescriptionCheck() + "'";
            }
            if(task.getUserResponsibility() != 0){
                ref_user_responsibility = ", ref_user_responsibility = " + task.getUserResponsibility();
            }
            
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("UPDATE tasks ");
            sql.append("SET name = '" + task.getName() + "' "
                    + ", ref_user_created = " + task.getUserCreatedId() + " "
                    + "" + ref_user_analyze + ""
                    + "" + ref_user_doit + ""
                    + "" + ref_user_check + ""
                    + "" + date_analyze + ""
                    + "" + date_doit + ""
                    + "" + date_check + ""
                    + ", description_create_task = '" + task.getDescriptionCreate() + "' "
                    + "" + description_analyze_task + ""
                    + "" + description_doit_task + ""
                    + "" + description_check_task + ""
                    + "" + ref_user_responsibility + ""
                    + ", state = " + task.getState() + " "
                    + "WHERE id = " + task.getId() + " ");

            statement.execute(sql.toString());
            
            System.out.println("PASS - UPDATE TASK: " + task.getName() );
            return true;

        } catch (Exception e) {
            System.out.println("FAIL - UPDATE TASK: " + task.getName() + " ERRO: " + e );
            return false;
        }
    }

    @Override
    public boolean delete(Task task) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "DELETE FROM tasks WHERE id = " + task.getId() + ""; 
            
            statement.execute(sql);
            
            System.out.println("PASS - DELETE TASK: " + task.getName() );
            
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - DELETE TASK: " + task.getName() + " ERRO: " + e );
                return false;
            } 
    }

    @Override
    public ArrayList<Task> getAll() {
        
        ArrayList tasks = new ArrayList();
        
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM tasks ORDER BY name";
            ResultSet result = statement.executeQuery(sql);                      
            
            while(result.next()){
                
            Task task = new Task();    
                
            int idTask                  = result.getInt("id");
            String name                 = result.getString("name");
            int userCreate              = result.getInt("ref_user_created");
            int userAnalyze             = result.getInt("ref_user_analyze");
            int userDoit                = result.getInt("ref_user_doit");
            int userCheck               = result.getInt("ref_user_check");
            Date dateCreate             = result.getDate("date_create");
            Date dateAnalyze            = result.getDate("date_analyze");       
            Date dateDoit               = result.getDate("date_doit");
            Date dateCheck              = result.getDate("date_check");
            String descriptionCreate    = result.getString("description_create_task");
            String descriptionAnalyze   = result.getString("description_analyze_task");
            String descriptionDoit      = result.getString("description_doit_task");      
            String descriptionCheck     = result.getString("description_check_task");
            int userResponsibility      = result.getInt("ref_user_responsibility");
            int state                   = result.getInt("state");
            
            task.update(idTask,name,userCreate,userAnalyze,userDoit,userCheck,dateCreate,dateAnalyze,dateDoit,dateCheck,descriptionCreate,descriptionAnalyze,descriptionDoit,descriptionCheck,userResponsibility,state);
            tasks.add(task);
            }
            
            return tasks;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET TASKS ERRO: " + ex );
                return null;
            }
        
    }
    
    public ArrayList<Task> getAll(User user) {

        int userId = user.getId();
        ArrayList tasks = new ArrayList();

        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM tasks WHERE ref_user_created = " + userId + " OR ref_user_analyze = " + userId + " OR ref_user_doit = " + userId + " OR ref_user_check = " + userId + " OR ref_user_responsibility = " + userId;
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                Task task = new Task(); 
                
                int idTask                  = result.getInt("id");
                String name                 = result.getString("name");
                int userCreate              = result.getInt("ref_user_created");
                int userAnalyze             = result.getInt("ref_user_analyze");
                int userDoit                = result.getInt("ref_user_doit");
                int userCheck               = result.getInt("ref_user_check");
                Date dateCreate             = result.getDate("date_create");
                Date dateAnalyze            = result.getDate("date_analyze");
                Date dateDoit               = result.getDate("date_doit");
                Date dateCheck              = result.getDate("date_check");
                String descriptionCreate    = result.getString("description_create_task");
                String descriptionAnalyze   = result.getString("description_analyze_task");
                String descriptionDoit      = result.getString("description_doit_task");
                String descriptionCheck     = result.getString("description_check_task");
                int userResponsibility      = result.getInt("ref_user_responsibility");                
                int state                   = result.getInt("state");

                task.update(idTask, name, userCreate, userAnalyze, userDoit, userCheck, dateCreate, dateAnalyze, dateDoit, dateCheck, descriptionCreate, descriptionAnalyze, descriptionDoit, descriptionCheck,userResponsibility, state);
                tasks.add(task);
            }

            return tasks;

        } catch (SQLException ex) {
            System.out.println("FAIL - GET TASKS ERRO: " + ex);
            return null;
        }

    }
    
    public ArrayList<Task> getAll(User user, boolean userResponsability) {

        if(userResponsability){
            int userId = user.getId();
            ArrayList tasks = new ArrayList();

            try {
                Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
                String sql = "SELECT * FROM tasks WHERE ref_user_responsibility = " + userId;
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {

                    Task task = new Task();

                    int idTask = result.getInt("id");
                    String name = result.getString("name");
                    int userCreate = result.getInt("ref_user_created");
                    int userAnalyze = result.getInt("ref_user_analyze");
                    int userDoit = result.getInt("ref_user_doit");
                    int userCheck = result.getInt("ref_user_check");
                    Date dateCreate = result.getDate("date_create");
                    Date dateAnalyze = result.getDate("date_analyze");
                    Date dateDoit = result.getDate("date_doit");
                    Date dateCheck = result.getDate("date_check");
                    String descriptionCreate = result.getString("description_create_task");
                    String descriptionAnalyze = result.getString("description_analyze_task");
                    String descriptionDoit = result.getString("description_doit_task");
                    String descriptionCheck = result.getString("description_check_task");
                    int userResponsibility = result.getInt("ref_user_responsibility");
                    int state = result.getInt("state");

                    task.update(idTask, name, userCreate, userAnalyze, userDoit, userCheck, dateCreate, dateAnalyze, dateDoit, dateCheck, descriptionCreate, descriptionAnalyze, descriptionDoit, descriptionCheck, userResponsibility, state);
                    tasks.add(task);
                }

                return tasks;

            } catch (SQLException ex) {
                System.out.println("FAIL - GET TASKS ERRO: " + ex);
                return null;
            }
        }else{
            System.out.println("FAIL - GET TASKS ERRO: userResponsability FALSE");
            return null;    
        }

    }

    @Override
    public Task get(int id) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM tasks WHERE id = " + id;
            ResultSet result = statement.executeQuery(sql);                      
            
            Task task = new Task();
            
            while(result.next()){
                
            int idTask                  = result.getInt("id");
            String name                 = result.getString("name");
            int userCreate              = result.getInt("ref_user_created");
            int userAnalyze             = result.getInt("ref_user_analyze");
            int userDoit                = result.getInt("ref_user_doit");
            int userCheck               = result.getInt("ref_user_check");
            Date dateCreate             = result.getDate("date_create");
            Date dateAnalyze            = result.getDate("date_analyze");       
            Date dateDoit               = result.getDate("date_doit");
            Date dateCheck              = result.getDate("date_check");
            String descriptionCreate    = result.getString("description_create_task");
            String descriptionAnalyze   = result.getString("description_analyze_task");
            String descriptionDoit      = result.getString("description_doit_task");      
            String descriptionCheck     = result.getString("description_check_task");
            int userResponsibility      = result.getInt("ref_user_responsibility"); 
            int state                   = result.getInt("state");
            
            task.update(idTask,name,userCreate,userAnalyze,userDoit,userCheck,dateCreate,dateAnalyze,dateDoit,dateCheck,descriptionCreate,descriptionAnalyze,descriptionDoit,descriptionCheck,userResponsibility,state);

            }
            
            System.out.println("PASS - GET TASK: " + id);
            return task;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET TASK: " + id + " ERRO: " + ex );
                return null;
            }
    }
    
}
