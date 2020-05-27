package model.DAO;

import model.DB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Department;

public class DepartmentDAO implements DAO<Department>{

    private static DepartmentDAO instance = null;

    public DepartmentDAO getInstance() {
        if (instance == null) {
            instance = new DepartmentDAO();
        }
        return instance;
    }
    
    @Override
    public boolean insert(Department department) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "INSERT INTO departments (name,description,ref_user_manager) VALUES ('" + department.getName() + "','" + department.getDescription() + "'," + department.getManagerId() + ")";
            statement.execute(sql);
            
            System.out.println("PASS - INSERT DEPARTMENT: " + department.getName() );
            
            return true;
        } catch (Exception e) {
            System.out.println("FAIL - INSERT DEPARTMENT: " + department.getName() + " ERRO: " + e );
            return false;
        }
    }

    @Override
    public boolean update(Department department) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "UPDATE departments SET name = '" + department.getName() + "', description = '" + department.getDescription() + "', ref_user_manager = " + department.getManagerId()
                    + " WHERE id = " + department.getId() + " ";
            
            statement.execute(sql);
            
            System.out.println("PASS - UPDATE DEPARTMENT: " + department.getName() );
            System.out.println(sql);
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - UPDATE DEPARTMENT: " + department.getName() + " ERRO: " + e );
                return false;
            }
    }

    @Override
    public boolean delete(Department department) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "DELETE FROM departments WHERE id = " + department.getId() + ""; 
            
            statement.execute(sql);
            
            System.out.println("PASS - DELETE DEPARTMENT: " + department.getName() );
            
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - DELETE DEPARTMENT: " + department.getName() + " ERRO: " + e );
                return false;
            }
    }

    @Override
    public ArrayList<Department> getAll() {
        
        ArrayList departments = new ArrayList();
        
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM departments ORDER BY name";
            ResultSet result = statement.executeQuery(sql);                      
            
            while(result.next()){
            Department department = new Department();    
                
            int idDepartment        = result.getInt("id");
            String name             = result.getString("name");
            String description      = result.getString("description");
            int managerId           = result.getInt("ref_user_manager");
            
            department.update(idDepartment,name,description,managerId);
            departments.add(department);
            }
            return departments;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET DEPARTMENTS ERRO: " + ex );
                return null;
            }
    }

    @Override
    public Department get(int id) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM departments WHERE id = " + id;
            ResultSet result = statement.executeQuery(sql);  

            Department department = new Department();           
            
            while(result.next()){
                
            int idDepartment        = result.getInt("id");
            String name             = result.getString("name");
            String description      = result.getString("description");
            int managerId           = result.getInt("ref_user_manager");
            
            department.update(idDepartment,name,description,managerId);
            
            }
            
            System.out.println("PASS - GET DEPARTMENT: " + id );   
            return department;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET DEPARTMENT: " + id + " ERRO: " + ex );
                return null;
            }
    }
     
}
