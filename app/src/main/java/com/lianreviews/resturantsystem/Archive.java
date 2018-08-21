package com.lianreviews.resturantsystem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.lianreviews.resturantsystem.orders.OrdersAdapter;

public class Archive extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Archived orders");

        //TODO: Make the Archived button from dropdown menu in navigation clickable
        //Show the archived orders on the main page
        if (ResourceManager.loadArchivedOrders(this) != null) {
            //Create an OrderAdapter, whose data source is a list of Order.
            //The adapter knows how to create list items for each item in the list.
            OrdersAdapter ordersAdapter = new OrdersAdapter(
                    this, ResourceManager.loadArchivedOrders(this));

            //Get the ListView and set the adapter for the listView
            ListView listView = findViewById(R.id.main_activity_list_view);
            FloatingActionButton floatingActionButton =
                    findViewById(R.id.main_page_floating_button);
            if(ResourceManager.loadArchivedOrders(this) != null) {
                //Hides the "+" button on the bottom right
                floatingActionButton.setVisibility(View.GONE);
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
                        // close drawer when item is tap
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        if (menuItem.getTitle().toString().equals("Orders")){
                            Intent archived = new Intent(Archive.this,
                                    MainActivity.class);
                            Archive.this.startActivity(archived);
                        }
                        return true;
                    }
                });

        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
