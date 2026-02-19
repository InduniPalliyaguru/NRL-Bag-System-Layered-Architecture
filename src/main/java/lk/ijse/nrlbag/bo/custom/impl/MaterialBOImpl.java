package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.MaterialBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.MaterialDAO;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.MaterialDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MaterialBOImpl implements MaterialBO {

    private final MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL);

    @Override
    public MaterialDTO searchMaterial(int id) throws SQLException {
        return materialDAO.searchMaterial(id);
    }

    @Override
    public boolean saveMaterial(MaterialDTO materialDTO) throws SQLException {
        return materialDAO.saveMaterial(materialDTO);
    }

    @Override
    public boolean updateMaterial(MaterialDTO materialDTO) throws SQLException {
        return materialDAO.updateMaterial(materialDTO);
    }

    @Override
    public boolean deleteMaterial(int id) throws SQLException {
        return materialDAO.deleteMaterial(id);
    }

    @Override
    public List<MaterialDTO> getMaterial() throws SQLException {
        return materialDAO.getMaterial();
    }

    @Override
    public void printMaterialStockReport() throws SQLException, JRException {
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObj = getClass().getResourceAsStream("/lk/ijse/nrlbag/reports/materialStockReport.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(reportObj);

        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

        JasperViewer.viewReport(jp, false);
    }

    @Override
    public void printLowMaterialStockReport() throws SQLException, JRException {
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObj = getClass().getResourceAsStream("/lk/ijse/nrlbag/reports/lowStockMaterial.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(reportObj);

        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

        JasperViewer.viewReport(jp, false);
    }
}
