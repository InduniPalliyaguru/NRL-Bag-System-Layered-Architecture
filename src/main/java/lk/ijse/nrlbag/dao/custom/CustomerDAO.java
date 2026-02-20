package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.CrudDAO;
import lk.ijse.nrlbag.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {

    int totalCustomerCount() throws SQLException;

}
