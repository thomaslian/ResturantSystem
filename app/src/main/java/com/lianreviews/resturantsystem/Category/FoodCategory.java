package com.lianreviews.resturantsystem.Category;

import java.io.Serializable;

public class FoodCategory implements Serializable {

    private String mCategoryName;

    public FoodCategory(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getCategoryName() {
        return mCategoryName;
    }
}
