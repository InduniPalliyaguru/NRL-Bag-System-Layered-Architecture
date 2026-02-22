package lk.ijse.nrlbag.dto.tm;

public class PaymentTM {

    private int id;
    private int order_id;
    private double amount;
    private String payment_date;
    private String type;
    private String status;

    public PaymentTM() {
    }

    public PaymentTM(int id, int order_id, double amount, String payment_date, String type, String status) {
        this.id = id;
        this.order_id = order_id;
        this.amount = amount;
        this.payment_date = payment_date;
        this.type = type;
        this.status = status;
    }

    public PaymentTM(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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
}
