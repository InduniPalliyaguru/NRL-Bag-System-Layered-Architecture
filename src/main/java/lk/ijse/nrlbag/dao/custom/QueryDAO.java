package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.SuperDAO;
import lk.ijse.nrlbag.dto.tm.MaterialUsedTM;
import lk.ijse.nrlbag.dto.tm.OrderDetailsTM;
import lk.ijse.nrlbag.dto.tm.OrdersTM;
import lk.ijse.nrlbag.dto.tm.SupplierTM;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {

     List<MaterialUsedTM> getMaterialUsage() throws SQLException;

     OrderDetailsTM searchProduct(int id) throws SQLException;

     List<OrdersTM> getOrders() throws SQLException;

     OrdersTM searchOrderByOrderID(int id) throws SQLException;

     OrdersTM searchOrderByCustomerID(int id) throws SQLException;

     List<SupplierTM> getSuppliers() throws SQLException;

}
