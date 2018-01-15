package com.lianreviews.resturantsystem.category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lianreviews.resturantsystem.R;
import com.lianreviews.resturantsystem.ResourceManager;
import com.lianreviews.resturantsystem.food.AddFoodName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.lianreviews.resturantsystem.category.FoodCategory.CATEGORY_NAME;

public class AddCategory extends AppCompatActivity  {

    private String clickedCategory = "";
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Intent intent = getIntent();
        clickedCategory = intent.getStringExtra(CATEGORY_NAME);

        editText = findViewById(R.id.category_name_user_input);

        //Check if there is a clicked name or not. If it is an clicked name, the user
        // want to edit the name of an Category that exist. If not, the user want to add a new food
        if (!clickedCategory.equals("")) {
            editText.setText(clickedCategory);
        }

        Button removeButton = findViewById(R.id.remove_category_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCategoryIntent = new Intent(v.getContext(), CreateCategory.class);
                if(clickedCategory.equals("")){
                    v.getContext().startActivity(addCategoryIntent);
                    } else {
                    if (ResourceManager.removeCategory(AddCategory.this, clickedCategory)) {
                        Toast.makeText(AddCategory.this, clickedCategory + " deleted!",
                                Toast.LENGTH_SHORT).show();
                        v.getContext().startActivity(addCategoryIntent);
                    }
                }
            }
        });


        Button button = findViewById(R.id.add_category_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("") && !editText.getText().toString().equals(" ")) {
                    String userInputCategory = editText.getText().toString();
                    Log.d("Added ", userInputCategory);
                    ResourceManager.saveCategory(AddCategory.this, userInputCategory);
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
}
