package model.DAO;

import model.DB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Function;

public class FunctionDAO implements DAO<Function>{

    private static FunctionDAO instance = null;

    public FunctionDAO getInstance() {
        if (instance == null) {
            instance = new FunctionDAO();
        }
        return instance;
    }   
    
    @Override
    public boolean insert(Function function) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "INSERT INTO functions (name,description,ref_sector) VALUES ('" + function.getName() + "','" + function.getDescription() + "'," + function.getSectorId() + ")";
            
            statement.execute(sql);
            
            System.out.println("PASS - INSERT FUNCTION: " + function.getName() );
            
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - INSERT FUNCTION: " + function.getName() + " ERRO: " + e );
                return false;
            }
    }

    @Override
    public boolean update(Function function) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "UPDATE functions SET name = '" + function.getName() + "', description = '" + function.getDescription() + "', ref_sector = " + function.getSectorId() + " WHERE id = " + function.getId() + " ";
            
            statement.execute(sql);
            
            System.out.println("PASS - UPDATE FUNCTION: " + function.getName() );
            
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - UPDATE FUNCTION: " + function.getName() + " ERRO: " + e );
                return false;
            }
    }       

    @Override
    public boolean delete(Function function) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "DELETE FROM functions WHERE id = " + function.getId() + ""; 
            
            statement.execute(sql);
            
            System.out.println("PASS - DELETE FUNCTION: " + function.getName() );
            
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - DELETE FUNCTION: " + function.getName() + " ERRO: " + e );
                return false;
            }        
    }

    @Override
    public ArrayList<Function> getAll() {
        ArrayList functions = new ArrayList();
        
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM functions ORDER BY name";
            ResultSet result = statement.executeQuery(sql);                      
            
            while(result.next()){
            Function function = new Function();    
                
            int id                  = result.getInt("id");
            String name             = result.getString("name");
            String description      = result.getString("description");
            int sectorId            = result.getInt("ref_sector");
            
            function.update(id,name,description,sectorId);
            functions.add(function);
            }
            return functions;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET FUNCTIONS ERRO: " + ex );
                return null;
            }
    }

    @Override
    public Function get(int id) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM functions WHERE id = " + id;
            ResultSet result = statement.executeQuery(sql);  

            Function function = new Function();           
            
            while(result.next()){
            int idFunction      = result.getInt("id");
            String name         = result.getString("name");
            String description  = result.getString("description");
            int sector          = result.getInt("ref_sector");

            function.update(idFunction,name,description,sector);
            }
            
            System.out.println("PASS - GET FUNCTION: " + id );   
            return function;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET FUNCTION: " + id + " ERRO: " + ex );
                return null;
            }
    }
    
}
