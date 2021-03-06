package com.lianreviews.resturantsystem.category;
import java.io.Serializable;

public class FoodCategory implements Serializable {

    public static final String categoryFile = "currentCategories.ser";

    public final static String CATEGORY_NAME = "categoryName";

    private String mCategoryName;

    public FoodCategory(String categoryName) {
        mCategoryName = categoryName;
    }

    public String getCategoryName() {
        return mCategoryName;
    }
}
