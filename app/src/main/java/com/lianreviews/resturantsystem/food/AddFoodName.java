package com.lianreviews.resturantsystem.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lianreviews.resturantsystem.R;
import com.lianreviews.resturantsystem.ResourceManager;
import com.lianreviews.resturantsystem.category.CreateCategory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.lianreviews.resturantsystem.category.FoodCategory.CATEGORY_NAME;
import static com.lianreviews.resturantsystem.food.FoodName.FOOD_NAME;

public class AddFoodName extends AppCompatActivity {

    ArrayList<FoodName> foodNames;
    private EditText editText;
    private EditText priceEditText;
    private String clickedCategory = "";
    private String clickedName = "";
    private int productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_name);

        foodNames = new ArrayList<>();

        Intent intent = getIntent();
        clickedCategory = intent.getStringExtra(CATEGORY_NAME);
        clickedName = intent.getStringExtra(FOOD_NAME);

        editText = findViewById(R.id.food_name_user_input);
        priceEditText = findViewById(R.id.price_user_input);

        //Check if there is a clicked name or not. If it is an clicked name, the user
        // want to edit the name of an Food that exist. If not, the user want to add a new food
        if (!clickedName.equals("")) {
            editText.setText(clickedName);
            //Find the price of the product and set it to the priceEdiText
            ArrayList<FoodName> foodNames = ResourceManager.loadFoodNames(AddFoodName.this);
            for (int i = 0; i < foodNames.size(); i++){
                FoodName foodName = foodNames.get(i);
                if (foodName.getCategory().equals(clickedCategory) &&
                        foodName.getName().equals(clickedName)){
                    priceEditText.setText(String.valueOf(foodName.getPrice()));
                }
            }
        }

        //Show the category the Food is displayed in
        TextView categoryTextView = findViewById(R.id.category_name_add_food);
        categoryTextView.setText(clickedCategory);

        //Load FoodNames from the ResourceMananger
        if (ResourceManager.loadFoodNames(this) == null) {
            foodNames = null;
        } else {
            foodNames = ResourceManager.loadFoodNames(this);
        }

        //Create a button that removes the FoodName or exits the editor if nothing is saved
        Button removeButton = findViewById(R.id.remove_food_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent removeFoodIntent = new Intent(v.getContext(), CreateCategory.class);
                if(clickedName.equals("")){
                    v.getContext().startActivity(removeFoodIntent);
                } else {
                    if(ResourceManager.removeFood(AddFoodName.this,
                            new FoodName(clickedCategory, clickedName, productPrice))){
                            Toast.makeText(AddFoodName.this, clickedName + " deleted!",
                                    Toast.LENGTH_SHORT).show();
                            v.getContext().startActivity(removeFoodIntent);
                    }
                }
            }
        });

        //Create a button that saves the new or edited FoodName
        Button button = findViewById(R.id.add_food_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")
                        && !editText.getText().toString().equals(" ")
                        && !priceEditText.getText().toString().equals("")
                        && !priceEditText.getText().toString().equals(" ")
                        ) {
                    String userInputFoodName = editText.getText().toString();
                    productPrice = Integer.parseInt(priceEditText.getText().toString());
                    ResourceManager.saveFood(AddFoodName.this,
                            new FoodName(clickedCategory,  editText.getText().toString(), productPrice));
                    Toast.makeText(AddFoodName.this, userInputFoodName + " added!",
                            Toast.LENGTH_SHORT).show();
                    if (clickedName != null){
                        ResourceManager.removeFood(AddFoodName.this,
                                new FoodName(clickedCategory, clickedName, 0));
                    }

                    Intent addFoodIntent = new Intent(v.getContext(), CreateCategory.class);
                    v.getContext().startActivity(addFoodIntent);
                } else {
                    Toast.makeText(AddFoodName.this, "You need to add a food name to continue",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
