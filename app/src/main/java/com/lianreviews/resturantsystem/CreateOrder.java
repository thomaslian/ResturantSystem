package com.lianreviews.resturantsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class CreateOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        //Create an ArrayList of Word objects
        ArrayList<FoodCategory> foodCategories = new ArrayList<>();
        foodCategories.add(new FoodCategory("Drinks"));
        foodCategories.add(new FoodCategory("Food"));
        foodCategories.add(new FoodCategory("Dessert"));

        //Create an WordAdapter, whose data source is a list of Word.
        //The adapter knows how to create list items for each item in the list.
        FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter(this, foodCategories);

        //Get the listView and set the adapter for the listView
        GridView gridView = (GridView) findViewById(R.id.create_order_activity);
        gridView.setAdapter(foodCategoryAdapter);
    }
}
