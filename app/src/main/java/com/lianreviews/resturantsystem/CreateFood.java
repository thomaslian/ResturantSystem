package com.lianreviews.resturantsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class CreateFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);

        ArrayList<FoodName> foodNames = new ArrayList<>();
        foodNames.add(new FoodName("Drinks", "Cola", 1));
        foodNames.add(new FoodName("Starter", "Chips", 2));

        //Create an WordAdapter, whose data source is a list of Word.
        //The adapter knows how to create list items for each item in the list.
        FoodNameAdapter foodNameAdapter = new FoodNameAdapter(this, foodNames);

        //Get the listView and set the adapter for the listView
        GridView gridView = (GridView) findViewById(R.id.create_order_activity);
        gridView.setAdapter(foodNameAdapter);
    }
}
