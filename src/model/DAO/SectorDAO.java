package model.DAO;

import model.DB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Sector;

public class SectorDAO implements DAO<Sector>{

    private static SectorDAO instance = null;

    public SectorDAO getInstance() {
        if (instance == null) {
            instance = new SectorDAO();
        }
        return instance;
    }   
    
    
    @Override
    public boolean insert(Sector sector) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "INSERT INTO sectors (name,description,ref_departments,ref_user_leader) VALUES ('" + sector.getName() + "','" + sector.getDescription() + "'," + sector.getDepartmentId() + "," + sector.getLeaderId() + ")";
            
            statement.execute(sql);
            
            System.out.println("PASS - INSERT SECTOR: " + sector.getName() );
            
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - INSERT SECTOR: " + sector.getName() + " ERRO: " + e );
                return false;
            }
    }

    @Override
    public boolean update(Sector sector) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "UPDATE sectors SET name = '" + sector.getName() + "', description = '" + sector.getDescription() + "', ref_departments = " + sector.getDepartmentId() + ", ref_user_leader = " + sector.getLeaderId() + " WHERE id = " + sector.getId() + " ";
            
            statement.execute(sql);
            
            System.out.println("PASS - UPDATE SECTOR: " + sector.getName() );
            
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - UPDATE SECTOR: " + sector.getName() + " ERRO: " + e );
                return false;
            }
    }

    @Override
    public boolean delete(Sector sector) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            
            String sql = "DELETE FROM sectors WHERE id = " + sector.getId() + ""; 
            
            statement.execute(sql);
            
            System.out.println("PASS - DELETE SECTOR: " + sector.getName() );
            
            return true;

            } catch (Exception e) {
                System.out.println("FAIL - DELETE SECTOR: " + sector.getName() + " ERRO: " + e );
                return false;
            } 
    }

    @Override
    public ArrayList<Sector> getAll() {
        
        ArrayList sectors = new ArrayList();
        
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM sectors ORDER BY name";
            ResultSet result = statement.executeQuery(sql);                      
            
            while(result.next()){
            Sector sector = new Sector();    
                
            int id                  = result.getInt("id");
            String name             = result.getString("name");
            String description      = result.getString("description");
            int departmentId        = result.getInt("ref_departments");            
            int userId              = result.getInt("ref_user_leader");
                
            sector.update(id,name,description,departmentId,userId);
            sectors.add(sector);
            }
            return sectors;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET SECTORS ERRO: " + ex );
                return null;
            }
    }

    @Override
    public Sector get(int id) {
        try {
            Statement statement = ConnectionDB.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM sectors WHERE id = " + id;
            ResultSet result = statement.executeQuery(sql);  

            Sector sector = new Sector();           
            
            while(result.next()){
                
            int idSector            = result.getInt("id");
            String name             = result.getString("name");
            String description      = result.getString("description");
            int department          = result.getInt("ref_departments");
            int leader              = result.getInt("ref_user_leader");
            
            sector.update(idSector,name,description,department,leader);
            }
            
            System.out.println("PASS - GET SECTOR: " + id );
            return sector;
            
            } catch (SQLException ex) {
                System.out.println("FAIL - GET SECTOR: " + id + " ERRO: " + ex );
                return null;
            }
    }
    
    
}
