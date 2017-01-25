package com.infosupport.kantilever_order_management;

import com.infosupport.kantilever_order_management.domain.Address;
import com.infosupport.kantilever_order_management.domain.Order;
import com.infosupport.kantilever_order_management.domain.OrderItem;
import com.infosupport.kantilever_order_management.domain.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by maart on 18-1-2017.
 */

public class JSONParser {

    private static Logger LOGGER = Logger.getLogger("JSONParser");

    private JSONParser(){

    }

    private static Address parseAddress(JSONObject jsonAddress) {
        try {
            return new Address(
                    jsonAddress.getString("city"),
                    jsonAddress.getString("street"),
                    jsonAddress.getString("zip"));
        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "parseAddress", e);
        }
        return null;
    }

    private static List<OrderItem> parseOrderItems(JSONArray jsonArray) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject orderItem = jsonArray.getJSONObject(i);
                int amount = orderItem.getInt("amount");
                Product product = parseProduct(orderItem.getJSONObject("product"));

                orderItems.add(new OrderItem(amount, product));
            } catch (JSONException e) {
                LOGGER.log(Level.SEVERE, "parseOrderItems", e);
            }
        }
        return orderItems;
    }

    private static Product parseProduct(JSONObject jsonProduct) {
        try {
            return new Product(
                    jsonProduct.getLong("id"),
                    jsonProduct.getString("name"));
        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "jsonProduct", e);
        }
        return null;
    }


    public static List<Order> parseOrdersJsonArray(JSONArray jsonArray) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String bsKey = jsonObject.getString("bsKey");
                String orderState = jsonObject.getString("orderState");
                String orderTime = jsonObject.getString("orderTime");

                Address address = parseAddress(jsonObject.getJSONObject("shippingAddress"));
                Collection<OrderItem> orderItems = parseOrderItems(jsonObject.getJSONArray("orderitems"));

                Order order = new Order();
                order.setId(id);
                order.setBsKey(bsKey);
                order.setOrderState(orderState);
                order.setOrderTime(orderTime);
                order.setItems(orderItems);
                order.setShippingAddress(address);
                orders.add(order);
            } catch (JSONException e) {
                LOGGER.log(Level.SEVERE, "parseOrdersJsonArray", e);
            }
        }
        return orders;
    }


}
