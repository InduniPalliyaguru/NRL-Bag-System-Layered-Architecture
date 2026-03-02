package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.OrdersDAO;
import lk.ijse.nrlbag.dao.CrudUtil;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.entity.Orders;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersDAOImpl implements OrdersDAO {

    @Override
    public int totalOrderCount() throws SQLException {

        // in here get the number of orders from customer table
        ResultSet result = CrudUtil.execute("SELECT COUNT(*) AS Total_orders FROM Orders");
        int orderCount = 0;

        // get the int value from the execution
        if (result.next()) {
            orderCount = result.getInt("Total_orders");
        }

        return orderCount;

    }

    @Override
    public boolean update(Orders entity) throws SQLException {

        // pass the query for update the database
        boolean result = CrudUtil.execute("UPDATE Orders SET customer_id=?, order_date=?, deadline=?, status=?, total_cost=?, remaining_payment=? WHERE orders_id=?;",
                entity.getCustomer_id(),
                entity.getOrder_date(),
                entity.getDeadline(),
                entity.getStatus(),
                entity.getTotal_cost(),
                entity.getTotal_cost(),
                entity.getOrders_id()
        );
        return result;

    }

    @Override
    public boolean deleteData(int id) throws SQLException {

        boolean result = CrudUtil.execute("DELETE FROM Orders WHERE orders_id=?",id);
        return result;

    }

    @Override
    public int completeOrderCount() throws SQLException{

        // in here get the number of orders from customer table
        ResultSet result = CrudUtil.execute("SELECT COUNT(*) AS Total_Complete_Orders FROM Orders WHERE status='Completed';");
        int orderCount = 0;

        // get the int value from the execution
        if (result.next()) {
            orderCount = result.getInt("Total_Complete_Orders");
        }

        return orderCount;

    }

    @Override
    public int pendingOrderCount() throws SQLException{

        // in here get the number of orders from customer table
        ResultSet result = CrudUtil.execute("SELECT COUNT(*) AS Total_Pending_Orders FROM Orders WHERE status='Pending';");
        int orderCount = 0;

        // get the int value from the execution
        if (result.next()) {
            orderCount = result.getInt("Total_Pending_Orders");
        }

        return orderCount;

    }

    @Override
    public int processingOrderCount() throws SQLException{

        // in here get the number of orders from customer table
        ResultSet result = CrudUtil.execute("SELECT COUNT(*) AS Total_Processing_Orders FROM Orders WHERE status='Processing';");
        int orderCount = 0;

        // get the int value from the execution
        if (result.next()) {
            orderCount = result.getInt("Total_Processing_Orders");
        }

        return orderCount;

    }

    @Override
    public int cancelledOrderCount() throws SQLException{

        // in here get the number of orders from customer table
        ResultSet result = CrudUtil.execute("SELECT COUNT(*) AS Total_Cancel_Orders FROM Orders WHERE status='Cancelled';");
        int orderCount = 0;

        // get the int value from the execution
        if (result.next()) {
            orderCount = result.getInt("Total_Cancel_Orders");
        }

        return orderCount;

    }

    @Override
    public int getOrderByMonths(int month) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) FROM Orders WHERE MONTH(order_date)=?",month);

        if(rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public ResultSet getMonthlyIncome() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT MONTH(payment_date) AS month, SUM(amount) AS income " +
                "FROM Payment WHERE status IN ('Partial','Completed') " +
                "GROUP BY MONTH(payment_date)");

        return resultSet;
    }

    @Override
    public boolean updateOrderRemainingPayment(Connection conn, double remaining, int id) throws SQLException {

        // here get the total order cost
        boolean orderUpdate = CrudUtil.execute(
                conn,
                "UPDATE Orders SET remaining_payment = ? WHERE orders_id = ?",
                remaining,
                id
        );

        return orderUpdate;

    }

    @Override
    public int getOverdueOrderCount() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(*) FROM Orders WHERE deadline < CURDATE() AND status NOT IN ('Completed','Cancelled')");

        if (rs.next()) {
            return rs.getInt(1);  // get the value from first column
        }
        return 0;
    }

    @Override
    public int saveOrder(Orders entity) throws SQLException {

        return CrudUtil.executeAndReturnGeneratedKey("INSERT INTO Orders (customer_id, order_date, deadline, status, total_cost, remaining_payment) VALUES (?,?,?,?,?,?)",
                entity.getCustomer_id(),
                entity.getOrder_date(),
                entity.getDeadline(),
                entity.getStatus(),
                entity.getTotal_cost(),
                entity.getTotal_cost()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Orders search(String contact) throws SQLException {
        return null;
    }

    @Override
    public List<Orders> get() throws SQLException {
        return List.of();
    }

    @Override
    public boolean saveData(Orders entity) throws SQLException {
        return false;
    }

    @Override
    public Orders searchData(int id) throws SQLException {
        return null;
    }

    @Override
    public String save(Orders entity) throws SQLException {
        return "";
    }

    @Override
    public void printOrderConfirmation(int orderID) throws SQLException, JRException {
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObj = getClass().getResourceAsStream("/lk/ijse/nrlbag/reports/orderConfirmation.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(reportObj);

        Map<String, Object> params = new HashMap<>();
        params.put("ORDER_ID", orderID);

        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);

        JasperViewer.viewReport(jp, false);
    }


}
