package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.UserBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.UserDAO;
import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public UserDTO getUserDetails() throws SQLException {
        return userDAO.getUserDetails();
    }

    @Override
    public boolean updateLoginPassword(String password) throws SQLException {
        return userDAO.updateLoginPassword(password);
    }

    @Override
    public UserDTO validLogin(String name) throws SQLException {
        return userDAO.validLogin(name);
    }

    @Override
    public boolean updateUserDetails(UserDTO userDTO) throws SQLException {
        return userDAO.updateUserDetails(userDTO);
    }

    @Override
    public String getPasswordByEmail(String email) throws SQLException {
        return userDAO.getPasswordByEmail(email);
    }
}
