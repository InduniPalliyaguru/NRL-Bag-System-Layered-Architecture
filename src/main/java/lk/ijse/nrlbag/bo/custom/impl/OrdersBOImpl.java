package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.OrdersBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.OrdersDAO;
import lk.ijse.nrlbag.dto.OrderDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {

    private final OrdersDAO orderDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);

    @Override
    public List<OrderDTO> getOrders() throws SQLException {
        return orderDAO.getOrders();
    }

    @Override
    public OrderDTO searchOrderByOrderID(int id) throws SQLException {
        return orderDAO.searchOrderByOrderID(id);
    }

    @Override
    public OrderDTO searchOrderByCustomerID(int id) throws SQLException {
        return orderDAO.searchOrderByCustomerID(id);
    }

    @Override
    public int completeOrderCount() throws SQLException {
        return orderDAO.completeOrderCount();
    }

    @Override
    public int pendingOrderCount() throws SQLException {
        return orderDAO.pendingOrderCount();
    }

    @Override
    public int processingOrderCount() throws SQLException {
        return orderDAO.processingOrderCount();
    }

    @Override
    public int cancelledOrderCount() throws SQLException {
        return orderDAO.cancelledOrderCount();
    }
}
