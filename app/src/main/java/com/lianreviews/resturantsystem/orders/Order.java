package com.lianreviews.resturantsystem.orders;

import java.io.Serializable;


public class Order implements Serializable {

    public static final String tempOrderFileName = "tempOrder.ser";
    public static final String orderFileName = "orders.ser";

    private static final long serialVersionUID = 1L;

    private String mProductName;
    private Boolean mOrdered = false;
    private int mNumberOfProducts;
    private int mPriceOfProduct;
    private int mOrderNumber;


    public Order(String productName, Boolean ordered, int numberOfProducts, int priceOfProduct,
                 int orderNumber) {
        mProductName = productName;
        mOrdered = ordered;
        mNumberOfProducts = numberOfProducts;
        mPriceOfProduct = priceOfProduct;
        mOrderNumber = orderNumber;
    }

    public Order(String productName, int numberOfProducts, int priceOfProduct, int orderNumber){
        mProductName = productName;
        mNumberOfProducts = numberOfProducts;
        mPriceOfProduct = priceOfProduct;
        mOrderNumber = orderNumber;
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

    public int getOrderNumber(){
        return mOrderNumber;
    }

}
