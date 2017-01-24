package com.infosupport.kantilever_order_management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by kdomi on 18-1-2017.
 */

public class DashboardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button postedOrdersButton = (Button) findViewById(R.id.posted_orders_button);
        postedOrdersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(DashboardActivity.this, OrderListActivity.class);
                i.putExtra("OrderState", "Posted");
                startActivity(i);
            }
        });

        Button packedOrdersButton = (Button) findViewById(R.id.packed_orders_button);
        packedOrdersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent i = new Intent(DashboardActivity.this, OrderListActivity.class);
                i.putExtra("OrderState", "Packed");
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            Boolean val = data.getBooleanExtra("user_accepted", false);

            Log.v("Result", "User accepted: " + val);
        } else {
            Log.v("Result", "User canceled");
        }
    }
}
