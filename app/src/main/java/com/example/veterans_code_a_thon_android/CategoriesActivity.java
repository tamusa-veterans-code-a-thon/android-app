package com.example.veterans_code_a_thon_android;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterans_code_a_thon_android.adapters.CategoryAdapter;
import com.example.veterans_code_a_thon_android.models.Business;
import com.example.veterans_code_a_thon_android.models.Category;
import com.example.veterans_code_a_thon_android.models.Test;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the activity for displaying the available
 * categories. This can be made prettier later on.
 */
public class CategoriesActivity extends AppCompatActivity {
    public static final String TAG = "CategoriesActivity";

    //List<Business> businesses;
    //OR
    //List<Category> categories;
    Category[] categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //categories = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //The ref below may require a path or ".child("NAME_OF_DB")"
        DatabaseReference dbRef = database.getReference();

        // Attach a listener to read the data at our posts reference
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //FOR CATEGORIES:
                //Take snapshots as Business object

                //Add each object to categories[i]
                //Call Category method for sethash to extract
                //In this case, I am unsure how to loop this listener;
                // it is possible to either loop the whole
                // listener or loop through snapshots in the
                //listener; then can I continue
                //THIS ERROR REMAINS UNTIL I FIGURE OUT HOW TO LOOP; SORRY IN ADVANCE (cut this when fixed)

                // categories (set categories[] to this?)
                //notify adapter change

                List<Business> businesses = new ArrayList<>();

                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()) {
//                    Test test = new Test();
//                    test.setEmail(ds.getValue(Test.class).getEmail());
//                    //dataSnapshot.child("stars-challenge-83dfe-default-rtdb").getValue(Test.class).getEmail()
//                    Log.d(TAG, test.getEmail());

                            Test test = new Test();
                            test.setCategories(ds.getValue(Test.class).getCategories());
//                    List<String> arr = test.getCategoryTemps();
//                    Log.d(TAG, arr.get(0));
                            Log.d(TAG, test.getCategories().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                    }
                });

                //categories.addAll(Category.extractCategory(businesses));
                categories = Category.extractCategory(businesses);

                RecyclerView rvCategories = findViewById(R.id.rvCategories);

                //BE WARNED! "[Class].this" MAY BE WRONG!!! Take this out
                // of listener???
                final CategoryAdapter categoryAdapter =
                        new CategoryAdapter(CategoriesActivity.this, categories);
                rvCategories.setAdapter(categoryAdapter);
                rvCategories.setLayoutManager(
                        new LinearLayoutManager(CategoriesActivity.this));

                //NOTIFYDATASETCHANGE, FOO!
                categoryAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
/**
 * To summarize:
 * We take in a snapshot. Depending on how we need to loop (inside or outside listener)
 * we can send [each business OR individual businesses] to Category.extract, which will
 * extract the category from the business[es] and return them [individually OR as list].
 * No mater what, they need to be a list before they reach the recycler. Then they are converted
 * to an array and are sorted. The sorted array is used in the CategoryAdapter.
 *
 * ADDITIONALLY!
 * Make sure that I don't have to disect it; in Flixter, I have to loop through each JSON object,
 *  convert each object to the model, then store it in the list i return; depending on the struture
 *  of the DataSnapshot, this may or may not be needed!
 */