package lk.ijse.nrlbag.entity;

public class Customer {

    private int customer_id;
    private String name;
    private String address;
    private String contact;
    private String create_date;

    public Customer() {
    }

    public Customer(int customer_id, String name, String address, String contact, String create_date) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.create_date = create_date;
    }

    public Customer(int customer_id, String name, String address, String contact) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Customer(String name, String address, String contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
