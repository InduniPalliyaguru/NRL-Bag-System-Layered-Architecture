package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrdersBO extends SuperBO{

    // get the all details in orders table join with customer details also
    public List<OrderDTO> getOrders() throws SQLException;

    public OrderDTO searchOrderByOrderID(int id) throws SQLException;

    public OrderDTO searchOrderByCustomerID(int id) throws SQLException;

    public int completeOrderCount() throws SQLException;

    public int pendingOrderCount() throws SQLException;

    public int processingOrderCount() throws SQLException;

    public int cancelledOrderCount() throws SQLException;

}
