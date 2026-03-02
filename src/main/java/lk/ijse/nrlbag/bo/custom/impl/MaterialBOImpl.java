package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.MaterialBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.MaterialDAO;
import lk.ijse.nrlbag.dto.MaterialDTO;
import lk.ijse.nrlbag.entity.Material;
import net.sf.jasperreports.engine.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialBOImpl implements MaterialBO {

    private final MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MATERIAL);

    @Override
    public MaterialDTO searchMaterial(int id) throws SQLException {
        Material material = materialDAO.searchData(id);

        return new MaterialDTO(
                material.getMaterial_id(),
                material.getSupplier_id(),
                material.getName(),
                material.getUnit(),
                material.getQty_available()
        );
    }

    @Override
    public boolean saveMaterial(MaterialDTO materialDTO) throws SQLException {
        return materialDAO.saveData(new Material(
                materialDTO.getMaterial_id(),
                materialDTO.getMaterial_name(),
                materialDTO.getUnit(),
                materialDTO.getQtyAvailable(),
                materialDTO.getSupplier_id()
        ));
    }

    @Override
    public boolean updateMaterial(MaterialDTO materialDTO) throws SQLException {
        return materialDAO.update(new Material(
                materialDTO.getMaterial_id(),
                materialDTO.getMaterial_name(),
                materialDTO.getUnit(),
                materialDTO.getQtyAvailable(),
                materialDTO.getSupplier_id()
        ));
    }

    @Override
    public boolean deleteMaterial(int id) throws SQLException {
        return materialDAO.deleteData(id);
    }

    @Override
    public List<MaterialDTO> getMaterial() throws SQLException {
        List<Material> material = materialDAO.get();
        ArrayList<MaterialDTO> materialDTOS = new ArrayList<>();

        for (Material material1 : material) {
            MaterialDTO materialDTO = new MaterialDTO(
                    material1.getMaterial_id(),
                    material1.getSupplier_id(),
                    material1.getName(),
                    material1.getUnit(),
                    material1.getQty_available()
            );

            materialDTOS.add(materialDTO);
        }
        return materialDTOS;
    }

    @Override
    public void printMaterialStockReport() throws SQLException, JRException {
        materialDAO.printMaterialStockReport();
    }

    @Override
    public void printLowMaterialStockReport() throws SQLException, JRException {
        materialDAO.printLowMaterialStockReport();
    }
}
