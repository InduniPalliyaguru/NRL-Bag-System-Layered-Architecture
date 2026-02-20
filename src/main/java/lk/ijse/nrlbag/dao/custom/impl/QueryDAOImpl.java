package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.CrudUtil;
import lk.ijse.nrlbag.dao.custom.QueryDAO;
import lk.ijse.nrlbag.dto.tm.MaterialUsedTM;
import lk.ijse.nrlbag.dto.tm.OrderDetailsTM;
import lk.ijse.nrlbag.dto.tm.OrdersTM;
import lk.ijse.nrlbag.dto.tm.SupplierTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<MaterialUsedTM> getMaterialUsage() throws SQLException {
        ResultSet rs = CrudUtil.execute(
                "SELECT mu.orders_id, mu.material_id, mu.used_qty, m.name, m.unit " +
                        "FROM Material m JOIN Material_Used mu ON m.material_id = mu.material_id;"
        );

        List<MaterialUsedTM> materialUsedList = new ArrayList<>();

        // get rows one by one and add into order list
        while (rs.next()) {
            MaterialUsedTM usedList = new MaterialUsedTM(
                    rs.getInt("orders_id"),
                    rs.getInt("material_id"),
                    rs.getDouble("used_qty"),
                    rs.getString("name"),
                    rs.getString("unit")
            );
            materialUsedList.add(usedList);
        }
        return materialUsedList;
    }

    @Override
    public OrderDetailsTM searchProduct(int id) throws SQLException {
        // here, get details of the product
        ResultSet rs = CrudUtil.execute("SELECT o.product_id, o.quantity, o.unit_price, p.name FROM Order_Details o " +
                "JOIN Product p on o.product_id = p.product_id WHERE o.product_id=?;",id);

        if(rs.next()) {
            int productId = rs.getInt("product_id");
            String name = rs.getString("name");
            int qty = rs.getInt("quantity");
            double price = rs.getDouble("unit_price");

            return new OrderDetailsTM(productId, qty, price, name);
        }
        return null;
    }

    @Override
    public List<OrdersTM> getOrders() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT " +
                " o.orders_id," +
                " o.customer_id," +
                " c.name," +
                " c.contact," +
                " o.order_date," +
                " o.deadline," +
                " o.status," +
                " o.total_cost," +
                " o.remaining_payment," +
                " od.product_id," +
                " od.quantity" +
                " FROM Orders o" +
                " JOIN Customer c ON o.customer_id = c.customer_id" +
                " LEFT JOIN Order_Details od ON o.orders_id = od.orders_id;");

        List<OrdersTM> orderList = new ArrayList<>();

        // get rows one by one and add into order list
        while (rs.next()) {
            OrdersTM ordersTM = new OrdersTM(
                    rs.getInt("orders_id"),
                    rs.getInt("customer_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("order_date"),
                    rs.getString("deadline"),
                    rs.getString("status"),
                    rs.getDouble("total_cost"),
                    rs.getDouble(("remaining_payment")),
                    rs.getInt("product_id"),
                    rs.getInt("quantity")
            );
            orderList.add(ordersTM);
        }
        return orderList;
    }

    @Override
    public OrdersTM searchOrderByOrderID(int id) throws SQLException {

        // here, get details of the order and customer who place that order using a join query
        ResultSet rs = CrudUtil.execute("SELECT c.name, c.contact,o.customer_id, o.orders_id, o.order_date, " +
                "o.deadline, o.status, o.total_cost, o.remaining_payment FROM Orders o JOIN Customer c ON " +
                "o.customer_id = c.customer_id WHERE orders_id=?;",id);

        if(rs.next()) {
            int orderId = rs.getInt("orders_id");
            int cus_id = rs.getInt("customer_id");
            String order_date = rs.getString("order_date");
            String deadline = rs.getString("deadline");
            String status = rs.getString("status");
            double cost = rs.getDouble("total_cost");
            double remain = rs.getDouble("remaining_payment");
            String cusName = rs.getString("name");
            String contact = rs.getString("contact");

            return new OrdersTM(orderId,cus_id,cusName,contact,order_date,deadline,status,cost,remain);
        }
        return null;
    }

    @Override
    public OrdersTM searchOrderByCustomerID(int id) throws SQLException {
        // here, get details of the all orders and customer who place that orders using a join query
        ResultSet rs = CrudUtil.execute("SELECT c.name, c.contact,o.customer_id, o.orders_id, o.order_date, " +
                "o.deadline, o.status, o.total_cost, o.remaining_payment FROM Orders o JOIN Customer c ON " +
                "o.customer_id = c.customer_id WHERE o.customer_id=?;",id);

        if(rs.next()) {
            int orderId = rs.getInt("orders_id");
            int cus_id = rs.getInt("customer_id");
            String order_date = rs.getString("order_date");
            String deadline = rs.getString("deadline");
            String status = rs.getString("status");
            double cost = rs.getDouble("total_cost");
            double remain = rs.getDouble("remaining_payment");
            String cusName = rs.getString("name");
            String contact = rs.getString("contact");

            return new OrdersTM(orderId,cus_id,cusName,contact,order_date,deadline,status,cost,remain);
        }
        return null;
    }

    @Override
    public List<SupplierTM> getSuppliers() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT m.material_id, m.name,m.supplier_id, s.supplier_name, s.address, s.contact FROM " +
                "Material m JOIN Supplier s on m.supplier_id = s.supplier_id");

        List<SupplierTM> supplierList = new ArrayList<>();

        // get rows one by one and add into supplier list
        while (rs.next()) {
            SupplierTM supplierTM = new SupplierTM(
                    rs.getInt("supplier_id"),
                    rs.getString("supplier_name"),
                    rs.getString("address"),
                    rs.getString("contact"),
                    rs.getInt("material_id"),
                    rs.getString("name")
            );
            supplierList.add(supplierTM);
        }
        return supplierList;
    }
}
