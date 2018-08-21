package com.lianreviews.resturantsystem;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.lianreviews.resturantsystem.category.CreateCategory;
import com.lianreviews.resturantsystem.orders.OrderAdapter;
import com.lianreviews.resturantsystem.orders.OrdersAdapter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Orders");

        //Show the current orders on the main page
        if (ResourceManager.loadOrders(this) != null) {
            //Create an OrderAdapter, whose data source is a list of Order.
            //The adapter knows how to create list items for each item in the list.
            OrdersAdapter ordersAdapter = new OrdersAdapter(
                    this, ResourceManager.loadOrders(this));

            //Get the ListView and set the adapter for the listView
            ListView listView = findViewById(R.id.main_activity_list_view);
            if(ResourceManager.loadOrders(this) != null) {
                listView.setAdapter(ordersAdapter);
            }
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        if (menuItem.getTitle().toString().equals("Archive")){
                            Intent archived = new Intent(MainActivity.this,
                                    Archive.class);
                            MainActivity.this.startActivity(archived);
                        }
                        return true;
                    }
                });


        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Create a FloatingActionButton variable and assign it to our floating action button
        FloatingActionButton floatingActionButton = findViewById(R.id.main_page_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new intent to open the Create Category class
                Intent createOrderIntent =
                        new Intent(MainActivity.this, CreateCategory.class);

                //Start the new activity
                startActivity(createOrderIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
