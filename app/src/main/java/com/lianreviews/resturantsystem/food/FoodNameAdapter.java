package com.lianreviews.resturantsystem.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lianreviews.resturantsystem.R;

import java.util.ArrayList;

import static com.lianreviews.resturantsystem.category.FoodCategory.CATEGORY_NAME;
import static com.lianreviews.resturantsystem.food.FoodName.FOOD_NAME;


public class FoodNameAdapter extends ArrayAdapter<FoodName> {

    private Boolean mEditMode;

    /**
     * This is our own custom constructor (it does not mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want to
     * populate into the lists
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param arrayList A list of Word objects to display in a list.
     * @param editMode Gives information of editMode is enabled or not by the user
     * @param editMode Shows what category that belongs to this food
     */
    public FoodNameAdapter(Activity context, ArrayList<FoodName> arrayList, Boolean editMode) {
        //Here, we initialize the ArrayAdapter's internal storage for teh context and the list.
        //The second argument is used when the ArrayAdapter is populating a single TextView.
        //Because this is a custom adapter for two TextViews, the adapter is not going to use this
        //second argument, so it can be any value. Here, we used 0.
        super(context, 0, arrayList);

        // Assign editMode
        mEditMode = editMode;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that
     *                    should be displayed in list item view
     * @param convertView The recycled view to populate
     * @param parent      The parent ViewGroup that is used for inflation
     * @return The View for the position in the AdapterView
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if the existing view is being reused. If not, inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.create_food_list_item, parent, false);
        }
        // Get the FoodName object located at this position in the list
        final FoodName currentFoodName = getItem(position);

        TextView categoryNameTextView = (TextView) listItemView.findViewById(R.id.food_name);
        categoryNameTextView.setText(currentFoodName.getName());

        LinearLayout linearLayoutCreateFood =
                listItemView.findViewById(R.id.linear_layout_create_food);
        linearLayoutCreateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditMode) {
                    //Create an Intent that opens the AddFoodName class and pass the category clicked
                    Intent addFoodNameIntent = new Intent(v.getContext(), AddFoodName.class);
                    addFoodNameIntent.putExtra(CATEGORY_NAME, currentFoodName.getCategory());
                    addFoodNameIntent.putExtra(FOOD_NAME, currentFoodName.getName());
                    v.getContext().startActivity(addFoodNameIntent);
                    Toast.makeText(getContext(), "Edit mode is enabled",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //Create an Intent that opens the FoodPage class
                    Intent createFoodIntent = new Intent(v.getContext(), FoodPage.class);
                    // Create a bundle to store multiple strings
                    Bundle extras = new Bundle();
                    // Add the name to the bundle
                    extras.putString(FOOD_NAME, currentFoodName.getName());
                    // Add the category to the bundle
                    extras.putString(CATEGORY_NAME, currentFoodName.getCategory());
                    // Add the bundle to the intent
                    createFoodIntent.putExtras(extras);
                    v.getContext().startActivity(createFoodIntent);
                }
            }
        });

        return listItemView;
    }
}
