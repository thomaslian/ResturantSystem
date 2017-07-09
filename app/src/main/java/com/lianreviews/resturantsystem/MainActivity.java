package com.lianreviews.resturantsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lianreviews.resturantsystem.category.CreateCategory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView createNewOrder = (TextView) findViewById(R.id.create_new_order);
        createNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new intent to open the {@link CreateCategory}
                Intent createOrderIntent = new Intent(MainActivity.this, CreateCategory.class);

                //Start the new activity
                startActivity(createOrderIntent);
            }
        });
    }
}
