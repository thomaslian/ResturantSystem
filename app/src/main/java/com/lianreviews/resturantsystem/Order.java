package com.lianreviews.resturantsystem;

/**
 * Created by Thoma on 21.05.2017.
 */

public class Order {

    private String mProductName;
    private Boolean mOrdered = false;
    private int mNumberOfProducts;
    private int mPriceOfProduct;


    public Order(String productName, Boolean ordered, int numberOfProducts, int priceOfProduct) {
        mProductName = productName;
        mOrdered = ordered;
        mNumberOfProducts = numberOfProducts;
        mPriceOfProduct = priceOfProduct;
    }

    public int getmNumberOfProducts() {
        return mNumberOfProducts;
    }

    public int getmPriceOfProduct() {
        return mPriceOfProduct;
    }

    public Boolean getmOrdered() {

        return mOrdered;
    }

    public String getmProductname() {
        return mProductName;
    }
}
