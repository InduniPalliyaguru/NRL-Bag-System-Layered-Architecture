package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.CustomerPopUpBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.CustomerDAO;
import lk.ijse.nrlbag.dto.CustomerDTO;
import lk.ijse.nrlbag.entity.Customer;

import java.sql.SQLException;

public class CustomerPopUpBOImpl implements CustomerPopUpBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String saveCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.save(new Customer(customerDTO.getName(), customerDTO.getAddress(), customerDTO.getContact()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.update(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getContact()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO searchCustomer(String contact) throws SQLException {
        Customer customer = customerDAO.search(contact);

        return new CustomerDTO(
                customer.getCustomer_id(),
                customer.getName(),
                customer.getAddress(),
                customer.getContact(),
                customer.getCreate_date());
    }

}
