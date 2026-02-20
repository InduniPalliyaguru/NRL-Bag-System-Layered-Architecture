package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.CrudDAO;
import lk.ijse.nrlbag.entity.Orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders> {

     int totalOrderCount() throws SQLException;

     int completeOrderCount() throws SQLException;

     int pendingOrderCount() throws SQLException;

     int processingOrderCount() throws SQLException;

     int cancelledOrderCount() throws SQLException;

     int getOrderByMonths(int month) throws SQLException;

     ResultSet getMonthlyIncome() throws SQLException;

     boolean updateOrderRemainingPayment(Connection conn, double remaining, int id) throws SQLException;

     int getOverdueOrderCount() throws SQLException;

     int saveOrder(Orders entity) throws SQLException;

}
