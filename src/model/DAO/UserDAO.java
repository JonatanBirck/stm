package model.DAO;

import model.DB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import model.User;
import model.helper.Encryption;

public class UserDAO implements DAO<User>{

    private static UserDAO instance = null;

    public UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    } 
    
    @Override
    public boolean insert(User user) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String functionMain = "";
            String passwordEncry = new Encryption().getHash(user.getPassword()).toLowerCase();
  
            StringBuilder sql = new StringBuilder();
            
            sql.append("INSERT INTO users (login,password,name,email");
            
            if(user.getFunctionId() != 0){
                sql.append(",ref_function");
                functionMain = "," + user.getFunctionId();
            }

            
            sql.append(") VALUES ('" + user.getLogin() + "'"
                    + ",'" + passwordEncry + "'"
                    + ",'" + user.getName() + "'"
                    + ",'" + user.getEmail() + "'"
                    + "" + functionMain + ""
                    + ");");

            statement.execute(sql.toString());
            
            System.out.println("PASS - INSERT USER: " + user.getName() );
            
            return true;

        } catch (Exception e) {
            System.out.println("FAIL - INSERT USER: " + user.getName() + " ERRO: " + e );
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String functionMain = "";
            String passwordEncry = new Encryption().getHash(user.getPassword()).toLowerCase();
            
            if(user.getFunctionId() != 0){
                functionMain = ", ref_function = " + user.getFunctionId();
            }
  
            StringBuilder sql = new StringBuilder();
            
            sql.append("UPDATE users ");
            sql.append("SET login = '" + user.getLogin() + "' "
                    + ", password = '" + passwordEncry + "' "
                    + ", name = '" + user.getName() + "' "
                    + ", email = '" + user.getEmail() + "' "
                    + "" + functionMain + ""
                    + "WHERE id = " + user.getId() + " ");

            statement.execute(sql.toString());
            
            System.out.println("PASS - UPDATE USER: " + user.getName() );
            return true;

        } catch (Exception e) {
            System.out.println("FAIL - UPDATE USER: " + user.getName() + " ERRO: " + e );
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();

            String sql = "UPDATE users SET state = -1 WHERE id = " + user.getId();

            statement.execute(sql);

            System.out.println("PASS - UPDATE/DELETE USER: " + user.getName());
            return true;

        } catch (Exception e) {
            System.out.println("FAIL - UPDATE/DELETE USER: " + user.getName() + " ERRO: " + e);
            return false;
        }
    }

    public ArrayList<User> getAll() {
        
        ArrayList users = new ArrayList();
        
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM users ORDER BY name";
            ResultSet result = statement.executeQuery(sql);                      
            
            while(result.next()){
                User user = new User();    

                int idUser          = result.getInt("id");
                String login        = result.getString("login");
                String password     = result.getString("password");
                String name         = result.getString("name");
                String email        = result.getString("email");            
                int function        = result.getInt("ref_function");
                Date dateCreate     = result.getDate("date_create");
                int state           = result.getInt("state");

                if(state != -1){
                    user.update(idUser, login, password, name, email, function, dateCreate, state);
                    users.add(user);
                }
            }

            return users;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET USERS ERRO: " + ex );
                return null;
            }
    }

    @Override
    public User get(int id) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM users WHERE id = " + id;
            ResultSet result = statement.executeQuery(sql);                      
            
            User user = new User();           
            
            while(result.next()){
                
            int idUser          = result.getInt("id");
            String login        = result.getString("login");
            String password     = result.getString("password");
            String name         = result.getString("name");
            String email        = result.getString("email");            
            int function        = result.getInt("ref_function");
            Date dateCreate     = result.getDate("date_create");
            int state           = result.getInt("state");
            
            if(state != -1){
                user.update(idUser, login, password, name, email, function, dateCreate, state);
            } else
            {
                System.out.println("FAIL - GET USER: " + id + " ERRO: USUARIO DELETADO");
                return null;
            }

            }
            
            System.out.println("PASS - GET USER: " + id);            
            return user;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET USER: " + id + " ERRO: " + ex );
                return null;
            }
    }
    
    public User get(String loginUser) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM users WHERE login = '" + loginUser + "' ";
            ResultSet result = statement.executeQuery(sql);                      
            
            User user = new User();           
            
            while(result.next()){
                
            int idUser          = result.getInt("id");
            String login        = result.getString("login");
            String password     = result.getString("password");
            String name         = result.getString("name");
            String email        = result.getString("email");            
            int function        = result.getInt("ref_function");
            Date dateCreate     = result.getDate("date_create");
            int state           = result.getInt("state");
            
            if(state != -1){
                user.update(idUser, login, password, name, email, function, dateCreate, state);
            } else
            {
                System.out.println("FAIL - GET USER: " + loginUser + " ERRO: USUARIO DELETADO");
                return null;
            }

            }
            
            System.out.println("PASS - GET USER: " + loginUser);            
            return user;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET USER: " + loginUser + " ERRO: " + ex );
                return null;
            }
    }
   
}
