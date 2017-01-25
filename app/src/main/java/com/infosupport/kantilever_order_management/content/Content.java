package com.infosupport.kantilever_order_management.content;

import com.infosupport.kantilever_order_management.domain.Address;
import com.infosupport.kantilever_order_management.domain.Order;
import com.infosupport.kantilever_order_management.domain.OrderItem;
import com.infosupport.kantilever_order_management.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.order;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by kdomi on 17-1-2017.
 */

public class Content {

    //List van orders
    private static List<Order> orders = new ArrayList<Order>();
    //Map van orders op id
    private static Map<String, Order> orderMap = new HashMap<String, Order>();
    public static void clearOrders(){
        orders = new ArrayList<Order>();
        orderMap = new HashMap<String, Order>();
    }

    public static void addOrder(Order order){
        orders.add(order);
        orderMap.put(order.getId(), order);
    }

    public static List<Order> getOrderList(){
        return orders;
    }

    public static Map<String, Order> getOrderMap(){
        return orderMap;
    }
}
