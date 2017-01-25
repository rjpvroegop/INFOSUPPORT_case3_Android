package com.infosupport.kantilever_order_management;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.infosupport.kantilever_order_management.adapters.OrderItemPackedListAdapter;
import com.infosupport.kantilever_order_management.content.Content;
import com.infosupport.kantilever_order_management.domain.Order;


/**
 * Created by kdomi on 17-1-2017.
 */

public class OrderDetailStatePackedFragment extends Fragment {

    private String id = "id";
    private Order activeOrder;

    public OrderDetailStatePackedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString("id");
        if (!id.equals("id")) {
            activeOrder = Content.getOrderMap().get(id);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_detail_state_packed, container, false);
        OrderItemPackedListAdapter adapter = new OrderItemPackedListAdapter(this.getContext());
        adapter.addAllOrderItems(activeOrder.getItems());
        ((ListView) rootView.findViewById(R.id.orderItemPackedList)).setAdapter(adapter);


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
