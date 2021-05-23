package com.example.veterans_code_a_thon_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TempActivity extends AppCompatActivity {
    Button bToMap;
    Button bToList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        bToMap = findViewById(R.id.bToMap);
        bToList = findViewById(R.id.bToCategories);

        bToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TempActivity.this, Map.class);
                startActivity(intent);
            }
        });

        bToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TempActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

    }
}
