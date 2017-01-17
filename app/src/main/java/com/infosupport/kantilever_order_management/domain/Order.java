package com.infosupport.kantilever_order_management.domain;

/**
 * Created by kdomi on 17-1-2017.
 */

public class Order {
    private String id, orderDate, status;

    public Order(String id, String orderDate, String status) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(){};

    @Override
    public String toString(){
        return "Order: " + id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
