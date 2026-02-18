package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.SuperDAO;
import lk.ijse.nrlbag.dto.MaterialDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface MaterialDAO extends SuperDAO {

    public MaterialDTO searchMaterial(int id) throws SQLException;

    // pass values, to insert in the database
    public boolean saveMaterial(MaterialDTO materialDTO) throws SQLException;

    public boolean updateMaterial(MaterialDTO materialDTO) throws SQLException;

    public boolean deleteMaterial(int id) throws SQLException;

    public int totalLowMaterialCount() throws SQLException;

    public List<MaterialDTO> getMaterial() throws SQLException;

    public boolean updateMaterialQtyAvailable(Connection conn, double newQty, int materialID) throws SQLException;

    public List<MaterialDTO> searchMaterialByKeyword(String keyword) throws SQLException;

}
