package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.MaterialUsedDAO;
import lk.ijse.nrlbag.dto.MaterialUsedDTO;
import lk.ijse.nrlbag.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialUsedDAOImpl implements MaterialUsedDAO {

    public List<MaterialUsedDTO> getMaterialUsage() throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT mu.orders_id, mu.material_id, mu.used_qty, m.name, m.unit " +
                        "FROM Material m JOIN Material_Used mu ON m.material_id = mu.material_id;"
        );

        List<MaterialUsedDTO> materialUsedList = new ArrayList<>();

        // get rows one by one and add into order list
        while (rs.next()) {
            MaterialUsedDTO usedList = new MaterialUsedDTO(
                    rs.getInt("orders_id"),
                    rs.getInt("material_id"),
                    rs.getInt("used_qty"),
                    rs.getString("name"),
                    rs.getString("unit")
            );
            materialUsedList.add(usedList);
        }
        return materialUsedList;

    }

    public boolean saveMaterialUsed(MaterialUsedDTO materialUsedDTO) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Material_Used (orders_id, material_id, used_qty) VALUES (?,?,?)",
                materialUsedDTO.getOrder_id(),
                materialUsedDTO.getMaterial_id(),
                materialUsedDTO.getQty_used()
        );
    }

    public MaterialUsedDTO searchMaterialUsage(int materialID) throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT * FROM Material_Used WHERE material_id=?", materialID);

        if (rs.next()) {
            return new MaterialUsedDTO(
                    rs.getInt("orders_id"),
                    rs.getInt("material_id"),
                    rs.getDouble("used_qty")
            );
        }
        return null;
    }

    public boolean searchMaterialUsageByOrderID(int orderID) throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT * FROM Material_Used WHERE orders_id=?", orderID);

        return rs.next();
    }

    public boolean updateMaterialUsage(MaterialUsedDTO dto) throws SQLException {
        return CrudUtil.execute(

                "UPDATE Material_Used SET used_qty=? WHERE orders_id=? AND material_id=?",
                dto.getQty_used(),
                dto.getOrder_id(),
                dto.getMaterial_id()
        );
    }

    public boolean deleteMaterialUsage(int orderID, int materialID) throws SQLException {
        return CrudUtil.execute(
                "DELETE FROM Material_Used WHERE orders_id=? AND material_id=?",
                orderID,
                materialID
        );
    }

    public double getOldUsedQty(int orderID, int materialID) throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT used_qty FROM Material_Used WHERE orders_id=? AND material_id=?",
                orderID, materialID
        );
        if (rs.next()) {
            return rs.getDouble("used_qty");
        }
        return 0;
    }

}
