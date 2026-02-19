package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderPopUpBO extends SuperBO{

    public OrderDTO searchOrderByOrderID(int id) throws SQLException;

    public OderDetailsDTO searchProduct(int id) throws SQLException;

    public boolean updateOrder(OrderDTO orderDto) throws SQLException;

    public boolean updateOrderDetails(OderDetailsDTO orderDTO) throws SQLException;

    public boolean deleteOrderDetails(int oderID, int proID) throws SQLException;

    public boolean deleteOrder(int id) throws SQLException;

    public boolean saveMaterialUsage(MaterialUsedDTO materialUsedDTO, Double availableQty) throws SQLException;

    public MaterialUsedDTO searchMaterialUsage(int materialID) throws SQLException;

    public MaterialDTO searchMaterial(int id) throws SQLException;

    public boolean searchMaterialUsageByOrderID(int orderID) throws SQLException;

    public boolean updateMaterialUsage(MaterialUsedDTO dto) throws SQLException;

    public boolean deleteMaterialUsage(int orderID, int materialID) throws SQLException;

    public ProductDTO searchProducts(int id) throws SQLException;

    public List<MaterialUsedDTO> getMaterialUsage() throws SQLException;

    public List<MaterialDTO> searchMaterialByKeyword(String keyword) throws SQLException;

    public boolean saveOrderAndOrderID(OrderDTO orderDto) throws Exception;
}
