package com.lianreviews.resturantsystem;


public class FoodName {

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
