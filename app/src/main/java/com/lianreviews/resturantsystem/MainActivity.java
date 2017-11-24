package com.lianreviews.resturantsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.lianreviews.resturantsystem.category.CreateCategory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Orders");

        //Create a FloatingActionButton variable and assign it to our floating action button
        FloatingActionButton floatingActionButton = findViewById(R.id.main_page_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new intent to open the Create Category class
                Intent createOrderIntent =
                        new Intent(MainActivity.this, CreateCategory.class);

                //Start the new activity
                startActivity(createOrderIntent);
            }
        });
    }
}
