package com.lianreviews.resturantsystem;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public int getNumberOfProducts() {
        return mNumberOfProducts;
    }

    public int getPriceOfProduct() {
        return mPriceOfProduct;
    }

    public Boolean getOrdered() {

        return mOrdered;
    }

    public String getProductName() {
        return mProductName;
    }

    /**
     * This method saves the order to be viewed in the cart
     */
    public void save(File file, ArrayList<Order> orders) {
    }

    public ArrayList<Order> load(ArrayList<Order> orders){

        return null;
    }
}
