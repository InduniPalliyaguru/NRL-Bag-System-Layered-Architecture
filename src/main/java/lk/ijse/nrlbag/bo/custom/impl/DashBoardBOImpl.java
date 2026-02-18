package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.DashBoardBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.*;
import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardBOImpl implements DashBoardBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public int totalCustomerCount() throws SQLException {
        return customerDAO.totalCustomerCount();
    }

    @Override
    public int totalOrderCount() throws SQLException {
        return ordersDAO.totalOrderCount();
    }

    @Override
    public int totalLowMaterialCount() throws SQLException {
        return materialDAO.totalLowMaterialCount();
    }

    @Override
    public int totalPendingPaymentsCount() throws SQLException {
        return paymentDAO.totalPendingPaymentsCount();
    }

    @Override
    public int getOverdueOrderCount() throws SQLException {
        return ordersDAO.getOverdueOrderCount();
    }

    @Override
    public int getOrderByMonths(int month) throws SQLException {
        return ordersDAO.getOrderByMonths(month);
    }

    @Override
    public ResultSet getMonthlyIncome() throws SQLException {
        return ordersDAO.getMonthlyIncome();
    }

    @Override
    public UserDTO getUserDetails() throws SQLException {
        return userDAO.getUserDetails();
    }
}
