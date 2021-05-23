package com.example.veterans_code_a_thon_android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterans_code_a_thon_android.adapters.BusinessAdapter;
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

/**
 * This is the business activity, w
 */
public class BusinessesActivity extends AppCompatActivity {
    public static final String TAG = "BusinessActivity";

    //List<Business> businesses;
    //OR
    Business[] businesses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businesses);

       // businesses = getIntent();//???

        RecyclerView rvBusinesses = findViewById(R.id.rvBusinesses);

        Arrays.sort(businesses);
        final BusinessAdapter businessAdapter = new BusinessAdapter(this, businesses);
        rvBusinesses.setAdapter(businessAdapter);
        rvBusinesses.setLayoutManager(new LinearLayoutManager(this));
    }
}
