package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dto.UserDTO;
import lk.ijse.nrlbag.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO {

    public UserDTO validLogin(String name) throws SQLException;

    public UserDTO getUserDetails() throws SQLException;

    public boolean updateUserDetails(UserDTO userDTO) throws SQLException;

    public boolean updateLoginPassword(String password) throws SQLException;

    public String getPasswordByEmail(String email) throws SQLException ;

}
