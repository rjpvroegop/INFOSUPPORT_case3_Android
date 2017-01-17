package com.infosupport.kantilever_order_management;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class OrderListActivity extends FragmentActivity implements
		OrderListFragment.Callbacks {

	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_list);

		//als het een tabletscherm betreft two pane layout
		if (findViewById(R.id.order_detail_container) != null) {
			mTwoPane = true;
			((OrderListFragment) getSupportFragmentManager().findFragmentById(R.id.order_list)).setActivateOnItemClick(true);
		}

	}

	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putString(OrderDetailFragment.ARG_ITEM_ID, id);
			OrderDetailFragment fragment = new OrderDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction().
					replace(R.id.order_detail_container, fragment).commit();
			
		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			// ADD CODE
			Intent detailIntent = new Intent(this, OrderDetailActivity.class);
			detailIntent.putExtra(OrderDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
