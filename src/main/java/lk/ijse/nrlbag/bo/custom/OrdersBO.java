package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrdersBO extends SuperBO{

    // get the all details in orders table join with customer details also
     List<OrderDTO> getOrders() throws SQLException;

     OrderDTO searchOrderByOrderID(int id) throws SQLException;

     OrderDTO searchOrderByCustomerID(int id) throws SQLException;

     int completeOrderCount() throws SQLException;

     int pendingOrderCount() throws SQLException;

     int processingOrderCount() throws SQLException;

     int cancelledOrderCount() throws SQLException;

}
