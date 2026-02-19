package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO{

    // get the all details in supplier table join with material details also
    public List<SupplierDTO> getSuppliers() throws SQLException;

    public String saveSupplier(SupplierDTO supplierDTO) throws SQLException;

    public SupplierDTO searchSupplier(int id) throws SQLException;

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException;

    public boolean deleteSupplier(int id) throws SQLException;

}
