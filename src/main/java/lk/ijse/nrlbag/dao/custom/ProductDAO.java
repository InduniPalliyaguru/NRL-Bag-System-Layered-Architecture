package lk.ijse.nrlbag.dao.custom;

import lk.ijse.nrlbag.dao.CrudDAO;
import lk.ijse.nrlbag.entity.Product;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<Product> {

    void printProductList() throws SQLException, JRException;

}
