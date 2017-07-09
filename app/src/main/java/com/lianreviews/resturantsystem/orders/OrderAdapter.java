package com.lianreviews.resturantsystem.orders;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lianreviews.resturantsystem.R;

import java.util.ArrayList;


public class OrderAdapter extends ArrayAdapter<Order> {

    /**
     * This is our own custom constructor (it does not mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want to
     * populate into the lists
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param arrayList A list of Word objects to display in a list.
     */
    public OrderAdapter(Activity context, ArrayList<Order> arrayList) {
        //Here, we initialize the ArrayAdapter's internal storage for teh context and the list.
        //The second argument is used when the ArrayAdapter is populating a single TextView.
        //Because this is a custom adapter for two TextViews, the adapter is not going to use this
        //second argument, so it can be any value. Here, we used 0.
        super(context, 0, arrayList);
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
                    R.layout.create_order_list_item, parent, false);
        }
        // Get the FoodName object located at this position in the list
        final Order currentOrder = getItem(position);

        TextView numbersOrderedTextView = (TextView) listItemView.findViewById(R.id.quantity_ordered);
        int numbersOrdered = currentOrder.getNumberOfProducts();
        numbersOrderedTextView.setText(String.valueOf(numbersOrdered));

        TextView foodNameTextView = (TextView) listItemView.findViewById(R.id.food_name);
        foodNameTextView.setText(currentOrder.getProductName());

        TextView foodPriceTextView = (TextView) listItemView.findViewById(R.id.food_price);
        if (numbersOrdered == 1) {
            String text = "$" + String.valueOf(currentOrder.getPriceOfProduct());
            foodPriceTextView.setText(text);
        } else {
            int newPrice = (numbersOrdered * currentOrder.getPriceOfProduct());
            String text = "$" + String.valueOf(newPrice);
            foodPriceTextView.setText(text);
        }

        return listItemView;
    }
}
