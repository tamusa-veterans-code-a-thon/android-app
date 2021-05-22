package com.example.veterans_code_a_thon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {
private EditText RegistredEmail;
private TextView backtologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        RegistredEmail=findViewById(R.id.userEmail);
        backtologin=findViewById(R.id.backtoMainPage);
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPassword.this,MainActivity.class);
            }
        });
    }

    public void onClick(View view) {
       String mail=RegistredEmail.getText().toString().trim();
       if (mail.isEmpty()){
           Toast.makeText(getApplicationContext(),"Please enter your Email",Toast.LENGTH_SHORT).show();
       }
       else{
           //send recovery email
           Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
       }

    }
}