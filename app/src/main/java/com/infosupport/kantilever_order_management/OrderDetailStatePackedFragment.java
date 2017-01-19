package com.infosupport.kantilever_order_management;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import static android.R.attr.duration;
import static android.os.Build.ID;
import static com.infosupport.kantilever_order_management.OrderDetailFragment.ARG_ITEM_ID;


/**
 * Created by kdomi on 17-1-2017.
 */

public class OrderDetailStatePackedFragment extends Fragment {

    private String id = "id";

    public OrderDetailStatePackedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString("id");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_detail_state_packed, container, false);
        Button button = ((Button) rootView.findViewById(R.id.order_detail_state_packedButton));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Verander status naar "Finished" (REST call)
                RequestQueueSingleton.getInstance(getActivity().getApplicationContext())
                        .setOrderToStatus(RequestQueueSingleton.STATUS_TO_SENT_URL, id, getActivity());

            }
        });

        return rootView;
    }

}
