package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.MaterialUsedDAO;
import lk.ijse.nrlbag.dao.CrudUtil;
import lk.ijse.nrlbag.entity.Material_Used;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MaterialUsedDAOImpl implements MaterialUsedDAO {

    @Override
    public boolean saveData(Material_Used entity) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO Material_Used (orders_id, material_id, used_qty) VALUES (?,?,?)",
                entity.getOrder_id(),
                entity.getMaterial_id(),
                entity.getUsed_qty()
        );
    }

    @Override
    public Material_Used searchData(int materialID) throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT * FROM Material_Used WHERE material_id=?", materialID);

        if (rs.next()) {
            return new Material_Used(
                    rs.getInt("orders_id"),
                    rs.getInt("material_id"),
                    rs.getDouble("used_qty")
            );
        }
        return null;
    }

    @Override
    public boolean searchMaterialUsageByOrderID(int orderID) throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT * FROM Material_Used WHERE orders_id=?", orderID);

        return rs.next();
    }

    @Override
    public boolean update(Material_Used entity) throws SQLException {
        return CrudUtil.execute(

                "UPDATE Material_Used SET used_qty=? WHERE orders_id=? AND material_id=?",
                entity.getUsed_qty(),
                entity.getOrder_id(),
                entity.getMaterial_id()
        );
    }

    @Override
    public boolean deleteMaterialUsage(int orderID, int materialID) throws SQLException {
        return CrudUtil.execute(
                "DELETE FROM Material_Used WHERE orders_id=? AND material_id=?",
                orderID,
                materialID
        );
    }

    @Override
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

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Material_Used search(String contact) throws SQLException {
        return null;
    }

    @Override
    public List<Material_Used> get() throws SQLException {
        return List.of();
    }

    @Override
    public boolean deleteData(int id) throws SQLException {
        return false;
    }

    @Override
    public String save(Material_Used entity) throws SQLException {
        return "";
    }

}
