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

    public Category (JSONObject jsonObject) throws JSONException {
        category = jsonObject.getString("");//FireBase?
    }

    public static Category[] fromJsonArray(JSONArray categoryJsonArray)
            throws JSONException {
        HashSet<Category> businessCategories = new HashSet<>();

        //for loop through each bussiness
            //for loop through each business' categories
                //categories.add(new Category)

        Category categories[] = new Category[businessCategories.size()];
        //convert hashset to list (iterate hashset, or find helper method)
        Arrays.sort(categories);

        Log.d(TAG, categories.toString());

        return categories;
    }
}
