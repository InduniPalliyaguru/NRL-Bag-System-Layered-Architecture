package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.CrudDAO;
import lk.ijse.nrlbag.entity.Material_Used;

import java.sql.SQLException;

public interface MaterialUsedDAO extends CrudDAO<Material_Used> {

     boolean searchMaterialUsageByOrderID(int orderID) throws SQLException;

     boolean deleteMaterialUsage(int orderID, int materialID) throws SQLException;

     double getOldUsedQty(int orderID, int materialID) throws SQLException;

}
