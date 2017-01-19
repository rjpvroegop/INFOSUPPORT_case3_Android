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

    private String id = "id";

    public OrderDetailStatePostedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("id")) {
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
                RequestQueueSingleton.getInstance(getActivity().getApplicationContext())
                        .setOrderToStatus(RequestQueueSingleton.STATUS_TO_PACKED_URL, id, getActivity());

            }
        });
        return rootView;
    }
}
