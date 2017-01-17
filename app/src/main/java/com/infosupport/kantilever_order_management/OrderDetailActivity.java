package com.infosupport.kantilever_order_management;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a single Person detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link OrderListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link OrderDetailFragment}.
 */
public class OrderDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(OrderDetailFragment.ARG_ITEM_ID, getIntent()
					.getStringExtra(OrderDetailFragment.ARG_ITEM_ID));
			// ADD CODE
			OrderDetailFragment fragment = new OrderDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction().
					add(R.id.order_detail_container, fragment).commit();
		}
	}

	
}
