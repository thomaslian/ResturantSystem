package com.lianreviews.resturantsystem.food;


import java.io.Serializable;

public class FoodName implements Serializable {

    public final static String FOOD_NAME = "foodName";
    public final static String FOOD_NAME_SAVE = "currentFoodNames.ser";

    private String mCategory;
    private String mName;
    private int mPrice;

    public FoodName(String category, String name, int price) {
        mCategory = category;
        mName = name;
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public String getCategory() {
        return mCategory;
    }

    public int getPrice(){
        return mPrice;
    }

}
