package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.OrdersBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.OrdersDAO;
import lk.ijse.nrlbag.dao.custom.QueryDAO;
import lk.ijse.nrlbag.dto.OrderDTO;
import lk.ijse.nrlbag.dto.tm.OrdersTM;
import net.sf.jasperreports.engine.JRException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {

    private final OrdersDAO orderDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public List<OrderDTO> getOrders() throws SQLException {
        List<OrdersTM> ordersTm = queryDAO.getOrders();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (OrdersTM tm : ordersTm) {
            OrderDTO orderDTO = new OrderDTO(
                    tm.getId(),
                    tm.getCustomer_id(),
                    tm.getName(),
                    tm.getCustomerContact(),
                    tm.getOrder_date(),
                    tm.getDeadline(),
                    tm.getStatus(),
                    tm.getTotal_cost(),
                    tm.getRemaining_payment(),
                    tm.getProductId(),
                    tm.getQuantity()
            );

            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    @Override
    public OrderDTO searchOrderByOrderID(int id) throws SQLException {

        OrdersTM tm = queryDAO.searchOrderByOrderID(id);

        return new OrderDTO(
                tm.getId(),
                tm.getCustomer_id(),
                tm.getName(),
                tm.getCustomerContact(),
                tm.getOrder_date(),
                tm.getDeadline(),
                tm.getStatus(),
                tm.getTotal_cost(),
                tm.getRemaining_payment(),
                tm.getProductId(),
                tm.getQuantity()
        );
    }

    @Override
    public OrderDTO searchOrderByCustomerID(int id) throws SQLException {
        OrdersTM tm = queryDAO.searchOrderByCustomerID(id);

        return new OrderDTO(
                tm.getId(),
                tm.getCustomer_id(),
                tm.getName(),
                tm.getCustomerContact(),
                tm.getOrder_date(),
                tm.getDeadline(),
                tm.getStatus(),
                tm.getTotal_cost(),
                tm.getRemaining_payment(),
                tm.getProductId(),
                tm.getQuantity()
        );
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
