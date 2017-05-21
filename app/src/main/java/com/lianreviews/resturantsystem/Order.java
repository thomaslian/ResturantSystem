package com.lianreviews.resturantsystem;

/**
 * Created by Thoma on 21.05.2017.
 */

public class Order {

    private String mProductname;
    private Boolean mOrdered = false;
    private int mNumberOfProducts;

    public Order(String productName, Boolean ordered, int numberOfProducts) {
        mProductname = productName;
        mOrdered = ordered;
        mNumberOfProducts = numberOfProducts;
    }
}
