package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.bo.SuperBO;
import lk.ijse.nrlbag.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {

    // get the all details in supplier table join with material details also
     List<SupplierDTO> getSuppliers() throws SQLException;

     String saveSupplier(SupplierDTO supplierDTO) throws SQLException;

     SupplierDTO searchSupplier(int id) throws SQLException;

     boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException;

     boolean deleteSupplier(int id) throws SQLException;

}
