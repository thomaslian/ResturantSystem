package com.lianreviews.resturantsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

public class CreateCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        //Create an ArrayList of FoodCategory objects
        ArrayList<FoodCategory> foodCategories = new ArrayList<>();
        foodCategories.add(new FoodCategory("Drinks"));
        foodCategories.add(new FoodCategory("Food"));
        foodCategories.add(new FoodCategory("Dessert"));

        //Create an FoodCategoryAdapter, whose data source is a list of FoodCategory.
        //The adapter knows how to create list items for each item in the list.
        FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter(this, foodCategories);

        //Get the GridView and set the foodCategoryAdapter for the gridView
        GridView gridView = (GridView) findViewById(R.id.create_order_activity);
        gridView.setAdapter(foodCategoryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_cart:
                // User chose the "View cart", show what is ordered so far
                Intent intent = new Intent(this, CreateOrder.class);
                this.startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
