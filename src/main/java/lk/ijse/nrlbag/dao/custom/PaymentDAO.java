package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.CrudDAO;
import lk.ijse.nrlbag.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {

     int totalPendingPaymentsCount() throws SQLException;

     double getTotalPaidAmount(Payment entity) throws SQLException;

}
