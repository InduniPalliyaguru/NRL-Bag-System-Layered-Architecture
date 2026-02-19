package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.SQLException;

public interface UserBO extends SuperBO{

    public UserDTO getUserDetails() throws SQLException;

    public boolean updateLoginPassword(String password) throws SQLException;

    public UserDTO validLogin(String name) throws SQLException;

    public boolean updateUserDetails(UserDTO userDTO) throws SQLException;

    public String getPasswordByEmail(String email) throws SQLException ;
}
