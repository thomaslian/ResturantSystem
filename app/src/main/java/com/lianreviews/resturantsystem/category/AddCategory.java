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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AddCategory extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        final EditText editText = (EditText) findViewById(R.id.category_name_user_input);
        Button button = (Button) findViewById(R.id.add_category_button);

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
