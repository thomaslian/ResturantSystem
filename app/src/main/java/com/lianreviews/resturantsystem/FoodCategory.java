package com.lianreviews.resturantsystem;

/**
 * Created by Thoma on 08.05.2017.
 */

public class FoodCategory {

    private String mCategoryName;

    public FoodCategory(String categoryName){
        mCategoryName = categoryName;
    }

    public String getCategoryName(){
        return mCategoryName;
    }
}
