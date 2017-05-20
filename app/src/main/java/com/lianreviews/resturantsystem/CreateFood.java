package com.lianreviews.resturantsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CreateFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);


        // Create an intent and get the clicked food category
        Intent intent = getIntent();
        String clickedCategory = intent.getStringExtra(FoodCategoryAdapter.CATEGORY_NAME);

        List<String> foodCategory = new ArrayList<>();
        foodCategory.add("Drinks");
        foodCategory.add("Drinks");
        foodCategory.add("Dessert");
        foodCategory.add("Food");

        List<String> food = new ArrayList<>();
        food.add("Cola");
        food.add("Fanta");
        food.add("Banana milkshake");
        food.add("Hamburger");

        ArrayList<FoodName> foodNames = new ArrayList<>();

        // Go through each word and add every food and category
        // with the same "i" to the foodNames ArrayList
        for(int i = 0; i < foodCategory.size(); i++ ){
            if (foodCategory.get(i).equals(clickedCategory)) {
                foodNames.add(new FoodName(foodCategory.get(i), food.get(i)));
            }
        }


        //Create an WordAdapter, whose data source is a list of Word.
        //The adapter knows how to create list items for each item in the list.
        FoodNameAdapter foodNameAdapter = new FoodNameAdapter(this, foodNames);

        //Get the listView and set the adapter for the listView
        ListView gridView = (ListView) findViewById(R.id.create_food_activity);
        //GridView gridView = (GridView) findViewById(R.id.create_food_activity);
        gridView.setAdapter(foodNameAdapter);
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
                Intent intent = new Intent(this, ViewOrder.class);
                this.startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
