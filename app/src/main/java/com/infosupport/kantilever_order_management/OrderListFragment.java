package com.infosupport.kantilever_order_management;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.infosupport.kantilever_order_management.content.Content;
import com.infosupport.kantilever_order_management.domain.Order;

import org.json.JSONArray;

import java.util.List;


public class OrderListFragment extends ListFragment {

	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	private Callbacks mCallbacks = sDummyCallbacks;

	private int mActivatedPosition = ListView.INVALID_POSITION;

	public static String orderState = "orderState";

	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}
    private String ALL_ORDERS_URL = "http://192.168.2.48:10007/orders/";
    private String POSTED_ORDERS_URL = "http://192.168.2.48:10007/orders/posted";
    private String PACKED_ORDERS_URL = "http://192.168.2.48:10007/orders/packed";
    private String STATUS_TO_PACKED_URL = "http://192.168.2.48:10007/orders/pack/";
    private String STATUS_TO_SENT_URL = "http://192.168.2.48:10007/orders/sent/";

	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	public OrderListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getActivity().getIntent().getStringExtra("OrderState") != null) {
			orderState = getActivity().getIntent().getStringExtra("OrderState");
		}
			if(orderState.equals("Posted")){
				getActivity().setTitle("Orders ready to pick");
				getOrders(POSTED_ORDERS_URL);
			} else if(orderState.equals("Packed")){
				getActivity().setTitle("Orders ready to send");
				getOrders(PACKED_ORDERS_URL);
			}



	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
								long id) {
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		// ADD CODE
		mCallbacks.onItemSelected(Content.getOrderList().get(position).getId());

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

        mActivatedPosition = position;
    }

    private void getOrders(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                List<Order> mOrders = JSONParser.parseOrdersJsonArray(jsonArray);
                Content.clearOrders();
                for (Order order : mOrders) {
                    Content.addOrder(order);
//                    setOrderToStatus(STATUS_TO_SENT_URL, order.getId());
                }
                setListAdapter(new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_list_item_activated_1,
                        android.R.id.text1, Content.getOrderList()));
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("getOrders", String.valueOf(error));
            }
        });
        requestQueue.add(request);
    }

    private void setOrderToStatus(String url, final String orderId) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        url += orderId;
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("OrderPacked", orderId + "successfull!");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("setOrderPacked",  orderId + "failed!!");

            }
        });
        requestQueue.add(request);
    }

}
