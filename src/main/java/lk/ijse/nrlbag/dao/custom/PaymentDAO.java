package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.SuperDAO;
import lk.ijse.nrlbag.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends SuperDAO {

    public int totalPendingPaymentsCount() throws SQLException;

    // get the all details in payment table
    public List<PaymentDTO> getPayments() throws SQLException;

    public PaymentDTO searchPayment(int id) throws SQLException;

    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException;

    public double getTotalPaidAmount(PaymentDTO paymentDTO) throws SQLException;

    public boolean updatePayment(PaymentDTO paymentDTO) throws SQLException;

    public boolean deletePayment(int payID) throws SQLException;

}
