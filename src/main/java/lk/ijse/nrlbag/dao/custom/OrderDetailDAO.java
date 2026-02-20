package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.CrudDAO;
import lk.ijse.nrlbag.dto.OderDetailsDTO;
import lk.ijse.nrlbag.entity.Order_Details;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<Order_Details> {

     boolean saveOrderDetails(OderDetailsDTO orderDTO, int orderID) throws SQLException;

     boolean deleteOrderDetails(int oderID, int proID) throws SQLException;

}
