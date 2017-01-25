package com.infosupport.kantilever_order_management.domain;


import java.util.Collection;

/**
 * Created by kdomi on 17-1-2017.
 */

public class Order {
    private String id, bsKey, orderTime, orderState;
    private Address shippingAddress;
    private Collection<OrderItem> items;

    @Override
    public String toString() {
        return bsKey;
    }

    public Order(String id, String bsKey, String orderState, String orderTime,
                 Address shippingAddress, Collection<OrderItem> items) {
        this.id = id;
        this.bsKey = bsKey;
        this.items = items;
        this.orderState = orderState;
        this.orderTime = orderTime;
        this.shippingAddress = shippingAddress;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBsKey() {
        return bsKey;
    }

    public void setBsKey(String bsKey) {
        this.bsKey = bsKey;
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
