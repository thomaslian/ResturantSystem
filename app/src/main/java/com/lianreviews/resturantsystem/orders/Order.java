package com.lianreviews.resturantsystem.orders;

import java.io.Serializable;


public class Order implements Serializable {

    public static final String tempOrderFileName = "tempOrder.ser";

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

}
