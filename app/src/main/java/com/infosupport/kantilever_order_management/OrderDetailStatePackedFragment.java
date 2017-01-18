package com.infosupport.kantilever_order_management;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by kdomi on 17-1-2017.
 */

public class OrderDetailStatePackedFragment extends Fragment {

    public OrderDetailStatePackedFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_detail_state_packed, container, false);

        Button button = ((Button) rootView.findViewById(R.id.order_detail_state_packedButton));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Verander status naar "Send" (REST call)
            }
        });

        return rootView;
    }
}
