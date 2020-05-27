package model.DAO;

import java.util.ArrayList;


public interface DAO <Obj> {
    
    public boolean insert(Obj arg);

    public boolean update(Obj arg);

    public boolean delete(Obj arg);

    public ArrayList<Obj> getAll();

    public Obj get(int id);

}
