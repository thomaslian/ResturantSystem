package com.lianreviews.resturantsystem.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.lianreviews.resturantsystem.category.CreateCategory;
import com.lianreviews.resturantsystem.category.FoodCategory;
import com.lianreviews.resturantsystem.category.FoodCategoryAdapter;
import com.lianreviews.resturantsystem.orders.CreateOrder;
import com.lianreviews.resturantsystem.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class CreateFood extends AppCompatActivity {

    private Boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);

        // Create an intent and get the clicked food category
        Intent intent = getIntent();
        final String clickedCategory = intent.getStringExtra(FoodCategoryAdapter.CATEGORY_NAME);

        // Create a list of all categories
        List<String> foodCategory = new ArrayList<>();
        foodCategory.add("Drinks");
        foodCategory.add("Drinks");
        foodCategory.add("Dessert");
        foodCategory.add("Food");

        // Create a list of all the food
        List<String> food = new ArrayList<>();
        food.add("Cola");
        food.add("Fanta");
        food.add("Banana milkshake");
        food.add("Hamburger");

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                findViewById(R.id.food_name_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an Intent that opens the AddFoodName class and pass the category clicked
                Intent addFoodNameIntent = new Intent(v.getContext(), AddFoodName.class);
                addFoodNameIntent.putExtra(FoodCategoryAdapter.CATEGORY_NAME, clickedCategory);
                v.getContext().startActivity(addFoodNameIntent);
            }
        });

        final Button editModeButton = (Button) findViewById(R.id.edit_mode_button_food);
        editModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editMode){
                    editMode = true;
                    editModeButton.setBackgroundColor(ContextCompat.getColor(CreateFood.this,
                            R.color.orderColor));
                    editModeButton.setText(getResources().getString(R.string.edit_mode_on));
                } else {
                    editMode = false;
                    editModeButton.setBackgroundColor(ContextCompat.getColor(CreateFood.this,
                            R.color.cancelColor));
                    editModeButton.setText(getResources().getString(R.string.edit_mode_off));
                }
            }
        });

        ArrayList<FoodName> foodNames = new ArrayList<>();

        // Go through each word and add every food and category
        // with the same "i" to the foodNames ArrayList
        for (int i = 0; i < foodCategory.size(); i++) {
            if (foodCategory.get(i).equals(clickedCategory)) {
                foodNames.add(new FoodName(foodCategory.get(i), food.get(i)));
            }
        }

        FoodNameAdapter foodNameAdapter = null;
        ArrayList<FoodName> sortedNames = new ArrayList<>();

        if (loadFoodNames() == null) {
            //Create an FoodNameAdapter, whose data source is a list of FoodName.
            //The adapter knows how to create list items for each item in the list.
            foodNameAdapter = new FoodNameAdapter(this, foodNames);
        } else {
            ArrayList<FoodName> loadedNames = loadFoodNames();
                for (int i = 0; i < loadedNames.size(); i++) {
                    FoodName foodname = loadedNames.get(i);
                    if (foodname.getCategory().equals(clickedCategory)) {
                        sortedNames.add(foodname);
                    }
                foodNameAdapter = new FoodNameAdapter(this, sortedNames);
            }
        }

        //Get the ListView and set the adapter for the listView
        ListView listView = (ListView) findViewById(R.id.create_food_activity);
        listView.setAdapter(foodNameAdapter);
    }

    private ArrayList<FoodName> loadFoodNames() {
        File file = new File(getFilesDir(), "currentFoodNames.ser");

        ArrayList<FoodName> foodNames = null;

        // Check if file exist and if it exist load any other saved orders list
        if (file.exists()) {
            try {
                //Read Order array from file.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                foodNames = (ArrayList<FoodName>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return foodNames;
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
