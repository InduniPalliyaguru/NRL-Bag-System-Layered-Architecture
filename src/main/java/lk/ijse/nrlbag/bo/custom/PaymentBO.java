package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.bo.SuperBO;
import lk.ijse.nrlbag.dto.PaymentDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {

    // get the all details in payment table
     List<PaymentDTO> getPayments() throws SQLException;

     PaymentDTO searchPayment(int id) throws SQLException;

     boolean savePaymentWithOrderUpdate(PaymentDTO paymentDTO) throws SQLException, JRException;

     boolean updatePaymentWithOrderUpdate(PaymentDTO paymentDTO) throws SQLException;

     boolean deletePaymentWithOrderUpdate(int payID, int orderID) throws SQLException;

     void printOrderPaymentReceipt(int orderID) throws SQLException, JRException;

}
