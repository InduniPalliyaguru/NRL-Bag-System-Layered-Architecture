package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.SuperDAO;
import lk.ijse.nrlbag.dto.MaterialUsedDTO;

import java.sql.SQLException;
import java.util.List;

public interface MaterialUsedDAO extends SuperDAO {

    public List<MaterialUsedDTO> getMaterialUsage() throws SQLException;

    public boolean saveMaterialUsed(MaterialUsedDTO materialUsedDTO) throws SQLException;

    public MaterialUsedDTO searchMaterialUsage(int materialID) throws SQLException;

    public boolean searchMaterialUsageByOrderID(int orderID) throws SQLException;

    public boolean updateMaterialUsage(MaterialUsedDTO dto) throws SQLException;

    public boolean deleteMaterialUsage(int orderID, int materialID) throws SQLException;

    public double getOldUsedQty(int orderID, int materialID) throws SQLException;

}
