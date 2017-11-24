package com.lianreviews.resturantsystem.orders;

import java.io.Serializable;
import java.util.ArrayList;


public class Orders implements Serializable {

    private ArrayList<Order> mOrder;
    private int mOrderNumber;

    Orders (ArrayList<Order> order, int orderNumber){
        mOrder = order;
        mOrderNumber = orderNumber;
    }

    public ArrayList<Order> getOrder(){
        return mOrder;
    }
    public int getOrderNumber (){
        return mOrderNumber;
    }
}
