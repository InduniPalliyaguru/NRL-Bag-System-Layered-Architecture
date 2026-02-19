package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.OrderPopUpBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.*;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderPopUpBOImpl implements OrderPopUpBO {

    private final OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    private final MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL);
    private final MaterialUsedDAO materialUsedDAO = (MaterialUsedDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL_USED);
    private final ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);

    @Override
    public OrderDTO searchOrderByOrderID(int id) throws SQLException {
        return ordersDAO.searchOrderByOrderID(id);
    }

    @Override
    public OderDetailsDTO searchProduct(int id) throws SQLException {
        return orderDetailDAO.searchProduct(id);
    }

    @Override
    public boolean updateOrder(OrderDTO orderDto) throws SQLException {
        return ordersDAO.updateOrder(orderDto);
    }

    @Override
    public boolean updateOrderDetails(OderDetailsDTO orderDTO) throws SQLException {
        return orderDetailDAO.updateOrderDetails(orderDTO);
    }

    @Override
    public boolean deleteOrderDetails(int oderID, int proID) throws SQLException {
        return orderDetailDAO.deleteOrderDetails(oderID, proID);
    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        return ordersDAO.deleteOrder(id);
    }

    @Override
    public boolean saveMaterialUsage(MaterialUsedDTO materialUsedDTO, Double availableQty) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            // in here start the transaction and give a msg to stop the auto commit.
            conn.setAutoCommit(false);

            boolean isSaved = materialUsedDAO.saveMaterialUsed(materialUsedDTO);
            if (!isSaved) {
                conn.rollback();
                return false;
            }
            // in here send qty available for database update
            boolean isUpdated = materialDAO.updateMaterialQtyAvailable(conn, availableQty, materialUsedDTO.getMaterial_id());

            if (!isUpdated) {
                conn.rollback();
                return false;
            }
            conn.commit();
            return true;

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public MaterialUsedDTO searchMaterialUsage(int materialID) throws SQLException {
        return materialUsedDAO.searchMaterialUsage(materialID);
    }

    @Override
    public MaterialDTO searchMaterial(int id) throws SQLException {
        return materialDAO.searchMaterial(id);
    }

    @Override
    public boolean searchMaterialUsageByOrderID(int orderID) throws SQLException {
        return materialUsedDAO.searchMaterialUsageByOrderID(orderID);
    }

    @Override
    public boolean updateMaterialUsage(MaterialUsedDTO dto) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            // get the old used qty
            double oldUsedQty = materialUsedDAO.getOldUsedQty(dto.getOrder_id(), dto.getMaterial_id());

            // get the current stock
            MaterialDTO materialDTO = materialDAO.searchMaterial(dto.getMaterial_id());

            if (materialDTO == null) {
                conn.rollback();
                return false;
            }

            double currentStock = materialDTO.getQtyAvailable();

            // calculate the differences
            double differences = dto.getQty_used() - oldUsedQty;
            double newStock = currentStock - differences;

            // then update the material used

            boolean isUpdated = materialUsedDAO.updateMaterialUsage(dto);
            if (!isUpdated) {
                conn.rollback();
                return false;
            }

            boolean isStockUpdated = materialDAO.updateMaterialQtyAvailable(
                    conn,
                    newStock,
                    dto.getMaterial_id()
            );
            if (!isStockUpdated) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean deleteMaterialUsage(int orderID, int materialID) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            // get the old used qty
            double oldUsedQty = materialUsedDAO.getOldUsedQty(orderID, materialID);

            // get the current stock
            MaterialDTO materialDTO = materialDAO.searchMaterial(materialID);

            if (materialDTO == null) {
                conn.rollback();
                return false;
            }

            double currentStock = materialDTO.getQtyAvailable();

            // when deleting the material usage of the order that material qty add to the stock again.
            double newStock = currentStock + oldUsedQty;

            // then update the material used
//            boolean isDeleted = CrudUtil.execute(
//                    conn,
//                    "DELETE FROM Material_Used WHERE orders_id=? AND material_id=?",
//                    orderID,
//                    materialID
//            );

            boolean isDeleted = materialUsedDAO.deleteMaterialUsage(orderID, materialID);
            if (!isDeleted) {
                conn.rollback();
                return false;
            }

            boolean isStockUpdated = materialDAO.updateMaterialQtyAvailable(
                    conn,
                    newStock,
                    materialID
            );
            if (!isStockUpdated) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public ProductDTO searchProducts(int id) throws SQLException {
        return productDAO.searchProduct(id);
    }

    @Override
    public List<MaterialUsedDTO> getMaterialUsage() throws SQLException {
        return materialUsedDAO.getMaterialUsage();
    }

    @Override
    public List<MaterialDTO> searchMaterialByKeyword(String keyword) throws SQLException {
        return materialDAO.searchMaterialByKeyword(keyword);
    }

    @Override
    public boolean saveOrderAndOrderID(OrderDTO orderDto) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();

        try {

            conn.setAutoCommit(false);

            // pass the query for save to the database
            int lastOrderId = ordersDAO.saveOrder(orderDto);

            if (lastOrderId != 0) {
                boolean result = orderDetailDAO.saveOrderDetails(orderDto.getOrderDetails(), lastOrderId);

                if (result) {
                    // print invoice

                    ordersDAO.printOrderConfirmation(lastOrderId);
                } else {
                    throw new Exception("Something went wrong when print document");
                }
            } else {
                throw new Exception("Something went wrong when get order item id");
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
