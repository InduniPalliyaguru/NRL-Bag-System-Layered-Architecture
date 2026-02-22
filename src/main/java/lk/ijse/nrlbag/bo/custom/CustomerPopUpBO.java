package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerPopUpBO extends SuperBO{

     String saveCustomer(CustomerDTO customerDTO) throws SQLException;

     boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;

     boolean deleteCustomer(String id) throws SQLException;

     CustomerDTO searchCustomer(String contact) throws SQLException;

}
