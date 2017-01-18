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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by kdomi on 17-1-2017.
 */

public class Content {

    //List van orders
    private static List<Order> orders = new ArrayList<Order>();
    //Map van orders op id
    private static Map<String, Order> orderMap = new HashMap<String, Order>();

    private static void addOrder(Order order){
        orders.add(order);
        orderMap.put(order.getId(), order);
    }

    static {
        addOrder(new Order("1", "Posted", "2017-01-16 11:55", new Address("Tilburg", "Heuvel 14", "5038CN"),
                new ArrayList<OrderItem>(Arrays.asList(new OrderItem(1, new Product(1L, "Fiets1")), new OrderItem(4, new Product(2L, "Fiets2"))))));
        addOrder(new Order("2", "Posted", "2017-01-16 12:55", new Address("Breda", "Heuvel 14", "5038CN"),
                new ArrayList<OrderItem>(Arrays.asList(new OrderItem(8, new Product(1L, "Fiets1"))))));
        addOrder(new Order("3", "Packed", "2017-01-16 13:55", new Address("Dordrecht", "Heuvel 14", "5038CN"),
                new ArrayList<OrderItem>(Arrays.asList(new OrderItem(1, new Product(3L, "Fiets3")), new OrderItem(2, new Product(4L, "Fiets4"))))));
    }

    public static List<Order> getOrderList(){
        return orders;
    }

    public static Map<String, Order> getOrderMap(){
        return orderMap;
    }
}
