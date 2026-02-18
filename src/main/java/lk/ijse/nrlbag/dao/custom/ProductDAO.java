package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.SuperDAO;
import lk.ijse.nrlbag.dto.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends SuperDAO {

    public List<ProductDTO> getProductTable() throws SQLException;

    public ProductDTO searchProduct(int id) throws SQLException;

    public boolean saveProduct(ProductDTO productDTO) throws SQLException;

    public boolean updateProduct(ProductDTO productDTO) throws SQLException;

    public boolean deleteProduct(int id) throws SQLException;

}
