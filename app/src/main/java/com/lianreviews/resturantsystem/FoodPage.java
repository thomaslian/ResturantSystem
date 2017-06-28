package com.lianreviews.resturantsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.lianreviews.resturantsystem.FoodCategoryAdapter.CATEGORY_NAME;


public class FoodPage extends AppCompatActivity {


    int quantity = 1;

    private String filename = "tempOrder.ser";

    private ArrayList<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);

        // Get the passed Intent and store the values in Strings
        Intent intent = getIntent();
        final String clickedName = intent.getStringExtra("FOOD_NAME");
        final String clickedCategory = intent.getStringExtra("FOOD_CATEGORY");

        TextView foodNameTextView = (TextView) findViewById(R.id.food_name_food_page_text_view);
        foodNameTextView.setText(clickedName);

        Button orderButton = (Button) findViewById(R.id.order_button_food_page);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new file because the FileOutPutSteam takes it as an input
                //Without this we would not get access to the file directory of the app
                File file = new File(getFilesDir(), filename);
                LoadOrder(file);
                orders.add(new Order(clickedName, true, quantity, 10));
                SaveOrder(file);
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
    public void SaveOrder(File file) {
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


    public void LoadOrder(File file){
        if (file.exists()) {
            try {
                //Read Student array from file.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<Order> ordersFromSavedFile = (ArrayList<Order>) ois.readObject();
                orders = ordersFromSavedFile;
                Order order;
                int length = ordersFromSavedFile.size();
                Log.d("Length from saved file", Integer.toString(length));
                for (int i = 0; i < ordersFromSavedFile.size(); i++) {
                    order = ordersFromSavedFile.get(i);
                    Log.d("Loaded food name: ", order.getProductName());
                }

                //Student[] studentsFromSavedFile = (Student[]) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }
}
