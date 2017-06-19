package com.lianreviews.resturantsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class CreateOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("Cola", true, 1, 2));
        orders.add(new Order("Fanta", true, 3, 1));
        orders.add(new Order("Banana milkshake", true, 1, 4));
        orders.add(new Order("Hamburger", true, 1, 5));

        //Create an OrderAdapter, whose data source is a list of Order.
        //The adapter knows how to create list items for each item in the list.
        OrderAdapter orderAdapter = new OrderAdapter(this, orders);

        //Get the ListView and set the adapter for the listView
        ListView listView = (ListView) findViewById(R.id.create_order_activity);
        listView.setAdapter(orderAdapter);
    }
}
