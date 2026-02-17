package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO{

    public String saveCustomer(CustomerDTO customerDTO) throws SQLException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;

    public boolean deleteCustomer(String id) throws SQLException;

    public CustomerDTO searchCustomer(String contact) throws SQLException;

    public List<CustomerDTO> getCustomer() throws SQLException;

    public int totalCustomerCount() throws SQLException;

}
