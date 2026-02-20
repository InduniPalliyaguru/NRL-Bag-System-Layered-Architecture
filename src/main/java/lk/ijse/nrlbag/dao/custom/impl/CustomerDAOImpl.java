package lk.ijse.nrlbag.dao.custom.impl;

import lk.ijse.nrlbag.dao.custom.CustomerDAO;
import lk.ijse.nrlbag.dao.CrudUtil;
import lk.ijse.nrlbag.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public int totalCustomerCount() throws SQLException {

        // in here get the number of the customers from customer table
        ResultSet result = CrudUtil.execute("SELECT COUNT(*) AS Total_customer FROM Customer");
        int customerCount = 0;

        // get the int value from the execution
        if (result.next()) {
            customerCount = result.getInt("Total_customer");
        }

        return customerCount;

    }

    @Override
    public String save(Customer entity) throws SQLException {

        // in here before saving customer checking the already save or not
        Customer cus = search(entity.getContact());

        // if not save before insert data to the database
        if(cus == null) {
            boolean result = CrudUtil.execute("INSERT INTO Customer (name,address,contact) VALUES (?,?,?)",
                    entity.getName(),
                    entity.getAddress(),
                    entity.getContact());

            return result ? "" : "Failed to save customer!";

        } else {
            return "Customer contact already exist.";
        }
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        return CrudUtil.execute("UPDATE Customer SET name=? , address=? , contact=? WHERE customer_id=?",
                entity.getName(),
                entity.getAddress(),
                entity.getContact(),
                entity.getCustomer_id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Customer WHERE customer_id=?", Integer.parseInt(id));

    }

    @Override
    public Customer search(String contact) throws SQLException {
        //get the details according to the contact number
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer WHERE contact = ?",contact);

        if(result.next()) {
            return new Customer(
                    result.getInt("customer_id"),
                    result.getString("name"),
                    result.getString("address"),
                    result.getString("contact"),
                    result.getString("create_date")
            );
        }
        return null;
    }

    @Override
    public List<Customer> get() throws SQLException {
        // here, get the all the customer details to the list using customerDTO
        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer ORDER BY customer_id DESC");

        List<Customer> customerList = new ArrayList<>();

        while(rs.next()) {
            Customer cus = new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("contact"),
                    rs.getString("create_date")
            );
            customerList.add(cus);
        }
        return customerList;
    }

    @Override
    public boolean saveData(Customer entity) throws SQLException {
        return false;
    }

    @Override
    public Customer searchData(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteData(int id) throws SQLException {
        return false;
    }
}
