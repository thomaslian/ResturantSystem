package com.lianreviews.resturantsystem.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lianreviews.resturantsystem.R;
import com.lianreviews.resturantsystem.ResourceManager;
import com.lianreviews.resturantsystem.category.CreateCategory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class CreateOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Button deleteOrderButton = (Button) findViewById(R.id.cancel_order_button_in_cart);

        deleteOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteOrder()){
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

        if (ResourceManager.loadOrders(this) != null) {
            //Create an OrderAdapter, whose data source is a list of Order.
            //The adapter knows how to create list items for each item in the list.
            orderAdapter = new OrderAdapter(this, ResourceManager.loadOrders(this));
        } else {
            ArrayList<Order> orders = new ArrayList<>();
            orders.add(new Order("Nothing ordered", true, 1, 2));
            orderAdapter = new OrderAdapter(this, orders);
        }

        //Get the ListView and set the adapter for the listView
        ListView listView = (ListView) findViewById(R.id.create_order_activity);
        listView.setAdapter(orderAdapter);
    }

    /**
     * Deletes the temp order file
     * @return true if file is deleted. False if file is not found, or if it was any problems
     * deleting the file.
     */
    private Boolean deleteOrder(){
        Boolean isDeleted = false;
        File file = new File(getFilesDir(), Order.tempOrderFileName);
        if (file.exists()){
            if (file.delete()){
                isDeleted = true;
            }
        }
        return isDeleted;
    }
}
