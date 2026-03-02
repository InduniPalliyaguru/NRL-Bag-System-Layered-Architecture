package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.*;
import net.sf.jasperreports.engine.JRException;


import java.sql.SQLException;
import java.util.List;

public interface OrderPopUpBO extends SuperBO{

     OrderDTO searchOrderByOrderID(int id) throws SQLException;

     OderDetailsDTO searchProduct(int id) throws SQLException;

     boolean updateOrder(OrderDTO orderDto) throws SQLException;

     boolean updateOrderDetails(OderDetailsDTO orderDTO) throws SQLException;

     boolean deleteOrderDetails(int oderID, int proID) throws SQLException;

     boolean deleteOrder(int id) throws SQLException;

     boolean saveMaterialUsage(MaterialUsedDTO materialUsedDTO, Double availableQty) throws SQLException;

     MaterialUsedDTO searchMaterialUsage(int materialID) throws SQLException;

     MaterialDTO searchMaterial(int id) throws SQLException;

     boolean searchMaterialUsageByOrderID(int orderID) throws SQLException;

     boolean updateMaterialUsage(MaterialUsedDTO dto) throws SQLException;

     boolean deleteMaterialUsage(int orderID, int materialID) throws SQLException;

     ProductDTO searchProducts(int id) throws SQLException;

     List<MaterialUsedDTO> getMaterialUsage() throws SQLException;

     List<MaterialDTO> searchMaterialByKeyword(String keyword) throws SQLException;

     boolean saveOrderAndOrderID(OrderDTO orderDto) throws Exception;

     void printOrderConfirmation(int orderID) throws SQLException, JRException;

}
