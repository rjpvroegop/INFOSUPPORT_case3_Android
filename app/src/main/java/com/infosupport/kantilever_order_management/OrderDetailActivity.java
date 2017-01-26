package com.infosupport.kantilever_order_management;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.infosupport.kantilever_order_management.content.Content;

import static com.infosupport.kantilever_order_management.OrderDetailFragment.ARG_ITEM_ID;


public class OrderDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(ARG_ITEM_ID, getIntent()
                    .getStringExtra(ARG_ITEM_ID));
            OrderDetailFragment fragment = new OrderDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().
                    add(R.id.order_detail_container, fragment).commit();
            //knoppen op basis van status
            Bundle argumentsUpdateId = new Bundle();
            argumentsUpdateId.putString("id", getIntent().getStringExtra(ARG_ITEM_ID));
            if (Content.getOrderMap().get(getIntent().getStringExtra(ARG_ITEM_ID)).getOrderState().equals("Posted")) {
                OrderDetailStatePostedFragment postedFragment = new OrderDetailStatePostedFragment();
                postedFragment.setArguments(argumentsUpdateId);
                getSupportFragmentManager().beginTransaction().replace(R.id.order_detail_buttonsContainer, postedFragment).commit();
            } else {
                OrderDetailStatePackedFragment packedFragment = new OrderDetailStatePackedFragment();
                packedFragment.setArguments(argumentsUpdateId);
                getSupportFragmentManager().beginTransaction().replace(R.id.order_detail_buttonsContainer, packedFragment).commit();
            }
        }
    }


}
