package com.example.veterans_code_a_thon_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPassword extends AppCompatActivity {
private EditText RegistredEmail;
private TextView backtologin;
    FirebaseAuth auth;
    String email = RegistredEmail.getText().toString().trim();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        RegistredEmail = findViewById(R.id.userEmail);
        Button recover = findViewById(R.id.recoverPassword);
        backtologin = findViewById(R.id.backtoMainPage);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String email = RegistredEmail.getText().toString().trim();
recover.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull  Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Email has been sent",Toast.LENGTH_LONG).show();
                    Intent intentt = new Intent(ForgotPassword.this, MainActivity.class);
                    startActivity(intentt);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Account does not exist, go back to Log In and create an account!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
});
        // if user clicks backtologin button, they will be taken back to the main activity
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }




        }


