package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.ProductDAO;
import lk.ijse.nrlbag.dao.CrudUtil;
import lk.ijse.nrlbag.db.DBConnection;
import lk.ijse.nrlbag.entity.Product;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> get() throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT * FROM Product ORDER BY product_id DESC");

        List<Product> productList = new ArrayList<>();

        while (rs.next()) {
            Product pro = new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("size"),
                    rs.getDouble("basic_price")
            );
            productList.add(pro);
        }

        return productList;
    }

    @Override
    public Product searchData(int id) throws SQLException {

        ResultSet rs = CrudUtil.execute("SELECT * FROM Product WHERE product_id=? " , id);

        if (rs.next()) {
            return new Product(
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("size"),
                    rs.getDouble("basic_price")
            );
        }
        return null;
    }

    @Override
    public boolean saveData(Product entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO Product (name, size, basic_price) VALUES (?,?,?)",
                entity.getName(),
                entity.getSize(),
                entity.getBasic_price()
        );

        return result;
    }

    @Override
    public boolean update(Product entity) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE Product SET name=?, size=?, basic_price=? WHERE product_id=?",
                entity.getName(),
                entity.getSize(),
                entity.getBasic_price(),
                entity.getProduct_id()
        );
        return result;
    }

    @Override
    public boolean deleteData(int id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM Product WHERE product_id=?",id);

        return result;
    }

    @Override
    public void printProductList() throws SQLException, JRException {
        Connection conn = DBConnection.getInstance().getConnection();

        InputStream reportObj = getClass().getResourceAsStream("/lk/ijse/nrlbag/reports/productList.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(reportObj);

        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

        JasperViewer.viewReport(jp, false);
    }

    @Override
    public String save(Product entity) throws SQLException {
        return "";
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Product search(String contact) throws SQLException {
        return null;
    }

}
