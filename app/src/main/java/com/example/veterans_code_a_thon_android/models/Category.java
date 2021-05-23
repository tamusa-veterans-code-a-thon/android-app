package com.example.veterans_code_a_thon_android.models;

import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a category on the first page
 * of the app
 */
public class Category {
    private static final String TAG = "Category";
    String category;

    public Category (String category) {
        this.category = category;
    }

    //Depending on how we loop, this either returns individual categories or a list
    public static Category[] extractCategory (List<Business> businesses) {
        HashSet<String> businessCategories = new HashSet<>();


        for (int i = 0; i < businesses.size(); i++) {
            String cat = businesses.get(i).getCategories();
            //Log.d(TAG, "String: " + cat);
            cat = cat.replace("[", "");
            cat = cat.replace("]", "");
            cat = cat.replace("'", "");
            String[] arr = cat.split(",");
            for (int j = 0; j < arr.length; j++) {
                if (j > 0) {
                    arr[j] = arr[j].substring(1, arr[j].length());
                }
                System.out.println(arr[j]);
                String temp = arr[j];
                businessCategories.add(temp);
            }
            //Log.d(TAG, "String: " + cat);
        }

        Category[] categories = new Category[businessCategories.size()];

        String[] arr = new String[businessCategories.size()];
        businessCategories.toArray(arr);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            categories[i] = new Category(arr[i]);
        }

//        String[] cat = new String[businessCategories.size()];
//        Category[] categories = new Category[businessCategories.size()];
//        businessCategories.toArray(cat);
//        for (int i = 0; i < cat.length; i++) {
//            categories[i] = new Category();
//        }

        //for loop through each bussiness
            //for loop through each business' categories
                //businessCategories.add(new Category)

//        Category categories[] = new Category[businessCategories.size()];
//        //convert hashset to list (iterate hashset, or find helper method)
//        businessCategories.toArray(categories);
//
//        //Arrays.sort(categories);
//
//        for (int i = 0; i < businessCategories.size(); i++) {
//            Log.d(TAG, categories[i].getCategory());
//        }
//
//        Log.d(TAG, categories.toString());
        return categories;
    }

    public String getCategory () {
        return category;
    }
}
