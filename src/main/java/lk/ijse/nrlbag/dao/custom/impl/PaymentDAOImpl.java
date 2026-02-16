package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.PaymentDAO;
import lk.ijse.nrlbag.dto.PaymentDTO;
import lk.ijse.nrlbag.util.CrudUtil;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<PaymentDTO> getPayments() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Payment");

        List<PaymentDTO> paymentList = new ArrayList<>();

        // get rows one by one and add into payment list
        while (rs.next()) {
            PaymentDTO paymentDTO = new PaymentDTO(
                    rs.getInt("payment_id"),
                    rs.getInt("orders_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_date"),
                    rs.getString("type"),
                    rs.getString("status")
            );
            paymentList.add(paymentDTO);
        }
        return paymentList;

    }

    public PaymentDTO searchPayment(int id) throws SQLException {

        // get the payments details from the database
        ResultSet rs = CrudUtil.execute("SELECT * FROM Payment WHERE payment_id=?",id);

        if (rs.next()) {
            return new PaymentDTO(
                    rs.getInt("payment_id"),
                    rs.getInt("orders_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_date"),
                    rs.getString("type"),
                    rs.getString("status")
            );
        }
        return null;

    }

    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException {

        return CrudUtil.execute(
                "INSERT INTO Payment (amount, payment_date, type, status, orders_id) VALUES (?,?,?,?,?)",
                paymentDTO.getAmount(),
                paymentDTO.getPayment_date(),
                paymentDTO.getType(),
                paymentDTO.getStatus(),
                paymentDTO.getOrder_id()
        );

    }

    public double getTotalPaidAmount(PaymentDTO paymentDTO) throws SQLException {
        ResultSet rsPaid = CrudUtil.execute(

                "SELECT COALESCE(SUM(amount),0) AS paid FROM Payment WHERE orders_id = ?",
                paymentDTO.getOrder_id()
        );

        if (rsPaid.next()) {
            double totalPaid = rsPaid.getDouble("paid");
            return totalPaid;
        }
        return 0;
    }

    public boolean updatePayment(PaymentDTO paymentDTO) throws SQLException {
        return CrudUtil.execute(

                    "UPDATE Payment SET amount=?, payment_date=?, type=?, status=?, orders_id=? WHERE payment_id=?",
                    paymentDTO.getAmount(),
                    paymentDTO.getPayment_date(),
                    paymentDTO.getType(),
                    paymentDTO.getStatus(),
                    paymentDTO.getOrder_id(),
                    paymentDTO.getId()
            );
    }

    public boolean deletePayment(int payID) throws SQLException {
        return CrudUtil.execute(
                "DELETE FROM Payment WHERE payment_id=?",
                payID
        );
    }

}
