package com.lianreviews.resturantsystem.food;


import java.io.Serializable;

public class FoodName implements Serializable {

    public final static String FOOD_NAME = "foodName";
    public final static String FOOD_NAME_SAVE = "currentFoodNames.ser";

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
