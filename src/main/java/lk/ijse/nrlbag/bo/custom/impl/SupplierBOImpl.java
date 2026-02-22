package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.SupplierBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.QueryDAO;
import lk.ijse.nrlbag.dao.custom.SupplierDAO;
import lk.ijse.nrlbag.dto.CustomDTO;
import lk.ijse.nrlbag.dto.SupplierDTO;
import lk.ijse.nrlbag.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    private final SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public List<SupplierDTO> getSuppliers() throws SQLException {
        List<CustomDTO> supplierTM = queryDAO.getSuppliers();
        List<SupplierDTO> supplierDTOS = new ArrayList<>();

        for (CustomDTO sup : supplierTM) {
            SupplierDTO supplierDTO = new SupplierDTO(
                    sup.getId(),
                    sup.getName(),
                    sup.getAddress(),
                    sup.getContact(),
                    sup.getMaterialId(),
                    sup.getMaterialName()
            );
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    @Override
    public String saveSupplier(SupplierDTO supplierDTO) throws SQLException {
        return supplierDAO.save(new Supplier(supplierDTO.getName(), supplierDTO.getAddress(), supplierDTO.getContact()));
    }

    @Override
    public SupplierDTO searchSupplier(int id) throws SQLException {
        Supplier sup = supplierDAO.searchData(id);
        return new SupplierDTO(
                sup.getSupplier_id(),
                sup.getSupplier_name(),
                sup.getAddress(),
                sup.getContact()
        );
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException {
        return supplierDAO.update(new Supplier(
                supplierDTO.getId(),
                supplierDTO.getName(),
                supplierDTO.getAddress(),
                supplierDTO.getContact()
        ));
    }

    @Override
    public boolean deleteSupplier(int id) throws SQLException {
        return supplierDAO.deleteData(id);
    }
}
