package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.BOFactory;
import lk.ijse.nrlbag.bo.custom.CustomerBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.CustomerDAO;
import lk.ijse.nrlbag.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String saveCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.save(customerDTO);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.update(customerDTO);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO searchCustomer(String contact) throws SQLException {
        return customerDAO.search(contact);
    }

    @Override
    public List<CustomerDTO> getCustomer() throws SQLException {
        return customerDAO.get();
    }

    @Override
    public int totalCustomerCount() throws SQLException {
        return customerDAO.totalCustomerCount();
    }
}
