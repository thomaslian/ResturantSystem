package com.lianreviews.resturantsystem.orders;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lianreviews.resturantsystem.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Thoma on 24-Nov-17.
 */

public class OrdersAdapter extends ArrayAdapter<Orders> {

    /**
     * This is our own custom constructor (it does not mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want to
     * populate into the lists
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param arrayList A list of Word objects to display in a list.
     */
    public OrdersAdapter(Activity context, ArrayList<Orders> arrayList) {
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
                    R.layout.create_orders_list_item, parent, false);
        }

        // Get the order object located at this position in the list
        final Orders currentOrder = getItem(position);

        TextView orderNumberTextView = listItemView.findViewById(R.id.order_number_orders_page);
        String orderNumberText = "Order " + String.valueOf(currentOrder.getOrderNumber());
        orderNumberTextView.setText(String.valueOf(orderNumberText));

        // Get the order and create a product name variable to store product names
        ArrayList<Order> orders = currentOrder.getOrder();
        String productName = "";
        String amountOrdered = "";

        // Use a loop to add all of the food names to one long sentence
        for(int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            amountOrdered = String.valueOf(order.getNumberOfProducts());
            if (i == 0) {
                productName = amountOrdered + " x " + order.getProductName();
            }
            if (i > 0){
                productName = productName + ", " + amountOrdered + " x " + order.getProductName();
            }
        }
        TextView showFoodTextView = listItemView.findViewById(R.id.show_food_text_view);
        showFoodTextView.setText(productName);

        Order order;
        int productPrice;
        int numberOfProductsOrdered;
        int totalPrice = 0;
        int overallPrice = 0;

        // Use a loop to add the price of all the food together
        for(int i = 0; i < orders.size(); i++) {
            order = orders.get(i);
            productPrice = order.getPriceOfProduct();
            numberOfProductsOrdered = order.getNumberOfProducts();
            totalPrice = productPrice * numberOfProductsOrdered;
            overallPrice = overallPrice + totalPrice;
        }

        TextView priceTextView = listItemView.findViewById(R.id.price_orders_page);
        String productPriceToString = "Total: $" + String.valueOf(overallPrice);
        priceTextView.setText(productPriceToString);



        return listItemView;
    }
}
