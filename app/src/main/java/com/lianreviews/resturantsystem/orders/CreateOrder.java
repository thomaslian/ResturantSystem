package com.lianreviews.resturantsystem.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lianreviews.resturantsystem.MainActivity;
import com.lianreviews.resturantsystem.R;
import com.lianreviews.resturantsystem.ResourceManager;
import com.lianreviews.resturantsystem.category.CreateCategory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Button deleteOrderButton = findViewById(R.id.cancel_order_button_in_cart);
        Button orderButton = findViewById(R.id.order_button_in_cart);

        deleteOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteTempOrder()) {
                    Toast.makeText(CreateOrder.this, "Order is canceled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateOrder.this, "No order to cancel",
                            Toast.LENGTH_SHORT).show();
                }
                Intent addFoodIntent = new Intent(v.getContext(), CreateCategory.class);
                v.getContext().startActivity(addFoodIntent);
            }
        });

        OrderAdapter orderAdapter;


        //Show the current shopping cart with a OrderAdapter
        if (ResourceManager.loadTempOrder(this) != null) {
            //Create an OrderAdapter, whose data source is a list of Order.
            //The adapter knows how to create list items for each item in the list.
            orderAdapter = new OrderAdapter(this, ResourceManager.loadTempOrder(this));
        } else {
            ArrayList<Order> orders = new ArrayList<>();
            orders.add(new Order("Nothing ordered", true, 1,
                    2, 0));
            orderAdapter = new OrderAdapter(this, orders);
        }

        //Get the ListView and set the adapter for the listView
        ListView listView = findViewById(R.id.create_order_activity);
        listView.setAdapter(orderAdapter);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the current order
                ArrayList<Order> order = ResourceManager.loadTempOrder(CreateOrder.this);
                if (order != null) {
                    //Get all the orders from the save file
                    ArrayList<Orders> orders = ResourceManager.loadOrders(CreateOrder.this);
                    //If there is already a file, add the new order...
                    if (orders != null) {
                        //Add the new order to the rest of the orders
                        orders.add(new Orders(order, orders.size() + 1));
                    } else {
                        // ... OR if there is no savefile, create a new arraylist and add the
                        // order to that one
                        orders = new ArrayList<>();
                        orders.add(new Orders(order, 1));
                    }

                    Toast errorToast = Toast.makeText(CreateOrder.this,
                            "There was a problem creating the order", Toast.LENGTH_SHORT);

                    //If orders is saved, and the temp order is deleted. Show a Toast
                    // that the order was created.
                    if (ResourceManager.saveOrders(CreateOrder.this, orders)) {
                        if (deleteTempOrder()) {
                            Toast.makeText(CreateOrder.this, "Order was created",
                                    Toast.LENGTH_SHORT).show();
                            Intent createOrders = new Intent(v.getContext(), MainActivity.class);
                            v.getContext().startActivity(createOrders);
                        } else {
                            errorToast.show();
                        }
                    } else {
                        errorToast.show();
                    }
                }
            }
        });
    }

    /**
     * Deletes the temp order file
     *
     * @return true if file is deleted. False if file is not found, or if it was any problems
     * deleting the file.
     */
    private Boolean deleteTempOrder() {
        Boolean isDeleted = false;
        File file = new File(getFilesDir(), Order.tempOrderFileName);
        if (file.exists()) {
            if (file.delete()) {
                isDeleted = true;
            }
        }
        return isDeleted;
    }
}
