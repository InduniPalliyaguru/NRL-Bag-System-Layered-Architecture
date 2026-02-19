package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.ProductBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.ProductDAO;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.dto.ProductDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    private final ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);

    @Override
    public List<ProductDTO> getProductTable() throws SQLException {
        return productDAO.getProductTable();
    }

    @Override
    public ProductDTO searchProduct(int id) throws SQLException {
        return productDAO.searchProduct(id);
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) throws SQLException {
        return productDAO.saveProduct(productDTO);
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws SQLException {
        return productDAO.updateProduct(productDTO);
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        return productDAO.deleteProduct(id);
    }

    @Override
    public void printProductList() throws SQLException, JRException {
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObj = getClass().getResourceAsStream("/lk/ijse/nrlbag/reports/productList.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(reportObj);

        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

        JasperViewer.viewReport(jp, false);
    }
}
