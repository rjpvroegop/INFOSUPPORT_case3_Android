package com.infosupport.kantilever_order_management;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.infosupport.kantilever_order_management.content.Content;

import static com.infosupport.kantilever_order_management.OrderDetailFragment.ARG_ITEM_ID;

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
			arguments.putString(ARG_ITEM_ID, id);
			OrderDetailFragment fragment = new OrderDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction().
					replace(R.id.order_detail_container, fragment).commit();

			//knoppen op basis van status
			Bundle argumentsUpdateId = new Bundle();
			argumentsUpdateId.putString("id", id);
			if(Content.getOrderMap().get(id).getOrderState().equals("Posted")){
				OrderDetailStatePostedFragment postedFragment = new OrderDetailStatePostedFragment();
				postedFragment.setArguments(argumentsUpdateId);
				getSupportFragmentManager().beginTransaction().replace(R.id.order_detail_buttonsContainer, postedFragment).commit();
			} else {
				OrderDetailStatePackedFragment packedFragment = new OrderDetailStatePackedFragment();
				packedFragment.setArguments(argumentsUpdateId);
				getSupportFragmentManager().beginTransaction().replace(R.id.order_detail_buttonsContainer, packedFragment).commit();
			}

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			// ADD CODE
			Intent detailIntent = new Intent(this, OrderDetailActivity.class);
			detailIntent.putExtra(ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
