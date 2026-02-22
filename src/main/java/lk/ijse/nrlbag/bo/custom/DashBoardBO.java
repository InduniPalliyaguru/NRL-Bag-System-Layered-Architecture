package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DashBoardBO extends SuperBO{

     int totalCustomerCount() throws SQLException;

     int totalOrderCount() throws SQLException;

     int totalLowMaterialCount() throws SQLException;

     int totalPendingPaymentsCount() throws SQLException;

     int getOverdueOrderCount() throws SQLException;

     int getOrderByMonths(int month) throws SQLException;

     ResultSet getMonthlyIncome() throws SQLException;

     UserDTO getUserDetails() throws SQLException;

}
