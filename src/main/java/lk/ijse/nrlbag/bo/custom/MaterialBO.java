package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.bo.SuperBO;
import lk.ijse.nrlbag.dto.MaterialDTO;
import net.sf.jasperreports.engine.*;
import java.sql.SQLException;
import java.util.List;

public interface MaterialBO extends SuperBO {

     MaterialDTO searchMaterial(int id) throws SQLException;

    // pass values, to insert in the database
     boolean saveMaterial(MaterialDTO materialDTO) throws SQLException;

     boolean updateMaterial(MaterialDTO materialDTO) throws SQLException;

     boolean deleteMaterial(int id) throws SQLException;

     List<MaterialDTO> getMaterial() throws SQLException;

     void printMaterialStockReport() throws SQLException, JRException;

     void printLowMaterialStockReport() throws SQLException, JRException;

}
