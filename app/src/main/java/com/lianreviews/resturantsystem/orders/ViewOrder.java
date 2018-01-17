package com.lianreviews.resturantsystem.orders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.lianreviews.resturantsystem.R;
import com.lianreviews.resturantsystem.ResourceManager;

import java.util.ArrayList;

import static com.lianreviews.resturantsystem.orders.Orders.ORDER_NUMBER;

public class ViewOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        Intent intent = getIntent();
        int orderNumber = intent.getIntExtra(ORDER_NUMBER, 0);
        String orderNumberToString = "Order " + String.valueOf(orderNumber);

        TextView orderNumberText = findViewById(R.id.view_order_order_number);
        orderNumberText.setText(orderNumberToString);

        ArrayList<Order> clickedOrder = null;

        ArrayList<Orders> orders = ResourceManager.loadOrders(this);
        for (int i = 0; i < orders.size(); i++){
            Orders ordersl = orders.get(i);
            if (ordersl.getOrderNumber() == orderNumber){
                clickedOrder = ordersl.getOrder();
            }
        }

        if (clickedOrder != null) {
            ViewOrderAdapter viewOrderAdapter = new ViewOrderAdapter(this, clickedOrder);
            ListView listView = findViewById(R.id.view_order_list_view);
            listView.setAdapter(viewOrderAdapter);
        }
    }
}
