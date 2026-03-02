package lk.ijse.nrlbag.bo.custom.impl;

import lk.ijse.nrlbag.bo.custom.ProductBO;
import lk.ijse.nrlbag.dao.DAOFactory;
import lk.ijse.nrlbag.dao.custom.ProductDAO;
import lk.ijse.nrlbag.dto.ProductDTO;
import lk.ijse.nrlbag.entity.Product;
import net.sf.jasperreports.engine.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    private final ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);

    @Override
    public List<ProductDTO> getProductTable() throws SQLException {

        List<Product> products = productDAO.get();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product pro : products) {
            ProductDTO productDTO = new ProductDTO(
                    pro.getProduct_id(),
                    pro.getName(),
                    pro.getSize(),
                    pro.getBasic_price()
            );
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO searchProduct(int id) throws SQLException {
        Product pro = productDAO.searchData(id);
        return new ProductDTO(
                pro.getProduct_id(),
                pro.getName(),
                pro.getSize(),
                pro.getBasic_price()
        );
    }

    @Override
    public boolean saveProduct(ProductDTO productDTO) throws SQLException {
        return productDAO.saveData(new Product(productDTO.getName(), productDTO.getSize(), productDTO.getBasePrice()));
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws SQLException {
        return productDAO.update(new Product(
                productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getSize(),
                productDTO.getBasePrice()
        ));
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        return productDAO.deleteData(id);
    }

    @Override
    public void printProductList() throws SQLException, JRException {
        productDAO.printProductList();
    }
}
