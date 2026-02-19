package lk.ijse.nrlbag.entity;

public class Payment {

    private int payment_id;
    private double amount;
    private String payment_date;
    private String type;
    private String status;
    private int order_id;

    public Payment() {
    }

    public Payment(int payment_id, double amount, String payment_date, String type, String status, int order_id) {
        this.payment_id = payment_id;
        this.amount = amount;
        this.payment_date = payment_date;
        this.type = type;
        this.status = status;
        this.order_id = order_id;
    }

    public Payment(double amount, String payment_date, String type, String status, int order_id) {
        this.amount = amount;
        this.payment_date = payment_date;
        this.type = type;
        this.status = status;
        this.order_id = order_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
