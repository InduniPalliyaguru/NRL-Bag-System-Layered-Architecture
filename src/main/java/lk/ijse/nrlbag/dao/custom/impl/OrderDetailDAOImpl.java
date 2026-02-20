package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.OrderDetailDAO;
import lk.ijse.nrlbag.dto.OderDetailsDTO;
import lk.ijse.nrlbag.dao.CrudUtil;
import lk.ijse.nrlbag.entity.Order_Details;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean saveOrderDetails(OderDetailsDTO orderDTO, int orderID) throws SQLException {

        // pass the query for save to the database
        return CrudUtil.execute("INSERT INTO Order_Details (orders_id, product_id, quantity, unit_price) VALUES (?,?,?,?)",
                orderID,
                orderDTO.getProduct_id(),
                orderDTO.getQuantity(),
                orderDTO.getUnit_price()
        );

    }

    @Override
    public boolean update(Order_Details entity) throws SQLException {

        // pass the query for update the database
        boolean result = CrudUtil.execute("UPDATE Order_Details SET orders_id=?, product_id=?, quantity=?, unit_price=? WHERE orders_id=? AND product_id=?",
                entity.getOrders_id(),
                entity.getProduct_id(),
                entity.getQuantity(),
                entity.getUnit_price(),
                entity.getOrders_id(),
                entity.getProduct_id()
        );
        return result;

    }

    @Override
    public boolean deleteOrderDetails(int oderID, int proID) throws SQLException {

        boolean result = CrudUtil.execute("DELETE FROM Order_Details WHERE orders_id=? AND product_id=?", oderID, proID);
        return result;

    }

    @Override
    public String save(Order_Details entity) throws SQLException {
        return "";
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Order_Details search(String contact) throws SQLException {
        return null;
    }

    @Override
    public List<Order_Details> get() throws SQLException {
        return List.of();
    }

    @Override
    public boolean saveData(Order_Details entity) throws SQLException {
        return false;
    }

    @Override
    public Order_Details searchData(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteData(int id) throws SQLException {
        return false;
    }

}
