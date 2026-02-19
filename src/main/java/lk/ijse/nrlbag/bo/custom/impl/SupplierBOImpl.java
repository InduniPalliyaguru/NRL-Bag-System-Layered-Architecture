package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.SupplierBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.SupplierDAO;
import lk.ijse.nrlbag.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    private final SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public List<SupplierDTO> getSuppliers() throws SQLException {
        return supplierDAO.getSuppliers();
    }

    @Override
    public String saveSupplier(SupplierDTO supplierDTO) throws SQLException {
        return supplierDAO.saveSupplier(supplierDTO);
    }

    @Override
    public SupplierDTO searchSupplier(int id) throws SQLException {
        return supplierDAO.searchSupplier(id);
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException {
        return supplierDAO.updateSupplier(supplierDTO);
    }

    @Override
    public boolean deleteSupplier(int id) throws SQLException {
        return supplierDAO.deleteSupplier(id);
    }
}
