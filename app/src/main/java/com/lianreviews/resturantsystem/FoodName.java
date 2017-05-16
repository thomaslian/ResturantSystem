package com.lianreviews.resturantsystem;

/**
 * Created by Thoma on 16.05.2017.
 */

public class FoodName {

    private String mCategory;
    private String mName;
    private int mNumber;

    public FoodName (String category, String name, int number){
        mCategory = category;
        mName = name;
        mNumber = number;
    }

    public String getName(){
        return mName;
    }

    public String getCategory(){
        return mCategory;
    }
}
