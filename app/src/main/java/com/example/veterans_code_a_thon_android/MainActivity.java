package com.example.veterans_code_a_thon_android;
// hello, world

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
private EditText email, password;
private Button loginButton, newuser;
private TextView forgotPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.LoginEmail);
        password = findViewById(R.id.LoginPassword);
        loginButton = findViewById(R.id.LOGINButton);
        newuser = findViewById(R.id.newUser);
        forgotPasswordButton = findViewById(R.id.forgotPassword);
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, newUser.class));
            }
        });
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgotPassword.class));
            }
        });
    }
    //log the user in the email and password fields are not empty
    public void onClick(View view) {
        String mail=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if (mail.isEmpty()|| pass.isEmpty()){
            Toast.makeText(getApplicationContext(), "All fields required to log In", Toast.LENGTH_SHORT).show();
        }
        else{
            //log in the user!
        }
    }

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");

        //In Flixter, we get database/json update
        //in the mainactivity




}