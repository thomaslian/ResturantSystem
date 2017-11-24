package com.lianreviews.resturantsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.lianreviews.resturantsystem.category.CreateCategory;
import com.lianreviews.resturantsystem.orders.OrderAdapter;
import com.lianreviews.resturantsystem.orders.OrdersAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Orders");

        //Show the current orders on the main page
        if (ResourceManager.loadOrders(this) != null) {
            //Create an OrderAdapter, whose data source is a list of Order.
            //The adapter knows how to create list items for each item in the list.
            OrdersAdapter ordersAdapter = new OrdersAdapter(
                    this, ResourceManager.loadOrders(this));

            //Get the ListView and set the adapter for the listView
            ListView listView = findViewById(R.id.main_activity_linear_layout);
            if(ResourceManager.loadOrders(this) != null) {
                listView.setAdapter(ordersAdapter);
            }
        }


        //Create a FloatingActionButton variable and assign it to our floating action button
        FloatingActionButton floatingActionButton = findViewById(R.id.main_page_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new intent to open the Create Category class
                Intent createOrderIntent =
                        new Intent(MainActivity.this, CreateCategory.class);

                //Start the new activity
                startActivity(createOrderIntent);
            }
        });
    }
}
