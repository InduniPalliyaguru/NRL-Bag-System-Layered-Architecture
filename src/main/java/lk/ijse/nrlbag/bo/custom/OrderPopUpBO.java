package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.OderDetailsDTO;
import lk.ijse.nrlbag.dto.OrderDTO;

import java.sql.SQLException;

public interface OrderPopUpBO extends SuperBO{

    public OrderDTO searchOrderByOrderID(int id) throws SQLException;

    public OderDetailsDTO searchProduct(int id) throws SQLException;

    public boolean updateOrder(OrderDTO orderDto) throws SQLException;

    public boolean updateOrderDetails(OderDetailsDTO orderDTO) throws SQLException;

    public boolean deleteOrderDetails(int oderID, int proID) throws SQLException;

    public boolean deleteOrder(int id) throws SQLException;
}
