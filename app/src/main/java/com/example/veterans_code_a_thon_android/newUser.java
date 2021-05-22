package com.example.veterans_code_a_thon_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class newUser extends AppCompatActivity {
    private EditText firstname, lastname,email,password;
    private Button createaccount;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        firstname=findViewById(R.id.name);
        lastname=findViewById(R.id.last);
        email=findViewById(R.id.email);
        createaccount=findViewById(R.id.createAccount);
        password=findViewById(R.id.password);
        firebaseAuth=FirebaseAuth.getInstance();

    }
//when the create account button is clicked, user infomation will be converted to a string and white space will be deleted
    public void onClick(View view) {
        String firstn=firstname.getText().toString().trim();
        String lastn=lastname.getText().toString().trim();
        String mail=email.getText().toString().trim();
        String passw=password.getText().toString().trim();
        //if any fields are empty a warning message will appear
        if (firstn.isEmpty()||lastn.isEmpty()||mail.isEmpty()||passw.isEmpty()){
            Toast.makeText(getApplicationContext(),"All Fields Required",Toast.LENGTH_SHORT).show();
        }
        else if(passw.length()<7){
            Toast.makeText(getApplicationContext(),"Password must contain 7 or more characters",Toast.LENGTH_SHORT).show();
        }
        else{

            firebaseAuth.createUserWithEmailAndPassword(mail,passw ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                //@Override
                //public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {

              //  }

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Registration was successful", Toast.LENGTH_SHORT).show();
                        sendEmailVerification();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Failed to Register", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Verification Email is sent. Verify your email then Log in again",Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(newUser.this,MainActivity.class ));
                }
            });
        }
    }
    }
