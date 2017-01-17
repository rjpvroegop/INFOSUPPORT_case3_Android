package com.infosupport.kantilever_order_management;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infosupport.kantilever_order_management.content.Content;
import com.infosupport.kantilever_order_management.domain.Order;

/**
 * A fragment representing a single Person detail screen. This fragment is
 * either contained in a {@link OrderListActivity} in two-pane mode (on
 * tablets) or a {@link OrderDetailActivity} on handsets.
 */
public class OrderDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The content this fragment is presenting.
	 */
	private Order mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public OrderDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = Content.getOrderMap().get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_order_detail,
				container, false);

		// Show the content as text in a TextView.
		if (mItem != null) {
			// ADD CODE
			((TextView) rootView.findViewById(R.id.id)).setText(mItem.getId());
			((TextView) rootView.findViewById(R.id.orderDateTime)).setText(mItem.getOrderDate());
			((TextView) rootView.findViewById(R.id.status)).setText(mItem.getStatus());
		}

		return rootView;
	}
}
