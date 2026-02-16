package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dto.CustomerDTO;
import lk.ijse.nrlbag.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {

    public String saveCustomer(CustomerDTO customerDTO) throws SQLException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;

    public boolean deleteCustomer(String id) throws SQLException;

    public CustomerDTO searchCustomer(String contact) throws SQLException;

    public List<CustomerDTO> getCustomer() throws SQLException;

    public int totalCustomerCount() throws SQLException;

}
