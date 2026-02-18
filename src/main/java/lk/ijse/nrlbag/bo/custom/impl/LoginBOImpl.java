package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.LoginBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.UserDAO;
import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public String getPasswordByEmail(String email) throws SQLException {
        return userDAO.getPasswordByEmail(email);
    }

    @Override
    public UserDTO validLogin(String name) throws SQLException {
        return userDAO.validLogin(name);
    }
}
