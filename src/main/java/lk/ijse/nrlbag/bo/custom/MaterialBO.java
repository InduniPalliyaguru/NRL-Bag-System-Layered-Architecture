package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.MaterialDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MaterialBO extends SuperBO{

    public MaterialDTO searchMaterial(int id) throws SQLException;

    // pass values, to insert in the database
    public boolean saveMaterial(MaterialDTO materialDTO) throws SQLException;

    public boolean updateMaterial(MaterialDTO materialDTO) throws SQLException;

    public boolean deleteMaterial(int id) throws SQLException;

    public List<MaterialDTO> getMaterial() throws SQLException;

    public void printMaterialStockReport() throws SQLException, JRException;

    public void printLowMaterialStockReport() throws SQLException, JRException;

}
