package com.lianreviews.resturantsystem.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lianreviews.resturantsystem.ResourceManager;
import com.lianreviews.resturantsystem.orders.Order;
import com.lianreviews.resturantsystem.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.lianreviews.resturantsystem.category.FoodCategory.CATEGORY_NAME;
import static com.lianreviews.resturantsystem.food.FoodName.FOOD_NAME;


public class FoodPage extends AppCompatActivity {


    int quantity = 1;
    int price = 0;
    ArrayList<FoodName> foodNames;

    private String filename = "tempOrder.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);

        // Get the passed Intent and store the values in Strings
        Intent intent = getIntent();
        final String clickedName = intent.getStringExtra(FOOD_NAME);
        final String clickedCategory = intent.getStringExtra(CATEGORY_NAME);

        TextView foodNameTextView = findViewById(R.id.food_name_food_page_text_view);
        foodNameTextView.setText(clickedName);

        foodNames = ResourceManager.loadFoodNames(this);

        if (foodNames != null) {
            for (int i = 0; i < foodNames.size(); i++) {
                FoodName food = foodNames.get(i);
                if (food.getName().equals(clickedName)) {
                    price = food.getPrice();
                }
            }
        }

        TextView priceTextView = findViewById(R.id.price_food_page);
        priceTextView.setText(String.valueOf(price));

        Button orderButton = findViewById(R.id.order_button_food_page);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrder(clickedName, true, quantity, price);
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
     * This method saves the order to be viewed in the cart
     */
    public void saveOrder(String clickedName, Boolean clicked, int quantity, int price) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(getFilesDir(), filename);

        ArrayList<Order> orders = new ArrayList<>();


        // Check if one of the ordered names already contains the one that is
        // being ordered. Add the number of ordered to the quantity and remove the
        // old one. So we would not have multiple of the same ones in the cart.
        if (file.exists()) {
            orders = ResourceManager.loadTempOrder(FoodPage.this);
            Order order;
            for (int i = 0; i < orders.size(); i++) {
                order = orders.get(i);
                if (order.getProductName().equals(clickedName)) {
                    quantity = order.getNumberOfProducts() + quantity;
                    orders.remove(i);
                }
            }
        }

        // Add the new order to the orders list
        orders.add(new Order(clickedName, clicked, quantity, price, 0));

        // Save the orders list with the new order
        try {
            //Write Order array to file.
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orders);
            Log.d("Saved orders123", orders.toString());
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else {

            //Show a toast when the user tries to go over 100
            Toast.makeText(this,
                    "You can't have more than 100",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //Show a toast when the user tries to go below 1
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
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }
}
