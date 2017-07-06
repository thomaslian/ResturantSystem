package com.lianreviews.resturantsystem.Category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lianreviews.resturantsystem.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AddCategory extends AppCompatActivity implements Serializable  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        final EditText editText = (EditText) findViewById(R.id.category_name_user_input);
        Button button = (Button) findViewById(R.id.add_category_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText() != null) {
                    String userInputCategory = editText.getText().toString();
                    Log.d("Added ", userInputCategory);
                    saveCategory(userInputCategory);
                    Toast.makeText(AddCategory.this, userInputCategory + " added!",
                            Toast.LENGTH_SHORT).show();
                    Intent addCategoryIntent = new Intent(v.getContext(), CreateCategory.class);
                    v.getContext().startActivity(addCategoryIntent);
                } else {
                    Toast.makeText(AddCategory.this, "You need to add a category to continue",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveCategory(String categoryName) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(getFilesDir(), "currentCategories.ser");

        ArrayList<FoodCategory> categories = new ArrayList<>();

        // Check if file exist and if it exist load any other saved orders list
        if (file.exists()) {
            try {
                //Read FoodCategory array from file.
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

        // Add the new category to the orders list
        categories.add(new FoodCategory(categoryName));

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
