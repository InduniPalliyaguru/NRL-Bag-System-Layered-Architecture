package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DashBoardBO extends SuperBO{

    public int totalCustomerCount() throws SQLException;

    public int totalOrderCount() throws SQLException;

    public int totalLowMaterialCount() throws SQLException;

    public int totalPendingPaymentsCount() throws SQLException;

    public int getOverdueOrderCount() throws SQLException;

    public int getOrderByMonths(int month) throws SQLException;

    public ResultSet getMonthlyIncome() throws SQLException;

    public UserDTO getUserDetails() throws SQLException;

}
