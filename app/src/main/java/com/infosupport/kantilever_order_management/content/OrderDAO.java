package com.infosupport.kantilever_order_management.content;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.infosupport.kantilever_order_management.domain.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by maart on 17-1-2017.
 */

public class OrderDAO {

    private Handler mHandler;
    private AsyncTask<Void, Void, Void> mTask;
    private static String PRODUCTS_URL = "http://10.32.40.6:10007/orders/";

    public void getAllOrdersAsync(Handler handler){
        mHandler = handler;
        mTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                Log.v("getAllOrdersAsync", "Loading data from AsyncTask");
                try {
                    Collection<Order> orders = parseJSON(retrieveJson());
                    Message msg = new Message();
                    msg.obj = orders;
                    if (!mTask.isCancelled()){
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        mTask.execute();
    }

    private JSONArray retrieveJson() throws Exception {
        JSONArray jObj = null;

        URL url = new URL(PRODUCTS_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream is;

        int code = urlConnection.getResponseCode();

        if (code != 200) {
            throw new Exception("Invalid status code: " + code);
        }
        Log.v("OrderDAO", "Successfully fetched JSON");
        try {
            is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            jObj = new JSONArray(sb.toString());
        } finally {
            urlConnection.disconnect();
        }
        return jObj;
    }

    private Collection<Order> parseJSON(JSONArray jsonArray) throws JSONException {
        Collection<Order> orders = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            Order order = new Order();

            order.setId(obj.getString("id"));
            orders.add(order);
        }
        return orders;
    }

}
