package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.CrudDAO;
import lk.ijse.nrlbag.entity.Payment;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {

     int totalPendingPaymentsCount() throws SQLException;

     double getTotalPaidAmount(Payment entity) throws SQLException;

    void printOrderPaymentReceipt(int orderID) throws SQLException, JRException;

}
