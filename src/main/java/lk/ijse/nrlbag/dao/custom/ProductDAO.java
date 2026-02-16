package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dto.ProductDTO;
import lk.ijse.nrlbag.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductDAO {

    public List<ProductDTO> getProductTable() throws SQLException;

    public ProductDTO searchProduct(int id) throws SQLException;

    public boolean saveProduct(ProductDTO productDTO) throws SQLException;

    public boolean updateProduct(ProductDTO productDTO) throws SQLException;

    public boolean deleteProduct(int id) throws SQLException;

}
