package lk.ijse.nrlbag.entity;

public class Order_Details {

    private int orders_id;
    private int product_id;
    private int quantity;
    private double unit_price;

    public Order_Details() {
    }

    public Order_Details(int orders_id, int product_id, int quantity, double unit_price) {
        this.orders_id = orders_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public Order_Details(int product_id, int quantity, double unit_price) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
}
