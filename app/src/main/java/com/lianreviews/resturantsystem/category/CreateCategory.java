package com.lianreviews.resturantsystem.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.lianreviews.resturantsystem.orders.CreateOrder;
import com.lianreviews.resturantsystem.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class CreateCategory extends AppCompatActivity {

    private Boolean editMode = false;
    private ArrayList<FoodCategory> foodCategories;
    private Button editModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        //Create an ArrayList of FoodCategory objects
        foodCategories = new ArrayList<>();
        foodCategories.add(new FoodCategory("Drinks"));
        foodCategories.add(new FoodCategory("Food"));
        foodCategories.add(new FoodCategory("Dessert"));

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                findViewById(R.id.category_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an Intent that opens the AddCategory class and pass
                Intent addCategoryIntent = new Intent(v.getContext(), AddCategory.class);
                v.getContext().startActivity(addCategoryIntent);
            }
        });

        setAdapter();

        editModeButton = (Button) findViewById(R.id.edit_mode_button_category);
        editModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editMode) {
                    editMode = true;
                    editModeButton.setBackgroundColor(ContextCompat.getColor(CreateCategory.this,
                            R.color.orderColor));
                    editModeButton.setText(getResources().getString(R.string.edit_mode_on));
                    setAdapter();
                } else {
                    editMode = false;
                    editModeButton.setBackgroundColor(ContextCompat.getColor(CreateCategory.this,
                            R.color.cancelColor));
                    editModeButton.setText(getResources().getString(R.string.edit_mode_off));
                    setAdapter();
                }
            }
        });
    }

        private void setAdapter(){
        FoodCategoryAdapter foodCategoryAdapter;

        // If there is no saved category from the user, load the default categories
        if (loadCategoryNames() != null) {
            //Create an FoodCategoryAdapter, whose data source is a list of FoodCategory.
            //The adapter knows how to create list items for each item in the list.
            foodCategoryAdapter = new FoodCategoryAdapter(this, loadCategoryNames(), editMode);
        } else {
            foodCategoryAdapter = new FoodCategoryAdapter(this, foodCategories, editMode);
        }

        //Get the GridView and set the foodCategoryAdapter for the gridView
        GridView gridView = (GridView) findViewById(R.id.create_order_activity);
        gridView.setAdapter(foodCategoryAdapter);
    }

    private ArrayList<FoodCategory> loadCategoryNames(){
        File file = new File(getFilesDir(), FoodCategory.categoryFile);

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
