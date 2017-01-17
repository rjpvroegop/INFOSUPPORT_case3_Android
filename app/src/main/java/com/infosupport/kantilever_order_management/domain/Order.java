package com.infosupport.kantilever_order_management.domain;


import java.util.Collection;

/**
 * Created by kdomi on 17-1-2017.
 */

public class Order {
    private String id, orderTime, orderState;
    private Address shippingAddress;
    private Collection<OrderItem> items;

    @Override
    public String toString(){
        return "Order: " + id;
    }

    public Order(String id, Collection<OrderItem> items, String orderState, String orderTime, Address shippingAddress) {
        this.id = id;
        this.items = items;
        this.orderState = orderState;
        this.orderTime = orderTime;
        this.shippingAddress = shippingAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<OrderItem> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItem> items) {
        this.items = items;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
