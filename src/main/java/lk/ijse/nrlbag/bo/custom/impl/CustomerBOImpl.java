package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.BOFactory;
import lk.ijse.nrlbag.bo.custom.CustomerBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.CustomerDAO;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.CustomerDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public CustomerDTO searchCustomer(String contact) throws SQLException {
        return customerDAO.search(contact);
    }

    @Override
    public List<CustomerDTO> getCustomer() throws SQLException {
        return customerDAO.get();
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
