package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.OrderDTO;
import lk.ijse.nrlbag.dto.PaymentDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO{

    // get the all details in payment table
    public List<PaymentDTO> getPayments() throws SQLException;

    public PaymentDTO searchPayment(int id) throws SQLException;

    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException;

    public double getTotalPaidAmount(PaymentDTO paymentDTO) throws SQLException;

    public boolean updatePayment(PaymentDTO paymentDTO) throws SQLException;

    public boolean deletePayment(int payID) throws SQLException;

    public boolean savePaymentWithOrderUpdate(PaymentDTO paymentDTO) throws SQLException, JRException;

    public boolean updatePaymentWithOrderUpdate(PaymentDTO paymentDTO) throws SQLException;

    public boolean deletePaymentWithOrderUpdate(int payID, int orderID) throws SQLException;

    public void printOrderPaymentReceipt(int orderID) throws SQLException, JRException;

}
