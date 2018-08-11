package com.lianreviews.resturantsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lianreviews.resturantsystem.orders.OrdersAdapter;

public class Archive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Archived orders");

        //TODO: Make the Archived button from dropdown menu in navigation clickable
        //Show the archived orders on the main page
        if (ResourceManager.loadArchivedOrders(this) != null) {
            //Create an OrderAdapter, whose data source is a list of Order.
            //The adapter knows how to create list items for each item in the list.
            OrdersAdapter ordersAdapter = new OrdersAdapter(
                    this, ResourceManager.loadArchivedOrders(this));

            //Get the ListView and set the adapter for the listView
            ListView listView = findViewById(R.id.main_activity_list_view);
            if(ResourceManager.loadArchivedOrders(this) != null) {
                listView.setAdapter(ordersAdapter);

            }
        }
    }
}
