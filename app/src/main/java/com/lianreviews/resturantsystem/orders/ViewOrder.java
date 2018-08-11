package com.lianreviews.resturantsystem.orders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lianreviews.resturantsystem.MainActivity;
import com.lianreviews.resturantsystem.R;
import com.lianreviews.resturantsystem.ResourceManager;

import java.util.ArrayList;

import static com.lianreviews.resturantsystem.orders.Orders.ORDER_NUMBER;

public class ViewOrder extends AppCompatActivity {

    /**
     * This function creates a overview over an order the user have tapped.
     * You will find this view when tapping an order listed on the MainActivity class.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        //Get the intent that holds the order number of the order the user tapped.
        Intent intent = getIntent();
        final int orderNumber = intent.getIntExtra(ORDER_NUMBER, 0);
        String orderNumberToString = "Order " + String.valueOf(orderNumber);

        //Create a TextView and shows the order number.
        TextView orderNumberText = findViewById(R.id.view_order_order_number);
        orderNumberText.setText(orderNumberToString);

        //Load all orders to check which order number that is the same one as the user clicked.
        //When the order is found. Assign that order to the variable clickedOrder
        ArrayList<Order> clickedOrder = null;
        ArrayList<Orders> orders = ResourceManager.loadOrders(this);
        for (int i = 0; i < orders.size(); i++){
            Orders ordersl = orders.get(i);
            if (ordersl.getOrderNumber() == orderNumber){
                clickedOrder = ordersl.getOrder();
            }
        }

        //If the order is found. Assign this order to the adapter, and show it to the user.
        if (clickedOrder != null) {
            ViewOrderAdapter viewOrderAdapter = new ViewOrderAdapter(this, clickedOrder);
            ListView listView = findViewById(R.id.view_order_list_view);
            listView.setAdapter(viewOrderAdapter);
        }

        /*
         * This is a button that archives the order when clicked.
         * Order will be deleted from the front page, and added to the archived orders page.
         */
        Button archiveOrderButton = findViewById(R.id.archive_order_button);
        final ArrayList<Order> finalClickedOrder = clickedOrder;
        archiveOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Orders> archivedOrders = new ArrayList<>();

                if (ResourceManager.loadArchivedOrders(v.getContext()) != null) {
                    archivedOrders = ResourceManager.loadArchivedOrders(v.getContext());
                }

                archivedOrders.add(new Orders(finalClickedOrder, orderNumber));

                if (ResourceManager.archiveOrders(ViewOrder.this, archivedOrders)) {
                    ResourceManager.removeOrder(ViewOrder.this, orderNumber);
                    Toast.makeText(ViewOrder.this, "Order was archived",
                                Toast.LENGTH_SHORT).show();
                        Intent createOrders = new Intent(v.getContext(), MainActivity.class);
                        v.getContext().startActivity(createOrders);
                }
            }
        });
    }
}
