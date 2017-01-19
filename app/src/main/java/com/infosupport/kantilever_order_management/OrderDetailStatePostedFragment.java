package com.infosupport.kantilever_order_management;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.infosupport.kantilever_order_management.OrderDetailFragment.ARG_ITEM_ID;


/**
 * Created by kdomi on 17-1-2017.
 */

public class OrderDetailStatePostedFragment extends Fragment {

    private String STATUS_TO_PACKED_URL = "http://10.32.41.111:10007/orders/pack/";
    String id = "id";
    public OrderDetailStatePostedFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey("id")){
            id = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_detail_state_posted, container, false);

        Button button = ((Button) rootView.findViewById(R.id.order_detail_state_postedButton));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Verander status naar "Finished" (REST call)
                setOrderToStatus(STATUS_TO_PACKED_URL, id);

            }
        });

        return rootView;
    }

    private void setOrderToStatus(String url, final String orderId) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        url += orderId;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent i = new Intent(getActivity(), OrderListActivity.class);
                startActivity(i);
                Toast.makeText(getActivity().getApplicationContext(), "Status change: Success", Toast.LENGTH_SHORT).show();
                Log.v("OrderPacked", orderId + "successfull!");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Status change: Failed", Toast.LENGTH_SHORT).show();
                Log.v("setOrderPacked",  orderId + "failed!!");

            }
        });
        requestQueue.add(request);
    }
}
