package com.infosupport.kantilever_order_management.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.infosupport.kantilever_order_management.R;
import com.infosupport.kantilever_order_management.domain.OrderItem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kdomi on 17-1-2017.
 */

public class OrderItemPackedListAdapter extends BaseAdapter {
    class RowItem {
        private int amount;
        private String productName;
    }

    private Context context;
    private ArrayList<RowItem> rowItems = new ArrayList<>();

    public OrderItemPackedListAdapter(Context ctx) {
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
            view = viewInflater.inflate(R.layout.listitem_orderitempackedadapter, null);
        }

        TextView amount = (TextView) view.findViewById(R.id.listitempacked_orderitemadapter_amount);
        TextView productName = (TextView) view.findViewById(R.id.listitempacked_orderitemadapter_productName);

        RowItem rowItem = rowItems.get(index);
        amount.setText(String.valueOf(rowItem.amount));
        productName.setText(rowItem.productName);

        return view;
    }
}
