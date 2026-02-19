package lk.ijse.nrlbag.entity;

public class Product {

    private int product_id;
    private String name;
    private String size;
    private double basic_price;

    public Product() {
    }

    public Product(int product_id, String name, String size, double basic_price) {
        this.product_id = product_id;
        this.name = name;
        this.size = size;
        this.basic_price = basic_price;
    }

    public Product(String name, String size, double basic_price) {
        this.name = name;
        this.size = size;
        this.basic_price = basic_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getBasic_price() {
        return basic_price;
    }

    public void setBasic_price(double basic_price) {
        this.basic_price = basic_price;
    }
}
