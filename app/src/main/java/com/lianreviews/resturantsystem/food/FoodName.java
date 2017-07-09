package com.lianreviews.resturantsystem.food;


import java.io.Serializable;

public class FoodName implements Serializable {

    private String mCategory;
    private String mName;

    public FoodName(String category, String name) {
        mCategory = category;
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public String getCategory() {
        return mCategory;
    }

}
