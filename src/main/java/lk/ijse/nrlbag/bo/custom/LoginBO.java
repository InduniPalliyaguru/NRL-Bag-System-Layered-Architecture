package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO{

    public String getPasswordByEmail(String email) throws SQLException;

    public UserDTO validLogin(String name) throws SQLException;

}
