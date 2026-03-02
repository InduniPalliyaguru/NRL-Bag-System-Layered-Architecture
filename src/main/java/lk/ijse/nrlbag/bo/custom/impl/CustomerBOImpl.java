package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.CustomerBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.CustomerDAO;
import lk.ijse.nrlbag.dto.CustomerDTO;
import lk.ijse.nrlbag.entity.Customer;
import net.sf.jasperreports.engine.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public CustomerDTO searchCustomer(String contact) throws SQLException {
        Customer cus = customerDAO.search(contact);
        return new CustomerDTO(
                cus.getCustomer_id(),
                cus.getName(),
                cus.getAddress(),
                cus.getContact(),
                cus.getCreate_date()
        );
    }

    @Override
    public List<CustomerDTO> getCustomer() throws SQLException {
        List<Customer> cus = customerDAO.get();
        List<CustomerDTO> cusDTO = new ArrayList<>();

        for (Customer customer : cus) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomer_id(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getContact(),
                    customer.getCreate_date()
            );

            cusDTO.add(customerDTO);
        }
        return cusDTO;
    }

    @Override
    public void printCustomerList() throws SQLException, JRException {
        customerDAO.printCustomerList();
    }

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

}
