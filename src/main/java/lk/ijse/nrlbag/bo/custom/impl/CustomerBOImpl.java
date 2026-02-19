package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.BOFactory;
import lk.ijse.nrlbag.bo.custom.CustomerBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.CustomerDAO;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.CustomerDTO;
import lk.ijse.nrlbag.entity.Customer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
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
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObj = getClass().getResourceAsStream("/lk/ijse/nrlbag/reports/customerList.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(reportObj);

        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

        JasperViewer.viewReport(jp, false);
    }

}
