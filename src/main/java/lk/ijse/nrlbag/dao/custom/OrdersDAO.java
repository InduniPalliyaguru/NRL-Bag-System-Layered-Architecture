package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.SuperDAO;
import lk.ijse.nrlbag.dto.OrderDTO;
import net.sf.jasperreports.engine.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends SuperDAO {

    // get the all details in orders table join with customer details also
    public List<OrderDTO> getOrders() throws SQLException;

    public int totalOrderCount() throws SQLException;

    public OrderDTO searchOrderByOrderID(int id) throws SQLException;

    public OrderDTO searchOrderByCustomerID(int id) throws SQLException;

    public boolean updateOrder(OrderDTO orderDto) throws SQLException;

    public boolean deleteOrder(int id) throws SQLException;

    public int completeOrderCount() throws SQLException;

    public int pendingOrderCount() throws SQLException;

    public int processingOrderCount() throws SQLException;

    public int cancelledOrderCount() throws SQLException;

    public int getOrderByMonths(int month) throws SQLException;

    public ResultSet getMonthlyIncome() throws SQLException;

    public boolean updateOrderRemainingPayment(Connection conn, double remaining, int id) throws SQLException;

    public int getOverdueOrderCount() throws SQLException;

    public void printOrderConfirmation(int orderID) throws SQLException, JRException;

    public int saveOrder(OrderDTO orderDto) throws SQLException;

}
