package com.infosupport.kantilever_order_management.domain;

/**
 * Created by kdomi on 17-1-2017.
 */
public class OrderItem {
    private int amount;
    private Product product;

    public OrderItem(int amount, Product product) {

        this.amount = amount;
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
