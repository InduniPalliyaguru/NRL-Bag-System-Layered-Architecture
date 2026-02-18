package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.ChangePasswordBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.UserDAO;
import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.SQLException;

public class ChangePasswordBOImpl implements ChangePasswordBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public UserDTO getUserDetails() throws SQLException {
        return userDAO.getUserDetails();
    }

    @Override
    public boolean updateLoginPassword(String password) throws SQLException {
        return userDAO.updateLoginPassword(password);
    }
}
