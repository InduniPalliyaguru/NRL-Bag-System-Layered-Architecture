package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.SQLException;

public interface ChangePasswordBO extends SuperBO{

    public UserDTO getUserDetails() throws SQLException;

    public boolean updateLoginPassword(String password) throws SQLException;


}
