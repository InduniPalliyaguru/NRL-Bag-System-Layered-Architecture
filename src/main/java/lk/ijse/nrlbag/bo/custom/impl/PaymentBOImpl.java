package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.BOFactory;
import lk.ijse.nrlbag.bo.custom.OrdersBO;
import lk.ijse.nrlbag.bo.custom.PaymentBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.OrdersDAO;
import lk.ijse.nrlbag.dao.custom.PaymentDAO;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.OrderDTO;
import lk.ijse.nrlbag.dto.PaymentDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentBOImpl implements PaymentBO {

    private final OrdersDAO ordersBO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    private final PaymentDAO paymentBO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public List<PaymentDTO> getPayments() throws SQLException {
        return paymentBO.getPayments();
    }

    @Override
    public PaymentDTO searchPayment(int id) throws SQLException {
        return paymentBO.searchPayment(id);
    }

    @Override
    public boolean savePaymentWithOrderUpdate(PaymentDTO paymentDTO) throws SQLException, JRException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            // in here start the transaction and give a msg to stop the auto commit.
            conn.setAutoCommit(false);

            // next save the payment in database in temporary
            boolean paymentSaved = paymentBO.savePayment(paymentDTO);

            if (!paymentSaved) {
                conn.rollback();
                return false;
            }

            // here get the total order cost from order table through orderModel

            OrderDTO orderDetail = ordersBO.searchOrderByOrderID(paymentDTO.getOrder_id());

            double totalCost = orderDetail.getTotal_cost();


            double totalPaid = paymentBO.getTotalPaidAmount(paymentDTO);
            if (totalPaid == 0) {
                return false;
            }

            // next calculate the remaining payment
            double remaining = totalCost - totalPaid;

            if (remaining<0) {
                conn.rollback();
                throw new SQLException("Over Payment is not allowed");
            }

            // update the order table
            boolean orderUpdate = ordersBO.updateOrderRemainingPayment(conn, remaining, paymentDTO.getOrder_id());

            if (!orderUpdate) {
                conn.rollback();
                return false;
            }

            // after adding to the database then print the payment receipt
            printOrderPaymentReceipt(paymentDTO.getOrder_id());

            conn.commit();
            return true;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean updatePaymentWithOrderUpdate(PaymentDTO paymentDTO) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            // in here start the transaction
            conn.setAutoCommit(false);


            boolean paymentUpdate = paymentBO.updatePayment(paymentDTO);
            if (!paymentUpdate) {
                conn.rollback();
                return false;
            }

            // here get the total order cost

            OrderDTO orderDetail = ordersBO.searchOrderByOrderID(paymentDTO.getOrder_id());

            double totalCost = orderDetail.getTotal_cost();

            // here get the total paid amount

            double totalPaid = paymentBO.getTotalPaidAmount(paymentDTO);
            if (totalPaid == 0) {
                return false;
            }

            // next calculate the remaining payment
            double remaining = totalCost - totalPaid;

            if (remaining<0) {
                conn.rollback();
                throw new SQLException("Over Payment is not allowed");
            }

            // update the order table
            boolean orderUpdate = ordersBO.updateOrderRemainingPayment(conn, remaining, paymentDTO.getOrder_id());

            if (!orderUpdate) {
                conn.rollback();
                return false;
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            conn.commit();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean deletePaymentWithOrderUpdate(int payID, int orderID) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            // in here start the transaction
            conn.setAutoCommit(false);

            // next delete the payment in database

            boolean paymentUpdate = paymentBO.deletePayment(payID);
            if (!paymentUpdate) {
                conn.rollback();
                return false;
            }

            // here get the total order cost

            OrderDTO orderDetail = ordersBO.searchOrderByOrderID(orderID);

            double totalCost = orderDetail.getTotal_cost();

            // when payment is deleted , the remaining is equal to totalCost
            double remaining = totalCost;

            // update the order table
            boolean orderUpdate = ordersBO.updateOrderRemainingPayment(conn, remaining, orderID);

            if (!orderUpdate) {
                conn.rollback();
                return false;
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            conn.commit();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
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
