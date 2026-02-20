package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.UserBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.UserDAO;
import lk.ijse.nrlbag.dto.UserDTO;
import lk.ijse.nrlbag.entity.User;

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
        User user = userDAO.search(name);
        return new UserDTO(user.getUserName(), user.getUser_password());
    }

    @Override
    public boolean updateUserDetails(UserDTO userDTO) throws SQLException {
        return userDAO.update(new User(userDTO.getUserName(), userDTO.getEmail(), userDTO.getName(), userDTO.getRole()));
    }

    @Override
    public String getPasswordByEmail(String email) throws SQLException {
        return userDAO.getPasswordByEmail(email);
    }
}
