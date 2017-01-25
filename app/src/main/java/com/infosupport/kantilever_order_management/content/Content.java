package com.infosupport.kantilever_order_management.content;

import com.infosupport.kantilever_order_management.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kdomi on 17-1-2017.
 */

public class Content {

    //List van orders
    private static List<Order> orders = new ArrayList<>();
    //Map van orders op id
    private static Map<String, Order> orderMap = new HashMap<>();

    public static void clearOrders() {
        orders = new ArrayList<>();
        orderMap = new HashMap<>();
    }

    public static void addOrder(Order order) {
        orders.add(order);
        orderMap.put(order.getId(), order);
    }

    public static List<Order> getOrderList() {
        return orders;
    }

    public static Map<String, Order> getOrderMap() {
        return orderMap;
    }
}
