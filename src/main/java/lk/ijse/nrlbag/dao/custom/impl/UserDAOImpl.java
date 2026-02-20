package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.UserDAO;
import lk.ijse.nrlbag.dto.UserDTO;
import lk.ijse.nrlbag.dao.CrudUtil;
import lk.ijse.nrlbag.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User search(String name) throws SQLException {

        ResultSet result = CrudUtil.execute("SELECT * FROM User WHERE userName=?", name);

        if(result.next()) {
            String userNAme = result.getString("userName");
            String password = result.getString("user_password");

            return new User(userNAme,password);
        }
        return null;
    }

    @Override
    public UserDTO getUserDetails() throws SQLException{
        ResultSet rs = CrudUtil.execute("SELECT * FROM User");

        if (rs.next()) {
            //userName, user_password, email, name, role
            return new UserDTO(
                    rs.getString("userName"),
                    rs.getString("user_password"),
                    rs.getString("name"),
                    rs.getString("role"),
                    rs.getString("email")
            );
        }
        return null;
    }

    @Override
    public boolean update(User entity) throws SQLException{

        return CrudUtil.execute("UPDATE User SET email=?, name=?, role=? WHERE userName=?",
                entity.getEmail(),
                entity.getName(),
                entity.getRole(),
                entity.getUserName());
    }

    @Override
    public boolean updateLoginPassword(String password) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE User SET user_password=? WHERE userName=?",
                password,
                "induni");

        return result;
    }

    @Override
    public String getPasswordByEmail(String email) throws SQLException {
        String retrievedPassword = null;

        ResultSet rs = CrudUtil.execute("SELECT user_password FROM User WHERE email=?", email);

        if (rs.next()) {
            retrievedPassword = rs.getString("user_password");
        }
        return retrievedPassword;
    }

    @Override
    public List<User> get() throws SQLException {
        return List.of();
    }

    @Override
    public boolean saveData(User entity) throws SQLException {
        return false;
    }

    @Override
    public User searchData(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteData(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String save(User entity) throws SQLException {
        return "";
    }
}
