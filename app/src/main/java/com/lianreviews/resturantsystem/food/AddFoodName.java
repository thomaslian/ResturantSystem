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
    private String clickedCategory = "";
    private String clickedName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_name);

        foodNames = new ArrayList<>();

        Intent intent = getIntent();
        clickedCategory = intent.getStringExtra(CATEGORY_NAME);
        clickedName = intent.getStringExtra(FOOD_NAME);

        editText = (EditText) findViewById(R.id.food_name_user_input);

        //Check if there is a clicked name or not. If it is an clicked name, the user
        // want to edit the name of an Food that exist. If not, the user want to add a new food
        if (!clickedName.equals("")) {
            editText.setText(clickedName);
        }

        //Show the category the Food is displayed in
        TextView categoryTextView = (TextView) findViewById(R.id.category_name_add_food);
        categoryTextView.setText(clickedCategory);

        //Load FoodNames from the ResourceMananger
        if (ResourceManager.loadFoodNames(this) == null) {
            foodNames = null;
        } else {
            foodNames = ResourceManager.loadFoodNames(this);
        }


        //Create a button that saves the new or edited FoodName
        Button button = (Button) findViewById(R.id.add_food_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("") && !editText.getText().toString().equals(" ")) {
                    String userInputFoodName = editText.getText().toString();

                    if (foodNames != null) {
                        // Go through each word and add every food and category
                        // with the same "i" to the foodNames ArrayList
                        for (int i = 0; i < foodNames.size(); i++) {
                            FoodName foodname = foodNames.get(i);
                            if (userInputFoodName.equals(foodname.getName())) {
                                foodNames.remove(i);
                            }
                            if (!clickedName.equals("")) {
                                if (clickedName.equals(foodname.getName())) {
                                    foodNames.remove(i);
                                }
                            }
                        }
                    } else {
                        foodNames = new ArrayList<>();
                    }
                    saveFood(clickedCategory, userInputFoodName, foodNames);
                    Toast.makeText(AddFoodName.this, userInputFoodName + " added!",
                            Toast.LENGTH_SHORT).show();
                    Intent addFoodIntent = new Intent(v.getContext(), CreateCategory.class);
                    v.getContext().startActivity(addFoodIntent);
                } else {
                    Toast.makeText(AddFoodName.this, "You need to add a food name to continue",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveFood(String category, String foodName, ArrayList<FoodName> foodNames) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(getFilesDir(), "currentFoodNames.ser");

        if (file.exists()) {
            foodNames = ResourceManager.loadFoodNames(AddFoodName.this);
        }

        // Add the new category to the orders list
        foodNames.add(new FoodName(category, foodName));

        // Save the categories list with the new category
        try {
            //Write FoodCategory array to file.
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(foodNames);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
