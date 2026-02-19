package lk.ijse.nrlbag.dao;

import lk.ijse.nrlbag.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    public String save(T entity) throws SQLException;

    public boolean update(T entity) throws SQLException;

    public boolean delete(String id) throws SQLException;

    public T search(String contact) throws SQLException;

    public List<T> get() throws SQLException;

}
