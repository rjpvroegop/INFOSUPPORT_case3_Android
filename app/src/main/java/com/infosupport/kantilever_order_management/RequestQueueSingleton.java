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

public final class RequestQueueSingleton {
    private static RequestQueueSingleton mInstance;
    private RequestQueue requestQueue;
    private static Context ctx;

    public static final String BASE_URL = "http://10.32.40.39:10001/bsbestellingenbeheer/";
    public static final String ALL_ORDERS_URL = BASE_URL + "orders/";
    public static final String POSTED_ORDERS_URL = BASE_URL + "orders/posted";
    public static final String PACKED_ORDERS_URL = BASE_URL + "orders/packed";
    public static final String STATUS_TO_PACKED_URL = BASE_URL + "orders/pack/";
    public static final String STATUS_TO_SENT_URL = BASE_URL + "orders/sent/";

    private final int retryMs = 1000;

    private RequestQueueSingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
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
        req.setRetryPolicy(new DefaultRetryPolicy(retryMs, 2, 2));
        getRequestQueue().add(req);
    }

    public void setOrderToStatus(String url, final String orderId, final Activity activity) {
        String finalUrl = url + orderId;
        StringRequest request = new StringRequest(Request.Method.POST, finalUrl, new Response.Listener<String>() {
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
