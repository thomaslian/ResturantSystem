package com.lianreviews.resturantsystem;

import android.content.Context;

import com.lianreviews.resturantsystem.category.FoodCategory;
import com.lianreviews.resturantsystem.food.AddFoodName;
import com.lianreviews.resturantsystem.food.FoodName;
import com.lianreviews.resturantsystem.orders.Order;
import com.lianreviews.resturantsystem.orders.Orders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ResourceManager {

    public static ArrayList<FoodName> loadFoodNames(Context context) {
        File file = new File(context.getFilesDir(), FoodName.FOOD_NAME_SAVE);

        ArrayList<FoodName> foodNames = new ArrayList<>();

        // Check if file exist and if it exist load any other saved orders list
        if (file.exists()) {
            try {
                //Read Order array from file.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                    foodNames = (ArrayList<FoodName>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            foodNames = null;
        }
        return foodNames;
    }

    public static ArrayList<Order> loadTempOrder(Context context) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "tempOrder.ser");

        ArrayList<Order> orders = new ArrayList<>();

        // Check if file exist and if it exist load any other saved orders list
        if (file.exists()) {
            try {
                //Read Order array from file.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                orders = (ArrayList<Order>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            orders = null;
        }
        return orders;
    }

    public static ArrayList<Orders> loadOrders(Context context) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), Order.orderFileName);

        ArrayList<Orders> orders = new ArrayList<>();

        // Check if file exist and if it exist load any other saved orders list
        if (file.exists()) {
            try {
                //Read Order array from file.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                orders = (ArrayList<Orders>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            orders = null;
        }
        return orders;
    }

    public static void saveCategory(Context context, String categoryName) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "currentCategories.ser");

        ArrayList<FoodCategory> categories = new ArrayList<>();

        // Check if file exist and if it exist load any other saved orders list
        if (file.exists()) {
            try {
                //Read FoodCategory array from file.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                categories = (ArrayList<FoodCategory>) ois.readObject();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Add the new category to the orders list
        categories.add(new FoodCategory(categoryName));

        // Save the categories list with the new category
        try {
            //Write FoodCategory array to file.
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(categories);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFood(Context context, FoodName foodName) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "currentFoodNames.ser");
        ArrayList<FoodName> foodNames = new ArrayList<>();

        if (file.exists()) {
            foodNames = ResourceManager.loadFoodNames(context);
        }

        if (foodNames != null) {
            for (int i = 0; i < foodNames.size(); i++) {
                FoodName foodNameFromList = foodNames.get(i);
                if (foodName.getName().equals(foodNameFromList.getName())) {
                    foodNames.remove(i);
                }
            }
        }

        // Add the new category to the orders list
        foodNames.add(foodName);

        // Save the categories list with the new category
        try {
            //Write FoodCategory array to file.
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(foodNames);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
