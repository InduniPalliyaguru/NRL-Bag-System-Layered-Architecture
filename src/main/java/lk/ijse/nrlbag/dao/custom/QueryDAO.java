package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.SuperDAO;
import lk.ijse.nrlbag.dto.CustomDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {

     List<CustomDTO> getMaterialUsage() throws SQLException;

    CustomDTO searchProduct(int id) throws SQLException;

     List<CustomDTO> getOrders() throws SQLException;

    CustomDTO searchOrderByOrderID(int id) throws SQLException;

    CustomDTO searchOrderByCustomerID(int id) throws SQLException;

     List<CustomDTO> getSuppliers() throws SQLException;

}
