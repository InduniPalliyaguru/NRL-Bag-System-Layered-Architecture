package lk.ijse.nrlbag.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

     String save(T entity) throws SQLException;

     boolean update(T entity) throws SQLException;

     boolean delete(String id) throws SQLException;

     T search(String contact) throws SQLException;

     List<T> get() throws SQLException;


     boolean saveData(T entity) throws SQLException;

     T searchData(int id) throws SQLException;

     boolean deleteData(int id) throws SQLException;

}
