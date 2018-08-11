package com.lianreviews.resturantsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class NavBarManager extends AppCompatActivity{

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.orders_nav_menu:
                // User choose to go to orders
                Intent orders = new Intent(NavBarManager.this,
                        MainActivity.class);
                NavBarManager.this.startActivity(orders);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
