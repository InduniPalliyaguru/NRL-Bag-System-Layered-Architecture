package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.OrderPopUpBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.*;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.*;
import lk.ijse.nrlbag.dto.tm.MaterialUsedTM;
import lk.ijse.nrlbag.dto.tm.OrderDetailsTM;
import lk.ijse.nrlbag.dto.tm.OrdersTM;
import lk.ijse.nrlbag.entity.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderPopUpBOImpl implements OrderPopUpBO {

    private final OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    private final MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL);
    private final MaterialUsedDAO materialUsedDAO = (MaterialUsedDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL_USED);
    private final ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public OrderDTO searchOrderByOrderID(int id) throws SQLException {


        OrdersTM tm = queryDAO.searchOrderByOrderID(id);

        return new OrderDTO(
                tm.getId(),
                tm.getCustomer_id(),
                tm.getName(),
                tm.getCustomerContact(),
                tm.getOrder_date(),
                tm.getDeadline(),
                tm.getStatus(),
                tm.getTotal_cost(),
                tm.getRemaining_payment(),
                tm.getProductId(),
                tm.getQuantity()
        );

    }

    @Override
    public OderDetailsDTO searchProduct(int id) throws SQLException {

        OrderDetailsTM orderDetailsTM = queryDAO.searchProduct(id);

        return new OderDetailsDTO(
                orderDetailsTM.getProduct_id(),
                orderDetailsTM.getQuantity(),
                orderDetailsTM.getUnit_price(),
                orderDetailsTM.getName()
        );
    }

    @Override
    public boolean updateOrder(OrderDTO orderDto) throws SQLException {
        return ordersDAO.update(new Orders(
                orderDto.getId(),
                orderDto.getCustomer_id(),
                orderDto.getOrder_date(),
                orderDto.getDeadline(),
                orderDto.getStatus(),
                orderDto.getTotal_cost(),
                orderDto.getRemaining_payment()
        ));
    }

    @Override
    public boolean updateOrderDetails(OderDetailsDTO orderDTO) throws SQLException {
        return orderDetailDAO.update(new Order_Details(
                orderDTO.getOrder_id(),
                orderDTO.getProduct_id(),
                orderDTO.getQuantity(),
                orderDTO.getUnit_price()
        ));
    }

    @Override
    public boolean deleteOrderDetails(int oderID, int proID) throws SQLException {
        return orderDetailDAO.deleteOrderDetails(oderID, proID);
    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        return ordersDAO.deleteData(id);
    }

    @Override
    public boolean saveMaterialUsage(MaterialUsedDTO materialUsedDTO, Double availableQty) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            // in here start the transaction and give a msg to stop the auto commit.
            conn.setAutoCommit(false);

            boolean isSaved = materialUsedDAO.saveData(new Material_Used(
                    materialUsedDTO.getOrder_id(),
                    materialUsedDTO.getMaterial_id(),
                    materialUsedDTO.getQty_used()
            ));
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

        Material_Used materialUsed = materialUsedDAO.searchData(materialID);

        return new MaterialUsedDTO(
                materialUsed.getOrder_id(),
                materialUsed.getMaterial_id(),
                materialUsed.getUsed_qty()
        );
    }

    @Override
    public MaterialDTO searchMaterial(int id) throws SQLException {
        Material material = materialDAO.searchData(id);

        return new MaterialDTO(
                material.getMaterial_id(),
                material.getSupplier_id(),
                material.getName(),
                material.getUnit(),
                material.getQty_available()
        );
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
            Material material = materialDAO.searchData(dto.getMaterial_id());

            if (material == null) {
                conn.rollback();
                return false;
            }

            double currentStock = material.getQty_available();

            // calculate the differences
            double differences = dto.getQty_used() - oldUsedQty;
            double newStock = currentStock - differences;

            // then update the material used

            boolean isUpdated = materialUsedDAO.update(new Material_Used(
                    dto.getOrder_id(),
                    dto.getMaterial_id(),
                    dto.getQty_used()
                    )
            );
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
            Material material = materialDAO.searchData(materialID);

            if (material == null) {
                conn.rollback();
                return false;
            }

            double currentStock = material.getQty_available();

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
        Product pro = productDAO.searchData(id);
        return new ProductDTO(
                pro.getProduct_id(),
                pro.getName(),
                pro.getSize(),
                pro.getBasic_price()
        );
    }

    @Override
    public List<MaterialUsedDTO> getMaterialUsage() throws SQLException {
        List<MaterialUsedTM> materialUsedTM = queryDAO.getMaterialUsage();
        List<MaterialUsedDTO> materialUsedDTOS = new ArrayList<>();

        for (MaterialUsedTM material : materialUsedTM) {
            MaterialUsedDTO materialUsedDTO = new MaterialUsedDTO(
                    material.getOrder_id(),
                    material.getMaterial_id(),
                    material.getQty_used(),
                    material.getMaterial_name(),
                    material.getUnit()
            );
            materialUsedDTOS.add(materialUsedDTO);
        }
        return materialUsedDTOS;
    }

    @Override
    public List<MaterialDTO> searchMaterialByKeyword(String keyword) throws SQLException {

        List<Material> material = materialDAO.searchMaterialByKeyword(keyword);
        List<MaterialDTO> materialDTOS = new ArrayList<>();

        for (Material material1 : material) {
            MaterialDTO materialDTO = new MaterialDTO(
                    material1.getMaterial_id(),
                    material1.getSupplier_id(),
                    material1.getName(),
                    material1.getUnit(),
                    material1.getQty_available()
            );

            materialDTOS.add(materialDTO);
        }
        return materialDTOS;
    }

    @Override
    public boolean saveOrderAndOrderID(OrderDTO orderDto) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();

        try {

            conn.setAutoCommit(false);
// pass the query for save to the database

            int lastOrderId = ordersDAO.saveOrder(new Orders(
                    orderDto.getCustomer_id(),
                    orderDto.getOrder_date(),
                    orderDto.getDeadline(),
                    orderDto.getStatus(),
                    orderDto.getTotal_cost()
            ));

            if (lastOrderId != 0) {
                boolean result = orderDetailDAO.saveOrderDetails(orderDto.getOrderDetails(), lastOrderId);

                if (result) {
                    // print invoice

                    printOrderConfirmation(lastOrderId);
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

    @Override
    public void printOrderConfirmation(int orderID) throws SQLException, JRException {
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObj = getClass().getResourceAsStream("/lk/ijse/nrlbag/reports/orderConfirmation.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(reportObj);

        Map<String, Object> params = new HashMap<>();
        params.put("ORDER_ID", orderID);

        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);

        JasperViewer.viewReport(jp, false);
    }
}
