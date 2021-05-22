package com.example.veterans_code_a_thon_android.models;

import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a category on the first page
 * of the app
 */
public class Category {
    String category;
    ImageView icon;

    public Category (JSONObject jsonObject) throws JSONException {
        category = jsonObject.getString("");//FireBase?
    }

    public static List<Category> fromJsonArray(JSONArray categoryJsonArray)
            throws JSONException {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < categoryJsonArray.length(); i++) {
            //categories.add(new Category(mov))
        }

        return categories;
    }
}
