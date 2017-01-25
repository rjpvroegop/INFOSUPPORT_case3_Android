package com.infosupport.kantilever_order_management;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infosupport.kantilever_order_management.content.Content;
import com.infosupport.kantilever_order_management.domain.Order;

public class OrderDetailFragment extends Fragment {
	public static final String ARG_ITEM_ID = "item_id";

	private Order mItem;

	public OrderDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			mItem = Content.getOrderMap().get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_order_detail,
				container, false);

		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.id)).setText(mItem.getId());
			((TextView) rootView.findViewById(R.id.orderDateTime)).setText(mItem.getOrderTime());
			((TextView) rootView.findViewById(R.id.status)).setText(mItem.getOrderState());
			((TextView) rootView.findViewById(R.id.address)).setText(mItem.getShippingAddress().toString());

		}

		return rootView;
	}
}
