package com.example.veterans_code_a_thon_android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterans_code_a_thon_android.adapters.CategoryAdapter;
import com.example.veterans_code_a_thon_android.models.Business;
import com.example.veterans_code_a_thon_android.models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    public static final String TAG = "CategoriesActivity";

    //List<Business> businesses;
    //OR
    List<Category> categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        categories = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();//Try without path?

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //FOR CATEGORIES:
                //Take snapshots as Business object
                Business business = dataSnapshot.getValue(Business.class);
                //Add each object to categories[i]
                //Call Category method for sethash to extract
                //In this case, I am unsure how to loop this listener;
                // it is possible to either loop the whole
                // listener or loop through snapshots in the
                //listener; then can I continue
                //THIS ERROR REMAINS UNTIL I FIGURE OUT HOW TO LOOP; SORRY IN ADVANCE (cut this when fixed)
                categories.add(Category.extractCategory(business));
                // categories (set categories[] to this?)
                //notify adapter change

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        //Below here is reliant on how the FireBase works; without data,
        //I cannot test where it goes
        RecyclerView rvCategories = findViewById(R.id.rvCategories);

        //Convert ArrayList to array in line below
        Category[] arr = (Category[]) categories.toArray();
        Arrays.sort(arr);
        final CategoryAdapter categoryAdapter = new CategoryAdapter(this, arr);
        rvCategories.setAdapter(categoryAdapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(this));

        //NOTIFYDATASETCHANGE, FOO!


        //categories = new Category[];//Need to get size?
    }
}
/**
 * To summarize:
 * We take in a snapshot. Depending on how we need to loop (inside or outside listener)
 * we can send [each business OR individual businesses] to Category.extract, which will
 * extract the category from the business[es] and return them [individually OR as list].
 * No mater what, they need to be a list before they reach the recycler. Then they are converted
 * to an array and are sorted. The sorted array is used in the CategoryAdapter
 */