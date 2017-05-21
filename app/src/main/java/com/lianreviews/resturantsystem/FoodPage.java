package com.lianreviews.resturantsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.lianreviews.resturantsystem.FoodCategoryAdapter.CATEGORY_NAME;


public class FoodPage extends AppCompatActivity {


    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);

        Intent intent = getIntent();
        final String clickedName = intent.getStringExtra("FOOD_NAME");
        final String clickedCategory = intent.getStringExtra("FOOD_CATEGORY");

        TextView foodNameTextView = (TextView) findViewById(R.id.food_name_food_page_text_view);
        foodNameTextView.setText(clickedName);

        Button orderButton = (Button) findViewById(R.id.order_button_food_page);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createFoodIntent = new Intent(v.getContext(), CreateFood.class);
                // Show a toast with information of what is ordered
                Toast.makeText(FoodPage.this, quantity + " of " + clickedName + " ordered.",
                        Toast.LENGTH_SHORT).show();
                createFoodIntent.putExtra(CATEGORY_NAME, clickedCategory);
                v.getContext().startActivity(createFoodIntent);
            }
        });
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment (View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else {

            //Show a toast when the user tries to go over 100 cups of coffee
            Toast.makeText(this,
                    "You can't have more than 100",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement (View view) {
        if (quantity == 1) {
            //Show a toast when the user tries to go below 1 cups of coffee
            Toast.makeText(this,
                    "You can't have less than 1",
                    Toast.LENGTH_SHORT).show();

            //Return will make the whole decrement method stop
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }
}
