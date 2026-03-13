package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.bo.SuperBO;
import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {

     UserDTO getUserDetails() throws SQLException;

     boolean updateLoginPassword(String password) throws SQLException;

     UserDTO validLogin(String name) throws SQLException;

     boolean updateUserDetails(UserDTO userDTO) throws SQLException;

     String getPasswordByEmail(String email) throws SQLException ;
}
