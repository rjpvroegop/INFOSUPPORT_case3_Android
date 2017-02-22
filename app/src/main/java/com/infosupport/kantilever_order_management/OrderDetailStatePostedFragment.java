package com.infosupport.kantilever_order_management;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.infosupport.kantilever_order_management.adapters.OrderItemPostedListAdapter;
import com.infosupport.kantilever_order_management.content.Content;
import com.infosupport.kantilever_order_management.domain.Order;


/**
 * Created by kdomi on 17-1-2017.
 */

public class OrderDetailStatePostedFragment extends Fragment {

    private String id = "id";
    private Order activeOrder;
    public OrderDetailStatePostedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("id")) {
            id = getArguments().getString("id");
        }
        if (!id.equals("id")){
            activeOrder = Content.getOrderMap().get(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_detail_state_posted, container, false);
        OrderItemPostedListAdapter adapter = new OrderItemPostedListAdapter(this.getContext());
        adapter.addAllOrderItems(activeOrder.getItems());
        ((ListView) rootView.findViewById(R.id.orderItemPostedList)).setAdapter(adapter);


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
