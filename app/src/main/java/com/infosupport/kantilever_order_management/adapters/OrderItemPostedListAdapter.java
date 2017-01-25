package com.infosupport.kantilever_order_management.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.infosupport.kantilever_order_management.R;
import com.infosupport.kantilever_order_management.domain.OrderItem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kdomi on 17-1-2017.
 */

public class OrderItemPostedListAdapter extends BaseAdapter {
    class RowItem {
        private int amount;
        private String productName;
    }

    private Context context;
    private ArrayList<RowItem> rowItems = new ArrayList<>();
    private int countChecked = 0;

    public OrderItemPostedListAdapter(Context ctx) {
        super();
        context = ctx;
    }

    public void addAllOrderItems(Collection<OrderItem> orderItemsToAdd) {
        for (OrderItem orderItem : orderItemsToAdd) {
            RowItem rowItem = new RowItem();
            rowItem.amount = orderItem.getAmount();
            rowItem.productName = orderItem.getProduct().getName();
            rowItems.add(rowItem);
        }
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater viewInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = viewInflater.inflate(R.layout.listitem_orderitempostedadapter, null);
        }

        TextView amount = (TextView) view.findViewById(R.id.listitem_orderitemadapter_amount);
        TextView productName = (TextView) view.findViewById(R.id.listitem_orderitemadapter_productName);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.listitem_orderitemadapter_picked);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button postedButton = (Button) view.getRootView().findViewById(R.id.order_detail_state_postedButton);
                if (checkBox.isChecked()) {
                    countChecked++;
                    Log.v("Click", "Enable");
                    if (getCount() == countChecked) {
                        postedButton.setEnabled(true);
                    }
                } else {
                    countChecked--;
                    if (getCount() != countChecked) {
                        postedButton.setEnabled(false);
                    }
                    Log.v("Click", "Disable");
                }

            }
        });

        RowItem rowItem = rowItems.get(index);
        amount.setText(String.valueOf(rowItem.amount));
        productName.setText(rowItem.productName);

        return view;
    }
}
