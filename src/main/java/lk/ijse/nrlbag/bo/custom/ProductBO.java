package lk.ijse.nrlbag.bo.custom;

import lk.ijse.nrlbag.bo.SuperBO;
import lk.ijse.nrlbag.dto.ProductDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO extends SuperBO {

     List<ProductDTO> getProductTable() throws SQLException;

     ProductDTO searchProduct(int id) throws SQLException;

     boolean saveProduct(ProductDTO productDTO) throws SQLException;

     boolean updateProduct(ProductDTO productDTO) throws SQLException;

     boolean deleteProduct(int id) throws SQLException;

     void printProductList() throws SQLException, JRException;

}
