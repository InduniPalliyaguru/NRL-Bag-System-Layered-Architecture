package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.CrudDAO;
import lk.ijse.nrlbag.entity.Material;
import net.sf.jasperreports.engine.JRException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MaterialDAO extends CrudDAO<Material> {

     int totalLowMaterialCount() throws SQLException;

     boolean updateMaterialQtyAvailable(double newQty, int materialID) throws SQLException;

     List<Material> searchMaterialByKeyword(String keyword) throws SQLException;

    void printMaterialStockReport() throws SQLException, JRException;

    void printLowMaterialStockReport() throws SQLException, JRException;

}
