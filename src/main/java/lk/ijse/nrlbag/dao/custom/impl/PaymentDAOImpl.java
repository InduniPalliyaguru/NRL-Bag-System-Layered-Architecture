package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.PaymentDAO;
import lk.ijse.nrlbag.dao.CrudUtil;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.entity.Payment;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentDAOImpl implements PaymentDAO {

    public int totalPendingPaymentsCount() throws SQLException {

        // in here get the number of orders from customer table
        ResultSet result = CrudUtil.execute("SELECT COUNT(*) AS Total_Pending_Payment FROM Payment WHERE status='Pending';");
        int paymentCount = 0;

        // get the int value from the execution
        if (result.next()) {
            paymentCount = result.getInt("Total_Pending_Payment");
        }

        return paymentCount;

    }

    // get the all details in payment table
    @Override
    public List<Payment> get() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Payment");

        List<Payment> paymentList = new ArrayList<>();

        // get rows one by one and add into payment list
        while (rs.next()) {
            Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_date"),
                    rs.getString("type"),
                    rs.getString("status"),
                    rs.getInt("orders_id")
            );
            paymentList.add(payment);
        }
        return paymentList;

    }

    @Override
    public Payment searchData(int id) throws SQLException {

        // get the payments details from the database
        ResultSet rs = CrudUtil.execute("SELECT * FROM Payment WHERE payment_id=?",id);

        if (rs.next()) {
            return new Payment(
                    rs.getInt("payment_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_date"),
                    rs.getString("type"),
                    rs.getString("status"),
                    rs.getInt("orders_id")
            );
        }
        return null;

    }

    @Override
    public boolean saveData(Payment entity) throws SQLException {

        return CrudUtil.execute(
                "INSERT INTO Payment (amount, payment_date, type, status, orders_id) VALUES (?,?,?,?,?)",
                entity.getAmount(),
                entity.getPayment_date(),
                entity.getType(),
                entity.getStatus(),
                entity.getOrder_id()
        );

    }

    @Override
    public double getTotalPaidAmount(Payment entity) throws SQLException {
        ResultSet rsPaid = CrudUtil.execute(

                "SELECT COALESCE(SUM(amount),0) AS paid FROM Payment WHERE orders_id = ?",
                entity.getOrder_id()
        );

        if (rsPaid.next()) {
            double totalPaid = rsPaid.getDouble("paid");
            return totalPaid;
        }
        return 0;
    }

    @Override
    public boolean update(Payment entity) throws SQLException {
        return CrudUtil.execute(

                    "UPDATE Payment SET amount=?, payment_date=?, type=?, status=?, orders_id=? WHERE payment_id=?",
                    entity.getAmount(),
                    entity.getPayment_date(),
                    entity.getType(),
                    entity.getStatus(),
                    entity.getOrder_id(),
                    entity.getPayment_id()
            );
    }

    @Override
    public boolean deleteData(int payID) throws SQLException {
        return CrudUtil.execute(
                "DELETE FROM Payment WHERE payment_id=?",
                payID
        );
    }

    @Override
    public String save(Payment entity) throws SQLException {
        return "";
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Payment search(String contact) throws SQLException {
        return null;
    }

    @Override
    public void printOrderPaymentReceipt(int orderID) throws SQLException, JRException {
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObj = getClass().getResourceAsStream("/lk/ijse/nrlbag/reports/orderPaymentReciept.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(reportObj);

        Map<String, Object> params = new HashMap<>();
        params.put("ORDER_ID", orderID);

        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);

        JasperViewer.viewReport(jp, false);
    }

}
