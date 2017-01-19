package com.infosupport.kantilever_order_management;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by maart on 19-1-2017.
 */

public class RequestQueueSingleton {
    private static RequestQueueSingleton mInstance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private RequestQueueSingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized RequestQueueSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestQueueSingleton(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(100, 2, 2));
        getRequestQueue().add(req);
    }

    public static String BASE_URL = "http://10.32.41.111:10007/";
    public static String ALL_ORDERS_URL = BASE_URL + "orders/";
    public static String POSTED_ORDERS_URL = BASE_URL + "orders/posted";
    public static String PACKED_ORDERS_URL = BASE_URL + "orders/packed";
    public static String STATUS_TO_PACKED_URL = BASE_URL + "orders/pack/";
    public static String STATUS_TO_SENT_URL = BASE_URL + "orders/sent/";

//    private void getOrders(String url, final Activity activity) {
//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray jsonArray) {
//                List<Order> mOrders = JSONParser.parseOrdersJsonArray(jsonArray);
//                Content.clearOrders();
//                for (Order order : mOrders) {
//                    Content.addOrder(order);
//                }
//                activity.setL
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.v("getOrders", String.valueOf(error));
//                Toast.makeText(ctx, "Could not connect to the server!", Toast.LENGTH_SHORT).show();
//                setListAdapter(new ArrayAdapter<>(ctx,
//                        android.R.layout.simple_list_item_activated_1,
//                        android.R.id.text1, new ArrayList<>()));
//            }
//        });
//        mInstance.addToRequestQueue(request);
//    }

    public void setOrderToStatus(String url, final String orderId, final Activity activity) {
        url += orderId;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent i = new Intent(activity, OrderListActivity.class);
                activity.startActivity(i);
                Toast.makeText(activity.getApplicationContext(), "Status change: Success", Toast.LENGTH_SHORT).show();
                Log.v("OrderPacked", orderId + "successfull!");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity.getApplicationContext(), "Status change: Failed", Toast.LENGTH_SHORT).show();
                Log.v("setOrderPacked", orderId + "failed!!");

            }
        });
        RequestQueueSingleton.getInstance(activity.getApplicationContext()).addToRequestQueue(request);
    }

}
