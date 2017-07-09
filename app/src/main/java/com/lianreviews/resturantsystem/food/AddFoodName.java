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

import com.lianreviews.resturantsystem.category.FoodCategory;
import com.lianreviews.resturantsystem.category.FoodCategoryAdapter;
import com.lianreviews.resturantsystem.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AddFoodName extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_name);

        Intent intent = getIntent();
        final String clickedCategory = intent.getStringExtra(FoodCategoryAdapter.CATEGORY_NAME);

        TextView categoryTextView = (TextView) findViewById(R.id.category_name_add_food);
        categoryTextView.setText(clickedCategory);

        final EditText editText = (EditText) findViewById(R.id.food_name_user_input);
        Button button = (Button) findViewById(R.id.add_food_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("") && !editText.getText().toString().equals(" ")) {
                    String userInputFoodName = editText.getText().toString();
                    Log.d("Added ", userInputFoodName);
                    saveFood(clickedCategory, userInputFoodName);
                    Toast.makeText(AddFoodName.this, userInputFoodName + " added!",
                            Toast.LENGTH_SHORT).show();
                    Intent addFoodIntent = new Intent(v.getContext(), CreateFood.class);
                    v.getContext().startActivity(addFoodIntent);
                } else {
                    Toast.makeText(AddFoodName.this, "You need to add a food name to continue",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveFood(String category, String foodName) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(getFilesDir(), "currentFoodNames.ser");

        ArrayList<FoodName> categories = new ArrayList<>();

        // Check if file exist and if it exist load any other saved orders list
        if (file.exists()) {
            try {
                //Read FoodCategory array from file.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                categories = (ArrayList<FoodName>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Add the new category to the orders list
        categories.add(new FoodName(category, foodName));

        // Save the categories list with the new category
        try {
            //Write FoodCategory array to file.
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(categories);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
