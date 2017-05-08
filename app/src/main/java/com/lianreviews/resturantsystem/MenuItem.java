package com.lianreviews.resturantsystem;

/**
 * Created by Thoma on 08.05.2017.
 */

public class MenuItem {

    private String mCategoryName;

    public MenuItem(String categoryName){
        mCategoryName = categoryName;
    }

    public String getCategoryName(){
        return mCategoryName;
    }
}
