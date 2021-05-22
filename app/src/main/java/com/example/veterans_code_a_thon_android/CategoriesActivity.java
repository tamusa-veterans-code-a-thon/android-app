package com.example.veterans_code_a_thon_android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterans_code_a_thon_android.adapters.CategoryAdapter;
import com.example.veterans_code_a_thon_android.models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CategoriesActivity extends AppCompatActivity {
    public static final String TAG = "CategoriesActivity";

    Category[] categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("PATH GOES HERE");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Post post = dataSnapshot.getValue(Post.class);
//                System.out.println(post);

                //String output = dataSnapshot.getValue();
                //WE need to store all JSON categories into "categories"
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        //Below here is reliant on how the FireBase works; without data,
        //I cannot test where it goes
        RecyclerView rvCategories = findViewById(R.id.rvCategories);

        final CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
        rvCategories.setAdapter(categoryAdapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(this));

        //NOTIFYDATASETCHANGE, FOO!


        //categories = new Category[];//Need to get size?
    }
}
