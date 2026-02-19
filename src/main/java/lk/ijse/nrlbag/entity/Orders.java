package lk.ijse.nrlbag.entity;

import lk.ijse.nrlbag.dto.OderDetailsDTO;

public class Orders {

    private int orders_id;
    private int customer_id;
    private String order_date;
    private String deadline;
    private String status;
    private double total_cost;
    private double remaining_payment;

    public Orders() {
    }

    public Orders(int orders_id, int customer_id, String order_date, String deadline, String status, double total_cost, double remaining_payment) {
        this.orders_id = orders_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.deadline = deadline;
        this.status = status;
        this.total_cost = total_cost;
        this.remaining_payment = remaining_payment;
    }

    public Orders(int orders_id, int customer_id, String order_date, String deadline, String status, double total_cost) {
        this.orders_id = orders_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.deadline = deadline;
        this.status = status;
        this.total_cost = total_cost;
    }

    public Orders(int customer_id, String order_date, String deadline, String status, double total_cost) {
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.deadline = deadline;
        this.status = status;
        this.total_cost = total_cost;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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
}
