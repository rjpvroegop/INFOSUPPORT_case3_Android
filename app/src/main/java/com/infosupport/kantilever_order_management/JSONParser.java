package com.infosupport.kantilever_order_management;

import android.util.Log;

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

/**
 * Created by maart on 18-1-2017.
 */

public class JSONParser {

    private static Address parseAddress(JSONObject JSONAddress){
        Address address = null;
        try{
            address = new Address(
                    JSONAddress.getString("city"),
                    JSONAddress.getString("street"),
                    JSONAddress.getString("zip"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return address;
    }

    private static List<OrderItem> parseOrderItems(JSONArray jsonArray){
        List<OrderItem> orderItems = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            try {
                OrderItem orderItem = null;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int amount = jsonObject.getInt("amount");
                Product product = parseProduct(jsonObject.getJSONObject("product"));

                orderItems.add(new OrderItem(amount, product));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderItems;
    }

    private static Product parseProduct(JSONObject jsonProduct){
        Product product = null;
        try{
            product = new Product(
                    jsonProduct.getLong("id"),
                    jsonProduct.getString("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }


    public static List<Order> parseOrdersJsonArray(JSONArray jsonArray) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String orderState = jsonObject.getString("orderState");
                String orderTime = jsonObject.getString("orderTime");

                Address address = parseAddress(jsonObject.getJSONObject("shippingAddress"));
                Collection<OrderItem> orderItems = parseOrderItems(jsonObject.getJSONArray("items"));

                Order order = new Order();
                order.setId(id);
                order.setOrderState(orderState);
                order.setOrderTime(orderTime);
                order.setItems(orderItems);
                order.setShippingAddress(address);
                orders.add(order);
            } catch (Exception e) {
                Log.e("parsing", e.toString());
            }
        }
        return orders;
    }


}
