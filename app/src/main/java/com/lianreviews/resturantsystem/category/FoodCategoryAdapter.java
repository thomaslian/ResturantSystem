package com.lianreviews.resturantsystem.category;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lianreviews.resturantsystem.food.CreateFood;
import com.lianreviews.resturantsystem.R;

import java.util.ArrayList;

import static com.lianreviews.resturantsystem.category.FoodCategory.CATEGORY_NAME;


public class FoodCategoryAdapter extends ArrayAdapter<FoodCategory> {

    private Boolean mEditMode;

    /**
     * This is our own custom constructor (it does not mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want to
     * populate into the lists
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param arrayList A list of Word objects to display in a list.
     * @param editMode Gives information of editMode is enabled or not by the user
     */

    public FoodCategoryAdapter(Activity context, ArrayList<FoodCategory> arrayList, Boolean editMode) {
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
                    R.layout.create_category_list_item, parent, false);
        }

        // Get the FoodCategory object located at this position in the list
        final FoodCategory currentFoodCategory = getItem(position);

        // Get the TextView and display the category name
        TextView categoryNameTextView = (TextView) listItemView.findViewById(R.id.category_name);
        categoryNameTextView.setText(currentFoodCategory.getCategoryName());
        categoryNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditMode) {
                    Intent addCategoryIntent = new Intent(v.getContext(), AddCategory.class);
                    addCategoryIntent.putExtra(CATEGORY_NAME, currentFoodCategory.getCategoryName());
                    v.getContext().startActivity(addCategoryIntent);
                    Toast.makeText(getContext(), "Edit mode is enabled",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //Create an Intent that opens the CreateFood class and pass the category clicked
                    Intent createFoodIntent = new Intent(v.getContext(), CreateFood.class);
                    //Get the button that is clicked
                    TextView b = (TextView) v;
                    //Get the name of the button
                    String buttonText = b.getText().toString();
                    createFoodIntent.putExtra(CATEGORY_NAME, buttonText);
                    v.getContext().startActivity(createFoodIntent);
                }
            }
        });

        return listItemView;
    }
}
