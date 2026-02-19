package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.dto.ProductDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO extends SuperBO{

    public List<ProductDTO> getProductTable() throws SQLException;

    public ProductDTO searchProduct(int id) throws SQLException;

    public boolean saveProduct(ProductDTO productDTO) throws SQLException;

    public boolean updateProduct(ProductDTO productDTO) throws SQLException;

    public boolean deleteProduct(int id) throws SQLException;

    public void printProductList() throws SQLException, JRException;

}
