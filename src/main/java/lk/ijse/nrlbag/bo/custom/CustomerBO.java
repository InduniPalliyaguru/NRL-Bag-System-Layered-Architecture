package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.CustomerDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO{

     CustomerDTO searchCustomer(String contact) throws SQLException;

     List<CustomerDTO> getCustomer() throws SQLException;

     void printCustomerList() throws SQLException, JRException;

    String saveCustomer(CustomerDTO customerDTO) throws SQLException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;


}
