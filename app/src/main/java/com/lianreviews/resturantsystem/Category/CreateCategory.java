package com.lianreviews.resturantsystem.Category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.lianreviews.resturantsystem.Orders.CreateOrder;
import com.lianreviews.resturantsystem.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                findViewById(R.id.category_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an Intent that opens the AddCategory class and pass the category clicked
                Intent addCategoryIntent = new Intent(v.getContext(), AddCategory.class);
                v.getContext().startActivity(addCategoryIntent);
            }
        });

        FoodCategoryAdapter foodCategoryAdapter;

        // If there is no saved category from the user, load the defualt categories
        if (loadCategoryNames() != null) {
            //Create an FoodCategoryAdapter, whose data source is a list of FoodCategory.
            //The adapter knows how to create list items for each item in the list.
            foodCategoryAdapter = new FoodCategoryAdapter(this, loadCategoryNames());
        } else {
            foodCategoryAdapter = new FoodCategoryAdapter(this, foodCategories);
        }

        //Get the GridView and set the foodCategoryAdapter for the gridView
        GridView gridView = (GridView) findViewById(R.id.create_order_activity);
        gridView.setAdapter(foodCategoryAdapter);
    }

    private ArrayList<FoodCategory> loadCategoryNames(){
        File file = new File(getFilesDir(), "currentCategories.ser");

        ArrayList<FoodCategory> categories = null;

        // Check if file exist and if it exist load any other saved orders list
        if (file.exists()) {
            try {
                //Read Order array from file.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                categories = (ArrayList<FoodCategory>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return categories;
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
