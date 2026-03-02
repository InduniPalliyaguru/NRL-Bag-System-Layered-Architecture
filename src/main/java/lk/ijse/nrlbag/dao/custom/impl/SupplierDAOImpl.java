package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.SupplierDAO;
import lk.ijse.nrlbag.dao.CrudUtil;
import lk.ijse.nrlbag.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public String save(Supplier entity) throws SQLException {

        // here check the there has a supplier contact before
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier WHERE contact=?", entity.getContact());

        if (!result.next()) {

            // if not have, save to the database
            boolean rs = CrudUtil.execute("INSERT INTO Supplier(supplier_name, address, contact) VALUES (?,?,?)",
                    entity.getSupplier_name(),
                    entity.getAddress(),
                    entity.getContact());

            return rs ? "" : "Failed to save Supplier";

        } else {
            return "Supplier Contact Already Exist!";
        }

    }

    @Override
    public Supplier searchData(int id) throws SQLException {

        // get the supplier details from the database
        ResultSet rs = CrudUtil.execute("SELECT * FROM Supplier WHERE supplier_id=?",id);

        if (rs.next()) {
            return new Supplier(
                    rs.getInt("supplier_id"),
                    rs.getString("supplier_name"),
                    rs.getString("address"),
                    rs.getString("contact")
            );
        }
        return null;

    }

    @Override
    public boolean update(Supplier entity) throws SQLException {

        return CrudUtil.execute("UPDATE Supplier SET supplier_name=?, address=?, contact=? WHERE supplier_id=?",
                entity.getSupplier_name(),
                entity.getAddress(),
                entity.getContact(),
                entity.getSupplier_id()
        );
    }

    @Override
    public boolean deleteData(int id) throws SQLException {

        return CrudUtil.execute("DELETE FROM Supplier WHERE Supplier_id=?",id);

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Supplier search(String contact) throws SQLException {
        return null;
    }

    @Override
    public List<Supplier> get() throws SQLException {
        return List.of();
    }

    @Override
    public boolean saveData(Supplier entity) throws SQLException {
        return false;
    }

}
