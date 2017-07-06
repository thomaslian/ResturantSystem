package com.lianreviews.resturantsystem.Orders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.lianreviews.resturantsystem.R;

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

        OrderAdapter orderAdapter;

        if (LoadOrder() != null) {
            //Create an OrderAdapter, whose data source is a list of Order.
            //The adapter knows how to create list items for each item in the list.
            orderAdapter = new OrderAdapter(this, LoadOrder());
        } else {
            ArrayList<Order> orders = new ArrayList<>();
            orders.add(new Order("Nothing ordered", true, 1, 2));

            orderAdapter = new OrderAdapter(this, orders);
        }

        //Get the ListView and set the adapter for the listView
        ListView listView = (ListView) findViewById(R.id.create_order_activity);
        listView.setAdapter(orderAdapter);
    }

    public ArrayList<Order> LoadOrder(){
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(getFilesDir(), "tempOrder.ser");

        ArrayList<Order> ordersFromSavedFile = null;

        // Check if file exist and if it exist load any other saved orders list
        if (file.exists()) {
            try {
                //Read Order array from file.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ordersFromSavedFile = (ArrayList<Order>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return ordersFromSavedFile;
    }
}
