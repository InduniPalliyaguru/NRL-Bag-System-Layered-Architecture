package lk.ijse.nrlbag.dto;

public class CustomDTO {

    private int order_id;
    private Integer material_id;
    private Double qty_used;
    private String material_name;
    private String unit;
    private int product_id;
    private int quantity;
    private double unit_price;
    private String name;
    private int id;
    private int customer_id;
    private String customerContact;
    private String order_date;
    private String deadline;
    private String status;
    private double total_cost;
    private double remaining_payment;
    private int productId;
    private OderDetailsDTO orderDetails;
    private String address;
    private String contact;
    private int materialId;
    private String materialName;

    public CustomDTO() {
    }

    public CustomDTO(int order_id, Integer material_id, Double qty_used, String material_name, String unit) {
        this.order_id = order_id;
        this.material_id = material_id;
        this.qty_used = qty_used;
        this.material_name = material_name;
        this.unit = unit;
    }

    public CustomDTO(int product_id, int quantity, double unit_price, String name) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.name = name;
    }

    public CustomDTO(int id, int customer_id, String name, String customerContact, String order_date, String deadline, String status, double total_cost, double remaining_payment, int productId, int quantity) {
        this.id = id;
        this.customer_id = customer_id;
        this.name = name;
        this.customerContact = customerContact;
        this.order_date = order_date;
        this.deadline = deadline;
        this.status = status;
        this.total_cost = total_cost;
        this.remaining_payment = remaining_payment;
        this.productId = productId;
        this.quantity = quantity;
    }

//    orderId,cus_id,cusName,contact,order_date,deadline,status,cost,remain


    public CustomDTO(int order_id, int customer_id, String name, String customerContact, String order_date, String deadline, String status, double total_cost, double remaining_payment) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.name = name;
        this.customerContact = customerContact;
        this.order_date = order_date;
        this.deadline = deadline;
        this.status = status;
        this.total_cost = total_cost;
        this.remaining_payment = remaining_payment;
    }

    public CustomDTO(int id, String name, String address, String contact, int materialId, String materialName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.materialId = materialId;
        this.materialName = materialName;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Integer getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Integer material_id) {
        this.material_id = material_id;
    }

    public Double getQty_used() {
        return qty_used;
    }

    public void setQty_used(Double qty_used) {
        this.qty_used = qty_used;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public double getRemaining_payment() {
        return remaining_payment;
    }

    public void setRemaining_payment(double remaining_payment) {
        this.remaining_payment = remaining_payment;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public OderDetailsDTO getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OderDetailsDTO orderDetails) {
        this.orderDetails = orderDetails;
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

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
