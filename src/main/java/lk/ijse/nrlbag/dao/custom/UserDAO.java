package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.CrudDAO;
import lk.ijse.nrlbag.dto.UserDTO;
import lk.ijse.nrlbag.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {

     UserDTO getUserDetails() throws SQLException;

     boolean updateLoginPassword(String password) throws SQLException;

     String getPasswordByEmail(String email) throws SQLException ;

}
