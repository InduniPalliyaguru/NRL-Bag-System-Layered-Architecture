package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.SuperDAO;
import lk.ijse.nrlbag.dto.OderDetailsDTO;

import java.sql.SQLException;

public interface OrderDetailDAO extends SuperDAO {

    public boolean saveOrderDetails(OderDetailsDTO orderDTO, int orderID) throws SQLException;

    public OderDetailsDTO searchProduct(int id) throws SQLException;

    public boolean updateOrderDetails(OderDetailsDTO orderDTO) throws SQLException;

    public boolean deleteOrderDetails(int oderID, int proID) throws SQLException;

}
