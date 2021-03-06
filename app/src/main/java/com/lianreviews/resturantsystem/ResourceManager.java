package com.lianreviews.resturantsystem;

import android.content.Context;
import android.icu.util.ULocale;

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

    /*
    ###################################################
    LOAD FILES
    ###################################################
     */

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

    public static ArrayList<FoodCategory> loadFoodCategories(Context context){
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
        return categories;
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

    public static ArrayList<Orders> loadArchivedOrders(Context context) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "archivedOrders.ser");

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

    /*
    ###################################################
    SAVE FILES
    ###################################################
     */

    public static void saveCategory(Context context, String categoryName) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "currentCategories.ser");

        ArrayList<FoodCategory> categories = new ArrayList<>();

        // Check if file exist and if it exist load any other saved categories
        if (file.exists()) {
            categories = ResourceManager.loadFoodCategories(context);
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

        // Add the new foodname to the orders list
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

    public static Boolean saveOrders(Context context, ArrayList<Orders> orders) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "orders.ser");


        // Save the orders list with the new order
        try {
            //Write Orders array to file.
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orders);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Boolean archiveOrders(Context context, ArrayList<Orders> orders) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "archivedOrders.ser");


        // Save the orders list with the new order
        try {
            //Write Orders array to file.
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orders);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*
    ###################################################
    EDIT FILES
    ###################################################
     */

    /*
    This function will remove a foodName from an ArrayList that is
    saved as a file. The code finds the file, loads the ArrayList from the function
    loadFoodnames and removes the specific foodName from the ArrayList. Then it saves the
    ArrayList as the same file (file is overwritten).
     */
    public static boolean removeFood(Context context, FoodName foodName) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "currentFoodNames.ser");
        ArrayList<FoodName> foodNames = new ArrayList<>();

        if (file.exists()) {
            foodNames = ResourceManager.loadFoodNames(context);
        }

        if (foodNames != null) {
            for (int i = 0; i < foodNames.size(); i++){
                FoodName loadedFoodName = foodNames.get(i);
                if (loadedFoodName.getName().equals(foodName.getName()) &&
                        loadedFoodName.getCategory().equals(foodName.getCategory())){
                    foodNames.remove(i);
                }
            }
        }

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
        return true;
    }

    public static boolean removeCategory(Context context, String categoryName) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "currentCategories.ser");

        ArrayList<FoodCategory> categories = new ArrayList<>();

        // Check if file exist and if it exist load any saved categories
        if (file.exists()) {
            categories = ResourceManager.loadFoodCategories(context);
        }

        if (categories != null) {
            for (int i = 0; i < categories.size(); i++){
                FoodCategory loadedCategoryName = categories.get(i);
                if (loadedCategoryName.getCategoryName().equals(categoryName)){
                    categories.remove(i);
                }
            }
        }

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
        return true;
    }

    public static boolean removeOrder(Context context, int orderNumber) {
        //Create a new file because the FileOutputSteam/FileInputStream takes it as an input
        //Without this we would not get access to the file directory of the app
        File file = new File(context.getFilesDir(), "orders.ser");
        ArrayList<Orders> orders = new ArrayList<>();

        if (file.exists()) {
            orders = ResourceManager.loadOrders(context);
        }

        if (orders != null) {
            for (int i = 0; i < orders.size(); i++){
                Orders loadedOrders = orders.get(i);
                if (loadedOrders.getOrderNumber() == orderNumber){
                    orders.remove(i);
                }
            }
        }

        // Save the new ArrayList with orders
        try {
            //Write Orders array to file.
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orders);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
